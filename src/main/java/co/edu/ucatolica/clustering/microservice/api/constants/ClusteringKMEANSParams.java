package co.edu.ucatolica.clustering.microservice.api.constants;


/**
 * Parametros del metodo KMEANS
 * 
 * @author Joseph Rubio
 * @version
 * @since
 */
public enum ClusteringKMEANSParams {
	
	/**
	 * Nombre del metodo
	 */
	KMEANS("KMEANS"),
	
	/**
	 * Nombre de la vatiable data frame en el R script
	 */
	DATA_FRAME_VARIABLE("dfv"),
	
	/**
	 * Numero de centros
	 */
	CENTERS("centers"),
	
	/**
	 * Iteraciones maximas
	 */
	ITER_MAX("iter_max"),
	
	/**
	 * Iteraciones iniciales
	 */
	N_START("nstart"),
	
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
	private ClusteringKMEANSParams(String value) {
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
