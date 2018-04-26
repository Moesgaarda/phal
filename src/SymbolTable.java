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
		if(true)
		symbolTable.peek().put(node.idNode.id, node);
	}
	
	/*FuncMap methods*/
	public void addToFuncMap(FuncNode node) {
		functionMap.put(node.idNode.id, node);
		//TODO CHECK HVIS ID ALLEREDE FINDES
	}
	public void removeFromFuncMap(FuncNode node) {
		functionMap.remove(node.idNode.id);
	}
	
	
}
