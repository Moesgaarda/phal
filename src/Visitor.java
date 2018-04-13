
public abstract class Visitor {
	public void visit(ProgramNode node) {
		if(node.includeNodes != null) 
		{
			for(IncludeNode include: node.includeNodes) 
			{
				include.accept(this);
			}
		}
		node.setupNode.accept(this);
		node.repeatNode.accept(this);
		if(node.funcNodes != null) 
		{
			for(FuncNode func: node.funcNodes) 
			{
				func.accept(this);
			}
		}
	}
	
	public void visit(IncludeNode node) {
		node.idNode.accept(this);
	}
	public void visit(SetupNode node) {
		if(node.setupCntNodes != null) 
		{
			for(SetupCntNode setup: node.setupCntNodes) 
			{
				setup.accept(this);
			}
		}
	}
	public void visit(SetupCntNode node) {
		if(node.dclNode != null) 
		{
			node.dclNode.accept(this);
			
		}
		else if (node.stmtNode != null)
		{
			node.stmtNode.accept(this);
		} 
			
	}
	public void visit(DclNode node) {
		node.accept(this);
	}
	public void visit(VarDclNode node) {
		node.idNode.accept(this);
		node.typeNode.accept(this);
		if(node.exprNodes != null)
		{
			for(ExprNode expr: node.exprNodes)
			{
				expr.accept(this);
			}
		}
	}
	public void visit(TypeNode node) {
	}
	public void visit(AdvDataTypeNode node) {
		node.accept(this);
	}
	public void visit(CmpDclNode node) {
		node.advTypeNode.accept(this);
		node.idNode.accept(this);
		for(LiteralExprNode num: node.literalExprNodes)
		{
			num.accept(this);
		}
	}
	public void visit(AdvTypeNode node) {
	}
	public void visit(GroupNode node) {
		node.idNode.accept(this);
		for(IdNode member: node.memberIdNodes) 
		{
			member.accept(this);
		}
	}
	public void visit(ListNode node) {
		node.idNode.accept(this);
		node.typeNode.accept(this);
		if(node.memberExprNodes != null)
		{
			for(ExprNode expr: node.memberExprNodes)
			{
				expr.accept(this);
			}
		}
	}
	public void visit(StmtNode node) {
		node.accept(this);
	}
	public void visit(WaitNode node) {
		node.exprNode.accept(this);
	}
	
	public void visit(SelectiveNode node) {
		node.accept(this);
	}
	public void visit(SwitchStmtNode node) {
		node.exprNode.accept(this);
		node.caseListNode.accept(this);
	}
	public void visit(CaseListNode node) {
		for(CaseStmtNode caseStmt: node.caseStmtNodes)
		{
			caseStmt.accept(this);
		}
		node.defaultCaseNode.accept(this);
	}
	public void visit(CaseStmtNode node) {
		node.exprNode.accept(this);
		if(node.stmtNodes != null)
		{
			for(StmtNode stmt : node.stmtNodes)
			{
				stmt.accept(this);
			}
		}
	}
	public void visit(DefaultCaseNode node) {
		if(node.stmtNodes != null)
		{
			for(StmtNode stmt : node.stmtNodes)
			{
				stmt.accept(this);
			}
		}
	}
	public void visit(IfStmtNode node) {
		node.ifNode.accept(this);
		if(node.ifStmtNodes != null)
		{
			for(StmtNode stmt : node.ifStmtNodes)
			{
				stmt.accept(this);
			}
		}
		if(node.elifNodes != null)
		{
			for(ElseIfStmtNode elseIfStmt : node.elifNodes)
			{
				elseIfStmt.accept(this);
			}
		}
		if(node.elseStmtNodes != null)
		{
			for(StmtNode elseStmt : node.elseStmtNodes)
			{
				elseStmt.accept(this);
			}
		}
	}
	public void visit(ElseIfStmtNode node) {
		node.exprNode.accept(this);
		if(node.stmtNodes != null)
		{
			for(StmtNode stmt : node.stmtNodes)
			{
				stmt.accept(this);
			}
		}
	}
	public void visit(IterativeNode node) {
		node.accept(this);
	}
	public void visit(LoopTimesNode node) {
		node.exprNode.accept(this);
		if(node.stmtNodes != null)
		{
			for(StmtNode stmt : node.stmtNodes)
			{
				stmt.accept(this);
			}
		}
	}
	public void visit(LoopUntilNode node) {
		node.exprNode.accept(this);
		if(node.idNode != null && node.numberNode != null) {
			node.idNode.accept(this);
			node.numberNode.accept(this);
		}
		if(node.stmtNodes != null)
		{
			for(StmtNode stmt : node.stmtNodes)
			{
				stmt.accept(this);
			}
		}
		
		
	}
	public void visit(FuncCallNode node) {
		node.idNode.accept(this);
		node.callCntNode.accept(this);
	}
	public void visit(CallCntNode node) {
		for(ExprNode expr: node.exprNodes)
		{
			expr.accept(this);
		}
	}
	public void visit(AssignmentNode node) {
		node.idNode.accept(this);
		if(node.subIdNode != null)
		{
			node.subIdNode.accept(this);
		}
		node.exprNode.accept(this);
	}
	public void visit(AdvTypeModifierNode node) {
		node.idNode.accept(this);
		for(ExprNode expr: node.exprNodes)
		{
			expr.accept(this);
		}
		
	}
	public void visit(RepeatNode node) {
		if(node.stmtNodes != null) {
			for(StmtNode stmt : node.stmtNodes)
			{
				stmt.accept(this);
			}
		}
	}
	public void visit(FuncNode node) {
		node.idNode.accept(this);
		node.parametersNode.accept(this);
		node.typeNode.accept(this);
		node.returnStmtNode.accept(this);
		if(node.funcCntNodes != null)
		{
			for(FuncCntNode funcCnt : node.funcCntNodes) 
			{
				funcCnt.accept(this);
			}
		}
	}
	public void visit(FuncCntNode node) {
		node.varDclNode.accept(this);
		node.stmtNode.accept(this);
	}
	public void visit(ParametersNode node) {
		node.noneNode.accept(this);
		for(ParamNode param: node.paramNodes)
		{
			param.accept(this);			
		}
	}
	public void visit(ParamNode node) {
		node.typeNode.accept(this);
		node.idNode.accept(this);
	}
	public void visit(ReturnStmtNode node) {
		node.exprNode.accept(this);
	}
	public void visit(ExprNode node) {
		node.accept(this);
	}
	public void visit(IdRefExprNode node) {
		node.idNode.accept(this);
	}
	public void visit(NoneNode node) {
		node.accept(this);
	}
	public void visit(LiteralExprNode node) {
	}
	public void visit(InfixExprNode node) {
		node.leftExprNode.accept(this);
		node.rightExprNode.accept(this);
	}
	public void visit(FuncExprNode node) {
		node.funcCallNode.accept(this);
	}
	public void visit(ParensExprNode node) {
		node.exprNode.accept(this);
	}
	public void visit(UnaryExprNode node) {
		node.exprNode.accept(this);
	}
	
	public void visit(IdNode node) {
	}
	public void visit(LiteralAdvancedNode node) {
		node.idNode.accept(this);
		for(ExprNode expr: node.exprNodes)
		{
			expr.accept(this);
		}
	}

	
}
