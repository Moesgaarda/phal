package CompilerError;

public class NotDeclaredError extends Error {

	private String varName;
	public NotDeclaredError(int columnNumber, int lineNumber, String varName) {
		super(columnNumber, lineNumber);
		this.varName = varName;
	}

	@Override
	public String toString() {
		return "ERROR - Line: " + lineNumber + ":" + columnNumber + " - Id: " + varName + " Not declared";
	}
}
