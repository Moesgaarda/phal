import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


import org.antlr.v4.runtime.tree.TerminalNode;


import org.antlr.v4.runtime.tree.ParseTree;

import enums.AssignementOperator;
import enums.InfixOperator;
import enums.LoopUntilOperator;
import enums.UnaryOperator;


public class BuildAST extends PhalBaseVisitor<AstNode> {
	@Override
	public AstNode visitProgram(PhalParser.ProgramContext ctx) {
		List<IncludeNode> includeNodes = new LinkedList<>();
		SetupNode setupNode = null;
		RepeatNode repeatNode = null;
		List<FuncNode> funcNodes = new LinkedList<>();
		
		for(PhalParser.IncludeContext include: ctx.include()) 
		{
			includeNodes.add((IncludeNode) visit(include));
		}
		if(ctx.setup() != null)
		{
			setupNode = (SetupNode)visit(ctx.setup());
		}
		if(ctx.repeat() != null)
		{
			repeatNode = (RepeatNode)visit(ctx.repeat());
		}
		for(PhalParser.FuncContext func: ctx.func()) 
		{
			funcNodes.add((FuncNode) visit(func));
		}
		return new ProgramNode(includeNodes, setupNode, repeatNode,funcNodes);
		
	}
	@Override public AstNode visitInclude(PhalParser.IncludeContext ctx) 
	{
		return new IncludeNode(new IdNode(ctx.ID().getText()));
	}
	
	@Override public AstNode visitSetup(PhalParser.SetupContext ctx) 
	{ 
		List<SetupCntNode> setupCnts = new LinkedList<>();
		for(PhalParser.SetupCntContext setupCnt: ctx.setupCnt()) 
		{
			setupCnts.add((SetupCntNode) visit(setupCnt));
		}
		return new SetupNode(setupCnts);
	}

	@Override public AstNode visitSetupCnt(PhalParser.SetupCntContext ctx) 
	{ 

		if(ctx.dcl() != null)
		{
			//System.out.println("\n \n" + ctx.dcl().getText());
			return new SetupCntNode((DclNode)visit(ctx.dcl()));
		}
		else if(ctx.stmt() != null)
		{
			
			return new SetupCntNode((StmtNode)visit(ctx.stmt()));
		}
		else
		{
			System.out.println("THIS REALLY SHOULDN*T HAPPEN");
		}
		//TODO exception throw her?
		return null;
	}
	@Override public AstNode visitDcl(PhalParser.DclContext ctx)
	{
		if(ctx.varDcl() != null)
		{
			return visit(ctx.varDcl());
		}
		else if(ctx.cmpDcl() != null)
		{
			return visit(ctx.cmpDcl());
		}
		else if(ctx.advDataType() != null)
		{
			return visit(ctx.advDataType());
		}
		else
		{
			System.out.println("Unknown declaration type, [Building AST]");
			return null;
		}
	}

	@Override public AstNode visitVarDcl(PhalParser.VarDclContext ctx) 
	{ 
		IdNode idNode = new IdNode(ctx.ID().getText());
		TypeNode typeNode = new TypeNode(ctx.type().getText());
		if(ctx.expr() != null)
		{
			ExprNode exprNode = (ExprNode)visit(ctx.expr());
			
			return new VarDclNode(idNode,exprNode, typeNode);
		}
		else
		{
			return new VarDclNode(idNode, typeNode);
		}
	}

	@Override
	public AstNode visitAdvDataType(PhalParser.AdvDataTypeContext ctx)
	{
		if(ctx.group() != null)
		{
			return visit(ctx.group());
		}
		else if(ctx.list() != null)
		{
			return visit(ctx.list());
		}
		else
		{
			System.out.println("Unknown advanced datatype, [Building AST]");
			return null;
		}
	}
	

	@Override 
	public AstNode visitCmpDcl(PhalParser.CmpDclContext ctx)  
	{ 
		AdvTypeNode advTypeNode = new AdvTypeNode(ctx.advType().getText());
		IdNode idNode = new IdNode(ctx.ID().getText());
		List<LiteralExprNode> literalExprNodes = new LinkedList<>();
		

		//TODO If stuff breaks, this might be why
		for(TerminalNode number: ctx.NUMBER())
		{
			literalExprNodes.add(new LiteralExprNode(number.getText(),Type.NUMBER));
		}
		return new CmpDclNode(advTypeNode, idNode, literalExprNodes);

	}
	@Override public AstNode visitGroup(PhalParser.GroupContext ctx)  
	{ 
		IdNode idNode = new IdNode(ctx.ID(0).getText());
		List<IdNode> memberIdNodes = new LinkedList<>();
		
		int count = ctx.ID().size();
		for(int i = 1; i < count; i++) // Starts at 1 since id 0 is the group name
		{
			//TODO might need to check for null
			memberIdNodes.add(new IdNode(ctx.ID(i).getText()));
		}
		return new GroupNode(idNode, memberIdNodes);
	}
	@Override public AstNode visitList(PhalParser.ListContext ctx)  
	{ 
		TypeNode typeNode = new TypeNode(ctx.type().getText());
		IdNode idNode = new IdNode(ctx.ID().getText());
		List<ExprNode> memberExprNodes = new LinkedList<>();
		for(PhalParser.ExprContext expr: ctx.expr()) 
		{
			memberExprNodes.add((ExprNode)visit(expr));
		}
	return new ListNode(typeNode, idNode, memberExprNodes);
	}

	@Override
	public AstNode visitStmt(PhalParser.StmtContext ctx) 
	{
		if(ctx.selective() != null)
		{
			return visit(ctx.selective());
		}
		else if(ctx.iterative() != null)
		{
			return visit(ctx.iterative());
		}
		else if(ctx.funcCall() != null) 
		{
			return visit(ctx.funcCall());
		}
		else if(ctx.assignment() != null) 
		{
			return visit(ctx.assignment());
		}
		else if(ctx.returnStmt() != null) 
		{
			return visit(ctx.returnStmt());
		}
		else if(ctx.waitStmt() != null) 
		{
			return visit(ctx.waitStmt());
		}
		else if(ctx.advTypeModifier() != null) 
		{
			return visit(ctx.advTypeModifier());
		}
		else
		{
			System.out.println("Stmt was of no known type. [Building AST]");
			return null;
		}
	}

	@Override public AstNode visitWaitStmt(PhalParser.WaitStmtContext ctx)  
	{ 
		ExprNode exprNode = (ExprNode) visit(ctx.expr());
		return new WaitNode(exprNode);
	}
	@Override public AstNode visitSelective(PhalParser.SelectiveContext ctx)
	{
		if(ctx.ifStmt() != null)
		{
			return visit(ctx.ifStmt());
		}
		else if(ctx.switchStmt() != null)
		{
			return visit(ctx.switchStmt());
		}
		else
		{
			System.out.println("Unkown selective type, [Building AST]");
			return null;
		}
	}
	@Override public AstNode visitSwitchStmt(PhalParser.SwitchStmtContext ctx)  
	{ 
		ExprNode exprNode = (ExprNode)visit(ctx.expr());
		CaseListNode caseListNode = (CaseListNode) visit(ctx.caseList());
		return new SwitchStmtNode(exprNode, caseListNode);
		
	}
	@Override public AstNode visitCaseList(PhalParser.CaseListContext ctx)  
	{ 
		DefaultCaseNode defaultCaseNode = (DefaultCaseNode) visit(ctx.defaultCase());
		List<CaseStmtNode> caseStmtNodes = new LinkedList<>();
		for(PhalParser.CaseStmtContext caseStmt: ctx.caseStmt())
		{
			caseStmtNodes.add((CaseStmtNode) visit(caseStmt));
		}
		return new CaseListNode(caseStmtNodes, defaultCaseNode);
	}
	@Override public AstNode visitCaseStmt(PhalParser.CaseStmtContext ctx)  
	{ 
		ExprNode exprNode = (ExprNode) visit(ctx.expr());
		List<StmtNode> stmtNodes = new LinkedList<>();
		if(ctx.stmt() != null)
		{
			for(PhalParser.StmtContext caseStmt: ctx.stmt())
			{
				stmtNodes.add((StmtNode) visit(caseStmt));
			}
		}
		return new CaseStmtNode(exprNode, stmtNodes);
	}
	@Override public AstNode visitDefaultCase(PhalParser.DefaultCaseContext ctx)  
	{ 
		List<StmtNode> stmtNodes = new LinkedList<>();
		if(ctx.stmt() != null)
		{
			for(PhalParser.StmtContext stmt: ctx.stmt())
			{
				stmtNodes.add((StmtNode) visit(stmt));
			}
		}
		return new DefaultCaseNode(stmtNodes);
	}
	@Override public AstNode visitIfStmt(PhalParser.IfStmtContext ctx)  
	{ 
		// Assigns the if expr
		ExprNode exprNode = (ExprNode) visit(ctx.expr(0));
		// Gets the if block
		BlockNode ifBlockNode = (BlockNode) visit(ctx.block(0));
		
		List<ElseIfStmtNode> elifNodes = new LinkedList<>();
		ElseBlockNode elseBlockNode = (ElseBlockNode) visit(ctx.elseBlock());
		// gets the blocks 
		//TODO HVAD VIS DER IKKE ER NOGEN ELSE?!
		int count = ctx.block().size();
		for(int i = 1; i < count;i++)
		{

			BlockNode bn = (BlockNode)visit(ctx.block(i));
			ExprNode en  = (ExprNode)visit(ctx.expr(i));
			elifNodes.add(new ElseIfStmtNode(en,bn));

			
		}
		
		
		
		
		return new IfStmtNode(exprNode, ifBlockNode, elifNodes,elseBlockNode);
	}
	@Override public AstNode visitBlock(PhalParser.BlockContext ctx)
	{
		List<StmtNode> stmtNodes = new LinkedList<>();
		if(ctx.stmt() != null)
		{
			for(PhalParser.StmtContext stmt: ctx.stmt())
			{
				stmtNodes.add((StmtNode)visit(stmt));
			}
		}
		return new BlockNode(stmtNodes);
	}
	@Override public AstNode visitElseBlock(PhalParser.ElseBlockContext ctx)
	{
		List<StmtNode> stmtNodes = new LinkedList<>();
		if(ctx.stmt() != null)
		{
			for(PhalParser.StmtContext stmt: ctx.stmt())
			{
				stmtNodes.add((StmtNode)visit(stmt));
			}
		}
		return new ElseBlockNode(stmtNodes);
	}
	
	@Override
	public AstNode visitIterative(PhalParser.IterativeContext ctx)
	{
		if(ctx.loopTimes() != null)
		{
			return visit(ctx.loopTimes());
		}
		else if(ctx.loopUntil() != null)
		{
			return visit(ctx.loopUntil());
		}
		else
		{
			System.out.println("Unkown loop type, [Building AST]");
			return null;
		}
	}
	
	@Override public AstNode visitLoopTimes(PhalParser.LoopTimesContext ctx)  
	{ 
		ExprNode exprNode = (ExprNode) visit(ctx.expr());
		List<StmtNode> stmtNodes = new LinkedList<>();
		if(ctx.stmt() != null)
		{
			for(PhalParser.StmtContext stmt: ctx.stmt())
			{
				stmtNodes.add((StmtNode) visit(stmt));
			}
		}
		return new LoopTimesNode(exprNode, stmtNodes);
	}
	@Override public AstNode visitLoopUntil(PhalParser.LoopUntilContext ctx)  
	{ 
		ExprNode exprNode = (ExprNode)visit(ctx.expr());
		IdNode idNode = new IdNode(ctx.ID().getText());
		LiteralExprNode numberNode = new LiteralExprNode(ctx.NUMBER().getText(),Type.NUMBER);
		List<StmtNode> stmtNodes = new LinkedList<>();
		LoopUntilOperator loopOperator;
		switch(getOperatorSymbol(ctx.children, "increase", "decrease")) {
		case "increase":
			loopOperator = LoopUntilOperator.INCREASE;
			break;
		case "decrease":
			loopOperator =  LoopUntilOperator.DECREASE;
			break;
		default:
			return null;
	}
		
		
		if(ctx.stmt() != null)
		{
			for(PhalParser.StmtContext stmt: ctx.stmt())
			{
				stmtNodes.add((StmtNode) visit(stmt));
			}
		}
		return new LoopUntilNode(exprNode, stmtNodes, idNode, numberNode, loopOperator);
	}
	@Override public AstNode visitFuncCall(PhalParser.FuncCallContext ctx)  
	{ 
		IdNode idNode = new IdNode(ctx.ID().getText());
		CallCntNode callCntNode = null;
		NoneNode noneNode = null;
		if(ctx.callCnt() != null)
		{
			callCntNode = (CallCntNode)visit(ctx.callCnt());
			return new FuncCallNode(idNode, callCntNode);
		}
		else
		{
			noneNode = (NoneNode)visit(ctx.none());
			return new FuncCallNode(idNode, noneNode);
		}
		
	}
	@Override public AstNode visitCallCnt(PhalParser.CallCntContext ctx)  
	{ 
		List<ExprNode> exprNodes = new LinkedList<>();
		
		for(PhalParser.ExprContext expr : ctx.expr()) {
			exprNodes.add((ExprNode)visit(expr));
		}
		
		return new CallCntNode(exprNodes);
	}
	@Override public AstNode visitAssignment(PhalParser.AssignmentContext ctx)  
	{ 
		IdNode idNode = null;
		ExprNode exprNode = (ExprNode)visit(ctx.expr());
		String id = ctx.ID(0).getText();
		AssignementOperator assignementOperator;
		switch(getOperatorSymbol(ctx.children, ":=", "+=", "-=")) {
			case ":=":
				assignementOperator = AssignementOperator.EQUALS;
				break;
			case "+=":
				assignementOperator =  AssignementOperator.PLUSEQUALS;
				break;
			case "-=":
				assignementOperator = AssignementOperator.MINUSEQUALS;
				break;
			default:
				return null;
		}
		
		if(ctx.ID(1) != null) {
			String subId = ctx.ID(1).getText();
			idNode = new IdNode(id, subId);
			return new AssignmentNode(idNode, exprNode, assignementOperator);
		}
		idNode = new IdNode(id);
		return new AssignmentNode(idNode, exprNode,assignementOperator);
	}
	@Override public AstNode visitAdvTypeModifier(PhalParser.AdvTypeModifierContext ctx)  
	{ 
		IdNode idNode = new IdNode(ctx.ID().getText());
		List<ExprNode> exprNodes = new LinkedList<>();
		
		for(PhalParser.ExprContext expr : ctx.expr()) {
			exprNodes.add((ExprNode)visit(expr));
		}
		
		return new AdvTypeModifierNode(exprNodes, idNode);
	}
	@Override public AstNode visitRepeat(PhalParser.RepeatContext ctx) 
	{ 
		List<StmtNode> stmtNodes = new LinkedList<>();
		
		for(PhalParser.StmtContext stmt : ctx.stmt()) {
			stmtNodes.add((StmtNode)visit(stmt));
		}
		
		return new RepeatNode(stmtNodes);
	}
	@Override public AstNode visitFunc(PhalParser.FuncContext ctx)  
	{ 
		IdNode idNode = new IdNode(ctx.ID().getText());
		TypeNode typeNode = null;
		if(ctx.type() != null)
		{
			typeNode = new TypeNode(ctx.type().getText());
		}
		else
		{
			typeNode = new TypeNode("none");
		}
		ParametersNode paramNode = (ParametersNode)visit(ctx.parameters());
		List<FuncCntNode> funcCntNodes = new LinkedList<>();
		if(typeNode.Type == Type.NONE){
			return new FuncNode(idNode, paramNode, new NoneNode(), funcCntNodes);
		}
	
		return new FuncNode(idNode, paramNode, typeNode, funcCntNodes);
	}
	
	
	@Override public AstNode visitFuncCnt(PhalParser.FuncCntContext ctx)  
	{ 
		if(ctx.varDcl() != null)
			return new FuncCntNode((VarDclNode)visit(ctx.varDcl()));
		else
			return new FuncCntNode((StmtNode)visit(ctx.stmt()));
	}
	
	
	@Override public AstNode visitParameters(PhalParser.ParametersContext ctx) 
	{ 
		if(ctx.none() != null)
			return new ParametersNode(new NoneNode());
		else {
			List<ParamNode> paramNodes = new LinkedList<>();
			for(PhalParser.ParamContext param: ctx.param()) {
				paramNodes.add((ParamNode)visit(param));
			}
			return new ParametersNode(paramNodes);
		}
	}
	
	
	@Override public AstNode visitParam(PhalParser.ParamContext ctx)  
	{ 
		TypeNode type = null;
		IdNode idNode = null;

		type = new TypeNode(ctx.type().getText()); 
		idNode = new IdNode(ctx.ID().getText());
			
		return new ParamNode(type, idNode);
	}
	@Override public AstNode visitReturnStmt(PhalParser.ReturnStmtContext ctx)  
	{ 
		ExprNode exprNode = null;
		
		if(ctx.expr() != null) {
			exprNode = (ExprNode)visit(ctx.expr());
		}
		
		return new ReturnStmtNode(exprNode);
	}
	
	
	@Override public AstNode visitInfixExpr(PhalParser.InfixExprContext ctx)  
	{ 
		ExprNode leftExprNode = (ExprNode)visit(ctx.expr(0));
		ExprNode rightExprNode = (ExprNode)visit(ctx.expr(1));
		InfixOperator infixOperator;
		
		switch(getOperatorSymbol(ctx.children, "+", "-", "*", "/", "%", "is not", 
				"!=", "=", "is", "<", "less than", ">", "greater than", "<=", "less than or equal to", 
				">=", "greater than or equal to", "&", "and", "|", "or")) {
		case "+":
			infixOperator =  InfixOperator.PLUS;
			break;
		case "-":
			infixOperator = InfixOperator.MINUS;
			break;
		case "*":
			infixOperator = InfixOperator.MULTIPLY;
			break;
		case "/":
			infixOperator = InfixOperator.DIVISION;
			break;
		case "%":
			infixOperator = InfixOperator.MODULO;
			break;
		case "!=":
		case "is not":	
			infixOperator = InfixOperator.NOTEQUAL;
			break;
		case "=":
		case "is":	
			infixOperator = InfixOperator.EQUAL;
			break;
		case "<":
		case "less than":	
			infixOperator = InfixOperator.LESSTHAN;
			break;
		case ">":
		case "greater than":	
			infixOperator = InfixOperator.GREATERTHAN;
			break;
		case "<=":
		case "less than or equal to":	
			infixOperator = InfixOperator.LESSTHANEQUAL;
			break;
		case ">=":
		case "greater than or equal to":	
			infixOperator = InfixOperator.GREATERTHANEQUAL;
			break;
		case "and":
		case "&":	
			infixOperator = InfixOperator.AND;
			break;
		case "or":
		case "|":	
			infixOperator = InfixOperator.OR;
			break;
		default:
			return null;
	}
		
		return new InfixExprNode(leftExprNode, infixOperator, rightExprNode); 
	}
	@Override public AstNode visitIdRefExpr(PhalParser.IdRefExprContext ctx)  
	{ 
		String id = ctx.ID(0).getText();
		
		if(ctx.ID(1) != null) {
			String subId = ctx.ID(1).getText();
			return new IdNode(id, subId);
		}
		else {
			return new IdNode(id);
		}
			
	}
	@Override public AstNode visitFuncExpr(PhalParser.FuncExprContext ctx)  
	{ 
		FuncCallNode funcCallNode = null;
		
		if(ctx.funcCall() != null) {
			funcCallNode = (FuncCallNode)visit(ctx.funcCall());
		}
		
		return new FuncExprNode(funcCallNode);
	}
	@Override public AstNode visitUnaryExpr(PhalParser.UnaryExprContext ctx) 
	{ 
		ExprNode exprNode = null;
		UnaryOperator unaryOperator = null;
		
		if(ctx.expr() != null) {
			exprNode = (ExprNode)visit(ctx.expr());
		}
		
		switch(getOperatorSymbol(ctx.children, "!", "not", "-")) {
			case "!":
			case "not":
				unaryOperator =  UnaryOperator.NOT;
				break;
			case "-":
				unaryOperator = UnaryOperator.NEGATIVE;
				break;
			default:
				return null;
		}
		
		return new UnaryExprNode(exprNode, unaryOperator);
	}
	@Override public AstNode visitLitNumExpr(PhalParser.LitNumExprContext ctx)  
	{ 
		return new LiteralExprNode(ctx.getText(), Type.NUMBER);
	}
	@Override public AstNode visitLitTextExpr(PhalParser.LitTextExprContext ctx)  
	{ 
		return new LiteralExprNode(ctx.getText(), Type.TEXT);
	}
	@Override public AstNode visitLitBoolExpr(PhalParser.LitBoolExprContext ctx) 
	{ 
		return new LiteralExprNode(ctx.getText(), Type.BOOL);
	}
	@Override public AstNode visitParenExpr(PhalParser.ParenExprContext ctx)  
	{ 
		ExprNode exprNode = null;
		if(ctx.expr() != null)
		{
			exprNode = (ExprNode)visit(ctx.expr());
		}
		return new ParensExprNode(exprNode);
	}
	@Override public AstNode visitLitAdvExpr(PhalParser.LitAdvExprContext ctx)  
	{ 
		List<ExprNode> exprNodes = new LinkedList<>();
		IdNode idNode = new IdNode(ctx.ID().getText());
		
		for(PhalParser.ExprContext exp: ctx.expr()) {
			exprNodes.add((ExprNode)visit(exp));
		}
		
		return new LiteralAdvancedNode(exprNodes, idNode);
	}
	@Override public AstNode visitNone(PhalParser.NoneContext ctx) 
	{ 
		return new NoneNode();
	}
	
	private String getOperatorSymbol(List<ParseTree> children, String... symbols) {
		String result = "";
		for(ParseTree child : children) {
			String current = child.getText();
			if(Arrays.asList(symbols).contains(current)) {
				result = current;
			}
		}
		
		return result;
	}
}

