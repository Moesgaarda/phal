// Generated from Phal.g4 by ANTLR 4.4
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PhalParser}.
 */
public interface PhalListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PhalParser#varDcl}.
	 * @param ctx the parse tree
	 */
	void enterVarDcl(@NotNull PhalParser.VarDclContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#varDcl}.
	 * @param ctx the parse tree
	 */
	void exitVarDcl(@NotNull PhalParser.VarDclContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#cmpDcl}.
	 * @param ctx the parse tree
	 */
	void enterCmpDcl(@NotNull PhalParser.CmpDclContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#cmpDcl}.
	 * @param ctx the parse tree
	 */
	void exitCmpDcl(@NotNull PhalParser.CmpDclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code litNumExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLitNumExpr(@NotNull PhalParser.LitNumExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code litNumExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLitNumExpr(@NotNull PhalParser.LitNumExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#advType}.
	 * @param ctx the parse tree
	 */
	void enterAdvType(@NotNull PhalParser.AdvTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#advType}.
	 * @param ctx the parse tree
	 */
	void exitAdvType(@NotNull PhalParser.AdvTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(@NotNull PhalParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(@NotNull PhalParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#dcl}.
	 * @param ctx the parse tree
	 */
	void enterDcl(@NotNull PhalParser.DclContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#dcl}.
	 * @param ctx the parse tree
	 */
	void exitDcl(@NotNull PhalParser.DclContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(@NotNull PhalParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(@NotNull PhalParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code litBoolExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLitBoolExpr(@NotNull PhalParser.LitBoolExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code litBoolExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLitBoolExpr(@NotNull PhalParser.LitBoolExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(@NotNull PhalParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(@NotNull PhalParser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#switchStmt}.
	 * @param ctx the parse tree
	 */
	void enterSwitchStmt(@NotNull PhalParser.SwitchStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#switchStmt}.
	 * @param ctx the parse tree
	 */
	void exitSwitchStmt(@NotNull PhalParser.SwitchStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code infixExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInfixExpr(@NotNull PhalParser.InfixExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code infixExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInfixExpr(@NotNull PhalParser.InfixExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(@NotNull PhalParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(@NotNull PhalParser.UnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idRefExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdRefExpr(@NotNull PhalParser.IdRefExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idRefExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdRefExpr(@NotNull PhalParser.IdRefExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(@NotNull PhalParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(@NotNull PhalParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#loop}.
	 * @param ctx the parse tree
	 */
	void enterLoop(@NotNull PhalParser.LoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#loop}.
	 * @param ctx the parse tree
	 */
	void exitLoop(@NotNull PhalParser.LoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#repeat}.
	 * @param ctx the parse tree
	 */
	void enterRepeat(@NotNull PhalParser.RepeatContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#repeat}.
	 * @param ctx the parse tree
	 */
	void exitRepeat(@NotNull PhalParser.RepeatContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#funcCnt}.
	 * @param ctx the parse tree
	 */
	void enterFuncCnt(@NotNull PhalParser.FuncCntContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#funcCnt}.
	 * @param ctx the parse tree
	 */
	void exitFuncCnt(@NotNull PhalParser.FuncCntContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#caseStmt}.
	 * @param ctx the parse tree
	 */
	void enterCaseStmt(@NotNull PhalParser.CaseStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#caseStmt}.
	 * @param ctx the parse tree
	 */
	void exitCaseStmt(@NotNull PhalParser.CaseStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#group}.
	 * @param ctx the parse tree
	 */
	void enterGroup(@NotNull PhalParser.GroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#group}.
	 * @param ctx the parse tree
	 */
	void exitGroup(@NotNull PhalParser.GroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#include}.
	 * @param ctx the parse tree
	 */
	void enterInclude(@NotNull PhalParser.IncludeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#include}.
	 * @param ctx the parse tree
	 */
	void exitInclude(@NotNull PhalParser.IncludeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#rType}.
	 * @param ctx the parse tree
	 */
	void enterRType(@NotNull PhalParser.RTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#rType}.
	 * @param ctx the parse tree
	 */
	void exitRType(@NotNull PhalParser.RTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funcExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFuncExpr(@NotNull PhalParser.FuncExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFuncExpr(@NotNull PhalParser.FuncExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#selective}.
	 * @param ctx the parse tree
	 */
	void enterSelective(@NotNull PhalParser.SelectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#selective}.
	 * @param ctx the parse tree
	 */
	void exitSelective(@NotNull PhalParser.SelectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(@NotNull PhalParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(@NotNull PhalParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code litTextExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLitTextExpr(@NotNull PhalParser.LitTextExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code litTextExpr}
	 * labeled alternative in {@link PhalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLitTextExpr(@NotNull PhalParser.LitTextExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#iterative}.
	 * @param ctx the parse tree
	 */
	void enterIterative(@NotNull PhalParser.IterativeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#iterative}.
	 * @param ctx the parse tree
	 */
	void exitIterative(@NotNull PhalParser.IterativeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(@NotNull PhalParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(@NotNull PhalParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(@NotNull PhalParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(@NotNull PhalParser.ListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#caseList}.
	 * @param ctx the parse tree
	 */
	void enterCaseList(@NotNull PhalParser.CaseListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#caseList}.
	 * @param ctx the parse tree
	 */
	void exitCaseList(@NotNull PhalParser.CaseListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#call}.
	 * @param ctx the parse tree
	 */
	void enterCall(@NotNull PhalParser.CallContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#call}.
	 * @param ctx the parse tree
	 */
	void exitCall(@NotNull PhalParser.CallContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#listCnt}.
	 * @param ctx the parse tree
	 */
	void enterListCnt(@NotNull PhalParser.ListCntContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#listCnt}.
	 * @param ctx the parse tree
	 */
	void exitListCnt(@NotNull PhalParser.ListCntContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(@NotNull PhalParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(@NotNull PhalParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#func}.
	 * @param ctx the parse tree
	 */
	void enterFunc(@NotNull PhalParser.FuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#func}.
	 * @param ctx the parse tree
	 */
	void exitFunc(@NotNull PhalParser.FuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#advDataType}.
	 * @param ctx the parse tree
	 */
	void enterAdvDataType(@NotNull PhalParser.AdvDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#advDataType}.
	 * @param ctx the parse tree
	 */
	void exitAdvDataType(@NotNull PhalParser.AdvDataTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#setup}.
	 * @param ctx the parse tree
	 */
	void enterSetup(@NotNull PhalParser.SetupContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#setup}.
	 * @param ctx the parse tree
	 */
	void exitSetup(@NotNull PhalParser.SetupContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void enterFuncCall(@NotNull PhalParser.FuncCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void exitFuncCall(@NotNull PhalParser.FuncCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#defaultCase}.
	 * @param ctx the parse tree
	 */
	void enterDefaultCase(@NotNull PhalParser.DefaultCaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#defaultCase}.
	 * @param ctx the parse tree
	 */
	void exitDefaultCase(@NotNull PhalParser.DefaultCaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(@NotNull PhalParser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(@NotNull PhalParser.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(@NotNull PhalParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(@NotNull PhalParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhalParser#setupCnt}.
	 * @param ctx the parse tree
	 */
	void enterSetupCnt(@NotNull PhalParser.SetupCntContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#setupCnt}.
	 * @param ctx the parse tree
	 */
	void exitSetupCnt(@NotNull PhalParser.SetupCntContext ctx);
}