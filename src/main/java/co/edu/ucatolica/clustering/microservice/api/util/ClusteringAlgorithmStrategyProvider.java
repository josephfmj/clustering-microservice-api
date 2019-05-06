package co.edu.ucatolica.clustering.microservice.api.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import co.edu.ucatolica.clustering.microservice.api.constants.ClusteringMethods;
import co.edu.ucatolica.clustering.microservice.api.service.IClusteringAlgorithm;

@Component
public class ClusteringAlgorithmStrategyProvider {

	private Map<String, RunClusteringAlgorithm> clusteringAlgorithmFactory;

	private IClusteringAlgorithm kmeans;

	private IClusteringAlgorithm pam;

	private IClusteringAlgorithm clara;

	private IClusteringAlgorithm hierarchical;

	@Autowired
	public ClusteringAlgorithmStrategyProvider(@Qualifier("kmeans") IClusteringAlgorithm kmeans,
			@Qualifier("pam") IClusteringAlgorithm pam, @Qualifier("clara") IClusteringAlgorithm clara,
			@Qualifier("hierarchical") IClusteringAlgorithm hierarchical) {
		
		this.kmeans = kmeans;
		this.pam = pam;
		this.clara=clara;
		this.hierarchical = hierarchical;

		build();

	}

	public Map<String, RunClusteringAlgorithm> getProvider() {
		return clusteringAlgorithmFactory;
	}

	public void setClusteringAlgorithmFactory(Map<String, RunClusteringAlgorithm> clusteringAlgorithmFactory) {
		this.clusteringAlgorithmFactory = clusteringAlgorithmFactory;
	}

	public IClusteringAlgorithm getKmeans() {
		return kmeans;
	}

	public void setKmeans(IClusteringAlgorithm kmeans) {
		this.kmeans = kmeans;
	}

	public IClusteringAlgorithm getPam() {
		return pam;
	}

	public void setPam(IClusteringAlgorithm pam) {
		this.pam = pam;
	}

	public IClusteringAlgorithm getClara() {
		return clara;
	}

	public void setClara(IClusteringAlgorithm clara) {
		this.clara = clara;
	}

	public IClusteringAlgorithm getHierarchical() {
		return hierarchical;
	}

	public void setHierarchical(IClusteringAlgorithm hierarchical) {
		this.hierarchical = hierarchical;
	}

	/**
	 * Genera la fabrica  de algortimos de clustering, 
	 * los cuales etan construidos bajo el patron Strategy
	 */
	private void build() {

		clusteringAlgorithmFactory = new HashMap<>();
		
		clusteringAlgorithmFactory.put(ClusteringMethods.KMEANS.toString(),
				(r) -> kmeans.execClusteringMethod(r));
		
		clusteringAlgorithmFactory.put(ClusteringMethods.PAM.toString(), 
				(r) -> pam.execClusteringMethod(r));
		
		clusteringAlgorithmFactory.put(ClusteringMethods.CLARA.toString(), 
				(r) -> clara.execClusteringMethod(r));
		
		clusteringAlgorithmFactory.put(ClusteringMethods.HIERARCHICAL.toString(),
				(r) -> hierarchical.execClusteringMethod(r));

	}

}
