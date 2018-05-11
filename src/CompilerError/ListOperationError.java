package CompilerError;

public class ListOperationError extends Error{
	private String id;
	
	public ListOperationError(int columnNumber, int lineNumber, String id) {
		super(columnNumber, lineNumber);
		this.id = id;
	}
	
	@Override 
	public String toString() {
		return "ERROR - Line: " + lineNumber + ":" + columnNumber + " - The id '" + id + "' is not a list. This operation requires a list.";
    }
}

