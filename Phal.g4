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
			
VarDcls		: VarDcls VarDcl
			|
			;
			
VarDcl	 	: Type 'id'
			| Type 'id' 'assignment' 'value'
			| Type 'id' 'assignment' 'id';

Type		: 'number'
			| 'text'
			| 'letter'
			| 'bool' ;
			
CmpDcls		: CmpDcl CmpDcls
			|
			;
			
CmpDcl		: AdvType 'id' 'assignment' 'pin' 'pinNumber';

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

GrpCnt		: 'id'
			| 'groupName';

Stmts		: Stmt Stmts
			| 
			;

Stmt		: Selective
			| Iterative
			| FuncCall
			| AssStmt;

Selective	: Switch
			| IfStmt;
			
Switch		: 'switch' 'leftParen' Expr 'rightParen' CaseList 'rightBracket';

CaseList	: Cases DefaultCase;

Cases		: Case Cases
			|
			;
			
Case		: 'number' 'colon' Stmts;

DefaultCase	: 'default' 'colon' Stmts;

IfStmt		: ; /* Implement if */

Iterative	: Loop;

Loop		: 'loop' 'value' 'times' 'leftBracket' Stmts 'rightBracket'
			| 'loop' 'until' 'id' Oper 'value' 'leftBracket' Stmts 'rightBracket';

FuncCall	: 'call' 'id' 'with' 'leftParen' CallParams 'rightParen';

CallParams	: CallParam 'comma' CallParams
			| CallParam
			| 'none'
			;

CallParam	: 'id'
			| 'value';

Assignment	: 'id' 'assignment' AssStmt
			| 'id' 'assignment' LogicalStmt;
			
AssStmt		: 'id' Oper AssStmt
			| 'id' Oper 'value'
			| 'id' Oper 'id'
			| 'value' Oper 'value'
			| 'value' Oper 'id'
			| 'id' CompOp
			| 'value' CompOp
			| 'value'
			| 'id';
			
CompOp: 	'increment'
			| 'decrement';

Oper		: 'plus' 
			| 'minus' 
			| 'divide'
			| 'times';

LogicalStmt	: 'id' LogicOper LogicalStmt
			| 'id' LogicOper 'id'
			| 'id' LogicOper 'value'
			| 'value' LogicOper LogicalStmt
			| 'value' LogicOper 'id'
			| 'value' LogicOper 'value'
			| 'id'
			| 'value';

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
			
Func		: 'define' 'id' 'with' 'leftParen' Params 'rightParen' 'returnType' Type 'leftBracket' FuncContent 'rightBracket';

FuncContent	: VarDcls Stmts ReturnStmt
			|
			;

Params		: Param 'comma' Params
			| Param
			| 'none';

Param		: Type 'paramName';

ReturnStmt	: 'return' 'id'
			| 'return' 'value'
			|
			;