
public class PrettyPrinter extends Visitor{
	int indention = 0;
	
	private String indent() {
		return new String(new char[indention]).replace("\0", "    ");
	}
	
	private void printl(String input) {
		System.out.println(indent() + input);
	}
	
	@Override 
	public void visit(ProgramNode node) {
		printl("Program");
		indention++;
		super.visit(node);
		indention--;
	}
	@Override public void visit(IncludeNode node) {
		printl("Include " + node.idNode.id);
	}
	@Override public void visit(SetupNode node) {
		printl("");
		printl("Setup");
		indention++;
		for(SetupCntNode scn : node.setupCntNodes) {
			visit(scn);
		}
		indention--;
	}
	@Override public void visit(SetupCntNode node) {
		if(node.dclNode != null) {
			printl("Declaration");
			indention++;
				visit(node.dclNode);
			indention--;
		}
		if(node.stmtNode != null) {
			indention++;
			printl("Statement");
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
			printl(node.typeNode.type);
			printl(node.idNode.id);
			printl("=");
			visit(node.exprNode);
		}
	else {
		printl(node.typeNode.type);
		printl(node.idNode.id);
	}
		
	}
	@Override public void visit(TypeNode node) {
		printl(node.type);
	}
	@Override public void visit(AdvDataTypeNode node) {
		super.visit(node);
	}
	@Override public void visit(CmpDclNode node) {
			printl(node.advTypeNode.type);
			printl(node.idNode.id);
			printl("=");
			for(LiteralExprNode litexp : node.literalExprNodes) {
				visit(litexp);
			}
	}
	@Override public void visit(AdvTypeNode node) {
		printl(node.type);
	}
	@Override public void visit(GroupNode node) {
		printl("Group");
		indention++;
		printl(node.idNode.id);
		indention++;
			for(IdNode id : node.memberIdNodes) {
				visit(id);
			}
		indention--;
		indention--;
	}
	@Override public void visit(ListNode node) {
		printl("List");
		indention++;
		printl(node.typeNode.type);
		indention++;
		printl(node.idNode.id);
		indention++;
			for(ExprNode expr : node.memberExprNodes) {
				visit(expr);
			}
			indention--;
			indention--;
			indention--;
	}
	@Override public void visit(StmtNode node) {
		printl("I am in visit(StmtNode)");
	}
	@Override public void visit(WaitNode node) {
		printl("Wait");
		indention++;
			visit(node.exprNode);
		indention--;
	}
	@Override public void visit(SelectiveNode node) {
		printl("Selective");
	}
	@Override public void visit(SwitchStmtNode node) {
		printl("Switch");
	}
	@Override public void visit(CaseListNode node) {
		printl("CaseList");
	}
	@Override public void visit(CaseStmtNode node) {
		printl("CaseStmt");
	}
	@Override public void visit(DefaultCaseNode node) {
		printl("Default case");
	}
	@Override public void visit(IfStmtNode node) {
		printl("If!");
	}
	@Override public void visit(BlockNode node) {
		printl("BlockNode!");
	}
	@Override public void visit(ElseIfStmtNode node) {
		printl("ElifNode");
	}
	@Override public void visit(IterativeNode node) {
		printl("IterNode");
	}
	@Override public void visit(LoopTimesNode node) {
		printl("LoopTimesNode");
	}
	@Override public void visit(LoopUntilNode node) {
		printl("LoopUntilNode");
	}
	@Override public void visit(FuncCallNode node) {
		printl("FuncCallNode");
	}
	@Override public void visit(CallCntNode node) {
		printl("CallCntNode");
	}
	@Override public void visit(AssignmentNode node) {
		printl("AssignemntNode");
	}
	@Override public void visit(AdvTypeModifierNode node) {
		printl("AdvTypeModifierNode");
	}
	@Override public void visit(RepeatNode node) {
		printl("");
		printl("Repeat");
		indention++;
		if(!node.stmtNodes.isEmpty()) {
			for(StmtNode stmt : node.stmtNodes) {
				visit(stmt);
			}
		}
		indention--;
	}
	@Override public void visit(FuncNode node) {
		printl("Function");
		indention++;
		super.visit(node);		
		
		for(FuncCntNode func : node.funcCntNodes) {
			visit(func);
		}
		
		indention--;
	}
	@Override public void visit(FuncCntNode node) {
		printl("i am here PLS");
	}
	@Override public void visit(ParametersNode node) {
		if(node.noneNode != null) {
			printl("none");
		}else {
			for(ParamNode pn : node.paramNodes) {
				visit(pn);
			}
		}
	}
	@Override public void visit(ParamNode node) {
		printl(node.typeNode.type + " " + node.idNode.id);
	}
	@Override public void visit(ReturnStmtNode node) {
		visit(node.exprNode);
	}
	@Override public void visit(ExprNode node) {
		super.visit(node);
	}
	@Override public void visit(IdRefExprNode node) {
		printl("I am idref");
	}
	@Override public void visit(NoneNode node) {
		printl("noneNode");
	}
	@Override public void visit(LiteralExprNode node) {
		printl(node.literalExprNode);
	}
	@Override public void visit(InfixExprNode node) {
		printl(node.infixOperator.toString());
	}
	@Override public void visit(FuncExprNode node) {
		printl("FuncExprNode");
	}
	@Override public void visit(ParensExprNode node) {
		printl("ParensNode");
	}
	@Override public void visit(UnaryExprNode node) {
		printl("UnaryExpr");
	}
	@Override public void visit(IdNode node) {
		if(node.subId != null)
			printl(node.id + "." + node.subId);
		else
			printl(node.id);
	}
	@Override public void visit(LiteralAdvancedNode node) {
		printl("LitAdvNode");
	}
}
