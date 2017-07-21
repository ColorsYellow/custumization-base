package cn.com.do1.component.customization.base.vo;

import cn.com.do1.common.exception.BaseException;

/**
 * 统一返回结果类
 * @author zhibinliu
 */
public class Result {
    private int code;
    private String desc;
    private Object data;

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
