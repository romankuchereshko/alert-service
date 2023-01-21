package com.simulator.alertservice.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

@Configuration
public class RestClientConfig {

    @Bean
    public RestTemplate alertRestClient(final RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder.build();
        final CloseableHttpClient httpClient = HttpClients.custom()
            .setSSLHostnameVerifier(new NoopHostnameVerifier())
            .build();
        final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }

}
