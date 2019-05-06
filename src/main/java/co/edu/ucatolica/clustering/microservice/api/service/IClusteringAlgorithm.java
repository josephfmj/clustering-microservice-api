package co.edu.ucatolica.clustering.microservice.api.service;

import co.edu.ucatolica.clustering.microservice.api.model.RServeRequest;
import co.edu.ucatolica.clustering.microservice.api.model.RserveResponse;

public interface IClusteringAlgorithm {
	
	public RserveResponse execClusteringMethod(RServeRequest request);

}
