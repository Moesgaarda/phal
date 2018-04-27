import org.antlr.v4.runtime.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainClass {
	public static List<CompilerError.Error> CompileErrors = new ArrayList<>();
	public static List<CompilerError.Error> CompileWarnings = new ArrayList<>();
	public static void main(String args[]) 
	{

		String fileName = "..\\Phal\\src\\PhalLangEx4";
        File file = new File(fileName);
        FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(file);
			ANTLRInputStream inputStream = new ANTLRInputStream(fis);
			PhalLexer lexer = new PhalLexer(inputStream);
			CommonTokenStream ts = new CommonTokenStream(lexer);
			PhalParser parser = new PhalParser(ts);
			PhalParser.ProgramContext cst = parser.program();
			
			System.out.println(cst.toStringTree());
			BuildAST astBuilder = new BuildAST();
			AstNode ast = astBuilder.visit(cst);
			
			PrettyPrinter pp = new PrettyPrinter();
			pp.visit((ProgramNode) astBuilder.visitProgram(cst));
			
			BindingVisitor BV = new BindingVisitor();
			BV.visit((ProgramNode) ast);
			
			int i = 2; 
			//TODO SLET ER KUN TIL TESTING
			if(i==3) {}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	
		
	}
	

}
