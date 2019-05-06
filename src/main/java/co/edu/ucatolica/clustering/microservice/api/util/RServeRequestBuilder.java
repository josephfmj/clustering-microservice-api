package co.edu.ucatolica.clustering.microservice.api.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPDouble;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.RList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import co.edu.ucatolica.clustering.microservice.api.model.ClusteringExecutionRequest;
import co.edu.ucatolica.clustering.microservice.api.model.RServeRequest;

@Component
public class RServeRequestBuilder {

	private static Logger LOGGER = LoggerFactory.getLogger(RServeRequestBuilder.class);
	
	private static final String ROW_NAMES="row_names";
	
	public RServeRequest buildRequest(ClusteringExecutionRequest data){
		
		RServeRequest rServeRequest = new RServeRequest();
		rServeRequest.setParams(data.getParams());
		rServeRequest.setData_frame(createDataFrame(data.getData_frame()));
		return rServeRequest;
	}
	
	
	private Optional<REXP> createDataFrame(Map<String, List<String>> data){
		
		String[] names;
		List<REXP> registers;
		RList rList;
		String[] rownames=null;
		
		if(data.get(ROW_NAMES) != null){
			
			rownames=data.get(ROW_NAMES).toArray(new String[0]);
			data.remove(ROW_NAMES);			
			
		}
		
		names=data.keySet().toArray(new String[0]);
		registers = createRegisters(data);
		rList = new RList(registers,names);
		
		try {
			
			return Optional.ofNullable(RServeUtils.createDataFrame(rList, rownames));
			
		} catch (REXPMismatchException exception) {
			
			LOGGER.error("Se ha presentado un error al crear el data frame");
			LOGGER.error(exception.getMessage());
			
			return Optional.empty();
		}
		
	}
	
	private List<REXP> createRegisters(Map<String, List<String>> data) {
		
		List<REXP> registers = new ArrayList<REXP>();
		
		data.forEach((k,v)-> registers.add(this.getLisData(v)));
		
		return registers;
		
	}
	
	private REXPDouble getLisData(List<String> values) {
		
		return RServeUtils
				.createRDouble(ConvertionUtil
						.stringListToDoubleArray(values));
	}
	
	
}

