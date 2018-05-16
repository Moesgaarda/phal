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
		if(isAList(infixNode.leftExprNode) || isAList(infixNode.rightExprNode)) {
			MainClass.CompileErrors.add(new ListError(infixNode.columnNumber, infixNode.lineNumber, infixNode.infixOperator.toString()));
		}
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
		if(infixExprNode.leftExprNode.type == expectedType && infixExprNode.rightExprNode.type == expectedType) {
				return true;
		}
		return false;
	}
	
	@Override
	public void visit(UnaryExprNode unaryNode) {
		visit(unaryNode.exprNode);
		if(isAList(unaryNode.exprNode)) {
			MainClass.CompileErrors.add(new ListError(unaryNode.columnNumber, unaryNode.lineNumber, 
					unaryNode.unaryOperator.toString()));
		}
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
		if(unaryExprNode.exprNode.type == expectedType) {
			return true;
		}
			
		return false;
	}
	
	@Override
	public void visit(WaitNode waitNode) {
		super.visit(waitNode);
		if(waitNode.exprNode.type != Type.NUMBER) {
			MainClass.CompileErrors.add(new TypeError(waitNode.columnNumber, waitNode.lineNumber,
					waitNode.exprNode.type.toString(), Type.NUMBER.toString()));
		}
		if(isAList(waitNode.exprNode)) {
			MainClass.CompileErrors.add(new ListError(waitNode.columnNumber, waitNode.lineNumber));
		}
	}
	
	@Override
	public void visit(FuncNode funcNode) {
		super.visit(funcNode);
		for(int i = 0; i < funcNode.funcCntNodes.size(); i++) {
			if(funcNode.funcCntNodes.get(i).stmtNode instanceof ReturnStmtNode) {
				ReturnStmtNode rsNode = (ReturnStmtNode) funcNode.funcCntNodes.get(i).stmtNode;
				visit(rsNode.exprNode);
				
				if(rsNode.exprNode.type != funcNode.typeNode.Type) {
					MainClass.CompileErrors.add(new ReturnTypeError(rsNode.columnNumber, rsNode.lineNumber, funcNode.idNode.id,
							rsNode.exprNode.type.toString(), funcNode.typeNode.Type.toString(), false, false));
				}
				else if(isAList(rsNode.exprNode) && !funcNode.typeNode.islist) {
					MainClass.CompileErrors.add(new ReturnTypeError(rsNode.columnNumber, rsNode.lineNumber, funcNode.idNode.id,
							rsNode.exprNode.type.toString(), funcNode.typeNode.Type.toString(), true, false));
				}
				else if(!isAList(rsNode.exprNode) && funcNode.typeNode.islist) {
					MainClass.CompileErrors.add(new ReturnTypeError(rsNode.columnNumber, rsNode.lineNumber, funcNode.idNode.id,
							rsNode.exprNode.type.toString(), funcNode.typeNode.Type.toString(), true, true));
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
		int paramNum;
		
		if(actualParams != null) {
			actualParamsCount = actualParams.exprNodes.size();
		}
		if(formalParams.paramNodes != null) {
			formalParamsCount = formalParams.paramNodes.size();
		}
		
		if(actualParamsCount != 0 && formalParamsCount != 0) {
			if(actualParamsCount == formalParamsCount) {
				// Check if types match
				super.visit(actualParams);
				for(int i = 0; i < actualParamsCount; i++) {
					ExprNode actualParamExpr = actualParams.exprNodes.get(i);
					TypeNode formalParamType = formalParams.paramNodes.get(i).typeNode;
					paramNum = i + 1;
					
					if(!isAList(actualParamExpr) && formalParams.paramNodes.get(i).typeNode.islist) {
						MainClass.CompileErrors.add(new ParameterMismatchError(node.columnNumber, 
								node.lineNumber, paramNum, formalParamType.Type.toString(), actualParamExpr.type.toString(), true, true));
					}
					else if(isAList(actualParamExpr) && !formalParams.paramNodes.get(i).typeNode.islist) {
						MainClass.CompileErrors.add(new ParameterMismatchError(node.columnNumber, 
								node.lineNumber, paramNum, formalParamType.Type.toString(), actualParamExpr.type.toString(), true, false));
					}
					if(formalParamType.Type != actualParamExpr.type) {
						MainClass.CompileErrors.add(new ParameterMismatchError(node.columnNumber, 
								node.lineNumber, paramNum, formalParamType.Type.toString(), actualParamExpr.type.toString(), false, false));
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
		// Derefter checkes om typen af hver expressions er af samme type som listen.
		super.visit(node);
		
		if(node.idNode.dclNode instanceof ListNode){
			if(node.advancedTypeModifierOperator == AdvancedTypeModifierOperator.ADD) {
				for(int i = 0; i < node.exprNodes.size(); i++) {
					visit(node.exprNodes.get(i));
					if(isAList(node.exprNodes.get(i))) {
						MainClass.CompileErrors.add(new ListError(node.exprNodes.get(i).columnNumber, node.exprNodes.get(i).lineNumber));
					}
					if(node.idNode.type != node.exprNodes.get(i).type) {
						MainClass.CompileErrors.add(new AssignmentError(node.columnNumber, node.lineNumber, 
											node.exprNodes.get(i).type.toString(), node.idNode.type.toString()));
					}
				}
			}
			else if(node.advancedTypeModifierOperator == AdvancedTypeModifierOperator.REMOVE) {
				for(int i = 0; i < node.exprNodes.size(); i++) {
					visit(node.exprNodes.get(i));
					if(isAList(node.exprNodes.get(i))) {
						MainClass.CompileErrors.add(new ListError(node.exprNodes.get(i).columnNumber, node.exprNodes.get(i).lineNumber));
					}
					if(node.exprNodes.get(i).type != Type.NUMBER) {
						MainClass.CompileErrors.add(new TypeError(node.exprNodes.get(i).columnNumber, node.exprNodes.get(i).lineNumber,
								node.exprNodes.get(i).type.toString(), Type.NUMBER.toString()));
					}
				}
			}
		}
		else {
			MainClass.CompileErrors.add(new ListOperationError(node.columnNumber, node.lineNumber, node.idNode.id));
		}
	}
	
	@Override
	public void visit(AssignmentNode assNode) {
		typeCheckAssignment(assNode);
	}
	
	private void typeCheckAssignment(AssignmentNode node) {
		super.visit(node);
		
		// check om id er gruppe og expr bool
		if(node.idNode.type == Type.GROUP) {
			if(node.exprNode.type != Type.BOOL) {
				MainClass.CompileErrors.add(new AssignmentError(node.columnNumber, node.lineNumber, 
						node.exprNode.type.toString(), node.idNode.type.toString()));
			}
		}
		// check om id og expr er samme type
		else if(node.idNode.type != node.exprNode.type) {
			MainClass.CompileErrors.add(new AssignmentError(node.columnNumber, node.lineNumber, 
								node.exprNode.type.toString(), node.idNode.type.toString()));
		}
		// check efter om både id og expr er lister og omvendt
		if(node.idNode.dclNode instanceof ListNode && !isAList(node.exprNode)) {
			MainClass.CompileErrors.add(new ListAssignmentError(node.columnNumber, node.lineNumber, node.idNode.id, true));
		}
		else if(!(node.idNode.dclNode instanceof ListNode) && isAList(node.exprNode)) {
			MainClass.CompileErrors.add(new ListAssignmentError(node.columnNumber, node.lineNumber, node.idNode.id, false));
		}
		else {
			switch(node.assignmentOperator) {
				case EQUALS:
					break;
				case PLUSEQUALS:
				case MINUSEQUALS:
					if(node.idNode.type != Type.NUMBER && node.exprNode.type != Type.NUMBER) {
						if(node.idNode.dclNode instanceof ListNode) {
							// Hvis id'et er en liste behøves typen ikke være number for at bruge '+=' eller '-='
							break;
						}
						else {
							MainClass.CompileErrors.add(new CompoundAssignmentError(node.columnNumber, node.lineNumber, node.assignmentOperator, node.idNode.id));
						}
					}
			}
		}	
	}
	
	@Override
	public void visit(LiteralAdvancedNode node) {
		super.visit(node);
		if(isAList(node.exprNode)) {
			MainClass.CompileErrors.add(new ListError(node.exprNode.columnNumber, node.exprNode.lineNumber));
		}
		if(node.exprNode.type != Type.NUMBER) {
			MainClass.CompileErrors.add(new TypeError(node.columnNumber, node.lineNumber, node.exprNode.type.toString(), Type.NUMBER.toString()));
		}
		if(!(node.idNode.dclNode instanceof ListNode)) {
			MainClass.CompileErrors.add(new ListOperationError(node.columnNumber, node.lineNumber, node.idNode.id));
		}
		node.type = node.idNode.type;
	}
	
	@Override
	public void visit(ParensExprNode node) {
		super.visit(node);
		if(isAList(node)) {
			MainClass.CompileErrors.add(new ListError(node.columnNumber, node.lineNumber));
		}
		node.type = node.exprNode.type;
	}
	
	@Override
	public void visit(IdRefExprNode idRefNode) {
		super.visit(idRefNode);
		if(idRefNode.idNode.dclNode instanceof VarDclNode) {
			VarDclNode vdNode = (VarDclNode) idRefNode.idNode.dclNode;
			idRefNode.type = vdNode.typeNode.Type;
		}
		else if(idRefNode.idNode.dclNode instanceof CmpDclNode) {
			CmpDclNode cdNode = (CmpDclNode) idRefNode.idNode.dclNode;
			idRefNode.type = cdNode.advTypeNode.Type;
		}
		else if(idRefNode.idNode.dclNode instanceof ListNode) {
			ListNode lNode = (ListNode) idRefNode.idNode.dclNode;
			idRefNode.type = lNode.typeNode.Type;
		}
		else if(idRefNode.idNode.dclNode instanceof GroupNode) {
			idRefNode.type = Type.GROUP;
		}
		// N�r id'et er det formelle parameter
		else if(idRefNode.idNode.dclNode instanceof ParamNode) {
			ParamNode pNode = (ParamNode) idRefNode.idNode.dclNode;
			idRefNode.type = pNode.typeNode.Type;
		}
		
		if(idRefNode.idNode.subId != null) {
			if(idRefNode.idNode.subId == "reading") {
				if(idRefNode.type != Type.TEMPERATURESENSOR) {
					MainClass.CompileErrors.add(new TypeError(idRefNode.columnNumber, idRefNode.lineNumber, 
							idRefNode.type.toString(), Type.TEMPERATURESENSOR.toString()));
				}
			}
		}
	}
	
	@Override
	public void visit(IdNode idNode) {
		if(idNode.dclNode instanceof VarDclNode) {
			VarDclNode vdNode = (VarDclNode) idNode.dclNode;
			idNode.type = vdNode.typeNode.Type;
		}
		else if(idNode.dclNode instanceof CmpDclNode) {
			CmpDclNode cdNode = (CmpDclNode) idNode.dclNode;
			idNode.type = cdNode.advTypeNode.Type;
		}
		else if(idNode.dclNode instanceof ListNode) {
				ListNode lNode = (ListNode) idNode.dclNode;
				idNode.type = lNode.typeNode.Type;
		}
		else if(idNode.dclNode instanceof GroupNode) {
			idNode.type = Type.GROUP;
		}
		// N�r id'et er det formelle parameter
		else if(idNode.dclNode instanceof ParamNode) {
			ParamNode pNode = (ParamNode) idNode.dclNode;
			idNode.type = pNode.typeNode.Type;
		}
	}
	
	@Override
	public void visit(GroupNode node) {
		// Check om id'ernes typer er advTyper
		for(int i = 0; i < node.memberIdNodes.size(); i++) {
			if(!(node.memberIdNodes.get(i).dclNode instanceof CmpDclNode)) {
				
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
		super.visit(node);
		
		if(node.typeNode.Type == Type.GROUP || isAdvType(node.typeNode.Type)) {
			MainClass.CompileErrors.add(new DeclarationError(node.columnNumber, node.lineNumber, node.typeNode.Type.toString(), node.idNode.id));
		}
		if(node.exprNode != null) {
			if(isAList(node.exprNode)) {
				MainClass.CompileErrors.add(new ListError(node.exprNode.columnNumber, node.exprNode.lineNumber));
			}
			if(node.exprNode.type != node.typeNode.Type) {
				MainClass.CompileErrors.add(new AssignmentError(node.columnNumber, node.lineNumber, 
						node.exprNode.type.toString(), node.typeNode.Type.toString()));
			}
		}
	}
	
	public boolean isAdvType(Type type) {
		if(type == Type.LIGHTBULB || type == Type.TEMPERATURESENSOR || type == Type.MOTOR) {
			return true;
		}
		return false;
	}
	
	@Override
	public void visit(ListNode node) {
		super.visit(node);
		if(!node.memberExprNodes.isEmpty()) {
			for(int i = 0; i < node.memberExprNodes.size(); i++) {
				
				if(isAList(node.memberExprNodes.get(i))){
					MainClass.CompileErrors.add(new ListError(node.memberExprNodes.get(i).columnNumber, node.memberExprNodes.get(i).lineNumber));
				}
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
		if(isAList(node.exprNode)){
				MainClass.CompileErrors.add(new ListError(node.columnNumber, node.lineNumber));
		}
		// checker om hver case expr er samme type som det vi switcher p�
		for(int i = 0; i < node.caseListNode.caseStmtNodes.size(); i++) {
			if(isAList(node.caseListNode.caseStmtNodes.get(i).exprNode)) {
				MainClass.CompileErrors.add(new ListError(node.caseListNode.caseStmtNodes.get(i).columnNumber,node.caseListNode.caseStmtNodes.get(i).lineNumber));
			}
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
		if(isAList(expr)) {
			MainClass.CompileErrors.add(new ListError(expr.columnNumber, expr.lineNumber));
		}
		if(expr.type != Type.BOOL) {
			MainClass.CompileErrors.add(new TypeError(
					expr.columnNumber, expr.lineNumber, expr.type.toString(), Type.BOOL.toString()));
		}
	}
	
	private boolean isAList(ExprNode exprNode) {
		if(exprNode instanceof IdRefExprNode) {
			IdRefExprNode idRefExpr = (IdRefExprNode) exprNode;
			if(idRefExpr.idNode.dclNode instanceof ListNode) {
				return true;
			}
			else if(idRefExpr.idNode.dclNode instanceof ParamNode) {
				ParamNode paramNode = (ParamNode) idRefExpr.idNode.dclNode;
				if(paramNode.typeNode.islist) {
					return true;
				}
			}
		}
		return false;
	}
}
