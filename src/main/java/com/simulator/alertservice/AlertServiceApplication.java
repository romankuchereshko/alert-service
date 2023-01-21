package com.simulator.alertservice;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.simulator.alertservice.rest.dto.request.CreateAlertRequestDTO;
import com.simulator.alertservice.rest.dto.request.ResponderDTO;
import com.simulator.alertservice.rest.dto.request.VisibleToDTO;
import com.simulator.alertservice.rest.dto.response.CreateAlertResponseDTO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootApplication
public class AlertServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlertServiceApplication.class, args);


        // TODO implement with Spring
        RestTemplate restTemplate = new RestTemplate();
        CreateAlertRequestDTO requestDTO = CreateAlertRequestDTO.builder()
            .message("Received a critical frame(s)")
            .alias("critical frame 1")
            .description("frame data: ...")
            .responders(List.of(ResponderDTO.builder()
                .id("94950924-6be6-4ccb-ac88-1c3232ed1d3d")
                .type("team")
                .build()))
            .visibleTo(List.of(VisibleToDTO.builder()
                .id("94950924-6be6-4ccb-ac88-1c3232ed1d3d")
                .type("team")
                .build()))
            .actions(List.of("Check the engines!"))
            .tags(List.of("OverwriteQuietHours", "Critical"))
            .entity("Frame")
            .priority("P1")
            .build();

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        header.set("Authorization", "GenieKey 39699a7b-2731-443d-ac6e-bd4b6d97bba5");

        HttpEntity<CreateAlertRequestDTO> request = new HttpEntity<>(requestDTO, header);

        ResponseEntity<CreateAlertResponseDTO> createAlertResponseDTOResponseEntity = restTemplate.postForEntity(
            "https://api.opsgenie.com/v2/alerts", request, CreateAlertResponseDTO.class);

        CreateAlertResponseDTO body = createAlertResponseDTOResponseEntity.getBody();
    }

}
