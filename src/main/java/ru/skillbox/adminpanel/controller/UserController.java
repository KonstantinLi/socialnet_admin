package ru.skillbox.adminpanel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.adminpanel.dto.request.FindPersonRq;
import ru.skillbox.adminpanel.dto.response.CurrentUserInfoRs;
import ru.skillbox.adminpanel.service.StatisticsService;
import ru.skillbox.adminpanel.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin-console")
public class UserController {

    private final StatisticsService statisticsService;
    private final UserService userService;

    @ModelAttribute("CurrentUserInfo")
    public CurrentUserInfoRs currentUserInfoRs(@CookieValue(name = "jwtToken") String token) {
        return statisticsService.getCurrentUser(token);
    }

    @ModelAttribute("FindPersonRq")
    public FindPersonRq findPersonRq() {
        return new FindPersonRq();
    }

    @GetMapping("/users")
    public String getUsers(Model model, FindPersonRq findPersonRq) {
        return userService.buildUsersPage(model, findPersonRq);
    }

    @PostMapping("/users/block_unblock/{id}")
    public String blockUnblockUser(@PathVariable Long id, Model model) {
        userService.blockUnblockUser(id);
        return userService.buildUsersPage(model, new FindPersonRq());
    }
}
