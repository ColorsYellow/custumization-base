package cn.com.do1.component.customization.base.controller;

import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.exception.NonePrintException;
import cn.com.do1.component.customization.base.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
//@RequestMapping("/open")
public class TestController extends BaseController {


    @RequestMapping("/test2")
    public @ResponseBody
    Result test2() {
        return addData("数据1", "可以哦！");
    }

    @ResponseBody
    @RequestMapping("/test1")
    public Result test1() throws BaseException, Exception {
        Map param = new HashMap();
        param.put("user",getDqdpUser());
        return addData(param,"haha");
        //return addData("数据xxxx");
    }

    @ResponseBody
    @RequestMapping("/test3")
    public Result test3() throws BaseException, Exception {

        throw  new Exception("报错了");
        //return addData("数据xxxx");
    }

    @ResponseBody
    @RequestMapping("/test4")
    public Result test4() throws NonePrintException {

        throw  new NonePrintException(-1,"报错了");
        //return addData("数据xxxx");
    }
}
