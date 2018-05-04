import CompilerError.*;
import Warnings.*;
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
		if(node.exprNode != null) {
			node.isInitialized = true;
			node.exprNode.accept(this);
		}
	}
	@Override
	public void visit(CmpDclNode node)
	{
		ST.addDeclarationToSymbolTable(node);
		node.advTypeNode.accept(this);
		node.isInitialized = true;
		for(LiteralExprNode num: node.literalExprNodes)
		{
			num.accept(this);
		}
	}
	@Override
	public void visit(ListNode node)
	{
		ST.addDeclarationToSymbolTable(node);
		node.isInitialized = true;
		node.typeNode.accept(this);
		if(node.memberExprNodes != null)
		{
			for(ExprNode expr: node.memberExprNodes)
			{
				expr.accept(this);
			}
		}
	}
	@Override
	public void visit(GroupNode node)
	{
		ST.addDeclarationToSymbolTable(node);
		node.isInitialized = true;
		for(IdNode member: node.memberIdNodes) 
		{
			member.accept(this);
		}
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
	
	@Override 
	public void visit(AssignmentNode node)
	{
		ST.addAssignmentToSymbolTable(node);
		node.idNode.dclNode.isInitialized = true;
		node.exprNode.accept(this);
		
	}
	
	@Override
	public void visit(IdNode node) {
		ST.addIdToSymbolTable(node);
		if(  node.dclNode != null && node.dclNode.isInitialized == false) {
			MainClass.CompileWarnings.add(
					new NotInitializedWarning(node.columnNumber, node.lineNumber, node.id));
		}

	}
	@Override
	public void visit(LiteralAdvancedNode node) {
		ST.addIdToSymbolTable(node.idNode);
		
	}
	

}

