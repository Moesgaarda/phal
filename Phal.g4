/**
 * Define a grammar called Phal
 */
grammar Phal;

r        : (Include)* Setup Repeat (Func)*;

Include    : 'using' 'moduleName' NEWLINE;

Setup        : 'setup' '{' (SetupCnt)* '}';

SetupCnt    : Stmt | Dcl ;

Dcl         : VarDcl NEWLINE   |   CmpDcl NEWLINE |   AdvDataType ;

AdvDataType    : Group   |   List ;

VarDcl        : Type ID  | Type ID '=' AssStmt ;
 
Type        : NUMBER |  TEXT  |  LETTER  |  BOOL ;

CmpDcl    : AdvType ID '=' 'pin' 'PINNUMBER' ;

AdvType    : 'lightbulb'  |  'coffeeMachine'   |    'temperatureSensor' ;

Group        : 'group' 'groupName' '{' (GrpCnt NEWLINE)+ '}' ;

GrpCnt            : ID   |   'groupName' ;

List        : 'list' Type 'listName' '{' ListCnt ( ',' ListCnt)* '}' ;

ListCnt        : ID | VALUE ;

Stmt        : (Selective   |   Iterative   |   FuncCall NEWLINE  |   Assignment NEWLINE );

Selective    : Switch   |   IfStmt ;

Switch        :  'switch' '(' ID ')' { CaseList }   |   'switch' '(' VALUE ')' '{' CaseList '}' ;
 
CaseList    : (Case NEWLINE)+ DefaultCase ;

Case        : VALUE ':' (Stmt)* NEWLINE;

DefaultCase    : 'default' ':' (Stmt)* NEWLINE;

IfStmt        : 'if' '(' Condition ')' 'then' '{' (Stmt)* '}' |
				|    'if' '(' Condition ')' 'then' '{' (Stmt)*  '}' 'else'  '{'  (Stmt)*  '}'  |
				|    'if' '(' Condition ')' 'then' '{' (Stmt)*  '}' 'else'  IfStmt  ;

Condition    :  ID LogicOper  LogicalStmt  |  ID LogicOper ID  |  ID LogicOper VALUE  |  VALUE LogicOper LogicalStmt
     		   |    VALUE LogicOper ID  |  VALUE LogicOper VALUE  |  ID   |  'boolValue' ;

Iterative    : Loop ;

Loop        : 'loop' VALUE 'times' '{' (Stmt)* '}'    |    'loop' 'until' Condition '{' (Stmt)* '}' ;

FuncCall    : 'call' ID 'with' '(' Call ')'   |    ID'.methodId(' Call ')';

Call        : NONE | CallParam ( ',' CallParam)* ;

CallParam    : ID   |   VALUE ;

Assignment    : ID '=' AssStmt    |    ID '=' LogicalStmt | CompOp ID   |   ID CompOp  ;

AssStmt    : ID Oper AssStmt   |  VALUE Oper AssStmt   |   ID CompOp Oper AssStmt  |    ID CompOp |   VALUE   |    ID ;

Oper         : '+'  |  '-'  |  '/' |   '*' |   '%' ;

CompOp    : 'increment'    |    'decrement' ;

LogicalStmt     : ID LogicOper  LogicalStmt  |  ID LogicOper ID  |  ID LogicOper VALUE  |  VALUE LogicOper LogicalStmt
  			      |    VALUE LogicOper ID  |  VALUE LogicOper VALUE  |  ID   |  VALUE ;

LogicOper    : '>'   |  '<'   |   '<='  |   '>='   |   'is not'   |   'is'   |  '='   |   '!=' ;

Repeat    : 'repeat' '{'  (RepeatCnt)* '}'  ;

RepeatCnt    : VarDcl NEWLINE  |   Stmt ;

Func        : 'define' ID 'with' '(' Parameters ')' 'returnType' Type '{' ((Stmt | ReturnStmt) NEWLINE)*  ReturnStmt?'}' ;

Parameters    :  (Param)+    |    NONE ;

Param        : Type 'paramName' ;

ReturnStmt    : 'return' (ID | VALUE) ;

NEWLINE    : ('\r') '\n' ;
VALUE : ID+ ;  // Skal have en rigtig værdi
ID : ('a'..'z' | 'A'..'Z') (('a'..'z' | 'A'..'Z') | ('0'..'9'))*;
LETTER: ('a'..'z' | 'A'..'Z');
TEXT : '"' ~('\r' | '\n' | '"')* '"' ;
NUMBER : ('0'..'9');
BOOL : 'true'|'false' | 'on' |'off'; 
NONE : 'none';
