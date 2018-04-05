// Generated from Phal.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PhalParser}.
 */
public interface PhalListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PhalParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(PhalParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(PhalParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#include}.
	 * @param ctx the parse tree
	 */
	void enterInclude(PhalParser.IncludeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#include}.
	 * @param ctx the parse tree
	 */
	void exitInclude(PhalParser.IncludeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#setup}.
	 * @param ctx the parse tree
	 */
	void enterSetup(PhalParser.SetupContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#setup}.
	 * @param ctx the parse tree
	 */
	void exitSetup(PhalParser.SetupContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#setupCnt}.
	 * @param ctx the parse tree
	 */
	void enterSetupCnt(PhalParser.SetupCntContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#setupCnt}.
	 * @param ctx the parse tree
	 */
	void exitSetupCnt(PhalParser.SetupCntContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#dcl}.
	 * @param ctx the parse tree
	 */
	void enterDcl(PhalParser.DclContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#dcl}.
	 * @param ctx the parse tree
	 */
	void exitDcl(PhalParser.DclContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#varDcl}.
	 * @param ctx the parse tree
	 */
	void enterVarDcl(PhalParser.VarDclContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#varDcl}.
	 * @param ctx the parse tree
	 */
	void exitVarDcl(PhalParser.VarDclContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(PhalParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(PhalParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#advDataType}.
	 * @param ctx the parse tree
	 */
	void enterAdvDataType(PhalParser.AdvDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#advDataType}.
	 * @param ctx the parse tree
	 */
	void exitAdvDataType(PhalParser.AdvDataTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#cmpDcl}.
	 * @param ctx the parse tree
	 */
	void enterCmpDcl(PhalParser.CmpDclContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#cmpDcl}.
	 * @param ctx the parse tree
	 */
	void exitCmpDcl(PhalParser.CmpDclContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#advType}.
	 * @param ctx the parse tree
	 */
	void enterAdvType(PhalParser.AdvTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#advType}.
	 * @param ctx the parse tree
	 */
	void exitAdvType(PhalParser.AdvTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#group}.
	 * @param ctx the parse tree
	 */
	void enterGroup(PhalParser.GroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#group}.
	 * @param ctx the parse tree
	 */
	void exitGroup(PhalParser.GroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(PhalParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(PhalParser.ListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#listCnt}.
	 * @param ctx the parse tree
	 */
	void enterListCnt(PhalParser.ListCntContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#listCnt}.
	 * @param ctx the parse tree
	 */
	void exitListCnt(PhalParser.ListCntContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(PhalParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(PhalParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#selective}.
	 * @param ctx the parse tree
	 */
	void enterSelective(PhalParser.SelectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#selective}.
	 * @param ctx the parse tree
	 */
	void exitSelective(PhalParser.SelectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#switchStmt}.
	 * @param ctx the parse tree
	 */
	void enterSwitchStmt(PhalParser.SwitchStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#switchStmt}.
	 * @param ctx the parse tree
	 */
	void exitSwitchStmt(PhalParser.SwitchStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#caseList}.
	 * @param ctx the parse tree
	 */
	void enterCaseList(PhalParser.CaseListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#caseList}.
	 * @param ctx the parse tree
	 */
	void exitCaseList(PhalParser.CaseListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#caseStmt}.
	 * @param ctx the parse tree
	 */
	void enterCaseStmt(PhalParser.CaseStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#caseStmt}.
	 * @param ctx the parse tree
	 */
	void exitCaseStmt(PhalParser.CaseStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#defaultCase}.
	 * @param ctx the parse tree
	 */
	void enterDefaultCase(PhalParser.DefaultCaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#defaultCase}.
	 * @param ctx the parse tree
	 */
	void exitDefaultCase(PhalParser.DefaultCaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(PhalParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(PhalParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#iterative}.
	 * @param ctx the parse tree
	 */
	void enterIterative(PhalParser.IterativeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#iterative}.
	 * @param ctx the parse tree
	 */
	void exitIterative(PhalParser.IterativeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#loop}.
	 * @param ctx the parse tree
	 */
	void enterLoop(PhalParser.LoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#loop}.
	 * @param ctx the parse tree
	 */
	void exitLoop(PhalParser.LoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void enterFuncCall(PhalParser.FuncCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void exitFuncCall(PhalParser.FuncCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#call}.
	 * @param ctx the parse tree
	 */
	void enterCall(PhalParser.CallContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#call}.
	 * @param ctx the parse tree
	 */
	void exitCall(PhalParser.CallContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(PhalParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(PhalParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#repeat}.
	 * @param ctx the parse tree
	 */
	void enterRepeat(PhalParser.RepeatContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#repeat}.
	 * @param ctx the parse tree
	 */
	void exitRepeat(PhalParser.RepeatContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#func}.
	 * @param ctx the parse tree
	 */
	void enterFunc(PhalParser.FuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#func}.
	 * @param ctx the parse tree
	 */
	void exitFunc(PhalParser.FuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#funcCnt}.
	 * @param ctx the parse tree
	 */
	void enterFuncCnt(PhalParser.FuncCntContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#funcCnt}.
	 * @param ctx the parse tree
	 */
	void exitFuncCnt(PhalParser.FuncCntContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#rType}.
	 * @param ctx the parse tree
	 */
	void enterRType(PhalParser.RTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#rType}.
	 * @param ctx the parse tree
	 */
	void exitRType(PhalParser.RTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(PhalParser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(PhalParser.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(PhalParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(PhalParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(PhalParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(PhalParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code infixExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInfixExpr(PhalParser.InfixExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code infixExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInfixExpr(PhalParser.InfixExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idRefExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdRefExpr(PhalParser.IdRefExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idRefExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdRefExpr(PhalParser.IdRefExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funcExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFuncExpr(PhalParser.FuncExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFuncExpr(PhalParser.FuncExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(PhalParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(PhalParser.UnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code litNumExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLitNumExpr(PhalParser.LitNumExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code litNumExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLitNumExpr(PhalParser.LitNumExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code litTextExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLitTextExpr(PhalParser.LitTextExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code litTextExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLitTextExpr(PhalParser.LitTextExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code litBoolExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLitBoolExpr(PhalParser.LitBoolExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code litBoolExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLitBoolExpr(PhalParser.LitBoolExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(PhalParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(PhalParser.ParenExprContext ctx);
}