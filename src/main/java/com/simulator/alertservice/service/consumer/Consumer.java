package com.simulator.alertservice.service.consumer;

import java.util.Arrays;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.alertservice.service.FrameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oil.station.domain.frame.Frame;

@Slf4j
@Component
@AllArgsConstructor
public class Consumer {

    private final ObjectMapper objectMapper;

    private final FrameService frameService;

    @KafkaListener(topics = "${topic.name}")
    public void consumeMessage(final String message) throws JsonProcessingException {
        log.info("Message consumed {}", message);

        final List<Frame> frames = Arrays.asList(this.objectMapper.readValue(message, Frame[].class));

        this.frameService.createAlert(frames);
    }

}