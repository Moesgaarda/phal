package Warnings;

public class VarNotUsedWarning extends CompilerError.Error {
	private String id;
	public VarNotUsedWarning(int columnNumber, int lineNumber, String id) {
		super(columnNumber, lineNumber);
		this.id = id;
	}
	@Override
	public String toString() {
		return "WARNING - Line: " + lineNumber + ":" + columnNumber + " - Variable \"" + id + "\" is never used";
	}
}
