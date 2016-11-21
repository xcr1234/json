grammar JSON;

/*
定义JSON的语法结构
使用antlr的java生成器，生成到com.misaka.xson.parse.json包下。

引用：
《The Definitive ANTLR 4 Reference》第6.2章 《Parse Json》。
*/

json : object   #JsonObject
     	| array     #JsonArray
     ;
/*
JsonObject的结构
JsonObject由若干对Key-Pair组成，它们用逗号分隔。
空的JsonObject就是{}.
*/
object :
           	'{' pair (',' pair)* '}'
           	| '{' '}'
          ;
/*
定义JSON 名称/值对的结构，
也就是
key:value.
这里的STRING注意是 : '"' (ESC | ~["\\])* '"' ;
也就是带有引号的任意字符串。

"firstName" : "John"
*/
pair : STRING ':' value;

array :
     	'[' value (',' value)* ']'
     	| '[' ']'
     ;
/*
Json 值.
Json 值可以是：
    数字（整数或浮点数）
    字符串（在双引号中）
    逻辑值（true 或 false）
    数组（在方括号中）
    对象（在花括号中）
    null
*/
value :
	STRING      #String
	| NUMBER    #NUMBER
	| object    #ObjectValue
	| array     #ArrayValue
	| 'true'    #BOOLEANTRUE
	| 'false'   #BOOLEANFALSE
	| 'null'    #NULL
	;
STRING : '"' (ESC | ~["])* '"' ;
fragment  ESC : '\\' (["\\/bfnrt] | UNICODE);
fragment UNICODE : 'u' HEX HEX HEX HEX;
fragment HEX : [0-9a-fA-F];
NUMBER : '-'? INT '.' INT EXP?
	| '-'? INT EXP
	| '-'? INT
	;
fragment INT : '0'| [1-9][0-9]*;
fragment EXP : [Ee] [+\-]? INT;

WS : (' '|'\t'|'\r'|'\n')+ -> skip ;