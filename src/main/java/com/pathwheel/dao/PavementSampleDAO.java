package com.pathwheel.dao;

import com.pathwheel.mapping.GeographicCoordinate;
import com.pathwheel.model.PavementSample;

import java.util.List;

public interface PavementSampleDAO {
	void register(PavementSample data, String smartDevice);	
	List<PavementSample> fetchByArea(List<GeographicCoordinate> verticesPolygon, int travelModeId);
}
