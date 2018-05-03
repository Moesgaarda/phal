import java.util.HashMap;
import CompilerError.*;
import Warnings.*;

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

	public AssignmentNode addAssignmentToSymbolTable(AssignmentNode node) {
		HashMap<String, AstNode> map =  symbolTable.peek();
		String key = node.idNode.id;
		
		if(map.containsKey(key))
		{
			DclNode dcl = (DclNode)map.get(key);
			if(!dcl.isUsed) {
				dcl.isUsed = true;
				map.put(key, dcl);
			}
			node.idNode.dclNode = dcl;

		}
		else
		{
			MainClass.CompileErrors.add(new NotDeclaredError(
					node.columnNumber, node.lineNumber, node.idNode.id));
		}
		return node;
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
	public IdRefExprNode addIdREfToSymbolTable(IdRefExprNode node) {
		HashMap<String, AstNode> map =  symbolTable.peek();
		String key = node.idNode.id;
		
		if(map.containsKey(key))
		{
			DclNode dcl = (DclNode)map.get(key);
			if(!dcl.isUsed) {
				dcl.isUsed = true;
				map.put(key, dcl);
			}
			node.idNode.dclNode = dcl;
		}
		else
		{
			MainClass.CompileErrors.add(new NotDeclaredError(
					node.columnNumber, node.lineNumber, node.idNode.id));
		}
		return node;

	}
	public IdNode addIdToSymbolTable(IdNode node) {
		HashMap<String, AstNode> map =  symbolTable.peek();
		String key = node.id;
		
		if(map.containsKey(key))
		{
			DclNode dcl = (DclNode)map.get(key);
			if(!dcl.isUsed) {
				dcl.isUsed = true;
				map.put(key, dcl);
			}
			node.dclNode = dcl;
		}
		else
		{
			MainClass.CompileErrors.add(new NotDeclaredError(
					node.columnNumber, node.lineNumber, node.id));
		}
		return node;
	}

	public AstNode getEntryInSymbolTable(String key) {
		//TODO Fungere ikke i funktioner DET SKAL FIXES ELLER KAN VI IKKE TYPE CHECKE DEM
		AstNode node = symbolTable.peek().get(key);
		return node;
	}
	
	
	
	/*
	 * Checks top of stack (current scope)
	 * 
	 * */
	public void checkVariablesAreUsed() {
		for(AstNode node : symbolTable.peek().values()) {
			if(node instanceof DclNode) {
				if(!((DclNode) node).isUsed) {
					MainClass.CompileWarnings.add(
							new VarNotUsedWarning(node.columnNumber, node.lineNumber, ((DclNode) node).idNode.id));
				}
			}
		}
		
	}
	
	public void checkFunctionsAreUsed() {
		for(FuncNode node: functionMap.values()) {
			if(!node.isUsed) {
				MainClass.CompileWarnings.add(new FuncNotUsedWarning(node.columnNumber, node.lineNumber, node.idNode.id));
			}
		}
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
		func.isUsed = true;
		return func;
	}
	
	
}
