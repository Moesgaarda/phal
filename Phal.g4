/**
 * Define a grammar called Phal
 */
grammar Phal;

TYPE
	: 'id' 'value' 'bool'
	;

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
			
VarDcls		: VarDcl
			|
			;
			
VarDcl	 	: Type 'id'
			| Type 'id' 'assignment' 'value'
			| Type 'id' 'assignment' 'id';

Type		: 'number'
			| 'text'
			| 'letter'
			| 'bool' ;
			
			