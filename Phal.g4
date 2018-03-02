/**
 * Define a grammar called Phal
 */
grammar Phal;

r 	: Includes Setup Repeat Funcs ;

Includes	: Include Includes 
			| /* Epsilon */
			;
			
Include 	: 'using' 'moduleName' ;

Setup 		: 'setup' 'leftBracket' SetupCnt 'rightBracket' ;

SetupCnt	: SetupExprs
			| 
			;
SetupExprs	: SetupExpr SetupExprs
			|
			;

SetupExpr   : Expr
			| SetupDcls;
			
Exprs	 	: Expr Exprs
			|
			;

Expr		: VarDcls
			| Dcls 
			;

SetupDcls	: CmpDcls
			| Groups
			;
			
Dcls        : VarDcls
			| Stmts 
			;
			
VarDcls		: VarDcl VarDcls
			|
			;
			
VarDcl	 	: Type ID
			| Type ID 'assignment' NUMBERVALUE
			| Type ID 'assignment' BOOLVALUE
			| Type ID 'assignment' TEXTVALUE
			| Type ID 'assignment' ID;

Type		: 'number'
			| 'text'
			| 'letter'
			| 'bool' ;
			
CmpDcls		: CmpDcl CmpDcls
			|
			;
			
CmpDcl		: AdvType ID 'assignment' 'pin' 'pinNumber';

AdvType		: 'lightBulb'
			| 'coffeeMachine'
			| 'temperatureSensor';
			
Groups		: Group Groups
			| 
			;

Group		: 'group' 'groupName' 'leftBracket' GrpCnts 'rightBracket';

GrpCnts		: GrpCnt GrpCnts
			| 
			;

GrpCnt		: ID
			| 'groupName';

Stmts		: Stmt Stmts
			| 
			;

Stmt		: Selective
			| Iterative
			| FuncCall
			| Assignment;

Selective	: Switch
			| IfStmt;
			
Switch		: 'switch' 'leftParen' Expr 'rightParen' CaseList 'rightBracket';

CaseList	: Cases DefaultCase;

Cases		: Case Cases
			|
			;
			
Case		: 'number' 'colon' Stmts;

DefaultCase	: 'default' 'colon' Stmts;

IfStmt		: 'if' 'leftParan' LogicalStmt 'rightParan' 'then' 'leftBracket' Exprs 'rightBracket'
			| 'if' 'leftParan' LogicalStmt 'rightParan' 'then' 'leftBracket' Exprs 'rightBracket' 'else' 'leftBracket' Exprs 'rightBracket'
			| 'if' 'leftParan' LogicalStmt 'rightParan' 'then' 'leftBracket' Exprs 'rightBracket' 'else' IfStmt 'leftBracket' Exprs 'rightBracket';

Iterative	: Loop;

Loop		: 'loop' NUMBERVALUE 'times' 'leftBracket' Stmts 'rightBracket'
			| 'loop' 'until' LogicalStmt 'leftBracket' Stmts 'rightBracket';

FuncCall	: 'call' ID 'with' 'leftParen' CallParams 'rightParen';

CallParams	: CallParam 'comma' CallParams
			| CallParam
			| NONE
			;

CallParam	: ID
			| NUMBERVALUE
			| BOOLVALUE
			| TEXTVALUE;

Assignment	: ID 'assignment' AssStmt
			| ID 'assignment' LogicalStmt;
			
AssStmt		: ID Oper AssStmt
			| ID Oper NUMBERVALUE
			| ID Oper ID
			| NUMBERVALUE Oper NUMBERVALUE
			| NUMBERVALUE Oper ID
			| ID CompOp
			| NUMBERVALUE CompOp
			| NUMBERVALUE
			| ID;
			
CompOp		: 'increment'
			| 'decrement';

Oper		: 'plus' 
			| 'minus' 
			| 'divide'
			| 'times';

LogicalStmt	: ID LogicOper LogicalStmt
			| ID LogicOper ID
			| ID LogicOper BOOLVALUE
			| BOOLVALUE LogicOper LogicalStmt
			| BOOLVALUE LogicOper ID
			| BOOLVALUE LogicOper BOOLVALUE
			| ID
			| BOOLVALUE;

LogicOper	: 'greaterThan'
			| 'lessThan'
			| 'lessThanEqual'
			| 'greaterThanEqual'
			| 'notEqual'
			| 'equal';

Repeat		: 'repeat' 'leftBracket' RepeatCnt 'rightBracket';

RepeatCnt	: VarDcls
			| Stmts
			|
			;

Funcs		: Func Funcs
			|
			;
			
Func		: 'define' ID 'with' 'leftParen' Params 'rightParen' 'returnType' Type 'leftBracket' FuncContent 'rightBracket';

FuncContent	: VarDcls Stmts ReturnStmt
			|
			;

Params		: Param 'comma' Params
			| Param
			| NONE;

Param		: Type 'paramName';

ReturnStmt	: 'return' ID
			| 'return' TEXTVALUE
			| 'return' NUMBERVALUE
			| 'return' BOOLVALUE
			;
			
ID : [a-zA-Z]+[a-zA-Z0-9]*;
NONE : 'none' ;
TEXTVALUE : '"' ~('\r' | '\n' | '"')* '"' ;
NUMBERVALUE : [0-9]+ 
			| [0-9]+.[0-9]+;
BOOLVALUE : 'true'|'false'
			|'on' |'off'; 
