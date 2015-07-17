grammar GraphQLShorthand;

schema
    : definition+
    ;

definition
    : enumDefinition
    | typeDefinition
    | interfaceDefinition
    | unionDefinition
    ;

enumDefinition
    : 'enum' NAME '{' NAME (',' NAME)* '}'
    ;

unionDefinition
    : 'union' NAME '=' NAME ('|' NAME)*
    ;

typeDefinition
    : 'type' NAME (':' NAME)? '{' field+ '}'
    ;

interfaceDefinition
    : 'interface' NAME '{' field+ '}'
    ;

field
    : NAME parameters? ':' fieldType
    ;

parameters
    : '(' parameter (',' parameter)* ')'
    ;

parameter
    : NAME ':' fieldType
    ;

fieldType
    : type nonNullType?
    | listType
    ;

type
    : BOOLEAN
    | STRING
    | INT
    | NAME
    ;

listType
    :   '[' type ']'
    ;

nonNullType
    : '!'
    ;

BOOLEAN : 'Boolean' ;
STRING  : 'String' ;
INT     : 'Int' ;
NAME    : [_A-Za-z][_0-9A-Za-z]* ;
WS      : [ \t\n\r]+ -> skip ;