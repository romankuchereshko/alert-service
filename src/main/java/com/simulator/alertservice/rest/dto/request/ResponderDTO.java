package com.simulator.alertservice.rest.dto.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@JsonRootName(value = "responders")
public class ResponderDTO implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

}
