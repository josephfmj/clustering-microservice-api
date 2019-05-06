package co.edu.ucatolica.clustering.microservice.api.model;

import java.util.Date;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class ClusteringExecData {

	@Id
	private ObjectId id;
	
	private String dataId;
	
	private String methodId;
	
	private Map<String, String> execParams;
	
	private Date executionDate;
	
	private RserveResponse result;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	public RserveResponse getResponse() {
		return result;
	}
	public void setResponse(RserveResponse result) {
		this.result = result;
	}
	public String getMethodId() {
		return methodId;
	}
	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}
	public Map<String, String> getExecParams() {
		return execParams;
	}
	public void setExecParams(Map<String, String> execParams) {
		this.execParams = execParams;
	}
	public Date getExecutionDate() {
		return executionDate;
	}
	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}
	public RserveResponse getResult() {
		return result;
	}
	public void setResult(RserveResponse result) {
		this.result = result;
	}	
	
}
