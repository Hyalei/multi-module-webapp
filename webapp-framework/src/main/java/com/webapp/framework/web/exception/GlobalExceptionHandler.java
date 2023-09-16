package com.webapp.framework.web.exception;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.webapp.common.exception.TokenParseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(TokenParseException.class)
    @ResponseBody
    public String TokenParseExceptionError(TokenParseException e) {
        JSONObject errorResult = JSONUtil.createObj();
        errorResult.put("messge", "token解析异常");
        errorResult.put("code", "401");
        return JSONUtil.toJsonPrettyStr(errorResult);
    }
}
