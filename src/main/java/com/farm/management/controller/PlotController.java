package com.farm.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farm.management.model.Farm;
import com.farm.management.model.Plot;
import com.farm.management.payload.PlotRequest;
import com.farm.management.security.CurrentUser;
import com.farm.management.security.UserPrincipal;
import com.farm.management.service.FarmService;
import com.farm.management.service.PlotService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class PlotController {
	
	@Autowired
    private PlotService plotService;
	
	@Autowired
	private FarmService farmService;
	
	// Build Get All Users REST API
	@PostMapping("/plot")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Plot> createPlot(@RequestBody PlotRequest plotRequest, @CurrentUser UserPrincipal currentUser){
        Farm savedFarm = farmService.getFarmById(plotRequest.getFarmId());
        Plot plot = new Plot();
        plot.setFarm(savedFarm);
        plot.setName(plotRequest.getName());
        plot.setDescription(plotRequest.getDescription());
        plot.setArea(plotRequest.getArea());
        plot.setType(plotRequest.getType());
        Plot savePlot = plotService.createPlot(plot);
        return new ResponseEntity<>(savePlot, HttpStatus.CREATED);
	}
    
    @GetMapping("farm-plots/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Plot>> findByFarmId(@PathVariable("id") Long id){
        List<Plot> plots = plotService.getPlotByFarmId(id);
        return new ResponseEntity<>(plots, HttpStatus.OK);
    }
    
    @GetMapping("owner-plots")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Plot>> getPlotByUserId(@CurrentUser UserPrincipal currentUser){
        List<Plot> plots = plotService.getPlotByUserId(currentUser.getId());
        return new ResponseEntity<>(plots, HttpStatus.OK);
    }
    
    @PutMapping("plot/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Plot> updatePlot(@PathVariable("id") Long plotId,
                                           @RequestBody Plot plot){
        plot.setId(plotId);
        Plot updatedPlot = plotService.updatePlot(plot);
        return new ResponseEntity<>(updatedPlot, HttpStatus.OK);
    }
    
    @DeleteMapping("plot/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> deletePlot(@PathVariable("id") Long plotId){
        plotService.deletePlot(plotId);
        return new ResponseEntity<>("Plot successfully deleted!", HttpStatus.OK);
    }

}
