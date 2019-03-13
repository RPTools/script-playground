lexer grammar ChatLexer;


@header {
    package net.rptools.script.parser;
}

// Default Mode
OPEN_INLINE_SCRIPT                  : '[[[' -> mode(INLINE_SCRIPT);
OPEN_INLINE_ROLL                    : '[[' -> mode(INLINE_ROLL);
TEXT                                : .+?;



mode INLINE_ROLL;
CLOSE_INLINE_ROLL                   : ']]' -> mode(DEFAULT_MODE);
ROLL_BODY                           : .+?;

ROLL_MULTIlINE_COMMENT              : '/*' .*? '*/';

ROLL_STRING                         : ROLL_SINGLE_QUOTE (ROLL_SINGLE_STRING_ESC | ~['\\])* ROLL_SINGLE_QUOTE
                                    | ROLL_DOUBLE_QUOTE (ROLL_DOUBLE_STRING_ESC | ~["\\])* ROLL_DOUBLE_QUOTE
                                    ;
fragment ROLL_DIGIT                 : [0-9];
fragment ROLL_SINGLE_QUOTE          : '\'';
fragment ROLL_DOUBLE_QUOTE          : '"';

fragment ROLL_SINGLE_STRING_ESC     : '\\' (ROLL_STRING_ESC | ROLL_SINGLE_QUOTE);
fragment ROLL_DOUBLE_STRING_ESC     : '\\' (ROLL_STRING_ESC | ROLL_DOUBLE_QUOTE);
fragment ROLL_STRING_ESC            : '\\' ([\\/bfnrt] | ROLL_UNICODE);
fragment ROLL_UNICODE               : 'u' ROLL_HEX ROLL_HEX ROLL_HEX ROLL_HEX;
fragment ROLL_HEX                   : [0-9a-fA-F];



mode INLINE_SCRIPT;
CLOSE_INLINE_SCRIPT                 : ']]]' -> mode(DEFAULT_MODE);
SCRIPT_BODY                         : .+?;

SCRIPT_MULTIlINE_COMMENT            : '/*' .*? '*/';

SCRIPT_STRING                       : ROLL_SINGLE_QUOTE (SCRIPT_SINGLE_STRING_ESC | ~['\\])* SCRIPT_SINGLE_QUOTE
                                    | SCRIPT_DOUBLE_QUOTE (SCRIPT_DOUBLE_STRING_ESC | ~["\\])* SCRIPT_DOUBLE_QUOTE
                                    ;
fragment SCRIPT_DIGIT               : [0-9];
fragment SCRIPT_SINGLE_QUOTE        : '\'';
fragment SCRIPT_DOUBLE_QUOTE        : '"';

fragment SCRIPT_SINGLE_STRING_ESC   : '\\' (SCRIPT_STRING_ESC | SCRIPT_SINGLE_QUOTE);
fragment SCRIPT_DOUBLE_STRING_ESC   : '\\' (SCRIPT_STRING_ESC | SCRIPT_DOUBLE_QUOTE);
fragment SCRIPT_STRING_ESC          : '\\' ([\\/bfnrt] | SCRIPT_UNICODE);
fragment SCRIPT_UNICODE             : 'u' SCRIPT_HEX SCRIPT_HEX SCRIPT_HEX SCRIPT_HEX;
fragment SCRIPT_HEX                 : [0-9a-fA-F];