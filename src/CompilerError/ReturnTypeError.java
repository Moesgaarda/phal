package CompilerError;

public class ReturnTypeError extends Error {
	private String actualType;
    private String expectedType;
    
	public ReturnTypeError(int columnNumber, int lineNumber, String actualType , String expectedType) {
		super(columnNumber, lineNumber);
		this.actualType = actualType;
        this.expectedType = expectedType;	
	}
	
	@Override
	public String toString() {
	    return "line " + lineNumber + ":" + columnNumber + " This function expects a return of type " + expectedType + " but the return is of type " + actualType;
	}
}
