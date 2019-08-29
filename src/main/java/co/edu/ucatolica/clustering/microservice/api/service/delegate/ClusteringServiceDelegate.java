/**
 * Universidad Catolica de Colombia - Copyright (c) 2018
 * https://www.ucatolica.edu.co/
 * Date: 09/11/2018
 */
package co.edu.ucatolica.clustering.microservice.api.service.delegate;

import java.util.Date;

import co.edu.ucatolica.clustering.microservice.api.model.*;
import co.edu.ucatolica.clustering.microservice.api.repository.ClusterMethodConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import co.edu.ucatolica.clustering.microservice.api.constants.ClusteringAlgorithmsConstants;
import co.edu.ucatolica.clustering.microservice.api.repository.ClusteringDataRepository;
import co.edu.ucatolica.clustering.microservice.api.repository.ClusteringExecutionRepository;
import co.edu.ucatolica.clustering.microservice.api.util.ClusteringAlgorithmStrategyProvider;
import co.edu.ucatolica.clustering.microservice.api.util.RServeRequestBuilder;

/**
 * Delegado que actua como fachada que contiene todos los servicios relacionados al Clustering
 * 
 * @author <a href="mailto:jarubio67@ucatolica.edu.co"> Joseph Alexander Rubio Tapias</a>
 * @version 1.0
 * 
 */
@Service
public class ClusteringServiceDelegate {
	
	/**
	 * Repositorio de ClusteringData
	 */
	private ClusteringDataRepository dataRepository;
	
	/**
	 * Repositorio de ClusteringExecData
	 */
	private ClusteringExecutionRepository excutionRepository;

	private ClusterMethodConfigRepository methodConfigRepository;
	
	/**
	 * Servicio para construir la peticion para Rserve
	 */
	private RServeRequestBuilder rserveRequestBuilder;
	
	/**
	 * La fábrica de algoritmos de clustering
	 */
	private ClusteringAlgorithmStrategyProvider algorithmStrategyProvider;
	
	/**
	 * Peticion de ejecucion de clustering
	 */
	private ClusteringExecutionRequest requestData;
	
	/**
	 * Datos de ejecucion 
	 */
	private ClusteringExecData execData;
	
	/**
	 * Constructor
	 * 
	 * @param dataRepository el repositorio de ClusteringData
	 * @param excutionRepository el repositorio de ClusteringExecData
	 * @param rserveRequestBuilder el servicio para construir la peticion para Rserve
	 * @param algorithmStrategyProvider la fábrica de algoritmos de clustering
	 */
	@Autowired
	public ClusteringServiceDelegate(ClusteringDataRepository dataRepository, ClusterMethodConfigRepository methodConfigRepository,
			ClusteringExecutionRepository excutionRepository, RServeRequestBuilder rserveRequestBuilder,
			ClusteringAlgorithmStrategyProvider algorithmStrategyProvider){
		
		this.dataRepository = dataRepository;
		this.excutionRepository = excutionRepository;
		this.methodConfigRepository = methodConfigRepository;
		this.rserveRequestBuilder = rserveRequestBuilder;
		this.algorithmStrategyProvider = algorithmStrategyProvider;
		
	}
	
	/**
	 * Ejecuta el algortimo de clustering seleccionado
	 * @param data los Datos de la ejecucion 
	 * @return el Id de la colleccion donde se guardan las ejecuciones
	 */
	public String prepareClusteringExec(ClusteringExecutionRequest request) {

		this.requestData=request;
		final String methodName = requestData.getParams().get(ClusteringAlgorithmsConstants.METHOD_NAME.name());
		execData = new ClusteringExecData();
		ClusterMethodConfig methodConfig = this.methodConfigRepository.findByName(methodName).get();
		ClusteringData data = new ClusteringData();
		data.setDate_data(new Date());
		data.setData_frame(request.getData_frame());
		ClusteringData savedData=dataRepository.save(data);
		execData.setDataId(savedData.getId().toString());
		execData.setMethodId(methodConfig.getId());
		execData.setExecParams(request.getParams());
		execData = excutionRepository.save(execData);
		
		return execData.getId().toString();
		
		
	}
	
	
	@Async
	public void asyncExecClusteringAlgorithm() {

		final String methodName = requestData.getParams().get(ClusteringAlgorithmsConstants.METHOD_NAME.name());
		requestData.getParams().remove(methodName);
		RserveResponse response = algorithmStrategyProvider.getProvider()
				.get(methodName)
				.runAlgorithm(rserveRequestBuilder.buildRequest(requestData));
		
		execData.setResponse(response);
		excutionRepository.save(execData);
	}
	
	
}
