package com.simulator.alertservice.rest.dto.client;

import com.simulator.alertservice.rest.dto.request.CreateAlertRequestDTO;
import com.simulator.alertservice.rest.dto.response.CreateAlertResponseDTO;

public interface AlertRestClient {

    CreateAlertResponseDTO createAlert(CreateAlertRequestDTO requestDTO);

}
