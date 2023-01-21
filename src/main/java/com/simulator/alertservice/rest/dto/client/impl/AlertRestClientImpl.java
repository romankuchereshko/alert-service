package com.simulator.alertservice.rest.dto.client.impl;

import java.util.Collections;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.simulator.alertservice.rest.dto.client.AlertRestClient;
import com.simulator.alertservice.rest.dto.request.CreateAlertRequestDTO;
import com.simulator.alertservice.rest.dto.response.CreateAlertResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlertRestClientImpl implements AlertRestClient {

    private final RestTemplate alertRestClient;

    @Value("${rest.client.alert-rest-client.url}")
    private String url;

    @Value("${rest.client.alert-rest-client.auth-request-id}")
    private String authorizationRequestId;

    @Override
    public CreateAlertResponseDTO createAlert(final CreateAlertRequestDTO requestDTO) {

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", authorizationRequestId);

        final HttpEntity<CreateAlertRequestDTO> request = new HttpEntity<>(requestDTO, headers);

        final ResponseEntity<CreateAlertResponseDTO> responseEntity = this.alertRestClient
            .postForEntity(url, request, CreateAlertResponseDTO.class);

        final CreateAlertResponseDTO alert = responseEntity.getBody();

        if (Objects.isNull(alert)) {
            log.info("There was a problem during the alert creation");
            return null;
        }

        return alert;
    }
}
