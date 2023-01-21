package com.simulator.alertservice.rest.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreateAlertResponseDTO implements Serializable {

    @JsonProperty("result")
    private String result;

    @JsonProperty("took")
    private Double took;

    @JsonProperty("requestId")
    private String requestId;

}
