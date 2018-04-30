package enums;

public enum AssignmentOperator {
	EQUALS,
	PLUSEQUALS,
	MINUSEQUALS;
	
	@Override
    public String toString() {
        String stringRep = "";
        switch(this) {
	        case EQUALS:
	        	stringRep = " := ";
	        	break;
	        case PLUSEQUALS:
	        	stringRep = " += ";
	        	break;
	        case MINUSEQUALS:
	        	stringRep = " -= ";
	        	break;
            default:
            	stringRep = this.toString();
        }
    return stringRep;
    }
}
