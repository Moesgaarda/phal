package Warnings;

public class NotUsedWarning extends CompilerError.Error {
	private String id;
	public NotUsedWarning(int columnNumber, int lineNumber, String id) {
		super(columnNumber, lineNumber);
		this.id = id;
	}
	@Override
	public String toString() {
		return "Line: " + lineNumber + ":" + columnNumber + " - Id: " + id + " never used";
	}
}
