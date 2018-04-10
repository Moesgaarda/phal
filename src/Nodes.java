import java.util.List;

abstract class AstNode {
	abstract void accept(Visitor v);
}
class ProgramNode extends AstNode{
	public List<IncludeNode> includeNodes;
	public SetupNode setupNode;
	public RepeatNode repeatNode;
	public List<FuncNode> funcNodes;
	
	public ProgramNode(List<IncludeNode> includeNodes, SetupNode setupNode, RepeatNode repeatNode, List<FuncNode> funcNodes) {
		this.includeNodes = includeNodes;
		this.setupNode = setupNode;
		this.repeatNode = repeatNode;
		this.funcNodes = funcNodes;
	}
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class IncludeNode extends AstNode{
	public IdNode idNode;
	
	public IncludeNode(IdNode idNode) {
		this.idNode = idNode;
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class SetupNode extends AstNode{
	public List<SetupCntNode> setupCntNodes;
	
	public SetupNode(List<SetupCntNode> setupCntNodes) {
		this.setupCntNodes = setupCntNodes;
	}
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class SetupCntNode extends AstNode{
	public DclNode dclNode;
	public StmtNode stmtNode;
	
	public SetupCntNode(DclNode dclNode, StmtNode stmtNode) {
		this.dclNode = dclNode;
		this.stmtNode = stmtNode;
	}
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class DclNode extends AstNode{
	public VarDclNode varDclNode;
	public CmpDclNode cmpDclNode;
	public AdvDataTypeNode advDataTypeNode;
	
	public DclNode(VarDclNode varDclNode, CmpDclNode cmpDclNode, AdvDataTypeNode advDataTypeNode) {
		this.varDclNode = varDclNode;
		this.cmpDclNode = cmpDclNode;
		this.advDataTypeNode = advDataTypeNode;
	}
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class VarDclNode extends AstNode{
	public IdNode idNode;
	public List<ExprNode> exprNodes;
	
	public VarDclNode(IdNode idNode, List<ExprNode> exprNodes) {
		
	}
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
class IdNode extends ExprNode{
	public String id;
	
	public IdNode(String id) {
		this.id = id;
	}
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}


