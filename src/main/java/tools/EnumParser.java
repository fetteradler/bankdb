package tools;

/**
 * Create different typs of enum
 * @author CM
 *
 */
public class EnumParser {

	/**
	 * Enum for 'titel' of a person.
	 * @author caro
	 *
	 */
	public enum titel {
		
		HERR, FRAU
	}
	
	/**
	 * Enum for 'status' of a person.
	 * @author caro
	 *
	 */
	public enum status {
		
		VERFUEGBAR, KRANK, BEURLAUBT;
	}

	/**
	 * Enum for 'kontoStatus' of a person.
	 * @author caro
	 *
	 */
	public enum kontoStatus {
		
		STANDARTKONTO, JUGENDKONTO, STUDENTENKONTO;
	}
}
