package ru.skillbox.adminpanel.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import ru.skillbox.adminpanel.dto.response.StatisticsResponseParseException;
import ru.skillbox.adminpanel.dto.response.UsersPerRegionRs;
import ru.skillbox.adminpanel.exception.MainAppConnectionFailedException;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class MainAppStatisticsManager {

    private final ObjectMapper objectMapper;

    @Value("${main-app.statistics-url}")
    private String mainAppStatisticsUrl;


    public List<UsersPerRegionRs> getUsersCountByCountryAll() {
        String url = mainAppStatisticsUrl + "/country/all";

        return getRegionStatisticsFromMainApp(url);
    }

    public List<UsersPerRegionRs> getUsersCountByCitiesAll() {
        String url = mainAppStatisticsUrl + "/city/all";

        return getRegionStatisticsFromMainApp(url);
    }

    private List<UsersPerRegionRs> getRegionStatisticsFromMainApp(String url) {
        HttpEntity<String> entity = new HttpEntity<>("parameters", new HttpHeaders());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response;
        List<UsersPerRegionRs> regionStatisticRsList;

        try {
            response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            regionStatisticRsList = objectMapper.readValue(response.getBody(), new TypeReference<>() {
            });
        } catch (ResourceAccessException e) {
            throw new MainAppConnectionFailedException("Ошибка подключения к основному приложению");
        } catch (JsonProcessingException e) {
            throw new StatisticsResponseParseException("Ошибка парсинга ответа от основного приложения");
        }

        if (regionStatisticRsList != null && !regionStatisticRsList.isEmpty()) {
            regionStatisticRsList.get(regionStatisticRsList.size() - 1).setRegion("Не указан");
        }

        return regionStatisticRsList;
    }

    public Long countAllUsers() {
        String url = mainAppStatisticsUrl + "/user";

        return getGeneralCountStatisticsFromMainApp(url);
    }

    public Long countAllPosts() {
        String url = mainAppStatisticsUrl + "/post";

        return getGeneralCountStatisticsFromMainApp(url);
    }

    private static long getGeneralCountStatisticsFromMainApp(String url) {
        HttpEntity<String> entity = new HttpEntity<>("parameters", new HttpHeaders());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response;

        try {
            response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            return Long.parseLong(Objects.requireNonNull(response.getBody()));
        } catch (ResourceAccessException e) {
            throw new MainAppConnectionFailedException("Ошибка подключения к основному приложению");
        }
    }
}
