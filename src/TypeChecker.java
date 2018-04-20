
public class TypeChecker extends Visitor{

	public boolean typeErrorInCountered = false;
	
	public void visit(InfixExprNode infixNode)
	{
		visit(infixNode.rightExprNode);
		visit(infixNode.leftExprNode);
		
		switch(infixNode.infixOperator) 
		{
		case PLUS: 
			//if()
			
		}
	}
	//private boolean c
}
