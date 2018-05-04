package Warnings;

public class NotInitializedWarning extends CompilerError.Error {
	private String id;
	public NotInitializedWarning(int columnNumber, int lineNumber, String id) {
		super(columnNumber, lineNumber);
		this.id = id;
	}
	@Override
	public String toString() {
		return "WARNING - Line: " + lineNumber + ":" + columnNumber + " - Variable \"" + id + "\" is not initialized";
	}
}
