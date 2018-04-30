
public class PrettyPrinter extends Visitor{
	int indention = 0;
	
	private String indent() {
		return new String(new char[indention]).replace("\0", "|   ");
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
			printl("Statement");
			indention++;
				visit(node.stmtNode);
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
			printl(":=");
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
		super.visit(node);
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
		printl("switch");
		printl("(");
		visit(node.exprNode);
		printl(")");
		printl("{");

		indention++;
		visit(node.caseListNode);
		indention--;
		printl("}");
		
	}
	@Override public void visit(CaseListNode node) {
		for(CaseStmtNode caseNode : node.caseStmtNodes) {
			visit(caseNode);
		}
		visit(node.defaultCaseNode);
	}
	@Override public void visit(CaseStmtNode node) {
		printl("case");
		visit(node.exprNode);
		printl(" :");
		for(StmtNode stmt : node.stmtNodes) {
			visit(stmt);
		}
	}
	@Override public void visit(DefaultCaseNode node) {
		printl("default");
		printl(":");
		for(StmtNode stmt : node.stmtNodes) {
			visit(stmt);
		}
	}
	@Override public void visit(IfStmtNode node) {
		printl("If");
		printl("(");
		visit(node.ifExprNode);
		printl(")");
		printl("{");
		indention++;
		visit(node.ifBlock);
		indention--;
		printl("}");
			for(ElseIfStmtNode stmt : node.elseIfStmts) {
				visit(stmt);
			}
			if(node.elseBlock != null) {
				printl("else");
				printl("{");
				indention++;
				visit(node.elseBlock);
				indention--;
				printl("}");
			}
		
	}
	@Override public void visit(BlockNode node) {
		for(StmtNode stmt : node.stmtNodes) {
			visit(stmt);
		}
	}
	@Override public void visit(ElseIfStmtNode node) {
		printl("else if");
		printl("(");
		visit(node.exprNode);
		printl(")");
		printl("{");
		indention++;
		visit(node.block);
		indention--;
		printl("}");
	}
	@Override public void visit(IterativeNode node) {
		printl("IterNode");
	}
	@Override public void visit(LoopTimesNode node) {
		printl("loop");
		visit(node.exprNode);
		printl("times");
		printl("{");
		indention++;
		for(StmtNode stmt : node.stmtNodes) {
			visit(stmt);
		}
		indention--;
		printl("}");
		
	}
	@Override public void visit(LoopUntilNode node) {
		printl("loop");
		printl("until");
		visit(node.exprNode);
		printl(node.loopOperator.toString());
		visit(node.idNode);
		printl("by");
		visit(node.numberNode);
		printl("{");
		indention++;
		for(StmtNode stmt : node.stmtNodes) {
			visit(stmt);
		}
		indention--;
		printl("}");
		
	}
	@Override public void visit(FuncCallNode node) {
		printl("call");
		visit(node.idNode);
		printl("with");
		printl("(");
		indention++;
		if(node.callCntNode != null) {
			visit(node.callCntNode);
		}else {
			printl("none");
		}
		indention--;
		printl(")");
	}
	@Override public void visit(CallCntNode node) {
		for(ExprNode expr : node.exprNodes) {
			visit(expr);
		}
	}
	@Override public void visit(AssignmentNode node) {
		visit(node.idNode);
		printl(node.assignmentOperator.toString());
		visit(node.exprNode);
		
	}
	@Override public void visit(AdvTypeModifierNode node) {
		switch(node.advancedTypeModifierOperator) {
			case ADD:
				printl("add");
				indention++;
				for(ExprNode expr : node.exprNodes) {
					visit(expr);
				}
				indention--;
				printl("to");
				visit(node.idNode);
				break;
			case REMOVE:
				printl("remove");
				printl("element");
				indention++;
				for(ExprNode expr : node.exprNodes) {
					visit(expr);
				}
				indention--;
				printl("from");
				visit(node.idNode);
				break;
		}
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
		printl("define");
		visit(node.idNode);
		printl("with");
		printl("(");
		visit(node.parametersNode);
		printl(")");
		printl("returnType");
		visit(node.typeNode);
		printl("{");
		indention++;
		for(FuncCntNode cnt : node.funcCntNodes) {
			visit(cnt);
		}
		indention--;
		printl("}");
		indention--;
	}
	@Override public void visit(FuncCntNode node) {
		if(node.varDclNode != null)
			visit(node.varDclNode);
		else
			visit(node.stmtNode);
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
		printl("Expr");
		indention++;
		visit(node.leftExprNode);
		
		printl(node.infixOperator.toString());
		
		visit(node.rightExprNode);
		indention--;
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
		printl("get");
		printl("element");
		for(ExprNode expr : node.exprNodes) {
			visit(expr);
		}
		printl("from");
		visit(node.idNode);
		printl("");
	}
}
