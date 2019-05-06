package co.edu.ucatolica.clustering.microservice.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ucatolica.clustering.microservice.api.model.ClusteringExecutionRequest;
import co.edu.ucatolica.clustering.microservice.api.service.delegate.ClusteringServiceDelegate;

@RestController("/run")
public class ClusteringController {

	@Autowired
	ClusteringServiceDelegate serviceDelegate;
	
	@RequestMapping(method = RequestMethod.POST, value = "/clustering")
	public ResponseEntity<String> execClustering(@RequestBody ClusteringExecutionRequest request) {
		
		String returnId=serviceDelegate.prepareClusteringExec(request);
		serviceDelegate.asyncExecClusteringAlgorithm();
		return new ResponseEntity<>(returnId,HttpStatus.OK);

	}

}
