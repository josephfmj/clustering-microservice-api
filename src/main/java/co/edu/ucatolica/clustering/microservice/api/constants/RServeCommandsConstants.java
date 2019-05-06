package co.edu.ucatolica.clustering.microservice.api.constants;


/**
 * Constantes relacionadas RServe
 * 
 * @author Joseph Rubio
 * @version	1.0
 * @since	1.0
 */
public enum RServeCommandsConstants {
	
	/**
	 * Commando para invocar el comando source en RServe
	 */
	SOURCE_FILE_COMMAND("source({})") {
		
		@Override
		public String getValue() {
			
			return this.replaceVariables(false);
		}
	},
	
	/**
	 * Commando para asigar variables en RServe
	 */
	ASSING_VARIABLES_COMMAND("try ( asignarVariables({}), silent = TRUE)") {
		
		@Override
		public String getValue() {
			
			return this.replaceVariables(true);
		}
	},
	
	/**
	 * Comando para ejecutar el algortitmo KMEANS
	 */
	EXEC_KMEANS_COMMAND("try (ejecutarKmeans(), silent = TRUE)") {
		@Override
		public String getValue() {
			return this.value;
		}
	},
	
	/**
	 * Comando para ejecutar el algortitmo CLARA
	 */
	EXEC_CLARA_COMMAND("try (ejecutarClara(), silent = TRUE)") {
		@Override
		public String getValue() {
			return this.value;
		}
	},
	
	/**
	 * Comando para ejecutar el algortitmo PAM
	 */
	EXEC_PAM_COMMAND("try (ejecutarPam(), silent = TRUE)") {
		@Override
		public String getValue() {
			return this.value;
		}
	},
	
	/**
	 * Comando para ejecutar el algortitmo AGNES
	 */
	EXEC_AGENES_COMMAND("try (ejecutarAgnes(), silent = TRUE)") {
		@Override
		public String getValue() {
			return this.value;
		}
	},
	
	/**
	 * Comando para ejecutar el algortitmo DIANA
	 */
	EXEC_DIANA_COMMAND("try (ejecutarDiana(), silent = TRUE)") {
		
		@Override
		public String getValue() {
			
			return this.value;
		}
	};

	/**
	 * Contructor privado del enum
	 * @param command el valor de la constante
	 */
	private RServeCommandsConstants(String value) {

		this.value = value;
	}

	/**
	 * El token para unificar las variables
	 */
	private static String COMMAND_JOIN_TOKEN = ",";
	
	/**
	 * El token para reemplazar por el valor de las variables
	 */
	private static String COMMAND_REPLACEMENT_TOKEN ="{}";
	
	/**
	 * Caracteres de reemplazo para invocar el comando source de R
	 */
	private static String SOURCE_REPLACE_TO_TOKEN = "\\\\";
	
	/**
	 * Caracteres originalers a reemplazar para usar el comando source de R
	 */
	private static String SOURCE_REPLACE_TOKEN = "\\";
	
	/**
	 * El valor de la constante
	 */
	protected String value;
	
	/**
	 * Variables para completar el comando de RServe
	 */
	protected String[] variables;
	
	/**
	 * Establece el valor de las variables a integrar dentro del comando de RServe,
	 * solo debe usarse si se trata de un comando que lo requiere,
	 * de lo contrario no hara nada.
	 * 
	 * @param variables las variables a integrar
	 * @return retorna el enum
	 */
	public RServeCommandsConstants setVariables(String... variables) {
		
		this.variables= variables;		
		return this;
	}

	/**
	 * Obtiene el valor de la constante
	 * @return el valor de la constante
	 */
	public abstract String getValue();
	
	/**
	 * Metodo para apartir de las variables obtner el comando completo de RServe,
	 * lanzarta una exepcion si el valor de las variables es nulo.
	 * 
	 * @param applyJoin bandera que indica si aplica la unificacion de variables
	 * @return el comando de RServe completo
	 */
	protected String replaceVariables(boolean applyJoin) {
		
		if(this.variables != null) {
			
			final String replacementVariables = applyJoin ? String
					.join(RServeCommandsConstants.COMMAND_JOIN_TOKEN, this.variables) :
						this.variables[0].replace(SOURCE_REPLACE_TOKEN, SOURCE_REPLACE_TO_TOKEN);
			
			return this.value
					.replace(RServeCommandsConstants.COMMAND_REPLACEMENT_TOKEN,replacementVariables);
		}else {
			
			throw new IllegalArgumentException("No se encontraron variables a unir"
					+ "Los argumentos no pueden ser null, "
					+ "asegurese de haber invocado primero el metodo setVariables");
		}
	}
	
}
