package co.edu.ucatolica.clustering.microservice.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import co.edu.ucatolica.clustering.microservice.api.constants.ClusteringKMEANSParams;
import co.edu.ucatolica.clustering.microservice.api.constants.RServeCommandsConstants;
import co.edu.ucatolica.clustering.microservice.api.model.RServeRequest;
import co.edu.ucatolica.clustering.microservice.api.model.RserveResponse;
import co.edu.ucatolica.clustering.microservice.api.service.IClusteringAlgorithm;
import co.edu.ucatolica.clustering.microservice.api.service.IRserveService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("kmeans")
public class KMeansAlgorithm extends AbstractClusteringAlgorithm implements IClusteringAlgorithm{
	
	@Autowired
	public KMeansAlgorithm(IRserveService rserveService, 
			@Value("${clustering.rserve.source.kmeans}") String rServeScriptSource) {
		
		this.rserveService = rserveService;
		this.rServeScriptSource = rServeScriptSource;
	}

	@Override
	public RserveResponse execClusteringMethod(RServeRequest request) {
			
		this.rServeScriptMethod = RServeCommandsConstants.EXEC_KMEANS_COMMAND.getValue();
		this.dataFrameName = ClusteringKMEANSParams.DATA_FRAME_VARIABLE.getValue();
		this.rServeDataFrame = request.getData_frame();
		this.rServeScriptSource = RServeCommandsConstants
				.SOURCE_FILE_COMMAND
				.setVariables("'"+this.rServeScriptSource+"'")
				.getValue().replaceAll(Pattern.quote("/"), Matcher.quoteReplacement("\\\\\\\\"));
		this.rServeAsignVariables = RServeCommandsConstants
				.ASSING_VARIABLES_COMMAND
				.setVariables(request.getParams().get(ClusteringKMEANSParams.CENTERS.getValue()),
						request.getParams().get(ClusteringKMEANSParams.ITER_MAX.getValue()),
						request.getParams().get(ClusteringKMEANSParams.N_START.getValue()),
						"'"+request.getParams().get(ClusteringKMEANSParams.DISTANCE_MEASURE.getValue())+"'")
				.getValue();
		
		return callRServeService();
	}

}
