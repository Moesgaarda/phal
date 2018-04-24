import java.util.LinkedList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

import enums.InfixOperator;
import enums.LoopUntilOperator;
import enums.UnaryOperator;
import enums.AssignementOperator;

//TODO tilføj ctx constructor til mange af klasserne så vi det bliver nemmere at diagnotisere programmet
abstract class AstNode {
	public AstNode() {}
	abstract void accept(Visitor v);
	public int lineNumber;
	public int columnNumber; // :-)
	
	@Override 
	public String toString() {
		return "Location: l " + lineNumber + " : c " + columnNumber;
	}
	
	public AstNode(ParserRuleContext ctx) {
		lineNumber = ctx.start.getLine();
		columnNumber = ctx.start.getCharPositionInLine();		
	}
}

class ProgramNode extends AstNode{
	public SetupNode setupNode;
	public RepeatNode repeatNode;
	public List<FuncNode> funcNodes;
	public List<IncludeNode> includeNodes = new LinkedList<>();
	
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
	
	public SetupCntNode(DclNode dclNode) {
		this.dclNode = dclNode;
	}
	public SetupCntNode(StmtNode stmtNode) {
		this.stmtNode = stmtNode;
	}
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

abstract class DclNode extends AstNode{
	abstract void accept(Visitor v);
}

class VarDclNode extends DclNode{
	public IdNode idNode;
	public ExprNode exprNode;
	public TypeNode typeNode;
	
	public VarDclNode(IdNode idNode, TypeNode typeNode) {
		this.idNode = idNode;
		this.typeNode = typeNode;
	}
	
	public VarDclNode(IdNode idNode, ExprNode exprNode, TypeNode typeNode) {
		this.idNode = idNode;
		this.exprNode = exprNode;
		this.typeNode = typeNode;
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class TypeNode extends AstNode{
	public String type;
	public Type Type;
	
	public TypeNode(String type) {
		this.type = type;
	
		switch(this.type) {
		case "number":
			this.Type = Type.NUMBER;
			break;
		case "text":
			this.Type = Type.TEXT;
			break;
		case "bool":
			this.Type = Type.BOOL;
			break;
		case "group":
			this.Type = Type.GROUP;
			break;
		case "list":
			this.Type = Type.LIST;
			break;
		case "lightbulb":
			this.Type = Type.LIGHTBULB;
			break;
		case "motor":
			this.Type = Type.MOTOR;
			break;
		case "temperaturesensor":
			this.Type = Type.TEMPERATURESENSOR;
			break;
		case "none":
			this.Type = Type.NONE;
			break;
		}
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

enum Type{
	NUMBER,
	TEXT,
	BOOL,
	GROUP,
	LIST,
	LIGHTBULB,
	MOTOR,
	TEMPERATURESENSOR,
	NONE
}

abstract class AdvDataTypeNode extends DclNode{
	abstract void accept(Visitor v);
}

class CmpDclNode extends DclNode{
	public AdvTypeNode advTypeNode;
	public IdNode idNode;
	public List<LiteralExprNode> literalExprNodes; 
	
	public CmpDclNode(AdvTypeNode advTypeNode, IdNode idNode, List <LiteralExprNode> literalExprNodes) {
		this.advTypeNode = advTypeNode;
		this.idNode = idNode;
		this.literalExprNodes = literalExprNodes;
	}
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class AdvTypeNode extends AstNode{
	public String type;
	public Type Type;
	
	public AdvTypeNode(String type){
		this.type = type;
	
		switch(this.type) {
		case "lightbulb":
			this.Type = Type.LIGHTBULB;
			break;
		case "motor":
			this.Type = Type.MOTOR;
			break;
		case "temperatureSensor":
			this.Type = Type.TEMPERATURESENSOR;
			break;
		default:
			//TODO maybe add exception
			System.out.println("got an adv type that doesn't exist @ Nodes L 207 : " + type );
		}
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class GroupNode extends AdvDataTypeNode{
	public IdNode idNode;
	public List<IdNode> memberIdNodes;
	
	public GroupNode(IdNode idNode, List<IdNode> memberIdNodes) {
		this.idNode = idNode;
		this.memberIdNodes = memberIdNodes;
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class ListNode extends AdvDataTypeNode{
	public TypeNode typeNode;
	public IdNode idNode;
	public List<ExprNode> memberExprNodes; 
	
	public ListNode(TypeNode typeNode, IdNode idNode, List<ExprNode> memberExprNodes) {
		this.typeNode = typeNode;
		this.idNode = idNode;
		this.memberExprNodes = memberExprNodes;
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

abstract class StmtNode extends AstNode{
	abstract void accept(Visitor v);
}

class WaitNode extends StmtNode{
	public ExprNode exprNode;
	
	public WaitNode(ExprNode exprNode) {
		this.exprNode = exprNode;
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

abstract class SelectiveNode extends StmtNode{	
	abstract void accept(Visitor v);
}

class SwitchStmtNode extends SelectiveNode{
	public ExprNode exprNode;
	public CaseListNode caseListNode;
	
	public SwitchStmtNode(ExprNode exprNode, CaseListNode caseListNode) {
		this.exprNode = exprNode;
		this.caseListNode = caseListNode;
	}
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class CaseListNode extends AstNode{
	public List<CaseStmtNode> caseStmtNodes;
    public DefaultCaseNode defaultCaseNode;
    
    public CaseListNode(List<CaseStmtNode> caseStmtNodes, DefaultCaseNode defaultCaseNode) {
    	this.caseStmtNodes = caseStmtNodes;
    	this.defaultCaseNode = defaultCaseNode;
    }
    
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class CaseStmtNode extends AstNode{
	public ExprNode exprNode;
	public List<StmtNode> stmtNodes;
	
	public CaseStmtNode(ExprNode exprNode, List<StmtNode> stmtNodes) {
		this.exprNode = exprNode;
		this.stmtNodes = stmtNodes;
	}
	// In case of no statements
	public CaseStmtNode(ExprNode exprNode) {
		this.exprNode = exprNode;
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class DefaultCaseNode extends AstNode{
	public List<StmtNode> stmtNodes;
	
	public DefaultCaseNode(List<StmtNode> stmtNodes) {
		this.stmtNodes = stmtNodes;
	}

	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class IfStmtNode extends SelectiveNode{
	public ExprNode ifExprNode;
	public BlockNode ifBlock;
	public List<ElseIfStmtNode> elseIfStmts;
	public BlockNode elseBlock;
	
	public IfStmtNode(ExprNode ifExprNode, BlockNode ifBlock, List<ElseIfStmtNode> elseIfStmts, BlockNode elseBlock) {
		this.ifExprNode = ifExprNode;
		this.ifBlock = ifBlock;
		this.elseIfStmts = elseIfStmts;
		this.elseBlock = elseBlock;
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class BlockNode extends StmtNode
{
	public List<StmtNode> stmtNodes;
	
	public BlockNode(List<StmtNode> stmtNodes)
	{
		this.stmtNodes = stmtNodes;
	}
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
	
}

class ElseIfStmtNode extends SelectiveNode{
	public ExprNode exprNode;
	public BlockNode block;
	
	public ElseIfStmtNode(ExprNode exprNode, BlockNode block) {
		this.exprNode = exprNode;
		this.block = block;
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

abstract class IterativeNode extends StmtNode{
	abstract void accept(Visitor v);
}



class LoopTimesNode extends IterativeNode{
	public ExprNode exprNode;
	public List<StmtNode> stmtNodes;
	
	public LoopTimesNode(ExprNode exprNode, List<StmtNode> stmtNodes) {
		this.exprNode = exprNode;
		this.stmtNodes = stmtNodes;
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class LoopUntilNode extends IterativeNode{
	public ExprNode exprNode;
	public IdNode idNode;
	public LiteralExprNode numberNode;
	public List<StmtNode> stmtNodes;
	public LoopUntilOperator loopOperator;
	
	public LoopUntilNode(ExprNode exprNode, List<StmtNode> stmtNodes, 
			IdNode idNode, LiteralExprNode numberNode, LoopUntilOperator loopOperator) {
		this.exprNode = exprNode;
		this.stmtNodes = stmtNodes;
		this.idNode = idNode;
		this.numberNode = numberNode;
		this.loopOperator = loopOperator;
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class FuncCallNode extends StmtNode{
	public IdNode idNode;
	public CallCntNode callCntNode; // Parameters
	
	public FuncCallNode(IdNode idNode, CallCntNode callCntNode) {
		this.idNode = idNode;
		this.callCntNode = callCntNode;
	}

	public FuncCallNode(IdNode idNode, NoneNode noneNode) {
		this.idNode = idNode;
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class CallCntNode extends AstNode{
	public List<ExprNode> exprNodes;
	
	public CallCntNode(List<ExprNode> exprNodes) {
		this.exprNodes = exprNodes;
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class AssignmentNode extends StmtNode{
	public IdNode idNode;
	public ExprNode exprNode;
	public AssignementOperator assignementOperator;
	
	public AssignmentNode(IdNode idNode, ExprNode exprNode, AssignementOperator assignementOperator) {
		this.idNode = idNode;
		this.exprNode = exprNode;
		this.assignementOperator = assignementOperator;
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class AdvTypeModifierNode extends StmtNode{
	public List<ExprNode> exprNodes;
	public IdNode idNode;
	
	AdvTypeModifierNode(List<ExprNode> exprNodes, IdNode idNode){
		this.exprNodes = exprNodes;
		this.idNode = idNode;
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}	
}

class RepeatNode extends AstNode{
	public List<StmtNode> stmtNodes;
	
	public RepeatNode(List<StmtNode> stmtNodes) {
		this.stmtNodes = stmtNodes;
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
// TODO Har fjernet returnStmtNode da de ligger unde funccnt, hvis problemer opstår overvej at ændre det igen
class FuncNode extends AstNode{
	public IdNode idNode;
	public ParametersNode parametersNode;
	public TypeNode typeNode = null;
	public List<FuncCntNode> funcCntNodes;
	
	// If all is set and returnType is not none
	public FuncNode(IdNode idNode, ParametersNode parametersNode, TypeNode typeNode, 
					List<FuncCntNode> funcCntNodes) {
		this.idNode = idNode;
		this.parametersNode = parametersNode;
		this.typeNode = typeNode;
		this.funcCntNodes = funcCntNodes;
	}
	
	// If returnType is none
	public FuncNode(IdNode idNode, ParametersNode parametersNode, NoneNode noneNode, 
			List<FuncCntNode> funcCntNodes) {
			this.idNode = idNode;
			this.parametersNode = parametersNode;
			this.funcCntNodes = funcCntNodes;
			this.typeNode = new TypeNode("none");
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class FuncCntNode extends AstNode{
	public VarDclNode varDclNode;
	public StmtNode stmtNode;
	
	public FuncCntNode(VarDclNode varDclNode) {
		this.varDclNode = varDclNode;
	}
	
	public FuncCntNode(StmtNode stmtNode) {
		this.stmtNode = stmtNode;
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class ParametersNode extends AstNode{
	public List<ParamNode> paramNodes;
	public NoneNode noneNode;
	
	public ParametersNode(List<ParamNode> paramNodes) {
		this.paramNodes = paramNodes;
	}
	
	public ParametersNode(NoneNode noneNode) {
		this.noneNode = noneNode;
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class ParamNode extends AstNode{
	public TypeNode typeNode;
	public IdNode idNode;
	
	public ParamNode(TypeNode typeNode, IdNode idNode) {
		this.typeNode = typeNode;
		this.idNode = idNode;
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class ReturnStmtNode extends StmtNode{
	public ExprNode exprNode;

	public ReturnStmtNode(ExprNode exprNode) {
		this.exprNode = exprNode;
	}
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

abstract class ExprNode extends AstNode{
	public ExprNode() {}	
	public ExprNode(ParserRuleContext ctx) {
		super(ctx);
	}
	
	public Type type;
	abstract void accept(Visitor v);
}

class IdRefExprNode extends ExprNode{
	public IdNode idNode;
	
	public IdRefExprNode(IdNode idNode) {
		this.idNode = idNode;
	}
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class NoneNode extends AstNode{
	public NoneNode() {}
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class LiteralExprNode extends ExprNode{
	public String literalExprNode; 
	public LiteralExprNode(String literalExprNode, Type type) {
		this.literalExprNode = literalExprNode;
		this.type = type;
	}
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class InfixExprNode extends ExprNode{
	public ExprNode leftExprNode;
	public ExprNode rightExprNode;
	public InfixOperator infixOperator;
	
	public InfixExprNode(ExprNode leftExprNode, InfixOperator infixOp, ExprNode rightExprNode) {
		this.leftExprNode = leftExprNode;
		this.infixOperator = infixOp;
		this.rightExprNode = rightExprNode;
	}
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class UnaryExprNode extends ExprNode{
	public ExprNode exprNode;
	public UnaryOperator unaryOperator;
	public UnaryExprNode(ExprNode exprNode, UnaryOperator unaryOperator) {
		this.exprNode = exprNode;
		this.unaryOperator = unaryOperator;
	}
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class FuncExprNode extends ExprNode{
	public FuncCallNode funcCallNode;
	
	public FuncExprNode(FuncCallNode funcCallNode) {
		this.funcCallNode = funcCallNode;
	}
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class ParensExprNode extends ExprNode{
	public ExprNode exprNode;
	
	public ParensExprNode(ExprNode exprNode) {
		this.exprNode = exprNode;
	}
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class IdNode extends ExprNode{
	public String id;
	public String subId;
	
	public IdNode(String id) {
		this.id = id;
	}
	
	public IdNode(String id, String subId) {
		this.id = id;
		this.subId = subId;
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}
class LiteralAdvancedNode extends ExprNode
{
	public List<ExprNode> exprNodes;
	public IdNode idNode;
	
	public LiteralAdvancedNode(List<ExprNode> exprNodes, IdNode idNode) {
		this.exprNodes = exprNodes;
		this.idNode = idNode;
	}
		
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}