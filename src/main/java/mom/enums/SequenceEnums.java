package mom.enums;

public class SequenceEnums {
	
	private static char zeroLpadChar = '0';

	public static enum AUTOGEN_SEQ_NAME {
		mom("mom_seq", 10, zeroLpadChar);
		
		private String value;
		private Integer length;
		private Character lpadChar;

		private AUTOGEN_SEQ_NAME(String value, int length,char lpadChar) {
			this.value = value;
			this.length = length;
			this.lpadChar = lpadChar;
		}

		public String getValue() {
			return this.value;
		}

		public Integer getLength() {
			return this.length;
		}

		public Character getLpadChar() {
			return this.lpadChar;
		}
	}
	
}	
