package adminpanel.controller;

import adminpanel.api.request.FindPostRq;
import adminpanel.api.response.MeRs;
import adminpanel.errors.TimeException;
import adminpanel.service.GetMeService;
import adminpanel.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    private final GetMeService getMeService;

    @ModelAttribute("MeRs")
    public MeRs MeRs() {
        return getMeService.getMe();
    }

    @ModelAttribute("FindPostRq")
    public FindPostRq findPostRq() {
        return new FindPostRq();
    }

    @GetMapping("/posts")
    public String getPosts(Model model, FindPostRq findPostRq) throws TimeException {
        return postsService.buildPostsPage(model, findPostRq);
    }

    @PostMapping("/post/block_unblock/{id}")
    public String blockUnblockUser(@PathVariable Long id, Model model) throws TimeException {
        postsService.blockUnblockPost(id);
        return postsService.buildPostsPage(model, new FindPostRq());

    }
}
