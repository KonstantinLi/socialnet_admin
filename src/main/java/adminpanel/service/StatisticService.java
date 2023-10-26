package adminpanel.service;

import adminpanel.api.response.RegionStatisticRs;
import adminpanel.repositories.PersonsRepository;
import adminpanel.repositories.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final PersonsRepository personsRepository;

    private final PostsRepository postsRepository;

    public String buildStatisticPage(Model model) {
        model.addAttribute("countriesWithUsers", getCountriesWithUsers());
        model.addAttribute("citiesWithUsers", getCitiesWithUsers());
        model.addAttribute("countUsers", getCountUser());
        model.addAttribute("countPosts", getCountPosts());
        model.addAttribute("statistics", "router-link-active");
        return "index";
    }

    private List<RegionStatisticRs> getCountriesWithUsers() {
        List<RegionStatisticRs> regionStatisticList = personsRepository.getCountryWithUsersCount();
        regionStatisticList.sort(Comparator.comparing(RegionStatisticRs::getCountUsers).reversed());
        return regionStatisticList;
    }

    private List<RegionStatisticRs> getCitiesWithUsers() {
        List<RegionStatisticRs> regionStatisticList = personsRepository.getCityWithUsersCount();
        regionStatisticList.sort(Comparator.comparing(RegionStatisticRs::getCountUsers).reversed());
        return regionStatisticList;
    }

    private Long getCountUser() {
        return personsRepository.count();
    }

    private Long getCountPosts() {
        return postsRepository.count();
    }
}
