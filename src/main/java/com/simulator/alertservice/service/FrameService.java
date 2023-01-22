package com.simulator.alertservice.service;

import java.util.List;

import com.simulation.library.domain.Frame;

public interface FrameService {

    void createAlert(List<Frame> frames);

}
