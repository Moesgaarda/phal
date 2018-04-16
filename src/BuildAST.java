import java.util.LinkedList;
import java.util.List;

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
		return new IncludeNode((IdNode) visit(ctx.ID()));
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
		DclNode dclNode = null;
		StmtNode stmtNode = null;
		if(ctx.dcl() != null)
		{
			return new SetupCntNode((DclNode)visit(ctx.dcl()));
		}
		else if(ctx.stmt() != null)
		{
			return new SetupCntNode((StmtNode)visit(ctx.stmt()));
		}
		//TODO exception throw her?
		return null;
	}

	@Override public AstNode visitVarDcl(PhalParser.VarDclContext ctx) 
	{ 
		IdNode idNode = null;
		List<ExprNode> exprNodes = new LinkedList<>();
		TypeNode typeNode;
		
		
	}
	@Override public AstNode visitType(PhalParser.TypeContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitAdvDataType(PhalParser.AdvDataTypeContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitCmpDcl(PhalParser.CmpDclContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitAdvType(PhalParser.AdvTypeContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitGroup(PhalParser.GroupContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitList(PhalParser.ListContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitStmt(PhalParser.StmtContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitWaitStmt(PhalParser.WaitStmtContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitSelective(PhalParser.SelectiveContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitSwitchStmt(PhalParser.SwitchStmtContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitCaseList(PhalParser.CaseListContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitCaseStmt(PhalParser.CaseStmtContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitDefaultCase(PhalParser.DefaultCaseContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitIfStmt(PhalParser.IfStmtContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitIterative(PhalParser.IterativeContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitLoopTimes(PhalParser.LoopTimesContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitLoopUntil(PhalParser.LoopUntilContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitFuncCall(PhalParser.FuncCallContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitCallCnt(PhalParser.CallCntContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitAssignment(PhalParser.AssignmentContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitAdvTypeModifier(PhalParser.AdvTypeModifierContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitRepeat(PhalParser.RepeatContext ctx) 
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitFunc(PhalParser.FuncContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitFuncCnt(PhalParser.FuncCntContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitParameters(PhalParser.ParametersContext ctx) 
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitParam(PhalParser.ParamContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitReturnStmt(PhalParser.ReturnStmtContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitInfixExpr(PhalParser.InfixExprContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitIdRefExpr(PhalParser.IdRefExprContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitFuncExpr(PhalParser.FuncExprContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitUnaryExpr(PhalParser.UnaryExprContext ctx) 
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitLitNumExpr(PhalParser.LitNumExprContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitLitTextExpr(PhalParser.LitTextExprContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitLitBoolExpr(PhalParser.LitBoolExprContext ctx) 
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitParenExpr(PhalParser.ParenExprContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitLitAdvExpr(PhalParser.LitAdvExprContext ctx)  
	{ 
		return visitChildren(ctx);
	}
	@Override public AstNode visitNone(PhalParser.NoneContext ctx) 
	{ 
		return visitChildren(ctx);
	}
}

