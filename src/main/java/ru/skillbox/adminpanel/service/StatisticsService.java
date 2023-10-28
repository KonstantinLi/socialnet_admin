package ru.skillbox.adminpanel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.skillbox.adminpanel.dto.response.CurrentUserInfoRs;
import ru.skillbox.adminpanel.security.JwtTokenUtils;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final JwtTokenUtils jwtTokenUtils;

    public String buildStatisticPage(Model model) {
        model.addAttribute("MeRs.firstName", "shepardcmndrTEST"/*, currentUserInfoRs()*/);
        model.addAttribute("MeRs.lastName", "shepardcmndrTEST"/*, currentUserInfoRs()*/);
        model.addAttribute("countriesWithUsers"/*, getCountriesWithUsers()*/);
        model.addAttribute("citiesWithUsers"/*, getCitiesWithUsers()*/);
        model.addAttribute("countUsers"/*, getCountUser()*/);
        model.addAttribute("countPosts"/*, getCountPosts()*/);
        model.addAttribute("statistics", "router-link-active");
        return "index";
    }

    public CurrentUserInfoRs getCurrentUser(String token) {
        Authentication authentication = jwtTokenUtils.getAuthentication(token);
        String name = (String) authentication.getPrincipal();
        name = name.split(",")[1];

        return new CurrentUserInfoRs(name);
    }
}
