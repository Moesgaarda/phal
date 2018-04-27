package CompilerError;

public class FunctionAlreadyDeclaredError  extends Error {
	private String functionName;
	
	public FunctionAlreadyDeclaredError(int columnNumber, int lineNumber, String functionName) {
		super(columnNumber, lineNumber);
		this.functionName = functionName; 
		
	}
	
	@Override
	public String toString() {
		return "Line: " + lineNumber + ":" + columnNumber + " - Function " + functionName + " already declared";
	}
}
