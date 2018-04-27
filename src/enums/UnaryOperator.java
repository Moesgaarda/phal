package enums;

public enum UnaryOperator{
	NOT,
	NEGATIVE;

	@Override
	public String toString() {
		String stringRep = "";
		switch (this) {
			case NOT:
				stringRep = "!";
			case NEGATIVE:
				stringRep = "-";
			default:
				stringRep = this.toString();
		}
		return stringRep;
	}
}
