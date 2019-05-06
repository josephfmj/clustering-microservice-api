package co.edu.ucatolica.clustering.microservice.api.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.ucatolica.clustering.microservice.api.model.ClusterMethodConfig;

@Repository
public interface ClusterMethodConfigRepository extends MongoRepository<ClusterMethodConfig, String> {
	
	public Optional<ClusterMethodConfig> findByName(String name);
}
