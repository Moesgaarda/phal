package Warnings;

public class UnreachableWarning extends CompilerError.Error {
	
	public UnreachableWarning(int columnNumber, int lineNumber) {
		super(columnNumber, lineNumber);
	}
	@Override
	public String toString() {
		return "WARNING - Line: " + lineNumber + ":" + columnNumber + " - Function has already returned. Code unreachable.";
	}
}
