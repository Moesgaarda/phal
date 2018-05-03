import CompilerError.*;
import enums.*;

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
					MainClass.CompileErrors.add(new InfixTypeError(infixNode.columnNumber, infixNode.lineNumber, 
							infixNode.leftExprNode.type.toString(), infixNode.rightExprNode.type.toString(), infixNode.infixOperator));
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
					MainClass.CompileErrors.add(new InfixTypeError(infixNode.columnNumber, infixNode.lineNumber, 
							infixNode.leftExprNode.type.toString(), infixNode.rightExprNode.type.toString(), infixNode.infixOperator));
				}
				break;
			
			case AND:
			case OR:
				if(correctType(infixNode, Type.BOOL)) {
					infixNode.type = Type.BOOL;
				}
				else {
					MainClass.CompileErrors.add(new InfixTypeError(infixNode.columnNumber, infixNode.lineNumber, 
							infixNode.leftExprNode.type.toString(), infixNode.rightExprNode.type.toString(), infixNode.infixOperator));
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
					MainClass.CompileErrors.add(new InfixTypeError(infixNode.columnNumber, infixNode.lineNumber, 
							infixNode.leftExprNode.type.toString(), infixNode.rightExprNode.type.toString(), infixNode.infixOperator));
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
							unaryNode.columnNumber, unaryNode.lineNumber, unaryNode.exprNode.type.toString(), Type.BOOL.toString()));
				}
				break;
				
			case NEGATIVE:
				if(correctType(unaryNode, Type.NUMBER)) {
					unaryNode.type = Type.NUMBER;
				}
				else {
					MainClass.CompileErrors.add(new TypeError(
							unaryNode.columnNumber, unaryNode.lineNumber, unaryNode.exprNode.type.toString(), Type.NUMBER.toString()));
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
	
	public void visit(WaitNode waitNode) {
		visit(waitNode.exprNode);
		if(waitNode.exprNode.type != Type.NUMBER) {
			MainClass.CompileErrors.add(new TypeError(waitNode.columnNumber, waitNode.lineNumber,
					waitNode.exprNode.type.toString(), Type.NUMBER.toString()));
		}
	}
	
	public void visit(FuncNode funcNode) {
		
		for(int i = 0; i < funcNode.funcCntNodes.size(); i++) {
			if(funcNode.funcCntNodes.get(i).stmtNode instanceof ReturnStmtNode) {
				ReturnStmtNode rsNode = (ReturnStmtNode) funcNode.funcCntNodes.get(i).stmtNode;
				visit(rsNode.exprNode);
				
				if(rsNode.exprNode.type != funcNode.typeNode.Type) {
					MainClass.CompileErrors.add(new ReturnTypeError(rsNode.columnNumber, rsNode.lineNumber,
							rsNode.exprNode.type.toString(), funcNode.typeNode.Type.toString()));
				}
				
			}
		}
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
	}
	
	public void visit(AdvTypeModifierNode node) {
		// TODO Der skal checkes om det faktisk er en liste
		// if(st.)
		// Derefter checkes om typen af alle expressions er af samme type som listen.
		for(int i = 0; i < node.exprNodes.size(); i++) {
			if(node.idNode.type != node.exprNodes.get(i).type) {
				MainClass.CompileErrors.add(new AssignmentError(node.columnNumber, node.lineNumber, 
											node.exprNodes.get(i).type.toString(), node.idNode.type.toString()));
				
			}
		}
	}
	
	public void visit(AssignmentNode assNode) {
		typeCheckAssignment(assNode);
	}
	
	private void typeCheckAssignment(AssignmentNode node) {
		// Lister og Groups? Hvordan skal det tjekkes?
		visit(node.exprNode);
		
		if(node.exprNode != null) {
			if(node.idNode.type != node.exprNode.type) {
				if(node.idNode.type != Type.GROUP) { // Er dette nødvendigt? Kan en group indgå i assignment?
					MainClass.CompileErrors.add(new AssignmentError(node.columnNumber, node.lineNumber, 
											node.exprNode.type.toString(), node.idNode.type.toString()));
				}
			}
		}
		
		switch(node.assignmentOperator) {
			case EQUALS:
				break;
				
			case PLUSEQUALS:
			case MINUSEQUALS:
				if(node.idNode.type != Type.NUMBER || node.exprNode.type != Type.NUMBER) {
					// if(st.getEntryInSymbolTable(node.idNode.id)) { // tjek om id'et er en liste
						
					}
					else {
						// ERROR
					}
				}
							
		}
	
	
	public void visit(LiteralAdvancedNode node) {
		visit(node.exprNode);
		// Mangler stadig at checke om id'et er en liste (eller gruppe?)
		if(node.exprNode.type != node.idNode.type) {
			MainClass.CompileErrors.add(new TypeError(
					node.columnNumber, node.lineNumber, node.exprNode.type.toString(), node.idNode.type.toString()));
		}
		node.type = node.idNode.type;
	}
	
	public void visit(ParensExprNode node) {
		visit(node.exprNode);
		node.type = node.exprNode.type;
	}
	
	public void visit(IdRefExprNode node) {
		node.type = node.idNode.dclNode.idNode.type;
	}
	
	public void visit(IdNode node) {
		node.type = node.dclNode.idNode.type;
	}
	
	public void visit(GroupNode node) {
		// Check om id'ernes typer er advTyper
		for(int i = 0; i < node.memberIdNodes.size(); i++) {
			if(!(node.memberIdNodes.get(i).dclNode instanceof CmpDclNode)) {
				MainClass.CompileErrors.add(new GroupError(node.memberIdNodes.get(i).columnNumber, node.memberIdNodes.get(i).lineNumber, 
						node.memberIdNodes.get(i).type.toString(), node.memberIdNodes.get(i).id));
			}
		}
	}
	
	public void visit(VarDclNode node) {
		if(node.exprNode != null) {
			visit(node.exprNode);
			if(node.exprNode.type != node.typeNode.Type) {
				MainClass.CompileErrors.add(new AssignmentError(node.columnNumber, node.lineNumber, 
						node.exprNode.type.toString(), node.typeNode.Type.toString()));
			}
		}
	}
	
	public void visit(ListNode node) {
		
		if(!node.memberExprNodes.isEmpty()) {
			for(int i = 0; i < node.memberExprNodes.size(); i++) {
				visit(node.memberExprNodes.get(i));
				if(node.typeNode.Type != node.memberExprNodes.get(i).type) {
				
					MainClass.CompileErrors.add(new TypeError(
							node.columnNumber, node.lineNumber, node.memberExprNodes.get(i).type.toString(), node.typeNode.type.toString()));
				}
			}
		}
	}

	public void visit(SwitchStmtNode node) {
		visit(node.exprNode);
		// checker om hver case expr er samme type som det vi switcher på
		for(int i = 0; i < node.caseListNode.caseStmtNodes.size(); i++) {
			visit(node.caseListNode.caseStmtNodes.get(i).exprNode);
			
			if(node.exprNode.type != node.caseListNode.caseStmtNodes.get(i).exprNode.type) {
				
				MainClass.CompileErrors.add(new TypeError( node.columnNumber, node.lineNumber, 
						node.caseListNode.caseStmtNodes.get(i).exprNode.type.toString(), node.exprNode.type.toString()));
			}
		}
	}

//	public void visit(CaseStmtNode node) {
//		visit(node.exprNode);
//		for(int i = 0; i < node.)
//	}
	
	public void visit(IfStmtNode node) {
		visit(node.ifExprNode);
		checkCondition(node.ifExprNode, node);
	}
	
	public void visit(ElseIfStmtNode node) {
		visit(node.exprNode);
		checkCondition(node.exprNode, node);
	}
	
	public void visit(LoopTimesNode node) {
		visit(node.exprNode);
		
		if(node.exprNode.type != Type.NUMBER) {
			MainClass.CompileErrors.add(new TypeError( node.columnNumber, node.lineNumber, 
										node.exprNode.type.toString(), Type.NUMBER.toString()));
		}
	}
	
	public void visit(LoopUntilNode node) {
		visit(node.exprNode);
		checkCondition(node.exprNode, node);
		
		if(node.idNode.type != Type.NUMBER) {
			MainClass.CompileErrors.add(new TypeError(node.columnNumber, node.lineNumber, 
					node.idNode.type.toString(), Type.NUMBER.toString()));
		}
	}
	
	private void checkCondition(ExprNode expr, AstNode node) {
		
		if(expr.type != Type.BOOL) {
			MainClass.CompileErrors.add(new TypeError(
					node.columnNumber, node.lineNumber, expr.type.toString(), Type.BOOL.toString()));
		}
	}
}
