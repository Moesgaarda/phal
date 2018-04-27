import CompilerError.*;

public class BindingVisitor extends Visitor {
	public SymbolTable ST = new SymbolTable();
	
	public BindingVisitor() {
		ST.openScope();
	}
	@Override
	public void visit(ProgramNode node)
	{
		/*Creates FunctionMap*/
		if(node.funcNodes != null)
		{
			for(FuncNode func: node.funcNodes)
			{
				ST.addToFuncMap(func);
			}
		}
		/*Creates ST*/
		node.setupNode.accept(this);
		node.repeatNode.accept(this);
		if(node.funcNodes != null)
		{
			for(FuncNode func: node.funcNodes) {
				func.accept(this);
			}
		}
	}
	
	
	@Override
	public void visit(FuncNode node)
	{
		ST.openScope();
		node.parametersNode.accept(this); 
		if(node.funcCntNodes != null)
		{
			for(FuncCntNode funcCnt: node.funcCntNodes)
			{
				funcCnt.accept(this);
			}
		}
		ST.closeScope();
	}
	@Override
	public void visit(ParamNode node)
	{
		ST.addParamToSymbolTable(node);
	}
	@Override
	public void visit(VarDclNode node)
	{
		ST.addDeclarationToSymbolTable(node);
	}
	@Override
	public void visit(CmpDclNode node)
	{
		ST.addDeclarationToSymbolTable(node);
	}@Override
	public void visit(ListNode node)
	{
		ST.addDeclarationToSymbolTable(node);
	}
	@Override
	public void visit(GroupNode node)
	{
		ST.addDeclarationToSymbolTable(node);
	}
	@Override
	public void visit(FuncCallNode node) {
		FuncNode func = ST.getFunctionFromFuncMap(node);
		//TODO OVERVEJ OM DET SKAL VÆRE HER
		if(func == null) {
			MainClass.CompileErrors.add(
					new FunctionNotDeclaredError( node.columnNumber,node.lineNumber, node.idNode.id)
					);
		}
	}
	
	@Override 
	public void visit(AssignmentNode node)
	{
		ST.addAssignmentToSymbolTable(node);
	}
	
}

