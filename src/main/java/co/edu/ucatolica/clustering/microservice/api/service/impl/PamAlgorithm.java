package co.edu.ucatolica.clustering.microservice.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import co.edu.ucatolica.clustering.microservice.api.constants.ClusteringPAMParams;
import co.edu.ucatolica.clustering.microservice.api.constants.RServeCommandsConstants;
import co.edu.ucatolica.clustering.microservice.api.model.RServeRequest;
import co.edu.ucatolica.clustering.microservice.api.model.RserveResponse;
import co.edu.ucatolica.clustering.microservice.api.service.IClusteringAlgorithm;
import co.edu.ucatolica.clustering.microservice.api.service.IRserveService;

@Service("pam")
public class PamAlgorithm extends AbstractClusteringAlgorithm implements IClusteringAlgorithm {

	@Autowired
	public PamAlgorithm(IRserveService rserveService, 
			@Value("${clustering.rserve.source.pam}") String rServeScriptSource) {
		
		this.rserveService = rserveService;
		this.rServeScriptSource = rServeScriptSource;
	}

	@Override
	public RserveResponse execClusteringMethod(RServeRequest request) {
		
		this.rServeScriptMethod = ClusteringPAMParams.PAM.getValue();
		this.dataFrameName = ClusteringPAMParams.DATA_FRAME_VARIABLE.getValue();
		this.rServeDataFrame = request.getData_frame();
		this.rServeScriptSource = RServeCommandsConstants
				.SOURCE_FILE_COMMAND
				.setVariables(this.rServeScriptSource)
				.getValue();
		this.rServeAsignVariables = RServeCommandsConstants
				.ASSING_VARIABLES_COMMAND
				.setVariables(request.getParams().get(ClusteringPAMParams.CLUSTERS.getValue()),
						request.getParams().get(ClusteringPAMParams.SWAP.getValue()),
						request.getParams().get(ClusteringPAMParams.DISTANCE_MEASURE.getValue()))
				.getValue();
		
		return callRServeService();
	}

}
