package CompilerError;

public class FunctionNotDeclaredError extends Error {
	private String functionName;
	
	public FunctionNotDeclaredError(int columnNumber, int lineNumber, String functionName) {
		super(columnNumber, lineNumber);
		this.functionName = functionName;
	}
	
	@Override
	public String toString() {
		return "ERROR - Line: " + lineNumber + ":" +columnNumber + " - Function " + functionName + " used but never declared";
	}
}
