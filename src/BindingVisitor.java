import java.util.HashMap;
import java.util.Stack;

public class BindingVisitor extends Visitor {
	public BindingVisitor() {
		symbolTable = new Stack<>();
		symbolTable.push(new HashMap<>());
	}
	private Stack<HashMap<String, AstNode>> symbolTable; 
	
	private void openScope() {
		symbolTable.push(new HashMap<>());
	}
	private void closeScope() {
		symbolTable.pop();
	}
	private void bindIdNodeToDeclaration(IdNode idNode) {
		
		for(int i = symbolTable.size() - 1; i >= 0; i--)
		{
			if(symbolTable.get(i).containsKey(idNode.id)) {
				
			}
		}
	}
}

