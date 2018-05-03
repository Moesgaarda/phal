import Warnings.*;
import java.util.LinkedList;
import java.util.Queue;
import CompilerError.*;

public class ReachabilityVisitor extends Visitor {
	
	@Override
	public void visit(ProgramNode node) {
		boolean isReturning;
		for(FuncNode func : node.funcNodes) {
			if(!func.typeNode.type.equals("none")) {
				isReturning = isBranchReturning(func);
				if(!isReturning)
					MainClass.CompileErrors.add(new FunctionNotReturningError(func.columnNumber, func.lineNumber, func.idNode.id));
				
			}
		}
	}
	
	
	public boolean isBranchReturning(FuncNode node) {
		Queue<IfStmtNode> ifStmtNodeQueue = new LinkedList<>();
		
		for(int i = 0; i < node.funcCntNodes.size(); i++) {
			if(node.funcCntNodes.get(i).stmtNode != null) {
				StmtNode s = node.funcCntNodes.get(i).stmtNode;	
				
				if(s instanceof ReturnStmtNode) {
					if(i < node.funcCntNodes.size()-1)
						MainClass.CompileWarnings.add(new UnreachableWarning(s.columnNumber, s.lineNumber));
					return true;
				}else if(s instanceof IfStmtNode)
					ifStmtNodeQueue.add((IfStmtNode) s);
			
			}
		}
		nodeLoop:
		while(ifStmtNodeQueue.peek() != null) {
			boolean isReturning;
			IfStmtNode ifNode = ifStmtNodeQueue.poll();
			
			if(ifNode.elseBlock != null) {
				isReturning = isBranchReturning(ifNode.elseBlock);
				if(!isReturning)
					continue;
			}else {
				continue;
			}
			
			if(!ifNode.elseIfStmts.isEmpty()) {
				for(ElseIfStmtNode elif : ifNode.elseIfStmts) {
					isReturning = isBranchReturning(elif.block);
					if(!isReturning)
						continue nodeLoop;
				}
			}
			return isReturning;
		}
		return false;
	}
	
	public boolean isBranchReturning(BlockNode node) {
		Queue<IfStmtNode> ifStmtNodeQueue = new LinkedList<>();
		
		for(int i = 0; i < node.stmtNodes.size(); i++) {
			StmtNode s = node.stmtNodes.get(i);	
			
			if(s instanceof ReturnStmtNode) {
				if(i < node.stmtNodes.size()-1)
					MainClass.CompileWarnings.add(new UnreachableWarning(s.columnNumber, s.lineNumber));
				return true;
			}else if(s instanceof IfStmtNode)
				ifStmtNodeQueue.add((IfStmtNode) s);	
		}
		
		nodeLoop:
		while(ifStmtNodeQueue.peek() != null) {
			boolean isReturning;
			IfStmtNode ifNode = ifStmtNodeQueue.poll();
			
			if(ifNode.elseBlock != null) {
				isReturning = isBranchReturning(ifNode.elseBlock);
				if(!isReturning)
					continue;
			}
			else {
				continue;
			}
			if(!ifNode.elseIfStmts.isEmpty()) {
				for(ElseIfStmtNode elif : ifNode.elseIfStmts) {
					isReturning = isBranchReturning(elif.block);
					if(!isReturning)
						continue nodeLoop;
					
				}
			}
			return isReturning;
		}
		return false;
	}
	
	public boolean isBranchReturning(ElseBlockNode node) {
		Queue<IfStmtNode> ifStmtNodeQueue = new LinkedList<>();
		
		for(int i = 0; i < node.stmtNodes.size(); i++) {
			StmtNode s = node.stmtNodes.get(i);	
			
			if(s instanceof ReturnStmtNode) {
				if(i < node.stmtNodes.size()-1)
					MainClass.CompileWarnings.add(new UnreachableWarning(s.columnNumber, s.lineNumber));
				return true;
			}else if(s instanceof IfStmtNode)
				ifStmtNodeQueue.add((IfStmtNode) s);	
		}
		
		nodeLoop:
		while(ifStmtNodeQueue.peek() != null) {
			boolean isReturning;
			IfStmtNode ifNode = ifStmtNodeQueue.poll();
			
			if(ifNode.elseBlock != null) {
				isReturning = isBranchReturning(ifNode.elseBlock);
				if(!isReturning)
					continue;
			}
			else {
				continue;
			}
			if(!ifNode.elseIfStmts.isEmpty()) {
				for(ElseIfStmtNode elif : ifNode.elseIfStmts) {
					isReturning = isBranchReturning(elif.block);
					if(!isReturning)
						continue nodeLoop;
					
				}
			}
			return isReturning;
		}
		return false;
	}
}
