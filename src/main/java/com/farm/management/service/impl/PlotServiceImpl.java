package com.farm.management.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.farm.management.model.Plot;
import com.farm.management.repository.PlotRepository;
import com.farm.management.service.PlotService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PlotServiceImpl implements PlotService {
	
	private PlotRepository plotRepository;

	@Override
    public Plot createPlot(Plot plot) {
        return plotRepository.save(plot);
    }
	
	@Override
    public void deletePlot(Long plotId) {
        plotRepository.deleteById(plotId);
    }

	@Override
	public Plot updatePlot(Plot plot) {
		Plot existingPlot = plotRepository.findById(plot.getId()).get();
		existingPlot.setName(plot.getName());
		existingPlot.setDescription(plot.getDescription());
		existingPlot.setArea(plot.getArea());
		existingPlot.setType(plot.getType());
        Plot updatedPlot = plotRepository.save(existingPlot);
        return updatedPlot;
	}

    public List<Plot> getPlotByFarmId(Long farmId){
        return plotRepository.getPlotByFarmId(farmId);
    }
    
    public List<Plot> getPlotByUserId(Long userId){
        return plotRepository.getPlotByUserId(userId);
    }
    
}
