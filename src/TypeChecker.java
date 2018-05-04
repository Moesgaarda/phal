import CompilerError.*;
import enums.*;

public class TypeChecker extends Visitor{

	public boolean typeErrorEnCountered = false;
	private SymbolTable st;
	
	public TypeChecker(SymbolTable st) {
		this.st = st;
	}
	
	@Override
	public void visit(ProgramNode programNode) {
		programNode.setupNode.accept(this);
		programNode.repeatNode.accept(this);
		
		if(programNode.funcNodes != null) {
			for(FuncNode func: programNode.funcNodes) {
				func.accept(this);
			}
		}
	}
	
	@Override
	public void visit(InfixExprNode infixNode)
	{
		super.visit(infixNode);
		
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
					infixNode.type = Type.ERROR;
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
					infixNode.type = Type.ERROR;
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
					infixNode.type = Type.ERROR;
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
					infixNode.type = Type.ERROR;
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
	
	@Override
	public void visit(UnaryExprNode unaryNode) {
		visit(unaryNode.exprNode);
		switch(unaryNode.unaryOperator) {
			case NOT:
				if(correctType(unaryNode, Type.BOOL)) {
					unaryNode.type = Type.BOOL;
				}
				else {
					unaryNode.type = Type.ERROR;
					MainClass.CompileErrors.add(new TypeError(
							unaryNode.columnNumber, unaryNode.lineNumber, unaryNode.exprNode.type.toString(), Type.BOOL.toString()));
				}
				break;
				
			case NEGATIVE:
				if(correctType(unaryNode, Type.NUMBER)) {
					unaryNode.type = Type.NUMBER;
				}
				else {
					unaryNode.type = Type.ERROR;
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
	
	@Override
	public void visit(WaitNode waitNode) {
		super.visit(waitNode);
		if(waitNode.exprNode.type != Type.NUMBER) {
			MainClass.CompileErrors.add(new TypeError(waitNode.columnNumber, waitNode.lineNumber,
					waitNode.exprNode.type.toString(), Type.NUMBER.toString()));
		}
	}
	
	@Override
	public void visit(FuncNode funcNode) {
		
		for(int i = 0; i < funcNode.funcCntNodes.size(); i++) {
			if(funcNode.funcCntNodes.get(i).stmtNode instanceof ReturnStmtNode) {
				ReturnStmtNode rsNode = (ReturnStmtNode) funcNode.funcCntNodes.get(i).stmtNode;
				visit(rsNode.exprNode);
				
				if(rsNode.exprNode.type != funcNode.typeNode.Type) {
					MainClass.CompileErrors.add(new ReturnTypeError(rsNode.columnNumber, rsNode.lineNumber, funcNode.idNode.id,
							rsNode.exprNode.type.toString(), funcNode.typeNode.Type.toString()));
				}
				
			}
		}
	}
	
	@Override
	public void visit(FuncExprNode funcExprNode) {
		ParametersNode formalParams = st.getFunctionFromFuncMap(funcExprNode.funcCallNode).parametersNode;
		
		checkActualAndFormalParams(funcExprNode.funcCallNode, funcExprNode.funcCallNode.callCntNode, formalParams);
		funcExprNode.type = st.getFunctionFromFuncMap(funcExprNode.funcCallNode).typeNode.Type;
		
	}
	
	@Override
	public void visit(FuncCallNode funcCallNode) {
		ParametersNode formalParams = st.getFunctionFromFuncMap(funcCallNode).parametersNode;
		
		checkActualAndFormalParams(funcCallNode, funcCallNode.callCntNode, formalParams);
		

	}
	
	private void checkActualAndFormalParams(FuncCallNode node, CallCntNode actualParams, ParametersNode formalParams) {
		int actualParamsCount = 0;
		int formalParamsCount = 0;
		
		if(actualParams != null) {
			actualParamsCount = actualParams.exprNodes.size();
		}
		if(formalParams.paramNodes != null) {
			formalParamsCount = formalParams.paramNodes.size();
		}
		
		if(actualParamsCount != 0 && formalParamsCount != 0) {
			if(actualParamsCount == formalParamsCount) {
				// Check if types match
				if(actualParamsCount != 0) {
					super.visit(actualParams);
					for(int i = 0; i < actualParamsCount; i++) {
						ExprNode actualParamExpr = actualParams.exprNodes.get(i);
						TypeNode formalParamType = formalParams.paramNodes.get(i).typeNode;
						
						if(formalParamType.Type != actualParamExpr.type) {
							MainClass.CompileErrors.add(new ParameterMismatchError(node.columnNumber, 
									node.lineNumber, i + 1, actualParamExpr.type.toString(), formalParamType.Type.toString()));
						}
					}
				}
			}
			else {
				MainClass.CompileErrors.add(new ParameterAmountError(node.columnNumber, 
								node.lineNumber, formalParamsCount, actualParamsCount));	
			}
		}
		else if(actualParamsCount != 0 || formalParamsCount != 0) {
			MainClass.CompileErrors.add(new ParameterAmountError(node.columnNumber, 
					 		node.lineNumber, formalParamsCount, actualParamsCount));
		}
	}
	
	@Override
	public void visit(AdvTypeModifierNode node) {
		// Derefter checkes om typen af alle expressions er af samme type som listen.
		super.visit(node);
		if(node.idNode.dclNode instanceof ListNode){
			for(int i = 0; i < node.exprNodes.size(); i++) {
				visit(node.exprNodes.get(i));
				if(node.idNode.type != node.exprNodes.get(i).type) {
					MainClass.CompileErrors.add(new AssignmentError(node.columnNumber, node.lineNumber, 
											node.exprNodes.get(i).type.toString(), node.idNode.type.toString()));
				}
			}
		}
		else {
			MainClass.CompileErrors.add(new ListError(node.columnNumber, node.lineNumber, node.idNode.id));
		}
	}
	
	@Override
	public void visit(AssignmentNode assNode) {
		typeCheckAssignment(assNode);
	}
	
	private void typeCheckAssignment(AssignmentNode node) {
		// Lister og Groups? Hvordan skal det tjekkes?
		super.visit(node);
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
	
	@Override
	public void visit(LiteralAdvancedNode node) {
		super.visit(node);
		// Mangler stadig at checke om id'et er en liste (eller gruppe?)
		if(node.exprNode.type != node.idNode.type) {
			MainClass.CompileErrors.add(new TypeError(
					node.columnNumber, node.lineNumber, node.exprNode.type.toString(), node.idNode.type.toString()));
		}
		node.type = node.idNode.type;
	}
	
	@Override
	public void visit(ParensExprNode node) {
		super.visit(node);
		node.type = node.exprNode.type;
	}
	
	@Override
	public void visit(IdRefExprNode node) {
		if(node.idNode.dclNode instanceof VarDclNode) {
			VarDclNode vdNode = (VarDclNode) node.idNode.dclNode;
			node.type = vdNode.typeNode.Type;
		}
		else if(node.idNode.dclNode instanceof CmpDclNode) {
			CmpDclNode cdNode = (CmpDclNode) node.idNode.dclNode;
			node.type = cdNode.advTypeNode.Type;
		}
		else if(node.idNode.dclNode instanceof ListNode) {
			ListNode lNode = (ListNode) node.idNode.dclNode;
			node.type = lNode.typeNode.Type;
		}
		else if(node.idNode.dclNode instanceof GroupNode) {
			node.type = Type.GROUP;
		}
	}
	
	@Override
	public void visit(IdNode node) {
		if(node.dclNode instanceof VarDclNode) {
			VarDclNode vdNode = (VarDclNode) node.dclNode;
			node.type = vdNode.typeNode.Type;
		}
		else if(node.dclNode instanceof CmpDclNode) {
			CmpDclNode cdNode = (CmpDclNode) node.dclNode;
			node.type = cdNode.advTypeNode.Type;
		}
		else if(node.dclNode instanceof ListNode) {
				ListNode lNode = (ListNode) node.dclNode;
				node.type = lNode.typeNode.Type;
		}
		else if(node.dclNode instanceof GroupNode) {
			node.type = Type.GROUP;
		}
	}
	
	@Override
	public void visit(GroupNode node) {
		// Check om id'ernes typer er advTyper
		for(int i = 0; i < node.memberIdNodes.size(); i++) {
			if(!(node.memberIdNodes.get(i).dclNode instanceof CmpDclNode)) {
				
				// Denne her løsning er ret hæslig. Kan sikkert laves pænere
				if(node.memberIdNodes.get(i).dclNode instanceof VarDclNode) {
					VarDclNode vdNode = (VarDclNode) node.memberIdNodes.get(i).dclNode;
					MainClass.CompileErrors.add(new GroupError(node.columnNumber, node.lineNumber, 
							vdNode.typeNode.Type.toString(), node.memberIdNodes.get(i).id));
				}
				else if(node.memberIdNodes.get(i).dclNode instanceof ListNode) {
					ListNode listNode = (ListNode) node.memberIdNodes.get(i).dclNode;
					MainClass.CompileErrors.add(new GroupError(node.memberIdNodes.get(i).columnNumber, node.memberIdNodes.get(i).lineNumber, 
							listNode.typeNode.Type.toString(), node.memberIdNodes.get(i).id));
				}
			}
		}
	}
	
	@Override
	public void visit(VarDclNode node) {
		if(node.exprNode != null) {
			super.visit(node);
			if(node.exprNode.type != node.typeNode.Type) {
				MainClass.CompileErrors.add(new AssignmentError(node.columnNumber, node.lineNumber, 
						node.exprNode.type.toString(), node.typeNode.Type.toString()));
			}
		}
	}
	
	@Override
	public void visit(ListNode node) {
		super.visit(node);
		if(!node.memberExprNodes.isEmpty()) {
			for(int i = 0; i < node.memberExprNodes.size(); i++) {
				if(node.typeNode.Type != node.memberExprNodes.get(i).type) {
				
					MainClass.CompileErrors.add(new TypeError(
							node.columnNumber, node.lineNumber, node.memberExprNodes.get(i).type.toString(), node.typeNode.type.toString()));
				}
			}
		}
	}

	@Override
	public void visit(SwitchStmtNode node) {
		super.visit(node);
		// checker om hver case expr er samme type som det vi switcher på
		for(int i = 0; i < node.caseListNode.caseStmtNodes.size(); i++) {
			if(node.exprNode.type != node.caseListNode.caseStmtNodes.get(i).exprNode.type) {
				
				MainClass.CompileErrors.add(new TypeError( node.columnNumber, node.lineNumber, 
						node.caseListNode.caseStmtNodes.get(i).exprNode.type.toString(), node.exprNode.type.toString()));
			}
		}
	}
	
	@Override
	public void visit(IfStmtNode node) {
		super.visit(node);
		checkCondition(node.ifExprNode);
	}
	
	@Override
	public void visit(ElseIfStmtNode node) {
		super.visit(node);
		checkCondition(node.exprNode);
	}
	
	@Override
	public void visit(LoopTimesNode node) {
		super.visit(node);
		if(node.exprNode.type != Type.NUMBER) {
			MainClass.CompileErrors.add(new TypeError( node.columnNumber, node.lineNumber, 
										node.exprNode.type.toString(), Type.NUMBER.toString()));
		}
	}
	
	@Override
	public void visit(LoopUntilNode node) {
		super.visit(node);
		checkCondition(node.exprNode);
		
		if(node.idNode.type != Type.NUMBER) {
			  MainClass.CompileErrors.add(new TypeError(node.columnNumber, node.lineNumber, 
					  node.idNode.type.toString(), Type.NUMBER.toString()));
		}
	}
	
	private void checkCondition(ExprNode expr) {
		
		if(expr.type != Type.BOOL) {
			MainClass.CompileErrors.add(new TypeError(
					expr.columnNumber, expr.lineNumber, expr.type.toString(), Type.BOOL.toString()));
		}
	}
}
