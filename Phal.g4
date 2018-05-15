/**
 * Define a grammar called Phal
 * Beware might need NEWLINE+* before include if NEWLINE+ or whitespace becomes a problem
 */

grammar Phal;

program
	:	NEWLINE* include* setup NEWLINE* repeat NEWLINE* (func NEWLINE*)* NEWLINE* EOF
	;
  
include
	:	'using' ID NEWLINE+
	;

setup        
	: 	'setup' NEWLINE* '{' NEWLINE* setupCnt* NEWLINE* '}'
	;

setupCnt    
	: 	dcl NEWLINE+
	| 	stmt 
	;

dcl         
	: 	varDcl    
	|   cmpDcl 
	|   advDataType 
	;

varDcl        
	: 	type ID 
	| 	type ID ':=' expr 
	;
listType
	:	'list of' type
	;  
type        
	: 	'number' 
	|  	'text'  
	|  	'bool' 
	|	'group'
	|	advType
	;
  
advDataType    
	: 	group  
	|   list
	;

cmpDcl    	
	: 	advType ID ':=' 'pin' NUMBER ( ',' NUMBER)*
	;

advType    
	: 	'lightbulb'  
	|  	'motor'   
	|  	'temperatureSensor' 
	;

group        
	: 	'group' ID NEWLINE* '{' NEWLINE* (ID NEWLINE+)+ '}' 		
	;

list        
	: 	'list' type ID '{' (NEWLINE* expr (NEWLINE* expr)*)? NEWLINE* '}'
	;


stmt        
	: 	selective  NEWLINE+
	|   iterative  NEWLINE+
	|   funcCall   NEWLINE+
	|   assignment NEWLINE+
	|	returnStmt NEWLINE*
	|   waitStmt NEWLINE+
	|	advTypeModifier NEWLINE+
	;
	
waitStmt
	:	'wait' expr 'seconds'
	;

	
selective    
	: 	ifStmt    
	|   switchStmt
	;

switchStmt        
	:  	'switch' '(' expr ')' NEWLINE* '{'NEWLINE* caseList '}'   
	;

 
caseList    
	: 	(caseStmt)+ defaultCase 
	;

caseStmt        
	: 	'case' expr ':' NEWLINE* stmt*
	;

defaultCase    
	: 	'default' ':' NEWLINE* stmt*
	;

ifStmt        
	: 	'if' '(' expr ')' 'then' NEWLINE* '{' NEWLINE* block NEWLINE* '}' NEWLINE*
			('else if' '(' expr ')' 'then' NEWLINE* '{' NEWLINE* block NEWLINE* '}')* NEWLINE*
			('else' 'then' NEWLINE* '{' NEWLINE* elseBlock NEWLINE* '}')? 
	;

block
	: stmt*
	;

elseBlock
	: stmt*
	;
	
iterative    
	: 	loopTimes
	|	loopUntil 
	;

loopTimes        
	: 	'loop' expr 'times' NEWLINE* '{' NEWLINE* stmt* NEWLINE* '}'    
	;

loopUntil
	:   'loop' 'until' expr (('increase'|'decrease') ID 'by' NUMBER)? NEWLINE* '{' NEWLINE* stmt* NEWLINE* '}'
	;

funcCall    
	:  	'call' ID 'with' '(' (none | callCnt) ')'
	;

callCnt        
	: 	expr ( ',' expr)* 
	;

assignment    
	: 	ID ('.' ID)? ':=' expr 
	|	ID ('.' ID)? '+=' expr 
	|	ID ('.' ID)? '-=' expr
	;
	
advTypeModifier
	:	'add' expr (',' expr)* 'to' ID
	|	'remove' 'element' expr (',' expr)* 'from' ID
	;
	
repeat    
	: 	'repeat' NEWLINE* '{' NEWLINE* stmt* NEWLINE* '}'  
	;

func        
	: 	'define' ID 'with' '(' parameters ')' 'returnType' (listType | type | none) NEWLINE* '{' NEWLINE* funcCnt* NEWLINE* '}' 
	;

funcCnt		
	:	varDcl NEWLINE* 
	| 	stmt NEWLINE*
	|	list NEWLINE+
	;

parameters    
	:  	param ( ',' param)*  
	|   none
	;

param        
	: 	type ID
	|   listType ID
	;

returnStmt    
	: 'return' expr
	;
	
expr
  :		('true'|'false' | 'on' |'off')													# litBoolExpr
  |		NUMBER																			# litNumExpr	
  |		ID																				# idRefExpr
  | 	TEXT																			# litTextExpr
  |		ID '.' ID																		# idRefExpr
  |		funcCall																		# funcExpr
  |		'(' expr ')'																	# parenExpr
  |   	('!'|'not') expr																# unaryExpr
  |  	 '-' expr                    												    # unaryExpr
  |		expr ('+'|'-') expr																# infixExpr
  |		expr ('*'|'/'|'%') expr															# infixExpr
  |		expr ('!='|'='| 'is' | 'is not') expr            								# infixExpr	
  |		expr ('<'|'>'|'less than'|'greater than') expr									# infixExpr
  |		expr ('<='|'>='|'less than or equal to'|'greater than or equal to') expr		# infixExpr
  |		expr ('and'|'&') expr															# infixExpr
  |		expr ('or'|'|') expr															# infixExpr 
  |		'get' 'element' expr 'from' ID													# litAdvExpr
  ;
 
none : 'none';

TEXT 			: '"' ~('\r' | '\n' | '"')* '"' ;
ID                 : LETTER (LETTER | DIGIT | '_' (LETTER|DIGIT))*;
fragment INTEGER 		: DIGIT+;
fragment FLOAT 			: (DIGIT | [1-9](DIGIT)+)'.'(DIGIT | (DIGIT)*[1-9]);
NUMBER 			: (INTEGER | FLOAT) ;

COMMENT 		: '#' ~('\r' | '\n')* 	-> skip ;
MULTILINECOMMET	: '/*' .*? '*/' 		-> skip ;
WS  			:   [ \t]+ 				-> channel(HIDDEN) ;
NEWLINE			:  [\r\n];

fragment LETTER			: [a-zA-Z];
fragment DIGIT 			: '0'..'9';
