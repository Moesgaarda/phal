package CompilerError;

public class GroupError extends Error{
	private String actualType;
	private String id;
	
	public GroupError(int columnNumber, int lineNumber, String actualType, String id) {
		super(columnNumber, lineNumber);
		this.actualType = actualType;
		this.id = id;
	}
	
	@Override
    public String toString() {
        return "ERROR - Line: " + lineNumber + ":" + columnNumber + " - The variable '" + id + "' is of type '" + actualType + "' which can't be used in a group declaration";
    }
}
