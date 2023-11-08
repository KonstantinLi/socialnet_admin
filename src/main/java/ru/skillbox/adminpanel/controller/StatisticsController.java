package ru.skillbox.adminpanel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/statistics")
    public String getStatistic(Model model) {
        return statisticsService.buildStatisticPage(model);
    }
}
