package ru.skillbox.adminpanel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.skillbox.adminpanel.dto.request.FindCommentRq;
import ru.skillbox.adminpanel.exception.TimeException;
import ru.skillbox.adminpanel.mapper.CommentMapper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final SearchComments searchComments;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    public String buildCommentsPage(Model model, FindCommentRq findCommentRq) throws TimeException {
        model.addAttribute("comments", "router-link-active");
        model.addAttribute("commentsList", findComments(findCommentRq));
        return "comments";
    }

    public void blockUnblockComment(Long id) {
        CommentEntity comment = commentRepository.findById(id).orElseThrow();
        comment.setIsBlocked(!comment.getIsBlocked());
        commentRepository.save(comment);
    }

    private List<CommentRs> findComments(FindCommentRq findCommentRq) throws TimeException {
        return commentToCommentRs(searchComments.findComments(findCommentRq)).stream()
                .sorted(Comparator.comparing(CommentRs::getId))
                .collect(Collectors.toList());
    }

    private List<CommentRs> commentToCommentRs(List<CommentEntity> comments) {
        return comments.stream().map(commentMapper::toCommentRs).collect(Collectors.toList());
    }
}
