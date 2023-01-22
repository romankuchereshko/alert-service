package com.simulator.alertservice.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.simulator.alertservice.rest.dto.client.AlertRestClient;
import com.simulator.alertservice.rest.dto.request.CreateAlertRequestDTO;
import com.simulator.alertservice.rest.dto.request.ResponderDTO;
import com.simulator.alertservice.rest.dto.request.VisibleToDTO;
import com.simulator.alertservice.rest.dto.response.CreateAlertResponseDTO;
import com.simulator.alertservice.service.FrameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.simulation.library.domain.Frame;

@Slf4j
@Component
@RequiredArgsConstructor
public class FrameServiceImpl implements FrameService {

    private static final String ALERT_MESSAGE = "Received a critical frame(s)";

    private static final String RESPONDERS_TYPE = "team";

    private static final List<String> ACTIONS = Collections.singletonList("Check the engines!");

    private static final List<String> TAGS = List.of("OverwriteQuietHours", "Critical");

    private static final String ENTITY_NAME = "Frame";

    private static final String PRIORITY = "P1";

    private final AlertRestClient alertRestClient;

    @Value("${rest.client.alert-rest-client.team-id}")
    private String teamId;

    @Override
    public void createAlert(final List<Frame> frames) {
        log.info("Got [{}] frames", frames.size());

        final List<Frame> criticalFrames = frames.stream().filter(Frame::getIsCritical).toList();
        log.info("Try to create alert for [{}] critical frames", criticalFrames.size());

        final List<CreateAlertResponseDTO> notCreatedAlerts = criticalFrames.stream()
            .map(frame -> this.alertRestClient.createAlert(this.buildCreateAlertRequest(frame)))
            .filter(Objects::isNull)
            .toList();

        if (CollectionUtils.isEmpty(notCreatedAlerts)) {
            log.info("Alerts for [{}] frames were created", criticalFrames.size());
        } else {
            log.info("Alerts for [{}] frames weren't created", notCreatedAlerts.size());
        }
    }

    private CreateAlertRequestDTO buildCreateAlertRequest(final Frame frame) {
        return CreateAlertRequestDTO.builder()
            .message(ALERT_MESSAGE)
            .alias(String.valueOf(Math.random()))
            .description(this.buildFrameDescription(frame))
            .responders(List.of(ResponderDTO.builder()
                .id(teamId)
                .type(RESPONDERS_TYPE)
                .build()))
            .visibleTo(List.of(VisibleToDTO.builder()
                .id(teamId)
                .type(RESPONDERS_TYPE)
                .build()))
            .actions(ACTIONS)
            .tags(TAGS)
            .entity(ENTITY_NAME)
            .priority(PRIORITY)
            .build();
    }

    private String buildFrameDescription(final Frame frame) {
        final String frameData = String.join(", ",
            String.format("well id: %s", frame.getWellId()),
            String.format("voltage: %s", frame.getVoltage()),
            String.format("electric current: %s", frame.getCurrent()),
            String.format("speed: %s", frame.getSpeed()),
            String.format("frequency: %s", frame.getFrequency()),
            String.format("temperature: %s", frame.getTemperature()),
            String.format("pressure: %s", frame.getPressure()),
            String.format("liquid flow rate: %s", frame.getLiquidFlowRate()),
            String.format("created at: %s", frame.getCreatedAt()));

        return String.format("Frame: %s", frameData);
    }

}
