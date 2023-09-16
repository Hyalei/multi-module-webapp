package com.webapp.common.exception;

/**
 * token解析异常
 */
public class TokenParseException extends RuntimeException{


    public TokenParseException(){

    }
    public TokenParseException(String s){
        super(s);
    }
}
