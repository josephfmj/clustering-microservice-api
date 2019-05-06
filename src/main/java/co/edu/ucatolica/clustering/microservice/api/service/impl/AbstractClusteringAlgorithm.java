package co.edu.ucatolica.clustering.microservice.api.service.impl;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RserveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.ucatolica.clustering.microservice.api.model.RserveResponse;
import co.edu.ucatolica.clustering.microservice.api.model.RserveResponse.Builder;
import co.edu.ucatolica.clustering.microservice.api.service.IRserveService;

public abstract class AbstractClusteringAlgorithm {
	
	protected static Logger LOGGER = LoggerFactory.getLogger(AbstractClusteringAlgorithm.class);
	
	protected String rServeScriptSource;
	
	protected String rServeAsignVariables;
	
	protected String dataFrameName;
	
	protected Optional<REXP> rServeDataFrame;
	
	protected String rServeScriptMethod;
	
	protected IRserveService rserveService;
	
	public RserveResponse  callRServeService() {
		
		RserveResponse.Builder rServeResponse = Builder
				.newInstance();
		
		try {
			final String response = rserveService
					.connectToRserve()
					.attachedRScript(rServeScriptSource)
					.assignDataFrame(dataFrameName, rServeDataFrame.get())
					.assignVariables(rServeAsignVariables)
					.executeRMethod(rServeScriptMethod);
			
			rServeResponse.withDescription(response);
			
		} catch (RserveException | IllegalArgumentException | REXPMismatchException | NoSuchElementException
				  exception) {
			
			LOGGER.error(rServeScriptMethod+" Error");
			LOGGER.error(exception.getMessage());
			rServeResponse.withDescription(StringUtils.EMPTY);
		}
		
		return rServeResponse.build();
		
	}	

}
