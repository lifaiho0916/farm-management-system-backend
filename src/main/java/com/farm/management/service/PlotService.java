package com.farm.management.service;

import java.util.List;

import com.farm.management.model.Plot;

public interface PlotService {

	Plot createPlot(Plot plot);

    List<Plot> getPlotByFarmId(Long farmId);
    
    List<Plot> getPlotByUserId(Long userId);

    Plot updatePlot(Plot plot);

    void deletePlot(Long id);
}
