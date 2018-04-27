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
			System.out.println("ALREADY DECLARED YOU BITCH PLZ ADD ERROR");
			//TODO ADD ERROR INSTEAD OR RETURN
		}
		else
		{
			symbolTable.peek().put(node.idNode.id, node);
		}

	}
	//TODO SKAL ÆNDRES DA DER IKKE SKAL TJEKKES FOR OM DEN ER DER MEN OMVENDT
	public void addAssignmentToSymbolTable(AssignmentNode node) {
		if(!symbolTable.peek().containsKey(node.idNode.id))
		{
			System.out.println("NOT DECLARED YOU BITCH PLZ ADD ERROR :" + node.idNode.id);
			//TODO ADD ERROR INSTEAD OR RETURN
		}
		else
		{
			symbolTable.peek().put(node.idNode.id, node);
		}

	}
	public void addParamToSymbolTable(ParamNode node) {
		if(symbolTable.peek().containsKey(node.idNode.id))
		{
			System.out.println("Parameter already exist YOU BITCH PLZ ADD ERROR :" + node.idNode.id);
			//TODO ADD ERROR INSTEAD OR RETURN
		}
		else
		{
			symbolTable.peek().put(node.idNode.id, node);
		}

	}
	
	
	/*FuncMap methods*/
	public void addToFuncMap(FuncNode node) {
		if(functionMap.containsKey(node.idNode.id))
		{
			System.out.println("ALREADY DECLARED YOU BITCH PLZ ADD ERROR");
			//TODO ADD ERROR INSTEAD OR RETURN
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
	public FuncNode getFunctionFromFuncMap(FuncCallNode node) {
		FuncNode func = functionMap.get(node.idNode.id);

		return func;
	}
	
	
}
