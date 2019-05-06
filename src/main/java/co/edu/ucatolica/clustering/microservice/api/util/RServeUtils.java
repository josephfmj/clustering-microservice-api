package co.edu.ucatolica.clustering.microservice.api.util;

import java.util.List;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPDouble;
import org.rosuda.REngine.REXPString;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.REXPGenericVector;
import org.rosuda.REngine.REXPList;
import org.rosuda.REngine.REXPMismatchException;

public class RServeUtils {
	
	
	
	public static REXPDouble createRDouble(double[] values) {
		
		return new REXPDouble(values);
	}
	
	public static RList createRlist(List<REXP> registers, List<String> names){		
		
		return new RList(registers,names);
	}	
	
	public static REXP createDataFrame(RList l, String[] rownames) throws REXPMismatchException {
    	
		if(rownames==null) {
			
			return REXP.createDataFrame(l);
			
		}else {
			
			return  new REXPGenericVector(l,
					  new REXPList(
							new RList(
								   new REXP[] {
									   new REXPString("data.frame"),
									   new REXPString(l.keys()),
									   new REXPString(rownames)
								   },
								   new String[] {
									   "class",
									   "names",
									   "row.names"
								   })));
		}
		
    }

}
