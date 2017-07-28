package cn.com.do1.component.customization.base.controller;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.exception.NonePrintException;
import cn.com.do1.common.util.string.StringUtil;
import cn.com.do1.common.util.web.WebUtil;
import cn.com.do1.component.addressbook.contact.service.IContactService;
import cn.com.do1.component.addressbook.contact.vo.UserInfoVO;
import cn.com.do1.component.addressbook.contact.vo.UserOrgVO;
import cn.com.do1.component.core.WxqyhAppContext;
import cn.com.do1.component.customization.base.util.ResultUtil;
import cn.com.do1.component.customization.base.vo.Result;
import cn.com.do1.dqdp.core.DqdpAppContext;
import cn.com.do1.dqdp.core.permission.IUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 控制器base类
 *
 * @author zhibinliu
 */
public class BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
    @Autowired
    private HttpServletRequest request;

    @Resource(name = "contactService")
    private IContactService contactService;

    /**
     * 添加返回的数据
     *
     * @param o 对象信息
     * @return
     */
    public Result addResult(Object o) {
        return addResult(o, ResultUtil.SUCCESS_MSG);
    }

    /**
     * 添加返回的数据和说明
     *
     * @param o    对象信息
     * @param desc 返回文字说明
     * @return
     */
    public Result addResult(Object o, String desc) {
        return ResultUtil.success(o, desc);
    }

    /**
     * 获取微信端登录用户
     *
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public UserInfoVO getWxUser() throws Exception, BaseException {
        return WxqyhAppContext.getCurrentUser(request);
    }

    /**
     * 获取后台登录用户
     *
     * @return
     * @throws Exception
     * @throws BaseException
     */
    public UserOrgVO getDqdpUser() throws Exception, BaseException {
        IUser user = (IUser) DqdpAppContext.getCurrentUser();
        return getDqdpUser(user.getUsername());
    }

    public UserOrgVO getDqdpUser(String userId) throws Exception, BaseException {
        if (StringUtil.isNullEmpty(userId)) {
            throw new NonePrintException("1002", "登录信息为空，请刷页面重试");
        }
        UserOrgVO userInfoVO = contactService.getOrgByUserId(userId);
        if (userInfoVO == null) {
            logger.error("获取登录人" + userId + "orgId的信息为空");
            throw new NonePrintException("1001", "登录人的信息为空");
        }
        return userInfoVO;
    }

    /**
     * 获取分页对象
     *
     * @return
     */
    public Pager getPager() {
        long total = this.getPageTotalRows();
        Pager pager = new Pager(request, this.getPageSize());
        if (total > -1L) {
            pager.setTotalRows(total);
        }

        return pager;
    }

    public long getPageTotalRows() {
        String pageSize = WebUtil.getParm(request, "pageTotalRows", "0");
        return "0".equals(pageSize) ? -1L : (long) Integer.parseInt(pageSize);
    }

    public int getPageSize() {
        String pageSize = WebUtil.getParm(request, "pageSize", "0");
        return "0".equals(pageSize) ? 10 : Integer.parseInt(pageSize);
    }
}
