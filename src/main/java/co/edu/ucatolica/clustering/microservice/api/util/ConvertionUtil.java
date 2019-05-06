package co.edu.ucatolica.clustering.microservice.api.util;

import java.util.List;

public class ConvertionUtil {
	
	public static double[] stringListToDoubleArray(List<String> list){
		
		 double[] doubleValues = list
				 .stream()
				 .mapToDouble(Double::parseDouble)
				 .toArray();
		 
		 return doubleValues;
	}

}
