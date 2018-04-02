// Generated from Phal.g4 by ANTLR 4.4
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PhalParser}.
 */
public interface PhalListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PhalParser#r}.
	 * @param ctx the parse tree
	 */
	void enterR(@NotNull PhalParser.RContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhalParser#r}.
	 * @param ctx the parse tree
	 */
	void exitR(@NotNull PhalParser.RContext ctx);
}