import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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
			astBuilder.visit(cst);

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	
		
	}
	

}
