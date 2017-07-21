package cn.com.do1.component.customization.base.util;

import cn.com.do1.common.util.string.StringUtil;
import cn.com.do1.component.customization.base.vo.Result;

/**
 * 返回结果工具类
 *
 * @author zhibinliu
 */
public class ResultUtil {

    public static final String FAIL_MSG = "出现异常";
    public static final String FAIL_CODE = "-1";
    public static final String SUCCESS_MSG = "操作成功";

    /**
     * 统一错误信息
     *
     * @param code
     * @param desc
     * @return
     */
    public static Result fail(String code, String desc) {
        Result result = new Result();
        if (StringUtil.isNullEmpty(code)) {
            result.setCode(-1);
        } else {
            result.setCode(Integer.parseInt(code));
        }
        if (StringUtil.isNullEmpty(desc)) {
            result.setDesc(FAIL_MSG);
        } else {
            result.setDesc(desc);
        }
        result.setDesc(desc);
        return result;
    }

    /**
     * 正确返回数据
     *
     * @param desc
     * @return
     */
    public static Result success(Object o, String desc) {
        Result result = new Result();
        result.setCode(0);
        result.setDesc(desc);
        result.setData(o);
        return result;
    }

}
