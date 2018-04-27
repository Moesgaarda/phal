package CompilerError;

public class ParameterAmountError extends Error{
	int expectedAmount;
	int actualAmount;
	
	public ParameterAmountError(int columnNumber, int lineNumber, int expectedAmount, int actualAmount) {
        super(columnNumber, lineNumber);
        this.expectedAmount = expectedAmount;
        this.actualAmount = actualAmount;
    }

    public String toString() {
        return "line " + lineNumber + ":" + columnNumber + " - Expected " + expectedAmount + " parameters, got " + actualAmount;
    }
}
