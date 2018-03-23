/**
 * Define a grammar called Phal
 */
grammar Phal;

r        : (Include)* Setup Repeat (Func)*;

Include    : 'using' ID NEWLINE;

Setup        : 'setup' '{' (SetupCnt)* '}';

SetupCnt    : Stmt | Dcl ;

Dcl         : VarDcl NEWLINE   |   CmpDcl NEWLINE |   AdvDataType ;

AdvDataType    : Group   |   List ;

VarDcl        : Type ID  | Type ID ':=' AssStmt ;
 
Type        : 'number' |  'text'  |  'letter'  |  'bool' ;

CmpDcl    : AdvType ID ':=' 'pin' INTEGER ;

AdvType    : 'lightbulb'  |  'coffeeMachine'   |  'temperatureSensor' ;

Group        : 'group' ID '{' (ID NEWLINE)+ '}' ;

List        : 'list' Type ID '{' ListCnt ( ',' ListCnt)* '}' ;

ListCnt        : ID | VALUE ;

Stmt        : Selective   |   Iterative   |   FuncCall NEWLINE  |   Assignment NEWLINE ;

Selective    : Switch   |   IfStmt ;

Switch        :  'switch' '(' ID ')' { CaseList }   |   'switch' '(' VALUE ')' '{' CaseList '}' ;
 
CaseList    : (Case NEWLINE)+ DefaultCase ;

Case        : VALUE ':' '{'(Stmt)*'}' NEWLINE;

DefaultCase    : 'default' ':' (Stmt)* NEWLINE;

IfStmt        : 'if' '(' Condition ')' 'then' '{' (Stmt)* '}' |
				|    'if' '(' Condition ')' 'then' '{' (Stmt)*  '}' 'else'  '{'  (Stmt)*  '}'  |
				|    'if' '(' Condition ')' 'then' '{' (Stmt)*  '}' 'else'  IfStmt  ;

Condition    :  ID LogicOper  LogicalStmt  |  ID LogicOper ID  |  ID LogicOper VALUE  |  VALUE LogicOper LogicalStmt
     		   |    VALUE LogicOper ID  |  VALUE LogicOper VALUE  |  ID   |  BOOL ;

Iterative    : Loop ;

Loop        : 'loop' INTEGER 'times' '{' (Stmt)* '}'    |    'loop' 'until ' Condition '{' (Stmt)* '}' ;

FuncCall    : 'call' ID 'with' '(' Call ')' |  'call' ID 'with' '(' 'none' ')'  |    ID'.'ID'(' Call ')' |    ID'.'ID'(' 'none' ')';

Call        : CallParam ( ',' CallParam)* ;

CallParam    : ID   |   VALUE ;

Assignment    : ID ':=' AssStmt    |    ID ':=' LogicalStmt | ID '+=' ID | ID '+=' VALUE| ID '-=' ID | ID '-=' VALUE ;

AssStmt    : ID Oper AssStmt   |  VALUE Oper AssStmt  | FuncCall Oper AssStmt |   VALUE   |    ID   |  FuncCall;

Oper         : '+'  |  '-'  |  '/' |   '*' |   '%' ;

LogicalStmt     : ID LogicOper  LogicalStmt  |  ID LogicOper ID  |  ID LogicOper VALUE  |  VALUE LogicOper LogicalStmt
  			      |    VALUE LogicOper ID  |  VALUE LogicOper VALUE  |  ID   |  VALUE ;

LogicOper    : '>'   |  '<'   |   '<='  |   '>='  |  '='   |   '!=' | '&' | '|' 
				| 'is not'   |   'is'   | 'greater than or equal to' | 'less than or equal to' |'greater than' | 'less than' | 'or' | 'and' | 'not';

Repeat    : 'repeat' '{' (Stmt)* '}'  ;

Func        : 'define' ID 'with' '(' Parameters ')' 'returnType' RType '{' (FuncCnt)*  ReturnStmt?'}' ;

FuncCnt		: VarDcl NEWLINE | Stmt | ReturnStmt;

RType		: Type | 'none';

Parameters    :  (Param)+  ;

Param        : Type 'paramName' ;

ReturnStmt    : 'return' (ID | VALUE | 'none') ;

NEWLINE    : ('\r') '\n' ;
VALUE : NUMBER | BOOL | TEXT ;  // Skal have en rigtig værdi
ID : ('a'..'z' | 'A'..'Z') (('a'..'z' | 'A'..'Z') | ('0'..'9'))*;
LETTER: ('a'..'z' | 'A'..'Z');
TEXT : '"' ~('\r' | '\n' | '"')* '"' ;
DIGIT : ('0'..'9');
INTEGER : (DIGIT |('1'..'9')(DIGIT)+);
FLOAT : ('-')?((DIGIT | ('1'..'9')(DIGIT)+)'.'(DIGIT | (DIGIT)*('1'..'9')));
NUMBER : ('-')?INTEGER | FLOAT ;
BOOL : 'true'|'false' | 'on' |'off'; 

