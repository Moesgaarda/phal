/**
 * Define a grammar called Phal
 */
grammar Phal;

r 	: Includes? Setup Repeat Funcs? ;

Includes	: Include Includes 
			| Include
			;
			
Include 	: 'using' 'moduleName' ;

Setup 		: 'setup' STARTBRACKET SetupCnts;

SetupCnts	: SetupCnt SetupCnts
			| ENDBRACKET
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

Group		: 'group' 'groupName' STARTBRACKET GrpCnts ENDBRACKET
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
			
Switch		: 'switch' STARTPAREN ID ENDPAREN CaseList ENDBRACKET
			| 'switch' STARTPAREN NUMBERVALUE ENDPAREN CaseList ENDBRACKET
			| 'switch' STARTPAREN TEXTVALUE ENDPAREN CaseList ENDBRACKET
			;

CaseList	: Cases DefaultCase;

Cases		: Case Cases
			| Case
			;
			
Case		: 'number' 'colon' Stmts;

DefaultCase	: 'default' 'colon' Stmts;

IfStmt		: 'if' STARTPAREN LogicalStmt 'ENDParan' 'then' STARTBRACKET Stmts ENDBRACKET
			| 'if' STARTPAREN LogicalStmt 'ENDParan' 'then' STARTBRACKET Stmts ENDBRACKET 'else' STARTBRACKET Stmts ENDBRACKET
			| 'if' STARTPAREN LogicalStmt 'ENDParan' 'then' STARTBRACKET Stmts ENDBRACKET 'else' IfStmt STARTBRACKET Stmts ENDBRACKET;

Iterative	: Loop;

Loop		: 'loop' NUMBERVALUE 'times' STARTBRACKET Stmts ENDBRACKET
			| 'loop' 'until' LogicalStmt STARTBRACKET Stmts ENDBRACKET;

FuncCall	: 'call' ID 'with' STARTPAREN CallParams ENDPAREN;

CallParams	: CallParam COMMA CallParams
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

Repeat		: 'repeat' STARTBRACKET RepeatCnts 
			;

RepeatCnts  : RepeatCnt RepeatCnts
			| ENDBRACKET
			;

RepeatCnt	: Stmts
			;

Funcs		: Func Funcs
			| Func
			;
			
Func		: 'define' ID 'with' STARTPAREN Params ENDPAREN 'returnType' Type STARTBRACKET FuncContent 
			;
			
FuncContents: FuncContent FuncContents
			| ReturnStmt ENDBRACKET
			;
			
FuncContent	: VarDcl 
			| Stmt 
			;

Params		: Param COMMA Params
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


STARTPAREN: '(';
ENDPAREN: ')';
COMMA: ',';
STARTBRACKET: '{';
ENDBRACKET: '}';			
ID : [a-zA-Z]+[a-zA-Z0-9]*;
NONE : 'none' ;
TEXTVALUE : '"' ~('\r' | '\n' | '"')* '"' ;
NUMBERVALUE : [0-9]+ 
			| [0-9]+.[0-9]+;
BOOLVALUE : 'true'|'false'
			|'on' |'off'; 
