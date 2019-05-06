package co.edu.ucatolica.clustering.microservice.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import co.edu.ucatolica.clustering.microservice.api.constants.ClusteringAGNESParams;
import co.edu.ucatolica.clustering.microservice.api.constants.ClusteringAlgorithmsConstants;
import co.edu.ucatolica.clustering.microservice.api.constants.ClusteringDIANAParams;
import co.edu.ucatolica.clustering.microservice.api.constants.RServeCommandsConstants;
import co.edu.ucatolica.clustering.microservice.api.model.RServeRequest;
import co.edu.ucatolica.clustering.microservice.api.model.RserveResponse;
import co.edu.ucatolica.clustering.microservice.api.service.IClusteringAlgorithm;
import co.edu.ucatolica.clustering.microservice.api.service.IRserveService;

@Service("hierarchical")
public class HierarchicalAlgorith extends AbstractClusteringAlgorithm implements IClusteringAlgorithm {

	private String optionalSource;

	@Autowired
	public HierarchicalAlgorith(IRserveService rserveService,
			@Value("${clustering.rserve.source.agnes}") String rServeDefaultScriptSource,
			@Value("${clustering.rserve.source.diana}") String rServeScriptSource) {

		this.rserveService = rserveService;
		this.rServeScriptSource = rServeDefaultScriptSource;
		this.optionalSource = rServeScriptSource;

	}

	@Override
	public RserveResponse execClusteringMethod(RServeRequest request) {

		if (request.getParams().get(ClusteringAlgorithmsConstants.TYPE.name())
				.equals(ClusteringDIANAParams.DIANA.name())) {
			this.rServeScriptSource = optionalSource;
			this.rServeScriptMethod = ClusteringDIANAParams.DIANA.getValue();
			this.dataFrameName = ClusteringDIANAParams.DATA_FRAME_VARIABLE.getValue();
			this.rServeAsignVariables = RServeCommandsConstants
					.ASSING_VARIABLES_COMMAND
					.setVariables(request.getParams().get(ClusteringDIANAParams.CLUSTERS.getValue()),
							request.getParams().get(ClusteringDIANAParams.DISTANCE_MEASURE.getValue()))
					.getValue();
		}
		
		this.rServeScriptMethod = ClusteringAGNESParams.AGNES.getValue();
		this.dataFrameName = ClusteringAGNESParams.DATA_FRAME_VARIABLE.getValue();
		this.rServeAsignVariables = RServeCommandsConstants
				.ASSING_VARIABLES_COMMAND
				.setVariables(request.getParams().get(ClusteringAGNESParams.CLUSTERS.getValue()),
						request.getParams().get(ClusteringAGNESParams.CLUSTRING_METHODS.getValue()),
						request.getParams().get(ClusteringAGNESParams.DISTANCE_MEASURE.getValue()))
				.getValue();
		
		this.rServeDataFrame = request.getData_frame();
		this.rServeScriptSource = RServeCommandsConstants
				.SOURCE_FILE_COMMAND
				.setVariables(this.rServeScriptSource)
				.getValue();
		
		return callRServeService();
	}

}
