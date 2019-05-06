package co.edu.ucatolica.clustering.microservice.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

public class ExecutionResponse {
	
	private String methodName;
	
	private ClusteringExecData executionData;
	
	private ExecutionResponse() {
	}
	
	public String getMethodName() {
		return methodName;
	}

	public ClusteringExecData getExecutionData() {
		return executionData;
	}

	@JsonIgnoreType
	public static class Builder {
		
		private String methodName;
		
		private ClusteringExecData executionData;
		
		private Builder() {
		}
		
		public static Builder newInstance() {
			
			return new Builder();
			
		}
		
		public Builder withMethodName(String methodName) {
			
			this.methodName = methodName;
			
			return this;
		}
		
		public Builder withExecutionData(ClusteringExecData executionData) {
			
			this.executionData = executionData;
			
			return this;
		}
		
		public ExecutionResponse build() {
			
			ExecutionResponse executionResponse = new ExecutionResponse();			
			executionResponse.methodName = this.methodName;
			executionResponse.executionData = this.executionData;
			
			return executionResponse;
		}
		
	}

}
