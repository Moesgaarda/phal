package CompilerError;

public class FunctionNotDeclaredError extends Error {
	private String functionName;
	
	public FunctionNotDeclaredError(int columnNumber, int lineNumber, String functionName) {
		super(columnNumber, lineNumber);
		this.functionName = functionName;
	}
	
	@Override
	public String toString() {
		return "Line: " + lineNumber + ": Function " + functionName + " used but never declared";
	}
}
