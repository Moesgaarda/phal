package Warnings;

public class FuncNotUsedWarning extends CompilerError.Error {
	private String id;
	public FuncNotUsedWarning(int columnNumber, int lineNumber, String id) {
		super(columnNumber, lineNumber);
		this.id = id;
	}
	@Override
	public String toString() {
		return "WARNING - Line: " + lineNumber + ":" + columnNumber + " - Function \"" + id + "\" never used";
	}
}
