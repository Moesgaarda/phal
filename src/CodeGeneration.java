import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import enums.*;

public class CodeGeneration extends Visitor{

	private PrintWriter writer;
	private List<Type> ComponentIncludesMap;
	
	CodeGeneration(List<Type> CIM) {
		ComponentIncludesMap = CIM;
		try {
			writer = new PrintWriter(new FileWriter( "../phal/PhalLangEx4/" + MainClass.inputFileName + ".ino", false));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private void printHeader() {
		writer.print("/* Phal AutoGenerated .ino file \n"
				     + "*  Created " + new SimpleDateFormat("dd-MM-yyyy").format(new Date()) 
				     + "\n*/\n\n"
					 );
	}
	private void printCmpIncludes() {
		for(Type t : ComponentIncludesMap) {
			writer.print("#include \"" + t +".h\" \n"
				     );
		}

	}
	
	@Override
	public void visit(ProgramNode node) {
		printHeader();
		printCmpIncludes();

		
		for(SetupCntNode dcl : node.setupNode.setupCntNodes) {
			if(dcl.dclNode instanceof VarDclNode)
				visit(dcl);
		}
		
		// visit includes
		
		visit(node.setupNode);
		visit(node.repeatNode);

		for(FuncNode func : node.funcNodes)
			visit(func);
		
		writer.close();
	}
	
	@Override
	public void visit(SetupNode node) {
		writer.print("void setup(){ \n");

		for(SetupCntNode cnt : node.setupCntNodes){
			if(cnt.dclNode instanceof  CmpDclNode){
				visit(cnt);
			}

			if(cnt.stmtNode != null){
				visit(cnt.stmtNode);
			}
		}


		writer.print("} \n\n");
	}
	
	@Override
	public void visit(SetupCntNode node){
		if(node.dclNode instanceof CmpDclNode){
			visit(node.dclNode);
		}

		if(node.dclNode instanceof VarDclNode){
			visit(node.dclNode);
		}
	}


	@Override
	public void visit(RepeatNode node) {
		writer.print("void loop(){ \n");
			super.visit(node);
		writer.print("} \n\n");
	}

	@Override
	public void visit(WaitNode node){
		writer.print("delay((");
		visit(node.exprNode);
		writer.print(")*1000);\n");
	}

	@Override
	public void visit(AssignmentNode node){
		visit(node.idNode);
		if(node.assignmentOperator == AssignmentOperator.EQUALS){
			writer.print(" = ");
		}
		else{
			writer.print(node.assignmentOperator.toString());
		}

		visit(node.exprNode);
		writer.print(";\n");
	}

	@Override
	public void visit(IdNode node){
		if(node.subId == null)
			writer.print(node.id);
		else
			writer.print(node.id + "." + node.subId);
	}

	@Override
	public void visit(VarDclNode node) {
			visit(node.typeNode);
			writer.print(" ");
			visit(node.idNode);

			if(node.exprNode != null){
				writer.print(" = ");
				visit(node.exprNode);
			}

			writer.print(";\n");
	}



	@Override
	public void visit(ReturnStmtNode node){
		writer.print("return ");
		visit(node.exprNode);
		writer.print(";");
	}

	@Override
	public void visit(CmpDclNode node) {
			switch(node.advTypeNode.Type){
				case TEMPERATURESENSOR:
					for(LiteralExprNode exp : node.literalExprNodes){
						writer.print("pinMode(");
						writer.print(exp.literalExprNode);
						writer.print(", INPUT);\n");
					}
					break;
				case LIGHTBULB:
				case MOTOR:
					for(LiteralExprNode exp : node.literalExprNodes){
						writer.print("pinMode(");
						writer.print(exp.literalExprNode);
						writer.print(", OUTPUT);\n");
					}
					break;
			}
	}

	@Override
	public void visit(InfixExprNode node){
		visit(node.leftExprNode);

		switch(node.infixOperator){
			case AND:
				writer.print(" && ");
				break;
			case OR:
				writer.print(" || ");
				break;
			case EQUAL:
				writer.print(" == ");
				break;
			default:
				writer.print(node.infixOperator.toString());
				break;

		}

		visit(node.rightExprNode);
	}


	@Override
	public void visit(LiteralExprNode node) {
		switch(node.literalExprNode) {
		case "on":
		case "true":
			writer.print("true");
			break;
		case "off":
		case "false":
			writer.print("false");
			break;
		default: 
			writer.print(node.literalExprNode);
			break;
		}
	}

	@Override
	public void visit(IfStmtNode node){
		writer.write("if(");
		visit(node.ifExprNode);
		writer.write("){\n");
		visit(node.ifBlock);
		writer.write("\n}\n");

		for(ElseIfStmtNode elif : node.elseIfStmts)
			visit(elif);

		if(node.elseBlock != null)
			visit(node.elseBlock);
	}

	@Override
	public void visit(ElseIfStmtNode node){
		writer.print("else if(");
		visit(node.exprNode);
		writer.print("){\n");
		visit(node.block);
		writer.print("}\n");
	}

	@Override
	public void visit(ElseBlockNode node) {
		writer.print("else{\n");
		for (StmtNode stmt : node.stmtNodes)
			visit(stmt);
		writer.print("}");
	}


	@Override
	public void visit(FuncNode node){
		visit(node.typeNode);
		writer.write(" ");
		visit(node.idNode);
		writer.write(" (");
		visit(node.parametersNode);
		writer.write("){\n");
		for(FuncCntNode funcCnt : node.funcCntNodes)
			visit(funcCnt);
		writer.write("\n}\n");
	}

	@Override
	public void visit(ParamNode node){
		visit(node.typeNode);
		writer.print(" ");
		visit(node.idNode);
	}

	@Override
	public void visit(ParametersNode node){
		for(int i = 0; i < node.paramNodes.size(); i++){
			if(i != 0) {
				writer.print(", ");
			}
			visit(node.paramNodes.get(i));
		}
	}

	@Override
	public void visit(TypeNode node){
		switch(node.Type){
			case NONE:
				writer.print("void");
				break;
			case NUMBER:
				writer.print("float"); // TODO fiks mig
				break;
			case BOOL:
				writer.print("bool");
				break;
			case TEXT:
				writer.print("String");
				break;
			case MOTOR:
				writer.print("motor");
				break;
			case LIGHTBULB:
				writer.print("lightbulb");
				break;
			case TEMPERATURESENSOR:
				writer.print("temperatureSensor");
				break;
			case LIST:
				writer.print("list"); // TODO FIks mig
				break;
			case GROUP:
				writer.print("group");
				break;
			default:
				writer.print("void");
				break;

		}

	}
}
 