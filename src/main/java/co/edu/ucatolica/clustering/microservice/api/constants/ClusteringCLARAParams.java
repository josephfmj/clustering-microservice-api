package co.edu.ucatolica.clustering.microservice.api.constants;


/**
 * Parametros del metodo CLARA
 * 
 * @author Joseph Rubio
 * @version	1.0
 * @since	1.0
 */
public enum ClusteringCLARAParams {
	
	/**
	 * Nombre del metodo
	 */
	CLARA("CLARA"),
	
	/**
	 * Nombre de la vatiable data frame en el R script
	 */
	DATA_FRAME_VARIABLE("df"),
	
	/**
	 * Cantidad de clusters
	 */
	CLUSTERS("clusters"),
	
	/**
	 * Escalar o estandarizar el data frame
	 */
	STAND("stand"),
	
	/**
	 * Numero de muestras
	 */
	SAMPLES("samples"),
	
	/**
	 * Fase swap
	 */
	PAM_LIKE("pamLike"),
	
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
	private ClusteringCLARAParams(String value) {
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
