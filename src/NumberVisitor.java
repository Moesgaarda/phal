import java.util.HashMap;

public class NumberVisitor extends Visitor {
    private HashMap<String, FuncNode> functionMap = new HashMap<>();

    public void visit(ProgramNode node){
        if(node.funcNodes != null) {
            for (FuncNode func : node.funcNodes) {
                if (func.typeNode.Type == Type.NUMBER) {
                    functionMap.put(func.idNode.id, func);
                }
            }
            for(FuncNode func : node.funcNodes){
                visit(func);
            }
        }
        visit(node.setupNode);
        visit(node.repeatNode);
    }
    @Override
    public void visit(VarDclNode node){
        if(node.typeNode.Type == Type.NUMBER && node.exprNode != null){
            node.isInt = checkExprType(node.exprNode);
        }
    }

    @Override
    public void visit(AssignmentNode node){
        if(node.idNode.type == Type.NUMBER){
            node.idNode.dclNode.isInt = checkExprType(node.exprNode);
        }
    }

    private boolean checkExprType(IdRefExprNode node){
        VarDclNode dcl = (VarDclNode) node.idNode.dclNode;
        return dcl.isInt;
    }

    private boolean checkExprType(LiteralExprNode node){
        try{
            Integer.parseInt(node.literalExprNode);
        }catch (NumberFormatException e){
            return false;
        }

        return true;
    }

    private boolean checkExprType(InfixExprNode node){
        boolean expr1 = checkExprType(node.leftExprNode);
        boolean expr2 = checkExprType(node.rightExprNode);

        return expr1 && expr2;
    }

    private boolean checkExprType(UnaryExprNode node){
        return checkExprType(node.exprNode);
    }

    private boolean checkExprType(FuncExprNode node){
        return true;
    }

    private boolean checkExprType(ParensExprNode node){
        return checkExprType(node.exprNode);
    }

    private boolean checkExprType(ExprNode node){
        return checkExprType(node);
    }



}
