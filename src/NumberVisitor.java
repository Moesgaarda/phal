import CompilerError.ListIndexError;
import CompilerError.ListIndexExprError;
import CompilerError.ListIndexTypeError;
import enums.Type;

public class NumberVisitor extends Visitor {
    @Override
    public void visit(VarDclNode node){
        if(node.typeNode.Type == Type.NUMBER && node.exprNode != null){
            node.typeNode.isInt = checkExprType(node.exprNode);
        }
    }

    @Override
    public void visit(AssignmentNode node) {
        if (node.idNode.type == Type.NUMBER) {
            if(node.idNode.dclNode instanceof VarDclNode){
                VarDclNode dcl = (VarDclNode) node.idNode.dclNode;
                if(dcl.typeNode.isInt){
                    dcl.typeNode.isInt = checkExprType(node.exprNode);
                }
            }else{
                ParamNode dcl = (ParamNode) node.idNode.dclNode;
                if(dcl.typeNode.isInt){
                    dcl.typeNode.isInt = checkExprType(node.exprNode);
                }
            }
        }
    }

    private boolean checkExprType(IdRefExprNode node){
        VarDclNode dcl = (VarDclNode) node.idNode.dclNode;
        return dcl.typeNode.isInt;
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
        for(ExprNode expr : node.funcCallNode.callCntNode.exprNodes){
            if(expr instanceof IdRefExprNode){
                VarDclNode dcl = ((VarDclNode)((IdRefExprNode) expr).idNode.dclNode);
                dcl.typeNode.isInt = false;
            }
        }
        return false;
    }

    private boolean checkExprType(LiteralAdvancedNode node){
        checkIndexType(node.exprNode);
        return false;
    }

    private boolean checkExprType(ParensExprNode node){
        return checkExprType(node.exprNode);
    }

    private boolean checkExprType(IdNode node){
        VarDclNode dcl = (VarDclNode) node.dclNode;
        return dcl.typeNode.isInt;
    }

    private boolean checkExprType(ExprNode node) {
        if(node instanceof IdRefExprNode){
            return checkExprType((IdRefExprNode)node);
        }else if(node instanceof LiteralExprNode){
            return checkExprType((LiteralExprNode)node);
        }else if(node instanceof InfixExprNode){
            return checkExprType((InfixExprNode)node);
        }else if(node instanceof UnaryExprNode){
            return checkExprType((UnaryExprNode)node);
        }else if(node instanceof FuncExprNode){
            return checkExprType((FuncExprNode)node);
        }else if(node instanceof LiteralAdvancedNode){
            return checkExprType((LiteralAdvancedNode)node);
        }else if(node instanceof ParensExprNode){
            return checkExprType((ParensExprNode)node);
        }else{
            return checkExprType((IdNode)node);
        }
    }

    private void checkIndexType(IdRefExprNode node){
        if(node.type != Type.NUMBER) {
            MainClass.CompileErrors.add(new ListIndexTypeError(node.columnNumber, node.lineNumber, node.idNode.id, node.type.toString()));
        }else {
            VarDclNode dcl = (VarDclNode) node.idNode.dclNode;
            if(!dcl.typeNode.isInt) {
                MainClass.CompileErrors.add((new ListIndexError(node.columnNumber, node.lineNumber, node.idNode.id)));
            }
        }
    }
    private void checkIndexType(LiteralExprNode node){
        if(node.type != Type.NUMBER){
            MainClass.CompileErrors.add(new ListIndexTypeError(node.columnNumber, node.lineNumber, node.literalExprNode, node.type.toString()));
        }else{
            try{
                Integer.parseInt(node.literalExprNode);
            }catch(NumberFormatException e){
                MainClass.CompileErrors.add(new ListIndexError(node.columnNumber, node.lineNumber));
            }
        }
    }

    private void checkIndexType(InfixExprNode node){
        checkIndexType(node.rightExprNode);
        checkIndexType(node.leftExprNode);
    }

    private void checkIndexType(UnaryExprNode node){
        checkIndexType(node.exprNode);
    }

    private void checkIndexType(FuncExprNode node){
        MainClass.CompileErrors.add(new ListIndexExprError(node.columnNumber, node.lineNumber));
    }

    private void checkIndexType(LiteralAdvancedNode node){
        MainClass.CompileErrors.add(new ListIndexExprError(node.columnNumber, node.lineNumber));
    }

    private void checkIndexType(ParensExprNode node){
        checkIndexType(node.exprNode);
    }

    private void checkIndexType(ExprNode node){
        checkIndexType(node);
    }
}
