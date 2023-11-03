package ru.skillbox.adminpanel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.skillbox.adminpanel.dto.MainAppStatisticsManager;
import ru.skillbox.adminpanel.dto.response.CurrentUserInfoRs;
import ru.skillbox.adminpanel.dto.response.UsersPerRegionRs;
import ru.skillbox.adminpanel.security.JwtTokenUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final JwtTokenUtils jwtTokenUtils;
    private final MainAppStatisticsManager mainAppStatisticsManager;

    public String buildStatisticPage(Model model) {
        model.addAttribute("countriesWithUsers", getUsersCountByCountryAll());
        model.addAttribute("citiesWithUsers", getUsersCountByCitiesAll());
        model.addAttribute("countUsers", countAllUsers());
        model.addAttribute("countPosts", countAllPosts());
        model.addAttribute("admin-console/statistics", "router-link-active");
        return "index";
    }

    public CurrentUserInfoRs getCurrentUser(String token) {
        Authentication authentication = jwtTokenUtils.getAuthentication(token);
        String name = (String) authentication.getPrincipal();
        name = name.split(",")[1];

        return new CurrentUserInfoRs(name);
    }

    private List<UsersPerRegionRs> getUsersCountByCountryAll() {
        return mainAppStatisticsManager.getUsersCountByCountryAll();
    }

    private List<UsersPerRegionRs> getUsersCountByCitiesAll() {
        return mainAppStatisticsManager.getUsersCountByCitiesAll();
    }

    private Long countAllUsers() {
        return mainAppStatisticsManager.countAllUsers();
    }

    private Long countAllPosts() {
        return mainAppStatisticsManager.countAllPosts();
    }
}
