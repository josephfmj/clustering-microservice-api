package co.edu.ucatolica.clustering.microservice.api.service.delegate;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.ucatolica.clustering.microservice.api.model.ClusterMethodConfig;
import co.edu.ucatolica.clustering.microservice.api.model.ClusteringExecData;
import co.edu.ucatolica.clustering.microservice.api.model.ExecutionResponse;
import co.edu.ucatolica.clustering.microservice.api.model.ExecutionResponse.Builder;
import co.edu.ucatolica.clustering.microservice.api.repository.ClusterMethodConfigRepository;
import co.edu.ucatolica.clustering.microservice.api.repository.ClusteringExecutionRepository;

public class ClusteringOperationsDelegate {

	private ClusterMethodConfigRepository clusterMethodConfigRepository;
	
	private ClusteringExecutionRepository clusteringExecutionRepository;
	
	@Autowired
	public ClusteringOperationsDelegate(ClusterMethodConfigRepository clusterMethodConfigRepository,
			ClusteringExecutionRepository clusteringExecutionRepository){
		
		this.clusterMethodConfigRepository = clusterMethodConfigRepository;
		this.clusteringExecutionRepository = clusteringExecutionRepository;
	}

	public Optional<ClusterMethodConfig> getClusteringConfigbyName(String name) {
		
		return clusterMethodConfigRepository.findByName(name)
				.map(method -> method);
	}

	public Optional<List<ClusterMethodConfig>> getAllClusteringConfig() {
		
		final List<ClusterMethodConfig> result =clusterMethodConfigRepository.findAll();
		return result.isEmpty()? Optional.empty(): Optional.of(result);
	}
	
	public Optional<ExecutionResponse> getClusteringExecutionResponse(String executionId){
		
		return clusteringExecutionRepository
				.findById(executionId)
				.map(this::buildExecutionResponse);
	}
	
	private ExecutionResponse buildExecutionResponse(ClusteringExecData data) {
		
		final ExecutionResponse.Builder execResponse = Builder.newInstance();
		final String methodName = clusterMethodConfigRepository
				.findById(data.getMethodId())
				.get()
				.getName();
				
		return execResponse
				.withMethodName(methodName)
				.withExecutionData(data)
				.build();
	}

}
