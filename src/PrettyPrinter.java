
public class PrettyPrinter extends Visitor{
	int indention = 0;
	
	private String indent() {
		return new String(new char[indention]).replace("\0", "    ");
	}
	
	private void print(String input) {
		System.out.println(indent() + input);
	}
	
	@Override 
	public void visit(ProgramNode node) {
		print("Program");
		indention++;
		super.visit(node);
		indention--;
	}
	@Override public void visit(IncludeNode node) {
		print("Include " + node.idNode.id);
	}
	@Override public void visit(SetupNode node) {
		print("Setup");
		indention++;
		for(SetupCntNode scn : node.setupCntNodes) {
			visit(scn);
		}
		indention--;
	}
	@Override public void visit(SetupCntNode node) {
		if(node.dclNode != null) {
			print("Declaration");
			indention++;
				visit(node.dclNode);
			indention--;
		}
		if(node.stmtNode != null) {
			indention++;
			print("Statement");
			indention++;
				visit(node.stmtNode);
			indention--;
			indention--;
		}
	}
	@Override public void visit(DclNode node) {
		super.visit(node);
	}
	@Override public void visit(VarDclNode node) {
		if(node.exprNode != null) {
			ExprNode expr = node.exprNode;
			print(node.typeNode.type + " " + node.idNode.id + " = "  );
		
		}
	else
		print(node.typeNode.type + " " + node.idNode.id);
		
	}
	@Override public void visit(TypeNode node) {
		print(node.type);
	}
	@Override public void visit(AdvDataTypeNode node) {
		print("Hello from the advDataTypeNode side");
	}
	@Override public void visit(CmpDclNode node) {
		print(node.idNode.id);
	}
	@Override public void visit(AdvTypeNode node) {
		print(node.type);
	}
	@Override public void visit(GroupNode node) {
	}
	@Override public void visit(ListNode node) {
	}
	@Override public void visit(StmtNode node) {
		print("statement");
	}
	@Override public void visit(WaitNode node) {
	}
	@Override public void visit(SelectiveNode node) {
	}
	@Override public void visit(SwitchStmtNode node) {
	}
	@Override public void visit(CaseListNode node) {
	}
	@Override public void visit(CaseStmtNode node) {
	}
	@Override public void visit(DefaultCaseNode node) {
	}
	@Override public void visit(IfStmtNode node) {
	}
	@Override public void visit(BlockNode node) {
	}
	@Override public void visit(ElseIfStmtNode node) {
	}
	@Override public void visit(IterativeNode node) {
	}
	@Override public void visit(LoopTimesNode node) {
	}
	@Override public void visit(LoopUntilNode node) {
	}
	@Override public void visit(FuncCallNode node) {
	}
	@Override public void visit(CallCntNode node) {
	}
	@Override public void visit(AssignmentNode node) {
	}
	@Override public void visit(AdvTypeModifierNode node) {
	}
	@Override public void visit(RepeatNode node) {
		print("Repeat");
		indention++;
		if(node.stmtNodes != null) {
			
		}
		indention--;
	}
	@Override public void visit(FuncNode node) {
		print("I am a function");
	}
	@Override public void visit(FuncCntNode node) {
	}
	@Override public void visit(ParametersNode node) {
	}
	@Override public void visit(ParamNode node) {
	}
	@Override public void visit(ReturnStmtNode node) {
	}
	@Override public void visit(ExprNode node) {
	}
	@Override public void visit(IdRefExprNode node) {
	}
	@Override public void visit(NoneNode node) {
	}
	@Override public void visit(LiteralExprNode node) {
	}
	@Override public void visit(InfixExprNode node) {
	}
	@Override public void visit(FuncExprNode node) {
	}
	@Override public void visit(ParensExprNode node) {
	}
	@Override public void visit(UnaryExprNode node) {
	}
	@Override public void visit(IdNode node) {
	}
	@Override public void visit(LiteralAdvancedNode node) {
	}
}
