package CompilerError;

public class DeclarationError extends Error {
	private String actualType;
	private String id;

	public DeclarationError(int columnNumber, int lineNumber, String actualType, String id) {
		super(columnNumber, lineNumber);
		this.actualType = actualType;
		this.id = id;
	}
	
	@Override 
	public String toString() {
		return "ERROR - Line: " + lineNumber + ":" + columnNumber + " - The variable '" + id + "' is type '" + actualType + "' which cannot be declared like this";
    }

}
