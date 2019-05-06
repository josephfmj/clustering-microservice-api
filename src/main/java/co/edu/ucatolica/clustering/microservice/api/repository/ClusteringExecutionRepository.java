package co.edu.ucatolica.clustering.microservice.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.ucatolica.clustering.microservice.api.model.ClusteringExecData;

@Repository
public interface ClusteringExecutionRepository extends MongoRepository<ClusteringExecData, String>{

}
