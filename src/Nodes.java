


abstract class AstNode {
	abstract void accept(Visitor v);
}

class IncludeNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class SetupNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class SetupCntNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class DclNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class VarDclNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class TypeNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class AdvDataTypeNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class CmpDclNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class AdvTypeNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class GroupNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class ListNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class ListCntNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
abstract class StmtNode extends AstNode{
	abstract void accept(Visitor v);
}
class SelectiveNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class SwitchStmtNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class CaseListNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class CaseStmtNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class DefaultCaseNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class IfStmtNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class IterativeNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class LoopNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class FuncCallNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class CallNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class AssignmentNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class RepeatNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class FuncNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class FuncCntNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class RTypeNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class ParametersNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class ParamNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class ReturnStmtNode extends AstNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
abstract class ExprNode extends AstNode{
	abstract void accept(Visitor v);
}
class IdRefExprNode extends ExprNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class LiteralExprNode extends ExprNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class InfixExprNode extends ExprNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class UnaryExprNode extends ExprNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class FuncExprNode extends ExprNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class ParensExprNode extends ExprNode{
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}


