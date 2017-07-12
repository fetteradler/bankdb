package tools;

public class EnumParser {

	public enum titel {
		
		HERR, FRAU;
	}
	
	public enum titelString {
		
		HERR("HERR"), FRAU("FRAU");
		private String titelName;
		private titelString(String titel) {
			this.titelName = titel;
		}
		
		@Override
	    public String toString(){
	        return titelName;
	    } 
	}
	
	public enum status {
		
		VERFUEGBAR, KRANK, BEURLAUBT;
	}
	
	public enum statusString {
		
		VERFUEGBAR("VERFUEGBAR"), KRANK("KRANK"), BEURLAUBT("BEURLAUBT");
		private String statusName;
		private statusString(String status) {
			this.statusName = status;
		}
		
		@Override
	    public String toString(){
	        return statusName;
	    } 
	}
	
	public enum kontoStatus {
		
		STANDARTKONTO, JUGENDKONTO, STUDENTENKONTO;
	}
	
	public enum kontoStatusString {
		
		STANDARTKONTO("STANDARTKONTO"), JUGENDKONTO("JUGENDKONTO"), STUDENTENKONTO("STUDENTENKONTO");
		private String statusName;
		private kontoStatusString(String status) {
			this.statusName = status;
		}
		
		@Override
	    public String toString(){
	        return statusName;
	    } 
	}

}
