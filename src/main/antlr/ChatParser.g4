parser grammar ChatParser;


@header {
    package net.rptools.script.parser;
}

options { tokenVocab=ChatLexer; }

chat                : (script | inlineRoll | text)* ;

script              : OPEN_INLINE_SCRIPT scriptBody CLOSE_INLINE_SCRIPT;

text                : TEXT+;

inlineRoll          : OPEN_INLINE_ROLL rollBody CLOSE_INLINE_ROLL;

rollBody            : (ROLL_MULTIlINE_COMMENT | ROLL_STRING | ROLL_BODY)+;

scriptBody          : (SCRIPT_MULTIlINE_COMMENT | SCRIPT_STRING | SCRIPT_BODY)+;
