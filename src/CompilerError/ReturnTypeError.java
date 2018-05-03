package CompilerError;

public class ReturnTypeError extends Error {
	private String actualType;
    private String expectedType;
    private String functionName;
    
	public ReturnTypeError(int columnNumber, int lineNumber,String functionName, String actualType , String expectedType) {
		super(columnNumber, lineNumber);
		this.functionName = functionName;
		this.actualType = actualType;
        this.expectedType = expectedType;	
	}
	
	@Override
	public String toString() {
	    return "line " + lineNumber + ":" + columnNumber + " The function '" + functionName + "' expects a return of type " + expectedType + " but the return is of type " + actualType;
	}
}
