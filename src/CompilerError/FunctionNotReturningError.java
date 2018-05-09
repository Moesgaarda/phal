package CompilerError;

public class FunctionNotReturningError extends Error {
	String functionName;
	public FunctionNotReturningError(int columnNumber, int lineNumber, String functionName) {
		super(columnNumber, lineNumber);
		this.functionName = functionName; 
	}
	
	@Override
	public String toString() {
		return "ERROR - Line: " + lineNumber + ":" + columnNumber + " - Function " + functionName + " Expected to return. But never returns a value";
	}
}
