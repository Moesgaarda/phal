package CompilerError;
import enums.InfixOperator;

public class ListError extends Error{
	private String op = null;
	
	public ListError(int columnNumber, int lineNumber, String op) {
		super(columnNumber, lineNumber);
		this.op = op;
	}
	
	public ListError(int columnNumber, int lineNumber) {
		super(columnNumber, lineNumber);
	}
	
	@Override 
	public String toString() {
		if(op != null) {
			return "ERROR - Line: " + lineNumber + ":" + columnNumber + " - The operator '" + op + "' cannot be used with a list variable";
		}
		else {
			return "ERROR - Line: " + lineNumber + ":" + columnNumber + " - This expression cannot be a list";
		
		}
	}

}
