import java.util.List;

public class BuildAST extends PhalBaseVisitor {
	@Override
	public AstNode visitProgram(PhalParser.ProgramContext context) {
		List<IncludeNode> includeNodes = null;
		SetupNode setupNode = null;
		RepeatNode repeatNode = null;
		List<FuncNode> funcNodes = null;
		
		
		return null;
		
	}
	@Override public AstNode visitInclude(PhalParser.IncludeContext ctx) 
	{
		return visitChildren(ctx); 
	}
	
	@Override public AstNode visitSetup(PhalParser.SetupContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitSetupCnt(PhalParser.SetupCntContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitDcl(PhalParser.DclContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitVarDcl(PhalParser.VarDclContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitType(PhalParser.TypeContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitAdvDataType(PhalParser.AdvDataTypeContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitCmpDcl(PhalParser.CmpDclContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitAdvType(PhalParser.AdvTypeContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitGroup(PhalParser.GroupContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitList(PhalParser.ListContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitStmt(PhalParser.StmtContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitWaitStmt(PhalParser.WaitStmtContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitSelective(PhalParser.SelectiveContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitSwitchStmt(PhalParser.SwitchStmtContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitCaseList(PhalParser.CaseListContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitCaseStmt(PhalParser.CaseStmtContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitDefaultCase(PhalParser.DefaultCaseContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitIfStmt(PhalParser.IfStmtContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitIterative(PhalParser.IterativeContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitLoopTimes(PhalParser.LoopTimesContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitLoopUntil(PhalParser.LoopUntilContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitFuncCall(PhalParser.FuncCallContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitCallCnt(PhalParser.CallCntContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitAssignment(PhalParser.AssignmentContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitAdvTypeModifier(PhalParser.AdvTypeModifierContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitRepeat(PhalParser.RepeatContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitFunc(PhalParser.FuncContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitFuncCnt(PhalParser.FuncCntContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitParameters(PhalParser.ParametersContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitParam(PhalParser.ParamContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitReturnStmt(PhalParser.ReturnStmtContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitInfixExpr(PhalParser.InfixExprContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitIdRefExpr(PhalParser.IdRefExprContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitFuncExpr(PhalParser.FuncExprContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitUnaryExpr(PhalParser.UnaryExprContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitLitNumExpr(PhalParser.LitNumExprContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitLitTextExpr(PhalParser.LitTextExprContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitLitBoolExpr(PhalParser.LitBoolExprContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitParenExpr(PhalParser.ParenExprContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitLitAdvExpr(PhalParser.LitAdvExprContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns AstNodehe result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public AstNode visitNone(PhalParser.NoneContext ctx) { return visitChildren(ctx); }
}
}
