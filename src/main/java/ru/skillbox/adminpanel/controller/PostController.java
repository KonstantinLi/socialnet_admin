package ru.skillbox.adminpanel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.adminpanel.dto.request.FindPostRq;
import ru.skillbox.adminpanel.dto.response.CurrentUserInfoRs;
import ru.skillbox.adminpanel.service.PostService;
import ru.skillbox.adminpanel.service.StatisticsService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin-console")
public class PostController {

    private final PostService postService;
    private final StatisticsService statisticsService;


    @ModelAttribute("CurrentUserInfo")
    public CurrentUserInfoRs currentUserInfoRs(@CookieValue(name = "jwtToken") String token) {
        return statisticsService.getCurrentUser(token);
    }

    @ModelAttribute("FindPostRq")
    public FindPostRq findPostRq() {
        return new FindPostRq();
    }

    @GetMapping("/posts")
    public String getPosts(Model model, FindPostRq findPostRq) {
        return postService.buildPostsPage(model, findPostRq);
    }

    @PostMapping("/post/block_unblock/{id}")
    public String blockUnblockUser(@PathVariable Long id, Model model) {
        postService.blockUnblockPost(id);
        return postService.buildPostsPage(model, new FindPostRq());

    }
}
