import org.antlr.v4.runtime.*;
import java.io.File;
import java.io.FileInputStream;

public class MainClass {

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

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	
		
	}
	

}
