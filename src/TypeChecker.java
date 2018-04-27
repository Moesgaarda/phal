
public class TypeChecker extends Visitor{

	public boolean typeErrorInCountered = false;
	private SymbolTable st;
	
	public TypeChecker(SymbolTable st) {
		this.st = st;
	}
	
	public void visit(InfixExprNode infixNode)
	{
		visit(infixNode.rightExprNode);
		visit(infixNode.leftExprNode);
		
		switch(infixNode.infixOperator) 
		{
			case PLUS: 
				// konkatenation af strenge
				if(correctType(infixNode, Type.TEXT)) {
					infixNode.type = Type.TEXT;
					break;
				}
			case MINUS:
			case MULTIPLY:
			case DIVISION:
			case MODULO:
				if(correctType(infixNode, Type.NUMBER)) {
					infixNode.type = Type.NUMBER;
				}
				else {
					// Error
				}
				break;
		
			case LESSTHANEQUAL:
			case GREATERTHANEQUAL:
			case GREATERTHAN:
			case LESSTHAN:
				if(correctType(infixNode, Type.NUMBER)) {
					infixNode.type = Type.BOOL;
				}
				else {
					// ERROR
				}
				break;
			
			case AND:
			case OR:
				if(correctType(infixNode, Type.BOOL)) {
					infixNode.type = Type.BOOL;
				}
				else {
					// ERROR
				}
				break;
		
			case EQUAL:
			case NOTEQUAL:
				if(correctType(infixNode, Type.TEXT)) {
					infixNode.type = Type.BOOL;
				}
				else if(correctType(infixNode, Type.NUMBER)) {
					infixNode.type = Type.BOOL;
				}
				else if(correctType(infixNode, Type.BOOL)) {
					infixNode.type = Type.BOOL;
				}
				else {
					// ERROR
				}
				break;
			default:
				// ERROR ?
			

		}
	}
	
	private boolean correctType(InfixExprNode infixExprNode, Type expectedType) {
		boolean typeIsCorrect = false;
		
		if(infixExprNode.leftExprNode.type == expectedType && infixExprNode.rightExprNode.type == expectedType) {
			typeIsCorrect = true;
		}
		else if(infixExprNode.leftExprNode instanceof IdNode) {
			IdNode idNode = (IdNode) infixExprNode.leftExprNode;
			if(idNode.type == expectedType && infixExprNode.rightExprNode.type == expectedType) {
				typeIsCorrect = true;
			}
		}
		else if(infixExprNode.rightExprNode instanceof IdNode) {
			IdNode idNode = (IdNode) infixExprNode.rightExprNode;
			if(idNode.type == expectedType && infixExprNode.leftExprNode.type == expectedType) {
				typeIsCorrect = true;
			}
		}
		return typeIsCorrect;
	}
	
	public void visit(UnaryExprNode unaryNode) {
		visit(unaryNode.exprNode);
		
		switch(unaryNode.unaryOperator) {
			case NOT:
				if(correctType(unaryNode, Type.BOOL)) {
					unaryNode.type = Type.BOOL;
				}
				else {
					// ERROR
				}
				break;
				
			case NEGATIVE:
				if(correctType(unaryNode, Type.NUMBER)) {
					unaryNode.type = Type.NUMBER;
				}
				else {
					// ERROR
				}
				break;
				
			default: 
				// ERROR
				
		}
	}
	
	private boolean correctType(UnaryExprNode unaryExprNode, Type expectedType) {
		boolean typeIsCorrect = false;
		
		if(unaryExprNode.exprNode.type == expectedType) {
			typeIsCorrect = true;
		}
		else if(unaryExprNode.exprNode instanceof IdNode) {
			IdNode idNode = (IdNode) unaryExprNode.exprNode;
			
			if(idNode.type == expectedType) {
				typeIsCorrect = true;
			}
		}
		
		return typeIsCorrect;
	}
	
	public void visit(FuncExprNode funcExprNode) {
		ParametersNode formalParams = st.getFunctionFromFuncMap(funcExprNode.funcCallNode).parametersNode;
		
		checkActualAndFormalParams(funcExprNode, funcExprNode.funcCallNode.callCntNode, formalParams);
		
	}
	
	public void visit(FuncCallNode funcCallNode) {
		ParametersNode formalParams = st.getFunctionFromFuncMap(funcCallNode).parametersNode;
		
		checkActualAndFormalParams(funcCallNode, funcCallNode.callCntNode, formalParams);
		

	}
	
	private void checkActualAndFormalParams(AstNode node, CallCntNode actualParams, ParametersNode formalParams) {
		if(actualParams != null && formalParams != null) {
			int actualParamsCount = actualParams.exprNodes.size();
			if(actualParamsCount == formalParams.paramNodes.size()) {
				// Check if types match
				if(actualParamsCount != 0) {
					for(int i = 0; i < actualParamsCount; i++) {
						ExprNode actualParamExpr = actualParams.exprNodes.get(i);
						TypeNode formalParamType = formalParams.paramNodes.get(i).typeNode;
						
						if(formalParamType.Type != actualParamExpr.type) {
							// Error. The types doesn't match
							
						}
					}
				}
			}
			else {
				// ERROR. Mismatch in amount of params
			}
		}
		else if(actualParams != null || formalParams != null) {
			// If it reaches here, one is null and the other isn't which means an error. Mismatch in amount of params
		}
		
		
	}

	
}
