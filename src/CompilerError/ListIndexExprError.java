package CompilerError;

public class ListIndexExprError extends Error {
    public ListIndexExprError(int columnNumber, int lineNumber){
        super(columnNumber, lineNumber);
    }

    @Override
    public String toString(){
        return "ERROR - Line: " + lineNumber + ":" + columnNumber + " - Lists can only be indexed with numbers or variables.";
    }
}
