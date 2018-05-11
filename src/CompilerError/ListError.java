package CompilerError;
import enums.InfixOperator;

public class ListError extends Error{
	private String op;
	
	public ListError(int columnNumber, int lineNumber, String op) {
		super(columnNumber, lineNumber);
		this.op = op;
	}
	
	@Override 
	public String toString() {
		return "ERROR - Line: " + lineNumber + ":" + columnNumber + " - The operator '" + op + "' cannot be used with a list variable";
    }

}
