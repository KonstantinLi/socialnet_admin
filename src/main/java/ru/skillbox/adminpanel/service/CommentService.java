package ru.skillbox.adminpanel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.skillbox.adminpanel.dto.request.FindCommentRq;
import ru.skillbox.adminpanel.dto.response.CommentRs;
import ru.skillbox.adminpanel.entity.PostComment;
import ru.skillbox.adminpanel.exception.SearchPeriodParsingException;
import ru.skillbox.adminpanel.mapper.CommentMapper;
import ru.skillbox.adminpanel.repository.PostCommentsRepository;
import ru.skillbox.adminpanel.util.CommentSearchExecutor;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentSearchExecutor commentSearchExecutor;
    private final CommentMapper commentMapper;
    private final PostCommentsRepository postCommentsRepository;

    public String buildCommentsPage(Model model, FindCommentRq findCommentRq) throws SearchPeriodParsingException {
        model.addAttribute("comments", "router-link-active");
        model.addAttribute("commentsList", findComments(findCommentRq));
        return "comments";
    }

    public void blockUnblockComment(Long id) {
        PostComment comment = postCommentsRepository.findById(id).orElseThrow();
        comment.setIsBlocked(!comment.getIsBlocked());
        postCommentsRepository.save(comment);
    }

    private List<CommentRs> findComments(FindCommentRq findCommentRq) throws SearchPeriodParsingException {
        return commentListToCommentRsList(commentSearchExecutor.findComments(findCommentRq)).stream()
                .sorted(Comparator.comparing(CommentRs::getTime).reversed())
                .toList();
    }

    private List<CommentRs> commentListToCommentRsList(List<PostComment> comments) {
        return comments.stream().map(commentMapper::toCommentRs).toList();
    }
}
