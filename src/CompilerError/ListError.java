package CompilerError;

public class ListError extends Error{
	private String id;
	
	public ListError(int columnNumber, int lineNumber, String id) {
		super(columnNumber, lineNumber);
		this.id = id;
	}
	
	@Override 
	public String toString() {
		return "line " + lineNumber + ":" + columnNumber + " - The id '" + id + "' is not a list. This operation requires a list.";
    }
}

