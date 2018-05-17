package CompilerError;

public class ListIndexError extends Error{

    String name;
    public ListIndexError(int columnNumber, int lineNumber){
        super(columnNumber,lineNumber);
    }

    public ListIndexError(int columnNumber, int lineNumber, String name){
        super(columnNumber, lineNumber);
        this.name = name;
    }
    @Override
    public String toString(){
        if(name != null){
            return "Error - Line: " + lineNumber + ":" + columnNumber + "- List can only be indexed with integers but " + name + " contains a comma seperated number";
        }
        return "Error - Line: " + lineNumber + ":" + columnNumber + " - List can only be indexed with integers but was indexed with a comma sperated number";
    }
}
