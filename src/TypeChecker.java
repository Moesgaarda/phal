import CompilerError.*;

public class TypeChecker extends Visitor{

	public boolean typeErrorEnCountered = false;
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
					//TODO Altså hvad type fejl er det her?? type error men med NUMBER eller TEXT
					// Det er en type error da typen ikke er NUMBER
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
					MainClass.CompileErrors.add(new TypeError(
							infixNode.columnNumber, infixNode.lineNumber,??,Type.NUMBER.toString()));
				}
				break;
			
			case AND:
			case OR:
				if(correctType(infixNode, Type.BOOL)) {
					infixNode.type = Type.BOOL;
				}
				else {
					MainClass.CompileErrors.add(new TypeError(
							infixNode.columnNumber, infixNode.lineNumber,??,Type.BOOL.toString()));
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
					MainClass.CompileErrors.add(new TypeError(
							infixNode.columnNumber, infixNode.lineNumber,??,Type.BOOL.toString()));
				}
				break;
			default:
				System.out.println("This should never happen [TypeChecker infixNode]");
				typeErrorEnCountered = true;

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
					MainClass.CompileErrors.add(new TypeError(
							unaryNode.columnNumber, unaryNode.lineNumber,??,Type.BOOL.toString()));
				}
				break;
				
			case NEGATIVE:
				if(correctType(unaryNode, Type.NUMBER)) {
					unaryNode.type = Type.NUMBER;
				}
				else {
					MainClass.CompileErrors.add(new TypeError(
							unaryNode.columnNumber, unaryNode.lineNumber,??,Type.NUMBER.toString()));
				}
				break;
				
			default: 
				System.out.println("This should never happen [TypeChecker UnaryNode]");
				typeErrorEnCountered = true;
				
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
		
		funcExprNode.type = st.getFunctionFromFuncMap(funcExprNode.funcCallNode).typeNode.Type;
		
	}
	
	public void visit(FuncCallNode funcCallNode) {
		ParametersNode formalParams = st.getFunctionFromFuncMap(funcCallNode).parametersNode;
		
		checkActualAndFormalParams(funcCallNode, funcCallNode.callCntNode, formalParams);
		

	}
	
	private void checkActualAndFormalParams(AstNode node, CallCntNode actualParams, ParametersNode formalParams) {
		int actualParamsCount = actualParams.exprNodes.size();
		int formalParamsCount = formalParams.paramNodes.size();
		
		if(actualParams != null && formalParams != null) {
			
			if(actualParamsCount == formalParamsCount) {
				// Check if types match
				if(actualParamsCount != 0) {
					for(int i = 0; i < actualParamsCount; i++) {
						ExprNode actualParamExpr = actualParams.exprNodes.get(i);
						TypeNode formalParamType = formalParams.paramNodes.get(i).typeNode;
						
						if(formalParamType.Type != actualParamExpr.type) {
							MainClass.CompileErrors.add(new ParameterMismatchError(node.columnNumber, 
									node.lineNumber, actualParamExpr.type.toString(), formalParamType.Type.toString()));
							
						}
					}
				}
			}
			else {
				MainClass.CompileErrors.add(new ParameterAmountError(node.columnNumber, 
								node.lineNumber, actualParamsCount, formalParamsCount));
				
			}
		}
		else if(actualParams != null || formalParams != null) {
			MainClass.CompileErrors.add(new ParameterAmountError(node.columnNumber, 
					 		node.lineNumber, actualParamsCount, formalParamsCount));
		}
		
		// both
		
	}
	
	public void visit(AssignmentNode assNode) {
		typeCheckAssignment(assNode);
	}
	
	private void typeCheckAssignment(AssignmentNode node) {
		
	}

	
}
