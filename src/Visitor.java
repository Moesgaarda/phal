
public abstract class Visitor {
	public void visit(ProgramNode node) {
		node.accept(this);
	}
	public void visit(IncludeNode node) {
		node.accept(this);
	}
	public void visit(SetupNode node) {
		node.accept(this);
	}
	public void visit(SetupCntNode node) {
		node.accept(this);
	}
	public void visit(DclNode node) {
		node.accept(this);
	}
	public void visit(VarDclNode node) {
		node.accept(this);
	}
	public void visit(TypeNode node) {
		node.accept(this);
	}
	public void visit(AdvDataTypeNode node) {
		node.accept(this);
	}
	public void visit(CmpDclNode node) {
		node.accept(this);
	}
	public void visit(AdvTypeNode node) {
		node.accept(this);
	}
	public void visit(GroupNode node) {
		node.accept(this);
	}
	public void visit(ListNode node) {
		node.accept(this);
	}
	public void visit(ListCntNode node) {
		node.accept(this);
	}

	public void visit(SelectiveNode node) {
		node.accept(this);
	}
	public void visit(SwitchStmtNode node) {
		node.accept(this);
	}
	public void visit(CaseListNode node) {
		node.accept(this);
	}
	public void visit(CaseStmtNode node) {
		node.accept(this);
	}
	public void visit(DefaultCaseNode node) {
		node.accept(this);
	}
	public void visit(IfStmtNode node) {
		node.accept(this);
	}
	public void visit(IterativeNode node) {
		node.accept(this);
	}
	public void visit(LoopNode node) {
		node.accept(this);
	}
	public void visit(FuncCallNode node) {
		node.accept(this);
	}
	public void visit(CallCntNode node) {
		node.accept(this);
	}
	public void visit(AssignmentNode node) {
		node.accept(this);
	}
	public void visit(RepeatNode node) {
		node.accept(this);
	}
	public void visit(FuncNode node) {
		node.accept(this);
	}
	public void visit(FuncCntNode node) {
		node.accept(this);
	}
	public void visit(RTypeNode node) {
		node.accept(this);
	}
	public void visit(ParametersNode node) {
		node.accept(this);
	}
	public void visit(ParamNode node) {
		node.accept(this);
	}
	public void visit(ReturnStmtNode node) {
		node.accept(this);
	}
	public void visit(IdRefExprNode node) {
		node.accept(this);
	}
	public void visit(LiteralExprNode node) {
		node.accept(this);
	}
	public void visit(InfixExprNode node) {
		node.accept(this);
	}
	public void visit(UnaryExprNode node) {
		node.accept(this);
	}
	public void visit(ParensExprNode node) {
		node.accept(this);
	}
	public void visit(FuncExprNode node) {
		node.accept(this);
	}
	public void visit(IdNode node) {
		node.accept(this);
	}
	
	
}
