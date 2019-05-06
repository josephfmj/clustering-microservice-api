package co.edu.ucatolica.clustering.microservice.api.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * @author Josseph
 *
 */
public class ClusteringData {
	
	@Id
	private ObjectId id;
	
	private Date date_data;
	
	private Map<String,List<String>> data_frame;

	
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Date getDate_data() {
		return date_data;
	}

	public void setDate_data(Date date_data) {
		this.date_data = date_data;
	}

	public Map<String, List<String>> getData_frame() {
		return data_frame;
	}

	public void setData_frame(Map<String, List<String>> data_frame) {
		this.data_frame = data_frame;
	}
	
	
	

}
