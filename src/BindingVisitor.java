import CompilerError.*;
//TODO TILFØJ TJEK OM TING BLIVER BRUGT
public class BindingVisitor extends Visitor {
	public SymbolTable ST = null;
	
	public BindingVisitor(SymbolTable symT) {
		ST = symT;
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
		ST.checkVariablesAreUsed();
		
		if(node.funcNodes != null)
		{
			for(FuncNode func: node.funcNodes) {
				func.accept(this);
				//TODO kunne rykkes sammen med løkken her over
			}
		}
		ST.checkFunctionsAreUsed();
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
		ST.checkVariablesAreUsed();
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
		super.visit(node);
	}
	@Override
	public void visit(CmpDclNode node)
	{
		ST.addDeclarationToSymbolTable(node);
		super.visit(node);
	}
	@Override
	public void visit(ListNode node)
	{
		ST.addDeclarationToSymbolTable(node);
		super.visit(node);
	}
	@Override
	public void visit(GroupNode node)
	{
		ST.addDeclarationToSymbolTable(node);
		super.visit(node);
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
		if(node.callCntNode != null)
		{
			node.callCntNode.accept(this);
		}
	}
	//TODO tænl over om det er nødvendigt at have disse metoder nu da vi bare tjekker id
	@Override 
	public void visit(AssignmentNode node)
	{
		ST.addAssignmentToSymbolTable(node);
		super.visit(node);
		
	}

	@Override
	public void visit(IdRefExprNode node) {
		ST.addIdREfToSymbolTable(node);
	}
	@Override

	public void visit(IdNode node) {
		ST.addIdToSymbolTable(node);

	}
	@Override
	public void visit(LiteralAdvancedNode node) {
		ST.addIdToSymbolTable(node.idNode);
		
	}
	
//TODO måske tilføj literal exprnode??

}

