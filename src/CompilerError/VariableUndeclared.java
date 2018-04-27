package CompilerError;

public class VariableUndeclared extends Error{
	String id;
	public VariableUndeclared(int columnNumber, int lineNumber, String id) {
		super(columnNumber, lineNumber);
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "line " + lineNumber +": variable " + id + " used but never declared";
	}
}
