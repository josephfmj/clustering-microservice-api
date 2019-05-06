package co.edu.ucatolica.clustering.microservice.api.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ucatolica.clustering.microservice.api.constants.ClusteringAlgorithmsConstants;
import co.edu.ucatolica.clustering.microservice.api.model.ClusterMethodConfig;
import co.edu.ucatolica.clustering.microservice.api.model.ExecutionResponse;
import co.edu.ucatolica.clustering.microservice.api.service.delegate.ClusteringOperationsDelegate;

@RestController("/operations")
public class ClusteringOperationController {
	
	@Autowired
	ClusteringOperationsDelegate clusteringOperationsDelegate;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/configuration/method/all")
	public ResponseEntity<List<ClusterMethodConfig>> getCongifAllMethods() {
		
		final Optional<List<ClusterMethodConfig>> result = clusteringOperationsDelegate.getAllClusteringConfig();
		
		return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,result);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/configuration/method/{name}")
	public ResponseEntity<ClusterMethodConfig> getConfigbyMethodName(@PathVariable("name") String methodName) {
		
		final Optional<ClusterMethodConfig> result = clusteringOperationsDelegate
				.getClusteringConfigbyName(methodName);
		
		return buildResponseEntity(HttpStatus.BAD_REQUEST,result);

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/executions/result/{id}")
	public ResponseEntity<ExecutionResponse> getExecutionResult(@PathVariable("id") String executionId){
		
		final Optional<ExecutionResponse> result = clusteringOperationsDelegate
				.getClusteringExecutionResponse(executionId);
		
		return buildResponseEntity(HttpStatus.BAD_REQUEST,result);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/executions/check/{id}")
	public ResponseEntity<String> checkExecutionResult(@PathVariable("id") String executionId){
		
		final Optional<ExecutionResponse> result = clusteringOperationsDelegate
				.getClusteringExecutionResponse(executionId);
		
		return buildCheckResponseEntity(HttpStatus.BAD_REQUEST,result);
	}
	
	/**
	 * Construye el respectivo ResponseEntity, 
	 * dependiendo de que el valor (result) este presente o no
	 * construira una respuesta favorable o una respuesta con estado de error
	 * 
	 * @param badStatus el estado http en caso de error
	 * @param result el valor
	 * @return el ResponseEntity
	 */
	private <T> ResponseEntity<T> buildResponseEntity(HttpStatus badStatus, Optional<T> result) {
		
		return result.isPresent() ? new ResponseEntity<T>(result.get(), HttpStatus.OK) :
				new ResponseEntity<>(badStatus);
	}
	
	private ResponseEntity<String> buildCheckResponseEntity(HttpStatus badStatus,
			Optional<ExecutionResponse> result) {
		
		if(result.isPresent()) {
			
			String resultStatus="";
			resultStatus = !ObjectUtils.anyNotNull(result.get().getExecutionData().getResult()) ?
					ClusteringAlgorithmsConstants.RESULT_OK.name():
						ClusteringAlgorithmsConstants.NOT_RESULT.name();
			
			return new ResponseEntity<String>(resultStatus, HttpStatus.OK);
		}
	
		return new ResponseEntity<>(badStatus);
	}

}
