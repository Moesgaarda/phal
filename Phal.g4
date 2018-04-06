/**
 * Define a grammar called Phal
 * Beware might need NEWLINE+* before include if NEWLINE+ or whitespace becomes a problem
 */

grammar Phal;

program
	:	NEWLINE* (include)* setup NEWLINE* repeat (func)* EOF
	;
  
include
	:	'using' ID NEWLINE+
	;

setup        
	: 	'setup' NEWLINE* '{' NEWLINE* (setupCnt)* NEWLINE* '}'
	;

setupCnt    
	: 	dcl 
	| 	stmt 
	;

dcl         
	: 	varDcl NEWLINE+   
	|   cmpDcl NEWLINE+ 
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
	: 	'group' ID '{' (ID NEWLINE+)+ '}' 
	;

list        
	: 	'list' type ID '{' listCnt ( ',' listCnt)* '}' 
	;

listCnt        
	: 	ID 
	| 	VALUE 
	;

stmt        
	: 	selective NEWLINE+  
	|   iterative NEWLINE+  
	|   funcCall NEWLINE+  
	|   assignment NEWLINE+ 
	|	returnStmt NEWLINE+
	;

selective    
	: 	switchStmt   
	|   ifStmt 
	;

switchStmt        
	:  	'switch' '(' ID ')' '{' caseList '}'   
	|   'switch' '(' VALUE ')' '{' caseList '}' 
	;

 
caseList    
	: 	(caseStmt NEWLINE+)+ defaultCase 
	;

caseStmt        
	: 	'case' VALUE ':' '{'(stmt)*'}' NEWLINE+
	;

defaultCase    
	: 	'default' ':' (stmt)* NEWLINE+
	;

ifStmt        
	: 	'if' '(' expr ')' 'then' '{' NEWLINE* (stmt)* NEWLINE* '}'
	|   'if' '(' expr ')' 'then' '{' NEWLINE* (stmt)* NEWLINE*  '}' 'else'  '{'  NEWLINE* (stmt)* NEWLINE*  '}'
	|   'if' '(' expr ')' 'then' '{' NEWLINE* (stmt)* NEWLINE*  '}' 'else'  ifStmt  
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
	: 	ID ':=' expr NEWLINE+
	| 	ID '+=' expr NEWLINE+
	| 	ID '-=' expr NEWLINE+
	;

repeat    
	: 	'repeat' NEWLINE* '{' NEWLINE* (stmt)* NEWLINE* '}'  
	;

func        
	: 	'define' ID 'with' '(' parameters? ')' 'returnType' rType NEWLINE? '{' (funcCnt)*  returnStmt?'}' 
	;

funcCnt		
	:	varDcl NEWLINE+ 
	| 	stmt 
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
  :		BOOL																			# litBoolExpr
  |		NUMBER																			# litNumExpr
  |		ID																				# idRefExpr
  |		TEXT																			# litTextExpr
  |		funcCall																		# funcExpr
  |		'(' expr ')'																	# parenExpr
  |   	('!'|'not') expr																# unaryExpr
  |		'-' expr																		# unaryExpr
  |		expr ('+'|'-') expr																# infixExpr
  |		expr ('*'|'/'|'%') expr															# infixExpr
  |		expr ('!='|'='| 'is' | 'is not') expr            								# infixExpr	
  |		expr ('<'|'>'|'less than'|'greater than') expr									# infixExpr
  |		expr ('<='|'>='|'less than or equal to'|'greater than or equal to') expr		# infixExpr
  |		expr ('and'|'&') expr															# infixExpr
  |		expr ('or'|'|') expr															# infixExpr 
  ;
  

ID 				: LETTER (LETTER | DIGIT)*;
INTEGER 		: DIGIT | ([1-9](DIGIT)+);
FLOAT 			: (DIGIT | [1-9](DIGIT)+)'.'(DIGIT | (DIGIT)*[1-9]);
NUMBER 			: INTEGER | FLOAT ;
BOOL 			: ('true'|'false' | 'on' |'off'); 
TEXT 			: '"' ~('\r' | '\n' | '"')* '"' ;

VALUE 			: NUMBER | BOOL | TEXT ;  
COMMENT 		: '#' ~('\r' | '\n')* 	-> skip ;
MULTILINECOMMET	: '/*' .*? '*/' 		-> skip ;
WS  			:   [ \t]+ 				-> channel(HIDDEN) ;
NEWLINE			:  [\r\n];

LETTER			: [a-zA-Z];
DIGIT 			: [0-9];
