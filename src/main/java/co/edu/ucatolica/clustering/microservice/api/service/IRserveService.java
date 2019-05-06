package co.edu.ucatolica.clustering.microservice.api.service;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RserveException;

public interface IRserveService {
	
	public IRserveService connectToRserve();
	
	public IRserveService attachedRScript(String source) throws RserveException;
	
	public IRserveService assignDataFrame(String dataFrameName, REXP dataFrame) throws RserveException;
	
	public IRserveService assignVariables(String assign) throws RserveException;
	
	public String executeRMethod(String method) throws RserveException, REXPMismatchException;

}
