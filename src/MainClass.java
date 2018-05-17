
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.io.*;

public class MainClass {
	
	public static boolean parseErrorOcurred = false;
	
	public static List<CompilerError.Error> CompileErrors = new ArrayList<>();
	public static List<CompilerError.Error> CompileWarnings = new ArrayList<>();
	public static String inputFileName;
	public static void main(String args[]) throws Exception
	{
		inputFileName = args[0];
		String comPort = args[1];
        File file = new File(inputFileName);
        AstNode ast = ASTBuilder(new FileInputStream(file));
//		PrettyPrinter pp = new PrettyPrinter();
//		pp.visit((ProgramNode)ast);
		SymbolTable ST = new SymbolTable();
		TypeChecking(ast, ST);	
		CodeGeneration cg = new CodeGeneration(ST.getCompInclMap());
		cg.visit((ProgramNode)ast);

		System.console().printf("Verifying Arduino code\n");

		String execstring = "arduino_debug --upload " + inputFileName + ".ino --port " + comPort;
		System.console().printf(execstring + "\n");
		Process compile = Runtime.getRuntime().exec(execstring );
		compile.waitFor();
		StringBuffer output = new StringBuffer();
		BufferedReader stdError = new BufferedReader(new
				InputStreamReader(compile.getErrorStream()));


		String line = "";
		while ((line = stdError.readLine())!= null) {
			output.append(line + "\n");
		}

		System.console().printf(output.toString());


		switch(compile.exitValue()){
			case 0:
				System.console().printf("\n\nUpload successful.\n");
				break;
			case 1:
				System.console().printf("\n\nBuild or upload failed.\n");
				break;
			case 2:
				System.console().printf("\n\nSketch not found.\n");
				break;
			case 3:
				System.console().printf("\n\nInvalid arguments.\n");
				break;
			case 4:
				System.console().printf("\n\nPreference for --get-pref does not exist.\n");
				break;
		}



	}
	
	public static void TypeChecking(AstNode ast, SymbolTable ST) {

		BindingVisitor bv = new BindingVisitor(ST);
		bv.visit((ProgramNode) ast);
		
		if(!CompileErrors.isEmpty()) {
			PrintErrorsAndExit();
		}
		
		TypeChecker tc = new TypeChecker(ST);
		tc.visit((ProgramNode) ast);
		
		if(!CompileErrors.isEmpty()) {
			PrintErrorsAndExit();
		}

		NumberVisitor nv = new NumberVisitor();
		nv.visit((ProgramNode) ast);

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