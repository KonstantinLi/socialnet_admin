package ru.skillbox.adminpanel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.adminpanel.dto.request.FindCommentRq;
import ru.skillbox.adminpanel.dto.request.FindPersonRq;
import ru.skillbox.adminpanel.dto.response.CurrentUserInfoRs;
import ru.skillbox.adminpanel.exception.TimeException;
import ru.skillbox.adminpanel.service.CommentService;
import ru.skillbox.adminpanel.service.StatisticsService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin-console")
public class CommentController {

    private final CommentService commentService;
    private final StatisticsService statisticsService;

    @ModelAttribute("CurrentUserInfo")
    public CurrentUserInfoRs currentUserInfoRs(@CookieValue(name = "jwtToken") String token) {
        return statisticsService.getCurrentUser(token);
    }

    @ModelAttribute("FindPersonRq")
    public FindPersonRq findPersonRq() {
        return new FindPersonRq();
    }

    @ModelAttribute("FindCommentRq")
    public FindCommentRq findCommentRq() {
        return new FindCommentRq();
    }

    @GetMapping("/comments")
    public String getComments(Model model, FindCommentRq findCommentRq) throws TimeException {
        return commentService.buildCommentsPage(model, findCommentRq);
    }

    @PostMapping("/comment/block_unblock/{id}")
    public String blockUnblockComment(@PathVariable Long id, Model model) throws TimeException {
        commentService.blockUnblockComment(id);
        return commentService.buildCommentsPage(model, new FindCommentRq());

    }
}
