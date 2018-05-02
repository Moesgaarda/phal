package CompilerError;
import enums.*;

public class InfixTypeError extends Error {
	private String typeOne;
	private String typeTwo;
	private InfixOperator operator;

	public InfixTypeError(int columnNumber, int lineNumber, String typeOne, String typeTwo, InfixOperator operator) {
		super(columnNumber, lineNumber);
		this.typeOne = typeOne;
		this.typeTwo = typeTwo;
		this.operator = operator;
		
	}
	@Override
	public String toString() {
		
		switch(operator) {
			case PLUS: 
				return "line " + lineNumber + ":" + columnNumber + " - Could not use the '" + operator.toString() + "' with expressions of the types"
					+ typeOne + " and " + typeTwo + "\n" + "Both must be of either type NUMBER or TEXT";
				
			case MINUS:
			case MULTIPLY:
			case DIVISION:
			case MODULO:
			case LESSTHANEQUAL:
			case GREATERTHANEQUAL:
			case GREATERTHAN:
			case LESSTHAN:
				return "line " + lineNumber + ":" + columnNumber + " - Could not use the '" + operator.toString() + "' with expressions of the types"
				+ typeOne + " and " + typeTwo + "\n" + "Both must be of type NUMBER";
		
			case AND:
			case OR:
				return "line " + lineNumber + ":" + columnNumber + " - Could not use the '" + operator.toString() + "' with expressions of the types"
				+ typeOne + " and " + typeTwo + "\n" + "Both must be of type BOOL";
			case EQUAL:
			case NOTEQUAL:
				return "line " + lineNumber + ":" + columnNumber + " - Could not use the '" + operator.toString() + "' with expressions of types"
				+ typeOne + " and " + typeTwo + "\n" + "Both must be of either type NUMBER, TEXT or BOOL";
				
			default:
				return "line " + lineNumber + ":" + columnNumber + " - Undefined type error";
		}
		
	}
}
