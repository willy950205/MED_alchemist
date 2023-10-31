package org.side.med.service.util;

import com.fasterxml.jackson.core.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.side.med.dto.DrugResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;

@Service
@PropertySource("classpath:/api/api_config.properties")
@RequiredArgsConstructor
@Slf4j
public class ApiServiceUtil {
    @Value("${api.key}")
    private String API_KEY;
    private final WebClient.Builder webClient;
    private final String API_URL = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService";


    public String getApiKey() {
        log.info("ApiServiceUtil.getApiKey");
        log.info("api key = "+API_KEY);
        return API_KEY;
    }

    public Mono<DrugResponseDto> serachDrug() {

//        Mono<DrugResponseDto> result = webClient.build()
//            .get()
//            .uri(API_URL)
        Mono<DrugResponseDto> res = webClient.build().get()
            .uri(uriBuilder -> uriBuilder
                .queryParam("serviceKey", API_KEY)
                .queryParam("type", "json")
                .build(API_URL))
            .retrieve()
            .bodyToMono(DrugResponseDto.class);



        log.info(res.toString());
        

        return res;
    }

}
