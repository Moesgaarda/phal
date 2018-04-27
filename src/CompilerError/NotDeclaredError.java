package CompilerError;

public class NotDeclaredError extends Error {

	private String varName;
	public NotDeclaredError(int columnNumber, int lineNumber, String varName) {
		super(columnNumber, lineNumber);
		this.varName = varName;
	}

	@Override
	public String toString() {
		return "Line: " + lineNumber + " Col: " + columnNumber + " Id: " + varName + " Not declared";
	}
}
