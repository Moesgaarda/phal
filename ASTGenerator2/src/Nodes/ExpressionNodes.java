package Nodes;

abstract class ExpressionNode {
}

abstract class InfixExpressionNode extends ExpressionNode {
    public ExpressionNode Left;
    public ExpressionNode Right;
}

class AdditionNode extends InfixExpressionNode{}
class SubtractionNode extends InfixExpressionNode{}
class MultiplicationNode extends InfixExpressionNode{}
class DivisionNode extends InfixExpressionNode{}

class NegateNode extends InfixExpressionNode{
    public ExpressionNode InnerNode;
}

class NumberNode extends ExpressionNode{
    public double Value;
}