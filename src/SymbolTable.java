import java.util.HashMap;
import CompilerError.*;
import java.util.Stack;

public class SymbolTable {

	private HashMap<String, FuncNode> functionMap = new HashMap<>();
	private Stack<HashMap<String, AstNode>> symbolTable = new Stack<>();
	
	/*ST methods*/
	public void openScope() {
		this.symbolTable.push(new HashMap<String, AstNode>());
	}
	public void closeScope() {
		this.symbolTable.pop();
	}
	public void addDeclarationToSymbolTable(DclNode node) {
		if(symbolTable.peek().containsKey(node.idNode.id))
		{
			MainClass.CompileErrors.add(new RedeclarationError(
					node.columnNumber, node.lineNumber, node.idNode.id));
		}
		else
		{
			symbolTable.peek().put(node.idNode.id, node);
		}

	}

	public void addAssignmentToSymbolTable(AssignmentNode node) {
		if(!symbolTable.peek().containsKey(node.idNode.id))
		{
			MainClass.CompileErrors.add(new NotDeclaredError(
					node.columnNumber, node.lineNumber, node.idNode.id));

		}
		else
		{
			symbolTable.peek().put(node.idNode.id, node);
		}

	}
	public void addParamToSymbolTable(ParamNode node) {
		if(symbolTable.peek().containsKey(node.idNode.id))
		{
			MainClass.CompileErrors.add(new RedeclarationError(
					node.columnNumber, node.lineNumber, node.idNode.id));

		}
		else
		{
			symbolTable.peek().put(node.idNode.id, node);
		}

	}
	public void getEntryInSymbolTable(String key) {
		
	}
	
	
	/*FuncMap methods*/
	public void addToFuncMap(FuncNode node) {
		if(functionMap.containsKey(node.idNode.id))
		{
			MainClass.CompileErrors.add(new RedeclarationError(
					node.columnNumber, node.lineNumber, node.idNode.id));
		}
		else
		{
			functionMap.put(node.idNode.id, node);
		}

	}

	public FuncNode getFunctionFromFuncMap(FuncCallNode node) {
		FuncNode func = functionMap.get(node.idNode.id);

		return func;
	}
	
	
}
