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

SetupCnt	: Exprs
			| 
			;
 
Exprs	 	: Expr Exprs
			|
			;

Expr		: SetupDcls ;

SetupDcls	: VarDcls
			| CmpDcls
			| Groups
			| Stmts ;
			
VarDcls		: VarDcl VarDcls
			|
			;
			
VarDcl	 	: Type ID
			| Type ID 'assignment' VALUE
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

Loop		: 'loop' VALUE 'times' 'leftBracket' Stmts 'rightBracket'
			| 'loop' 'until' ID Oper VALUE 'leftBracket' Stmts 'rightBracket';

FuncCall	: 'call' ID 'with' 'leftParen' CallParams 'rightParen';

CallParams	: CallParam 'comma' CallParams
			| CallParam
			| NONE
			;

CallParam	: ID
			| VALUE;

Assignment	: ID 'assignment' AssStmt
			| ID 'assignment' LogicalStmt;
			
AssStmt		: ID Oper AssStmt
			| ID Oper VALUE
			| ID Oper ID
			| VALUE Oper VALUE
			| VALUE Oper ID
			| ID CompOp
			| VALUE CompOp
			| VALUE
			| ID;
			
CompOp		: 'increment'
			| 'decrement';

Oper		: 'plus' 
			| 'minus' 
			| 'divide'
			| 'times';

LogicalStmt	: ID LogicOper LogicalStmt
			| ID LogicOper ID
			| ID LogicOper VALUE
			| VALUE LogicOper LogicalStmt
			| VALUE LogicOper ID
			| VALUE LogicOper VALUE
			| ID
			| VALUE;

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
			|
			;
			
ID : [a-zA-Z]+[a-zA-Z0-9]*;
NONE : 'none' ;
TEXTVALUE : '"' ~('\r' | '\n' | '"')* '"' ;
NUMBERVALUE : [0-9]+ 
			| [0-9]+.[0-9]+;