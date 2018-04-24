package enums;

public enum LoopUntilOperator {
	INCREASE,
	DECREASE;
	@Override
	public String toString() {
		String stringRep = "";
		switch(this) {
		case INCREASE:
			stringRep = " increase ";
			break;
		case DECREASE:
			stringRep = " decrease ";
			break;
		default:
			stringRep = this.toString();
		}
		return stringRep;
	}
}
