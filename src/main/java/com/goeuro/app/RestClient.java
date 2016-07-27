package com.goeuro.app;


import com.goeuro.app.model.Position;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;

/**
 * Created by admin on 7/27/2016.
 */
@Component
public class RestClient {

    org.slf4j.Logger log =  LoggerFactory.getLogger(getClass());

    private static final UriTemplate SUGGEST_CITY = new UriTemplate("position/suggest/en/{city}");
    private static final ParameterizedTypeReference<List<Position>> cityResponse = new ParameterizedTypeReference<List<Position>>() {  };


    private RestTemplate rest = new RestTemplate();


    @Value("${rest.endpoint:http://api.goeuro.com/api/v2/}")
    URI baseUrl;

    @PostConstruct
    public void logEndpoint() {
        log.info("GoEuro endpoint: {}", baseUrl);
    }


    public List<Position> getPosition(String query) {
        URI target = baseUrl.resolve(SUGGEST_CITY.expand(Collections.singletonMap("city", query)));
        log.info("GET {}", target);
        try {
            return rest.exchange(target, HttpMethod.GET, HttpEntity.EMPTY, cityResponse).getBody();
        } catch (RestClientException e) {
            log.error("position are empty {}: {}", target, e.getMessage());
            return emptyList();
        }
    }
}
