package co.edu.ucatolica.clustering.microservice.api.constants;


/**
 * Parametros del metodo DIANA
 * 
 * @author Joseph Rubio
 * @version
 * @since
 */
public enum ClusteringDIANAParams {
	
	/**
	 * Nombre del metodo
	 */
	DIANA("DIANA"),
	
	/**
	 * Nombre de la vatiable data frame en el R script
	 */
	DATA_FRAME_VARIABLE("df"),
	
	/**
	 * Numero de clusters
	 */
	CLUSTERS("clusters"),
	
	/**
	 * Medida de distancia
	 */
	DISTANCE_MEASURE("distance_methods");
	
	/**
	 * Valor del parametro
	 */
	private String value;
	
	/**
	 * Constructor del Enum
	 * @param value el valor
	 */
	private ClusteringDIANAParams(String value) {
		this.value = value;
	}

	/**
	 * Retorna el valor
	 * @return el valor
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Establece el valor
	 * @param value el valor
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
