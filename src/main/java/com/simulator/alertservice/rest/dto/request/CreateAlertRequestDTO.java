package com.simulator.alertservice.rest.dto.request;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class CreateAlertRequestDTO implements Serializable {

    @JsonProperty("message")
    private String message;

    @JsonProperty("alias")
    private String alias;

    @JsonProperty("description")
    private String description;

    @JsonProperty("responders")
    private List<ResponderDTO> responders;

    @JsonProperty("visibleTo")
    private List<VisibleToDTO> visibleTo;

    @JsonProperty("actions")
    private List<String> actions;

    @JsonProperty("tags")
    private List<String> tags;

    @JsonProperty("entity")
    private String entity;

    @JsonProperty("priority")
    private String priority;

}
