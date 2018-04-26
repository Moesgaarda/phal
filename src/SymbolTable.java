import java.util.HashMap;
import java.util.Stack;

public class SymbolTable {

	private HashMap<String, AstNode> functionMap = new HashMap<>();
	private Stack<HashMap<String, AstNode>> symbolTable = new Stack<>();
	
	/*ST methods*/
	public void openScope(HashMap<String, AstNode> scopeMap) {
		this.symbolTable.push(scopeMap);
	}
	public void closeScope() {
		this.symbolTable.pop();
	}
	public void addDeclarationToSymbolTable(DclNode node) {
		if(symbolTable.peek().containsKey(node.idNode.id))
		{
			System.out.println("ALREADY DECLARED YOU BITCH PLZ ADD ERROR");
			//TODO ADD ERROR INSTEAD
		}
		else
		{
			symbolTable.peek().put(node.idNode.id, node);
		}

	}
	public bool peek(DclNode node) {
		
	}
	public bool peek(FuncNode node) {
		
	}
	
	
	/*FuncMap methods*/
	public void addToFuncMap(FuncNode node) {
		if(functionMap.containsKey(node.idNode.id))
		{
			System.out.println("ALREADY DECLARED YOU BITCH PLZ ADD ERROR");
			//TODO ADD ERROR INSTEAD
		}
		else
		{
			functionMap.put(node.idNode.id, node);
		}

	}
	public void removeFromFuncMap(FuncNode node) {
		functionMap.remove(node.idNode.id);
		//TODO måske tilføje noget der kigger på om den eksisterede i forvejen?
	}
	
	
}
