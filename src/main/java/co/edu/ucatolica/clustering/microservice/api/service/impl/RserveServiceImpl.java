package co.edu.ucatolica.clustering.microservice.api.service.impl;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import co.edu.ucatolica.clustering.microservice.api.service.IRserveService;

@Service
@Scope("prototype")
public class RserveServiceImpl implements IRserveService {
	
	/**
	 * Logger de la clase
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(RserveServiceImpl.class);
	
	/**
	 * Conexiòn con RServe
	 */
	private RConnection rServerconnection;
	
	/**
	 * Puerto RServe
	 */
	private int rServePort;
	
	/**
	 * Host Rserve
	 */
	private String rServeHost;
	
	public RserveServiceImpl(@Value("${clustering.rserve.port}") int rServePort,
			@Value("${clustering.rserve.host}") String rServeHost) {
		
		this.rServePort = rServePort;
		this.rServeHost = rServeHost;
	}
	
	@Override
	public IRserveService connectToRserve() {
		
		try {
			rServerconnection = new RConnection(rServeHost, rServePort);
			LOGGER.info("la conexion a rserve fue creada en el host {} y el puerto {}",
					rServeHost, rServePort);
			return this;
		} catch (RserveException exception) {
			LOGGER.error("se ha presentado un error al intentar "
					+ "crear la conexion con rserve: host {} puerto {}",
					rServeHost, rServePort);
			throw new IllegalArgumentException(exception.getMessage());
		}
				
	}
	
	@Override
	public IRserveService attachedRScript(String source) throws RserveException {
		
		rServerconnection.eval(source);
		return this;
	}

	@Override
	public IRserveService assignDataFrame(String dataFrameName, REXP dataFrame) throws RserveException {
		
		rServerconnection.assign(dataFrameName, dataFrame);
		return this;
		
	}

	@Override
	public IRserveService assignVariables(String assign) throws RserveException {
		
		rServerconnection.eval(assign);
		return this;
		
	}	

	@Override
	public String executeRMethod(String method) throws RserveException, REXPMismatchException{
		
		final String response = rServerconnection
				.eval(method)
				.asString();
		
		rServerconnection.finalize();
		
		return response;
	}

}
