package enums;

public enum InfixOperator{
    PLUS,
    MINUS,
    MULTIPLY,
    DIVISION,
    MODULO,
    LESSTHANEQUAL,
    GREATERTHANEQUAL,
    AND,
    OR,
    LESSTHAN,
    GREATERTHAN,
    EQUAL,
    NOTEQUAL;
	
    @Override
    public String toString() {
        String stringRep = "";
        switch(this) {
	        case PLUS:
	        	stringRep = " + ";
	        	break;
	        case MINUS:
	        	stringRep = " - ";
	        	break;
	        case MULTIPLY:
	        	stringRep = " * ";
	        	break;
	        case DIVISION:
	        	stringRep = " / ";
	        	break;
	        case MODULO:
	        	stringRep = " % ";
	        	break;
	        case NOTEQUAL:
	        	stringRep = " != ";
	        	break;
	        case EQUAL:
	        	stringRep = " = ";
	        	break;
	        case LESSTHAN:
	        	stringRep = " < ";
	        	break;
	        case GREATERTHAN:
	        	stringRep = " > ";
	        	break;
	        case LESSTHANEQUAL:
	        	stringRep = " <= ";
	        	break;
	        case GREATERTHANEQUAL:
	        	stringRep = " >= ";
	        	break;
            case AND:
                stringRep = " & ";
                break;
            case OR:
            	stringRep = " | ";
            	break;
            default:
            	stringRep = this.toString();
        }
    return stringRep;
    }
}
