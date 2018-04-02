/**
 * Define a grammar called Phal
 * Beware might need NEWLINE* before include if newline or whitespace becomes a problem
 */
grammar Phal;

r        
	: 	(Include)* Setup Repeat (Func)* EOF
	;

Include    
	: 	'using' ID NEWLINE
	;

Setup        
	: 	'setup' '{' (SetupCnt)* '}'
	;

SetupCnt    
	: 	Dcl 
	| 	Stmt 
	;

Dcl         
	: 	VarDcl NEWLINE   
	|   CmpDcl NEWLINE 
	|   AdvDataType 
	;

VarDcl        
	: 	Type ID  
	| 	Type ID ':=' Expr 
	;
  
  Type        
	: 	'number' 
	|  	'text'  
	|  	'bool' 
	;
	

  
  FieldID
  :		ID('.'ID)+
  ;
  
AdvDataType    
	: 	Group   
	|   List 
	;

CmpDcl    	
	: 	AdvType ID ':=' 'pin' INTEGER 
	;

AdvType    
	: 	'lightbulb'  
	|  	'coffeeMachine'   
	|  	'temperatureSensor' 
	;

Group        
	: 	'group' ID '{' (ID NEWLINE)+ '}' 
	;

List        
	: 	'list' Type ID '{' ListCnt ( ',' ListCnt)* '}' 
	;

ListCnt        
	: 	ID 
	| 	VALUE 
	;

Stmt        
	: 	Selective   
	|   Iterative   
	|   FuncCall NEWLINE  
	|   Assignment NEWLINE 
	;

Selective    
	: 	Switch   
	|   IfStmt 
	;

Switch        
	:  	'switch' '(' ID ')' { CaseList }   
	|   'switch' '(' VALUE ')' '{' CaseList '}' 
	;

 
CaseList    
	: 	(Case NEWLINE)+ DefaultCase 
	;

Case        
	: 	VALUE ':' '{'(Stmt)*'}' NEWLINE
	;

DefaultCase    
	: 	'default' ':' (Stmt)* NEWLINE
	;

IfStmt        
	: 	'if' '(' Expr ')' 'then' '{' (Stmt)* '}'
	|   'if' '(' Expr ')' 'then' '{' (Stmt)*  '}' 'else'  '{'  (Stmt)*  '}'
	|   'if' '(' Expr ')' 'then' '{' (Stmt)*  '}' 'else'  IfStmt  
	;


Iterative    
	: 	Loop 
	;

Loop        
	: 	'loop' INTEGER 'times' '{' (Stmt)* '}'    
	|   'loop' 'until ' Expr '{' (Stmt)* '}' 
	;

FuncCall    
	: 	'call' ID 'with' '(' Call ')' 
	|  	'call' ID 'with' '(' 'none' ')'  
	|    ID'.'ID'(' Call ')' 
	|    ID'.'ID'(' 'none' ')';

Call        
	: 	Expr ( ',' Expr)* 
	;

Assignment    
	: 	ID ':=' Expr 
	| 	ID '+=' Expr 
	| 	ID '-=' Expr 
	;

Repeat    
	: 	'repeat' '{' (Stmt)* '}'  
	;

Func        
	: 	'define' ID 'with' '(' Parameters? ')' 'returnType' RType '{' (FuncCnt)*  ReturnStmt?'}' 
	;

FuncCnt		
	:	VarDcl NEWLINE 
	| 	Stmt 
	| 	ReturnStmt
	;

RType		
	: 	Type 
	| 	'none'
	;

Parameters    
	:  	Param ( ',' Param)*  
	;

Param        
	: 	Type ID
	;

ReturnStmt    
	: 'return' (ID | VALUE | 'none') 
	;
	
Expr
  :		ID		# unaryExpr
  |		FieldID
  |		DIGIT
  |		TEXT
  |		BOOL
  |		FuncCall
  |		'(' Expr ')'	
  |		'not' Expr
  |		'-' Expr
  |   ('!'|'not') Expr
  |		Expr ('*'|'/'|'%') Expr
  |		Expr ('+'|'-') Expr
  |		Expr ('<'|'>'|'less than'|'greater than') Expr
  |		Expr ('<='|'>='|'less than or equal to'|'greater than or equal to') Expr
  |		Expr ('!='|'='| 'is' | 'is not') Expr            								
  |		Expr ('and'|'&') Expr
  |		Expr ('or'|'|') Expr
  |		
  ;
	
	
//dgfdgfd
NEWLINE    : ('\r') '\n' ;
VALUE : NUMBER | BOOL | TEXT ;  
ID : LETTER (LETTER | DIGIT)*;
LETTER: ('a'..'z' | 'A'..'Z');
TEXT : '"' ~('\r' | '\n' | '"')* '"' ;
DIGIT : ('0'..'9');
INTEGER : (DIGIT |('1'..'9')(DIGIT)+);
FLOAT : ('-')?((DIGIT | ('1'..'9')(DIGIT)+)'.'(DIGIT | (DIGIT)*('1'..'9')));
NUMBER : ('-')?INTEGER | FLOAT ;
BOOL : 'true'|'false' | 'on' |'off'; 
COMMENT : '//' ~[\r\n]*             -> skip ;
