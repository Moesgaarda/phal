/**
 * Define a grammar called Phal
 */
grammar Phal;

r        : (Include)* Setup Repeat (Func)*;

Includes	: Include Includes 
			| Include
			;
			
Include 	: 'using' INCLUDE ;

Setup        : 'setup' '{' (SetupCnt)* '}';

SetupCnt    : Stmt | Dcl ;

Dcl			: VarDcl 
			| CmpDcl
			| Group
			;
			
VarDcls		: VarDcl VarDcls
			| VarDcl
			;
			
VarDcl	 	: Type ID
			| Type ID ASSIGNMENT Value
			| Type ID ASSIGNMENT ID
			;

Type		: 'number'
			| 'text'
			| 'letter'
			| 'bool' 
			;
		
Value		: NUMBERVALUE
			| BOOLVALUE
			| TEXTVALUE
			;
			
CmpDcls		: CmpDcl CmpDcls
			| CmpDcl
			;
			
CmpDcl		: AdvType ID ASSIGNMENT 'pin' PINNUMBER;

VarDcl        : Type ID  | Type ID '=' AssStmt ;
 
Type        : NUMBER |  TEXT  |  LETTER  |  BOOL ;

Group		: 'group' GROUPNAME  STARTBRACKET GrpCnts ENDBRACKET
			;

AdvType    : 'lightbulb'  |  'coffeeMachine'   |    'temperatureSensor' ;

GrpCnt		: ID
			| GROUPNAME ;

GrpCnt            : ID   |   'groupName' ;

List        : 'list' Type 'listName' '{' ListCnt ( ',' ListCnt)* '}' ;

ListCnt        : ID | VALUE ;

Stmt        : (Selective   |   Iterative   |   FuncCall NEWLINE  |   Assignment NEWLINE );

Cases		: Case Cases
			| Case
			;
			
Case		: 'number' COLON Stmts;

DefaultCase	: 'default' COLON Stmts;

IfStmt		: 'if' STARTPAREN Condition ENDPAREN 'then' STARTBRACKET Stmts ENDBRACKET
			| 'if' STARTPAREN Condition ENDPAREN 'then' STARTBRACKET Stmts ENDBRACKET 'else' STARTBRACKET Stmts ENDBRACKET
			| 'if' STARTPAREN Condition ENDPAREN 'then' STARTBRACKET Stmts ENDBRACKET 'else' IfStmt 
			;

DefaultCase    : 'default' ':' (Stmt)* NEWLINE;

Loop		: 'loop' NUMBERVALUE 'times' STARTBRACKET Stmts ENDBRACKET
			| 'loop' 'until' Condition STARTBRACKET Stmts ENDBRACKET;

FuncCall	: 'call' ID 'with' STARTPAREN Call ENDPAREN;

Call		: CallParams
			| NONE
			;
			
CallParams	: CallParam COMMA CallParams
			| CallParam
			;

Loop        : 'loop' VALUE 'times' '{' (Stmt)* '}'    |    'loop' 'until' Condition '{' (Stmt)* '}' ;

Assignment	: ID ASSIGNMENT AssStmt
			| ID ASSIGNMENT LogicalStmt;

AssStmt		: ID Oper AssStmt
			| NUMBERVALUE Oper AssStmt
			| ID CompOp Oper AssStmt
			| ID CompOp
			| NUMBERVALUE
			| ID;
			
CompOp		: INCREMENT
			| DECREMENT
			;

Oper		: PLUS 
			| MINUS 
			| TIMES
			| DIVIDE
			| MODULO
			;

Condition   : ID LogicOper LogicalStmt
			| NUMBERVALUE LogicOper LogicalStmt
			| BOOLVALUE LogicOper LogicalStmt
			| ID
			| BOOLVALUE;

LogicalStmt	: ID LogicOper LogicalStmt
			| NUMBERVALUE LogicOper LogicalStmt
			| BOOLVALUE LogicOper LogicalStmt
			| NUMBERVALUE
			| ID
			| BOOLVALUE;

LogicOper	: LESSTHAN
			| LESSTHANEQUAL
			| GREATERTHAN 
			| GREATERTHANEQUAL
			| NOTEQUAL
			| EQUALTO
			| AND
			| OR
			;

AssStmt    : ID Oper AssStmt   |  VALUE Oper AssStmt   |   ID CompOp Oper AssStmt  |    ID CompOp |   VALUE   |    ID ;

Oper         : '+'  |  '-'  |  '/' |   '*' |   '%' ;

CompOp    : 'increment'    |    'decrement' ;

Funcs		: Func Funcs
			| Func
			;
			
Func		: 'define' ID 'with' STARTPAREN Parameters ENDPAREN 'returnType' Type STARTBRACKET FuncContent 
			;
			
FuncContents: FuncContent FuncContents	
			| ReturnStmt ENDBRACKET
			;
			
FuncContent	: VarDcl 
			| Stmt 
			;

Parameters	: Params
			| NONE
			;

Params		: Param COMMA Params
			| Param
			;

Param		: Type ID
			;
			
ReturnStmt	: 'return' ID
			| 'return' Value
			|  NONE
			;

EQUALTO		: 'is' | '==';
NOTEQUAL	: 'is not' | '!=' ;
LESSTHAN	: '<';
LESSTHANEQUAL: '<=';
GREATERTHAN	: '>';
GREATERTHANEQUAL : '>=';
AND			: 'and' | '&&' ;
OR			: 'or'  | '||'  ; 
PLUS        : '+';
MINUS        : '-';
TIMES        : '*';
DIVIDE        : '/';
MODULO        : '%';
INCREMENT	: '++';
DECREMENT	: '--';
ASSIGNMENT	: '=';
COLON		: ':';
STARTPAREN	: '(';
ENDPAREN	: ')';
COMMA		: ',';
STARTBRACKET: '{';
ENDBRACKET	: '}';
INCLUDE		: [a-zA-Z]+[a-zA-Z0-9]*;
GROUPNAME 	: [a-zA-Z]+[a-zA-Z0-9]*;
ID 			: [a-zA-Z]+[a-zA-Z0-9]*;
NONE 		: 'none' ;
TEXTVALUE 	: '"' ~('\r' | '\n' | '"')* '"' ;
NUMBERVALUE : [0-9]+ 
			| [0-9]+.[0-9]+;
BOOLVALUE 	: 'true'|'false'
			|'on' |'off'; 
PINNUMBER	: [0-9]+;

Param        : Type 'paramName' ;

ReturnStmt    : 'return' (ID | VALUE) ;

NEWLINE    : ('\r') '\n' ;
VALUE : ID+ ;  // Skal have en rigtig v�rdi
ID : ('a'..'z' | 'A'..'Z') (('a'..'z' | 'A'..'Z') | ('0'..'9'))*;
LETTER: ('a'..'z' | 'A'..'Z');
TEXT : '"' ~('\r' | '\n' | '"')* '"' ;
NUMBER : ('0'..'9');
BOOL : 'true'|'false' | 'on' |'off'; 
NONE : 'none';
