/**
 * Define a grammar called Phal
 */
grammar Phal;

r 	: Includes? Setup Repeat Funcs? ;

Includes	: Include Includes 
			| Include
			;
			
Include 	: 'using' 'moduleName' ;

Setup 		: 'setup' LEFTBRACKET SetupCnts;

SetupCnts	: SetupCnt SetupCnts
			| RIGHTBRACKET
			;
			
SetupCnt	: Stmt
			| Dcl
			;

Dcl			: VarDcl 
			| CmpDcl
			| Group
			;
			
VarDcls		: VarDcl VarDcls
			| VarDcl
			;
			
VarDcl	 	: Type ID
			| Type ID 'assignment' NUMBERVALUE
			| Type ID 'assignment' BOOLVALUE
			| Type ID 'assignment' TEXTVALUE
			| Type ID 'assignment' ID
			;

Type		: 'number'
			| 'text'
			| 'letter'
			| 'bool' 
			;
			
CmpDcls		: CmpDcl CmpDcls
			| CmpDcl
			;
			
CmpDcl		: AdvType ID 'assignment' 'pin' 'pinNumber';

AdvType		: 'lightBulb'
			| 'coffeeMachine'
			| 'temperatureSensor'
			;
			
Groups		: Group Groups
			| Group
			;

Group		: 'group' 'groupName' LEFTBRACKET GrpCnts RIGHTBRACKET
			;

GrpCnts		: GrpCnt GrpCnts
			| GrpCnt
			;

GrpCnt		: ID
			| 'groupName';

Stmts		: Stmt Stmts
			| Stmt
			;

Stmt		: Selective
			| Iterative
			| FuncCall
			| Assignment
			;

Selective	: Switch
			| IfStmt;
			
Switch		: 'switch' 'leftParen' ID 'rightParen' CaseList RIGHTBRACKET
			| 'switch' 'leftParen' NUMBERVALUE 'rightParen' CaseList RIGHTBRACKET
			| 'switch' 'leftParen' TEXTVALUE 'rightParen' CaseList RIGHTBRACKET
			;

CaseList	: Cases DefaultCase;

Cases		: Case Cases
			| Case
			;
			
Case		: 'number' 'colon' Stmts;

DefaultCase	: 'default' 'colon' Stmts;

IfStmt		: 'if' 'leftParan' LogicalStmt 'rightParan' 'then' LEFTBRACKET Stmts RIGHTBRACKET
			| 'if' 'leftParan' LogicalStmt 'rightParan' 'then' LEFTBRACKET Stmts RIGHTBRACKET 'else' LEFTBRACKET Stmts RIGHTBRACKET
			| 'if' 'leftParan' LogicalStmt 'rightParan' 'then' LEFTBRACKET Stmts RIGHTBRACKET 'else' IfStmt LEFTBRACKET Stmts RIGHTBRACKET;

Iterative	: Loop;

Loop		: 'loop' NUMBERVALUE 'times' LEFTBRACKET Stmts RIGHTBRACKET
			| 'loop' 'until' LogicalStmt LEFTBRACKET Stmts RIGHTBRACKET;

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
			| 'equal'
			;

Repeat		: 'repeat' LEFTBRACKET RepeatCnts 
			;

RepeatCnts  : RepeatCnt RepeatCnts
			| RIGHTBRACKET
			;

RepeatCnt	: Stmts
			;

Funcs		: Func Funcs
			| Func
			;
			
Func		: 'define' ID 'with' 'leftParen' Params 'rightParen' 'returnType' Type LEFTBRACKET FuncContent 
			;
			
FuncContents: FuncContent FuncContents
			| ReturnStmt RIGHTBRACKET
			;
			
FuncContent	: VarDcl 
			| Stmt 
			;

Params		: Param 'comma' Params
			| Param
			| NONE
			;

Param		: Type 'paramName'
			;

ReturnStmt	: 'return' ID
			| 'return' TEXTVALUE
			| 'return' NUMBERVALUE
			| 'return' BOOLVALUE
			|  NONE
			;

LEFTBRACKET: '{';
RIGHTBRACKET: '}';			
ID : [a-zA-Z]+[a-zA-Z0-9]*;
NONE : 'none' ;
TEXTVALUE : '"' ~('\r' | '\n' | '"')* '"' ;
NUMBERVALUE : [0-9]+ 
			| [0-9]+.[0-9]+;
BOOLVALUE : 'true'|'false'
			|'on' |'off'; 
