package CompilerError;
import enums.AssignmentOperator;

public class CompoundAssignmentError extends Error{
	private String id;
	private AssignmentOperator op;
	

    public CompoundAssignmentError(int columnNumber, int lineNumber, AssignmentOperator op, String id) {
        super(columnNumber, lineNumber);
        this.op = op;
        this.id = id;
    }
    @Override
    public String toString() {
        return "ERROR - Line: " + lineNumber + ":" + columnNumber + "The operator " + op + " only works with variables of type 'NUMBER', unless the variable '" + id + "' is a list";
    }
}
