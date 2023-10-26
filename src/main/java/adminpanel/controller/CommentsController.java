package adminpanel.controller;

import adminpanel.api.request.FindCommentRq;
import adminpanel.api.response.MeRs;
import adminpanel.errors.TimeException;
import adminpanel.service.CommentsService;
import adminpanel.service.GetMeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CommentsController {

    private final CommentsService commentsService;

    private final GetMeService getMeService;

    @ModelAttribute("MeRs")
    public MeRs MeRs() {
        return getMeService.getMe();
    }

    @ModelAttribute("FindCommentRq")
    public FindCommentRq findCommentRq() {
        return new FindCommentRq();
    }

    @GetMapping("/comments")
    public String getModerators(Model model, FindCommentRq findCommentRq) throws TimeException {
        return commentsService.buildCommentsPage(model, findCommentRq);
    }

    @PostMapping("/comment/block_unblock/{id}")
    public String blockUnblockUser(@PathVariable Long id, Model model) throws TimeException {
        commentsService.blockUnblockComment(id);
        return commentsService.buildCommentsPage(model, new FindCommentRq());

    }
}
