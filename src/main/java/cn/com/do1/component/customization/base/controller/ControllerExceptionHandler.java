package cn.com.do1.component.customization.base.controller;

import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.exception.ExceptionCenter;
import cn.com.do1.common.exception.NonePrintException;
import cn.com.do1.component.customization.base.util.ResultUtil;
import cn.com.do1.component.customization.base.vo.Result;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * 统一异常处理，捕获所有抛到controller层的异常
 *
 * @author zhibinliu
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    private static final transient Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @Autowired
    private HttpServletRequest request;


    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public Result handler(Throwable e) {
        Result result;
        if (e.getCause() instanceof NonePrintException) {
            NonePrintException nonePrintException = (NonePrintException) e.getCause();
            result = ResultUtil.fail(nonePrintException.getErrCode(), nonePrintException.getErrMsg());
        } else if (e.getCause() instanceof BaseException) {
            logger.error("错误信息:", e);
            BaseException baseException = (BaseException) e.getCause();
            ExceptionCenter.addException(e,baseException.getErrMsg(), JSON.toJSONString(request.getParameterMap()));
            result = ResultUtil.fail(baseException.getErrCode(), baseException.getErrMsg());
        } else {
            logger.error("错误信息:", e);
            ExceptionCenter.addException(e,e.getMessage(),JSON.toJSONString(request.getParameterMap()));
            result = ResultUtil.fail(ResultUtil.FAIL_CODE, ResultUtil.FAIL_MSG);
        }
        return result;
    }
}
