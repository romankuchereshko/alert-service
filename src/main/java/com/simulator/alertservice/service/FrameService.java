package com.simulator.alertservice.service;

import java.util.List;

import oil.station.domain.frame.Frame;

public interface FrameService {

    void createAlert(List<Frame> frames);

}
