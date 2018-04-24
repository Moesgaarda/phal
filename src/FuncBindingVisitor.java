import java.util.HashMap;
import java.util.Stack;

public class FuncBindingVisitor extends Visitor {

	private Stack<HashMap<String, AstNode>> symbolTable;
	public FuncBindingVisitor() {
		symbolTable = new Stack<HashMap<String, AstNode>>();
		symbolTable.push(new HashMap<String, AstNode>());
	}
	@Override
	public void visit(ProgramNode node) {
		
	}
}
