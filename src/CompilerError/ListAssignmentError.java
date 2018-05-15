package CompilerError;

public class ListAssignmentError extends Error {
	private String id;
	private boolean idIsList;
	
	public ListAssignmentError(int columnNumber, int lineNumber, String id, boolean idIsList) {
		super(columnNumber, lineNumber);
		this.id = id;
		this.idIsList = idIsList;
		
	}
	@Override 
	public String toString() {
		if(idIsList) {
			return "ERROR - Line: " + lineNumber + ":" + columnNumber + "The expression is not a list and therefore could not be assigned to the list '" + id + "'";
		}
		else {
			return "ERROR - Line: " + lineNumber + ":" + columnNumber + "The expression is a list and therefore could not be assigned to the list '" + id + "'";
		}
	}
}
