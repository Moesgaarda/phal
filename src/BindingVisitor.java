import java.util.HashMap;
import java.util.Stack;


public class BindingVisitor extends Visitor {
	public SymbolTable ST = new SymbolTable();
	
	@Override
	public void visit(ProgramNode node)
	{
		/*Creates FunctionMap*/
		if(node.funcNodes != null)
		{
			for(FuncNode func: node.funcNodes)
			{
				func.accept(this);
			}
		}
	}
	@Override
	public void visit(FuncNode node)
	{
		
	}
	
}

