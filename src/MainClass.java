import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class MainClass {
	
	public static boolean parseErrorOcurred = false;
	
	public static List<CompilerError.Error> CompileErrors = new ArrayList<>();
	public static List<CompilerError.Error> CompileWarnings = new ArrayList<>();
	public static String inputFileName = "PhalLangEx4"; //TODO ER KUN TIL TEST FOR CODE GEN, SKAL �NDRES
	public static void main(String args[]) throws Exception
	{
		
		String fileName = "..\\Phal\\src\\PhalLangEx";
        File file = new File(fileName);
        AstNode ast = ASTBuilder(new FileInputStream(file));
		PrettyPrinter pp = new PrettyPrinter();
		pp.visit((ProgramNode)ast);
		SymbolTable ST = new SymbolTable();
		TypeChecking(ast, ST);	
		CodeGeneration cg = new CodeGeneration(ST.getCompInclMap());
		cg.visit((ProgramNode)ast);
	}
	
	public static void TypeChecking(AstNode ast, SymbolTable ST) {

		BindingVisitor bv = new BindingVisitor(ST);
		bv.visit((ProgramNode) ast);
		
		if(!CompileErrors.isEmpty()) {
			PrintErrorsAndExit();
		}
		//TODO Bare til at teste med skal nok f�rst printes n�r compileren har v�ret gennem alle steps
		if(!CompileWarnings.isEmpty()) {
			PrintWarnings();
		}
		
		TypeChecker tc = new TypeChecker(ST);
		tc.visit((ProgramNode) ast);
		
		if(!CompileErrors.isEmpty()) {
			PrintErrorsAndExit();
		}
		if(!CompileWarnings.isEmpty()) {
			PrintWarnings();
		}
	}
	
	public static AstNode ASTBuilder (InputStream is) throws Exception {
		ANTLRInputStream inputStream = new ANTLRInputStream(is);
		PhalLexer lexer = new PhalLexer(inputStream);
		CommonTokenStream ts = new CommonTokenStream(lexer);
		PhalParser parser = new PhalParser(ts);
		parser.addErrorListener(new phalErrorListener());
		PhalParser.ProgramContext cst = parser.program();
		if(parseErrorOcurred) {
			System.exit(0);
		}
		
		BuildAST astBuilder = new BuildAST();
		return astBuilder.visit(cst);
	}
	
	public static void PrintErrorsAndExit() {
		for(CompilerError.Error e : CompileErrors) {
			System.out.println(e);
		}
		System.exit(0);
	}
	public static void PrintWarnings() {
		for(CompilerError.Error e : CompileWarnings) {
			System.out.println(e);
		}
	}
	static class phalErrorListener implements ANTLRErrorListener {
		
		public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s, RecognitionException e) {
			parseErrorOcurred = true;
        }

		@Override
		public void reportAmbiguity(Parser arg0, DFA arg1, int arg2, int arg3, boolean arg4, BitSet arg5,
				ATNConfigSet arg6) {
			
		}

		@Override
		public void reportAttemptingFullContext(Parser arg0, DFA arg1, int arg2, int arg3, BitSet arg4,
				ATNConfigSet arg5) {
			
		}

		@Override
		public void reportContextSensitivity(Parser arg0, DFA arg1, int arg2, int arg3, int arg4, ATNConfigSet arg5) {
			
		}
	}

}
