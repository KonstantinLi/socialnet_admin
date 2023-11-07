package ru.skillbox.adminpanel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.adminpanel.annotation.Info;
import ru.skillbox.adminpanel.dto.response.CurrentUserInfoRs;
import ru.skillbox.adminpanel.service.StatisticsService;

@Controller
@RequestMapping("admin-console")
@RequiredArgsConstructor
@Info
public class StatisticsController {

    private final StatisticsService statisticsService;

    @ModelAttribute("CurrentUserInfo")
    public CurrentUserInfoRs currentUserInfoRs(@CookieValue(name = "jwtToken") String token) {
        return statisticsService.getCurrentUser(token);
    }

    //TODO remove authorization header?
    @GetMapping("/statistics")
    public String getStatistic(@RequestHeader("Authorization") String token,
                               Model model) {
        return statisticsService.buildStatisticPage(model);
    }
}
