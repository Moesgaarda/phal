/**
 * Define a grammar called Phal
 * Beware might need NEWLINE* before include if newline or whitespace becomes a problem
 */

grammar Phal;

program
	:	(include)* setup repeat (func)* EOF
	;
  
include
	:	'using' ID NEWLINE
	;

setup        
	: 	'setup' '{' (setupCnt)* '}'
	;

setupCnt    
	: 	dcl 
	| 	stmt 
	;

dcl         
	: 	varDcl NEWLINE   
	|   cmpDcl NEWLINE 
	|   advDataType 
	;

varDcl        
	: 	type ID  
	| 	type ID ':=' expr 
	;
  
type        
	: 	'number' 
	|  	'text'  
	|  	'bool' 
	;
  
advDataType    
	: 	group   
	|   list 
	;

cmpDcl    	
	: 	advType ID ':=' 'pin' INTEGER 
	;

advType    
	: 	'lightbulb'  
	|  	'coffeeMachine'   
	|  	'temperatureSensor' 
	;

group        
	: 	'group' ID '{' (ID NEWLINE)+ '}' 
	;

list        
	: 	'list' type ID '{' listCnt ( ',' listCnt)* '}' 
	;

listCnt        
	: 	ID 
	| 	VALUE 
	;

stmt        
	: 	selective   
	|   iterative   
	|   funcCall NEWLINE  
	|   assignment NEWLINE 
	;

selective    
	: 	switchStmt   
	|   ifStmt 
	;

switchStmt        
	:  	'switch' '(' ID ')' { caseList }   
	|   'switch' '(' VALUE ')' '{' caseList '}' 
	;

 
caseList    
	: 	(caseStmt NEWLINE)+ defaultCase 
	;

caseStmt        
	: 	VALUE ':' '{'(stmt)*'}' NEWLINE
	;

defaultCase    
	: 	'default' ':' (stmt)* NEWLINE
	;

ifStmt        
	: 	'if' '(' expr ')' 'then' '{' (stmt)* '}'
	|   'if' '(' expr ')' 'then' '{' (stmt)*  '}' 'else'  '{'  (stmt)*  '}'
	|   'if' '(' expr ')' 'then' '{' (stmt)*  '}' 'else'  ifStmt  
	;

iterative    
	: 	loop 
	;

loop        
	: 	'loop' INTEGER 'times' '{' (stmt)* '}'    
	|   'loop' 'until' expr '{' (stmt)* '}' 
	;

funcCall    
	: 	'call' ID 'with' '(' call ')' 
	|  	'call' ID 'with' '(' 'none' ')'  
	|    ID'.'ID'(' call ')' 
	|    ID'.'ID'(' 'none' ')';

call        
	: 	expr ( ',' expr)* 
	;

assignment    
	: 	ID ':=' expr 
	| 	ID '+=' expr 
	| 	ID '-=' expr 
	;

repeat    
	: 	'repeat' '{' (stmt)* '}'  
	;

func        
	: 	'define' ID 'with' '(' parameters? ')' 'returnType' rType '{' (funcCnt)*  returnStmt?'}' 
	;

funcCnt		
	:	varDcl NEWLINE 
	| 	stmt 
	| 	returnStmt
	;

rType		
	: 	type 
	| 	'none'
	;

parameters    
	:  	param ( ',' param)*  
	;

param        
	: 	type ID
	;

returnStmt    
	: 'return' (ID | VALUE | 'none') 
	;
	
expr
  :		ID																				# idRefExpr
  |		DIGIT																			# litNumExpr
  |		TEXT																			# litTextExpr
  |		BOOL																			# litBoolExpr
  |		funcCall																		# funcExpr
  |		'(' expr ')'																	# parenExpr
  |		'-' expr																		# unaryExpr
  |   ('!'|'not') expr																	# unaryExpr
  |		expr ('*'|'/'|'%') expr															# infixExpr
  |		expr ('+'|'-') expr																# infixExpr
  |		expr ('<'|'>'|'less than'|'greater than') expr									# infixExpr
  |		expr ('<='|'>='|'less than or equal to'|'greater than or equal to') expr		# infixExpr
  |		expr ('!='|'='| 'is' | 'is not') expr            								# infixExpr	
  |		expr ('and'|'&') expr															# infixExpr
  |		expr ('or'|'|') expr															# infixExpr
  ;
  


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
COMMENT : '#' ~('\r' | '\n')* 		-> skip ;
MULTILINECOMMET: '/*' .*? '*/' 		-> skip ;



