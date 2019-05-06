package co.edu.ucatolica.clustering.microservice.api.util;

import co.edu.ucatolica.clustering.microservice.api.model.RServeRequest;
import co.edu.ucatolica.clustering.microservice.api.model.RserveResponse;

@FunctionalInterface
public interface RunClusteringAlgorithm {
	
	public RserveResponse runAlgorithm(RServeRequest request);

}
