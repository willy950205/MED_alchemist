package org.side.med.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.side.med.dto.DrugResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import org.yaml.snakeyaml.util.UriEncoder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@PropertySource("classpath:/api/api_config.properties")
@Component
@Slf4j
public class ApiRestTemplateUtil {
    private final RestTemplate restTemplate;

    private ApiRestTemplateUtil() {
        this.restTemplate = new RestTemplate();
    }

    @Value("${api.key}")
    private String API_KEY;
    private final String API_URL = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService";


    public String getApiKey() {
        log.info("ApiServiceUtil.getApiKey");
        log.info("api key = "+API_KEY);
        return API_KEY;
    }

    public String searchDrug(){

        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();

        params.add("ServiceKey", API_KEY);
        params.add("pageNo", "1");
        params.add("numOfRows", "3");
        params.add("itemName", UriEncoder.encode("지르텍"));
        params.add("type", "json");

        URI uri = UriComponentsBuilder
                .fromUriString(API_URL)
                .path("/getDrbEasyDrugList")
                .queryParams(params)
                .build(true).toUri();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        return responseEntity.getBody();

    }


}
