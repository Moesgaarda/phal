package CompilerError;

public class ParameterMismatchError extends Error{
	private String expectedSignature;
    private String actualSignature;
    private int paramNum;

    public ParameterMismatchError(int columnNumber, int lineNumber, int paramNum, String expectedSignature, String actualSignature) {
        super(columnNumber, lineNumber);
        this.paramNum = paramNum;
        this.expectedSignature = expectedSignature;
        this.actualSignature = actualSignature;
    }

    @Override
    public String toString() {
        return "ERROR - Line: " + lineNumber + ":" + columnNumber + " - Argument " + paramNum +  ": Expected parameter of type '" + expectedSignature + "' but got '" + actualSignature + "'";
    }
}
