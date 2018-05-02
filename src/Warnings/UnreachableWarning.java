package Warnings;

public class UnreachableWarning extends CompilerError.Error {
	
	public UnreachableWarning(int columnNumber, int lineNumber) {
		super(columnNumber, lineNumber);
	}
	@Override
	public String toString() {
		return "Line: " + lineNumber + ":" + columnNumber + " - Function has already returned. Code unreachable.";
	}
}
