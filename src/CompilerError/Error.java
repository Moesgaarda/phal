package CompilerError;

public class Error {
	protected int columnNumber;
	protected int lineNumber;
	
	public Error(int columnNumber, int lineNumber) {
		this.columnNumber = columnNumber;
		this.lineNumber = lineNumber;
	}
}
