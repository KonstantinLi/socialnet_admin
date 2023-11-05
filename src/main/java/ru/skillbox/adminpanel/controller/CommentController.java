package ru.skillbox.adminpanel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.skillbox.adminpanel.dto.request.FindCommentRq;
import ru.skillbox.adminpanel.dto.request.FindPersonRq;
import ru.skillbox.adminpanel.service.StatisticsService;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final StatisticsService statisticsService;

    @ModelAttribute("FindPersonRq")
    public FindPersonRq findPersonRq() {
        return new FindPersonRq();
    }

    @ModelAttribute("FindCommentRq")
    public FindCommentRq findCommentRq() {
        return new FindCommentRq();
    }

    @GetMapping("/comments")
    public String getModerators(Model model, FindCommentRq findCommentRq) throws TimeException {
        return commentService.buildCommentsPage(model, findCommentRq);
    }

    @PostMapping("/comment/block_unblock/{id}")
    public String blockUnblockUser(@PathVariable Long id, Model model) throws TimeException {
        commentService.blockUnblockComment(id);
        return commentService.buildCommentsPage(model, new FindCommentRq());

    }
}
