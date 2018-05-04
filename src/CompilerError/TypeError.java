package CompilerError;

public class TypeError extends Error{
	private String actualType;
    private String expectedType;

    public TypeError(int columnNumber, int lineNumber, String actualType, String expectedType ) {
        super(columnNumber, lineNumber);
        this.actualType = actualType;
        this.expectedType = expectedType;
    }
    @Override
    public String toString() {
        return "ERROR - Line: " + lineNumber + ":" + columnNumber + " - Expected type '" + expectedType + "' but got type '" + actualType + "'";
    }
}
