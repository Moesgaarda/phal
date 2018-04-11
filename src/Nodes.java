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
	
	public VarDclNode(IdNode idNode) {
		this.idNode = idNode;
	}
	
	public VarDclNode(IdNode idNode, List<ExprNode> exprNodes) {
		this.idNode = idNode;
		this.exprNodes = exprNodes;
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
	// Denne kan vidst også laves abstract og så lade GroupNode og ListNode arve fra den.
	public GroupNode groupNode;
	public ListNode listNode;
	
	public AdvDataTypeNode(GroupNode groupNode) {
		this.groupNode = groupNode;

	}	
	public AdvDataTypeNode(ListNode listNode) {
		this.listNode = listNode;
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class CmpDclNode extends AstNode{
	public AdvTypeNode advTypeNode;
	public IdNode idNode;
	public List<LiteralExprNode> literalExprNodes; 
	// Dette er et number i CFGen, så getter på det skal være en LiteralExpr?
	
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
	// Denne indeholder kun keywords i CFGen. What to write here?
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class GroupNode extends AstNode{
	public IdNode idNode;
	public List<IdNode> idNodes;
	
	public GroupNode(IdNode idNode, List<IdNode> idNodes) {
		this.idNode = idNode;
		this.idNodes = idNodes;
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class ListNode extends AstNode{
	public TypeNode typeNode;
	public IdNode idNode;
	public List<ListCntNode> listCntNodes;
	
	public ListNode(TypeNode typeNode, IdNode idNode, List<ListCntNode> listCntNodes) {
		this.typeNode = typeNode;
		this.idNode = idNode;
		this.listCntNodes = listCntNodes;
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class ListCntNode extends AstNode{
	public IdNode idNode;
	public LiteralExprNode literalExprNode;
	
	public ListCntNode(IdNode idNode) {
		this.idNode = idNode;
	}
	public ListCntNode(LiteralExprNode literalExprNode) {
		this.literalExprNode = literalExprNode;
	}
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

abstract class StmtNode extends AstNode{
	abstract void accept(Visitor v);
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
	// Hvis der ingen stmts er
	public CaseStmtNode(ExprNode exprNode) {
		this.exprNode = exprNode;
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class DefaultCaseNode extends AstNode{
	public ExprNode exprNode;
	public List<StmtNode> stmtNodes;
	
	public DefaultCaseNode(ExprNode exprNode, List<StmtNode> stmtNodes) {
		this.exprNode = exprNode;
		this.stmtNodes = stmtNodes;
	}
	// Hvis der ingen stmts er bruges denne constructor
	public DefaultCaseNode(ExprNode exprNode) {
		this.exprNode = exprNode;
	}
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class IfStmtNode extends SelectiveNode{
	// Er sgu lidt i tvivl hvordan denne skal laves i forhold til vores CFG??
	// Den her klasse skal højst sandsynligt laves om
	public ExprNode exprNode;
	public List<StmtNode> ifBlockStmtNodes;
	public List<StmtNode> elseBlockStmtNodes;
	public IfStmtNode ifStmtNode;
	
	// Hvis der kun er en if
	public IfStmtNode(ExprNode exprNode, List<StmtNode> ifBlockStmtNodes) {
		this.exprNode = exprNode;
		this.ifBlockStmtNodes = ifBlockStmtNodes;
	}
	
	// Hvis der er en else block
	public IfStmtNode(ExprNode exprNode, List<StmtNode> ifBlockStmtNodes, List<StmtNode> elseBlockStmtNodes) {
		this.exprNode = exprNode;
		this.ifBlockStmtNodes = ifBlockStmtNodes;
		this.elseBlockStmtNodes = elseBlockStmtNodes;
	}
	
	// Hvis det er en nested if-else
	public IfStmtNode(ExprNode exprNode, List<StmtNode> ifBlockStmtNodes, 
							List<StmtNode> elseBlockStmtNodes, IfStmtNode ifStmtNode) {
		this.exprNode = exprNode;
		this.ifBlockStmtNodes = ifBlockStmtNodes;
		this.elseBlockStmtNodes = elseBlockStmtNodes;
		this.ifStmtNode = ifStmtNode;
	}
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class IterativeNode extends StmtNode{
	// Denne node virker lidt ligegyldig. Skal evt. ændres hvis det ændres i CFG'en.
	public LoopNode loopNode;
	
	public IterativeNode(LoopNode loopNode) {
		this.loopNode = loopNode;
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class LoopNode extends AstNode{
	// De to loops skal måske deles op i CFGen så vi kan lave forskellige nodes til dem.
	public ExprNode exprNode;
	public List<StmtNode> stmtNodes;
	
	public LoopNode(ExprNode exprNode, List<StmtNode> stmtNodes) {
		this.exprNode = exprNode;
		this.stmtNodes = stmtNodes;
	}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class FuncCallNode extends StmtNode{
	public IdNode idNode;
	public List<CallCntNode> callCntNodes; //Parametre
	
	public FuncCallNode(IdNode idNode, List<CallCntNode> callCntNodes) {
		this.idNode = idNode;
		this.callCntNodes = callCntNodes;
	}
	
	//Hvis der ingen parametre er
	public FuncCallNode(IdNode idNode) {
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
	
	public AssignmentNode(IdNode idNode, ExprNode exprNode) {
		this.idNode = idNode;
		this.exprNode = exprNode;
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

class FuncNode extends AstNode{
	public IdNode idNode;
	public ParametersNode parametersNode;
	public RTypeNode rTypeNode;
	public List<FuncCntNode> funcCntNodes;
	public ReturnStmtNode returnStmtNode;
	
	public FuncNode(IdNode idNode, ParametersNode parametersNode, RTypeNode rTypeNode, 
					List<FuncCntNode> funcCntNodes, ReturnStmtNode returnStmtNode) {
		this.idNode = idNode;
		this.parametersNode = parametersNode;
		this.rTypeNode = rTypeNode;
		this.funcCntNodes = funcCntNodes;
		this.returnStmtNode = returnStmtNode;
	}
	
	// Evt. Constructors til hvis der ingen retur stmt er eller ingen parametre er.
	public FuncNode(IdNode idNode, RTypeNode rTypeNode, 
					List<FuncCntNode> funcCntNodes, ReturnStmtNode returnStmtNode) {
		this.idNode = idNode;
		this.rTypeNode = rTypeNode;
		this.funcCntNodes = funcCntNodes;
		this.returnStmtNode = returnStmtNode;
	}
	public FuncNode(IdNode idNode, ParametersNode parametersNode, RTypeNode rTypeNode, 
					List<FuncCntNode> funcCntNodes) {
		this.idNode = idNode;
		this.parametersNode = parametersNode;
		this.rTypeNode = rTypeNode;
		this.funcCntNodes = funcCntNodes;
	}	
	public FuncNode(IdNode idNode, RTypeNode rTypeNode, List<FuncCntNode> funcCntNodes) {
		this.idNode = idNode;
		this.rTypeNode = rTypeNode;
		this.funcCntNodes = funcCntNodes;
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

class RTypeNode extends AstNode{
	public TypeNode typeNode;
	public AdvDataTypeNode advDataTypeNode;
	
	public RTypeNode(TypeNode typeNode) {
		this.typeNode = typeNode;
	}
	public RTypeNode(AdvDataTypeNode advDataTypeNode) {
		this.advDataTypeNode = advDataTypeNode;
	}
	public RTypeNode() {}
	
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class ParametersNode extends AstNode{
	public List<ParamNode> paramNodes;
	
	public ParametersNode(List<ParamNode> paramNodes) {
		this.paramNodes = paramNodes;
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
class LiteralExprNode extends ExprNode{
	public String literalExprNode; 
	// Kan være en TEXT, NUMBER eller BOOL på streng-form.
	// Der skal sikkert en type med på en eller anden måde.
	public LiteralExprNode(String literalExprNode) {
		this.literalExprNode = literalExprNode;
	}
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class InfixExprNode extends ExprNode{
	public ExprNode leftExprNode;
	public ExprNode rightExprNode;
	// Der skal muligvis operator med her, så der kan skelnes mellem hvilken InfixExpr det er.
	
	public InfixExprNode(ExprNode leftExprNode, ExprNode rightExprNode) {
		this.leftExprNode = leftExprNode;
		this.rightExprNode = rightExprNode;
	}
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}

class UnaryExprNode extends ExprNode{
	public ExprNode exprNode;
	// Der skal muligvis operator med her, så der kan skelnes mellem hvilken UnaryExpr det er.
	public UnaryExprNode(ExprNode exprNode) {
		this.exprNode = exprNode;
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
	
	public IdNode(String id) {
		this.id = id;
	}
	@Override
	void accept(Visitor v) {
		v.visit(this);
	}
}


