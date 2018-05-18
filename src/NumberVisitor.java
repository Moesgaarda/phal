import CompilerError.ListIndexError;
import CompilerError.ListIndexExprError;
import CompilerError.ListIndexTypeError;
import enums.Type;

import java.util.HashMap;

public class NumberVisitor extends Visitor {
    private HashMap<String, FuncNode> functionMap = new HashMap<>();
    @Override
    public  void visit(ProgramNode node){
        for(FuncNode func : node.funcNodes){
            functionMap.put(func.idNode.id, func);
        }
        super.visit(node);
    }
    @Override
    public void visit(VarDclNode node) {
        if (node.typeNode.Type == Type.NUMBER && node.exprNode != null) {
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
    @Override
    public void visit(ExprNode node){
        if(node instanceof FuncExprNode){
            visit((FuncExprNode) node);
        }
    }
    @Override
    public void visit(FuncCallNode node){
        FuncNode func = functionMap.get(node.idNode.id);
        for(int i = 0; i < node.callCntNode.exprNodes.size(); i++){
            if(func.parametersNode.paramNodes.get(i).typeNode.isInt){
                func.parametersNode.paramNodes.get(i).typeNode.isInt = checkExprType(node.callCntNode.exprNodes.get(i));
            }
        }
    }

    private boolean checkExprType(IdRefExprNode node){
        if(node.idNode.dclNode instanceof VarDclNode){
            return ((VarDclNode) node.idNode.dclNode).typeNode.isInt;
        }else{
            return ((ParamNode) node.idNode.dclNode).typeNode.isInt;
        }
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
        FuncNode func = functionMap.get(node.funcCallNode.idNode.id);
        for(int i = 0; i < node.funcCallNode.callCntNode.exprNodes.size(); i++){
            if(func.parametersNode.paramNodes.get(i).typeNode.isInt){
                func.parametersNode.paramNodes.get(i).typeNode.isInt = checkExprType(node.funcCallNode.callCntNode.exprNodes.get(i));
            }
            if(node.funcCallNode.callCntNode.exprNodes.get(i) instanceof  IdRefExprNode){
                IdRefExprNode idRef = (IdRefExprNode)node.funcCallNode.callCntNode.exprNodes.get(i);
                if(idRef.idNode.dclNode instanceof VarDclNode){
                    VarDclNode dcl = (VarDclNode)idRef.idNode.dclNode;
                    dcl.typeNode.isInt = false;
                }else {
                    ParamNode dcl = (ParamNode)idRef.idNode.dclNode;
                    dcl.typeNode.isInt = false;
                }
            }
        }/*
        for(ExprNode expr : node.funcCallNode.callCntNode.exprNodes){
            if(expr instanceof IdRefExprNode){
                IdRefExprNode idRef = (IdRefExprNode)expr;
                if(idRef.idNode.dclNode instanceof VarDclNode){
                    VarDclNode dcl = ((VarDclNode)((IdRefExprNode) expr).idNode.dclNode);
                    dcl.typeNode.isInt = false;
                }else {
                    ParamNode dcl = ((ParamNode)((IdRefExprNode) expr).idNode.dclNode);
                    dcl.typeNode.isInt = false;
                }
            }
        }*/
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
        if(node instanceof IdRefExprNode){
            checkIndexType((IdRefExprNode)node);
        }else if(node instanceof LiteralExprNode){
            checkIndexType((LiteralExprNode)node);
        }else if(node instanceof InfixExprNode){
            checkIndexType((InfixExprNode)node);
        }else if(node instanceof UnaryExprNode){
            checkIndexType((UnaryExprNode)node);
        }else if(node instanceof FuncExprNode){
            checkIndexType((FuncExprNode)node);
        }else if(node instanceof LiteralAdvancedNode){
            checkIndexType((LiteralAdvancedNode)node);
        }else if(node instanceof ParensExprNode){
            checkIndexType((ParensExprNode)node);
        }else{
            checkIndexType((IdNode)node);
        }
    }
}
