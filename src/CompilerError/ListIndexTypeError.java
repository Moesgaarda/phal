package CompilerError;

import java.util.List;

public class ListIndexTypeError extends  Error{
    String type;
    String name;

    public ListIndexTypeError(int columnNumber, int lineNumber, String name, String type){
        super(columnNumber, lineNumber);
        this.type = type;
        this.name = name;
    }

    @Override
    public String toString(){
        return "Error - Line: " + lineNumber + ":" + columnNumber + " - List can only be indexed with type number but variable " + name + "was of type " + type;
    }
}
