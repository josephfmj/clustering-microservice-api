package co.edu.ucatolica.clustering.microservice.api.model;

import java.util.Map;
import java.util.Optional;

import org.rosuda.REngine.REXP;

public class RServeRequest {
	
	private Map<String, String> params;
	
	private Optional<REXP> data_frame;

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public Optional<REXP> getData_frame() {
		return data_frame;
	}

	public void setData_frame(Optional<REXP> data_frame) {
		this.data_frame = data_frame;
	}
	
	

}
