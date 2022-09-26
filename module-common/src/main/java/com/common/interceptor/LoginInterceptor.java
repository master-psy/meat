package com.common.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 * @Desc 登录拦截器
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    //@Autowired
    //private LyUserMapper lyUserMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //String token = request.getHeader("X-TOKEN");
        //if (StringUtils.isNotBlank(token)) {
        //    String userPhone = JwtUtils.getClaimField(token, JwtUtils.USER_PHONE);
        //    if (StringUtils.isBlank(userPhone)) {
        //        //todo 抛出异常
        //        throw new BizException(ApiCodeEnum.PLEASE_LOGIN);
        //    }
        //    LyUserExample example = new LyUserExample();
        //    example.createCriteria().andPhoneEqualTo(userPhone);
        //    List<LyUser> lyUsers = lyUserMapper.selectByExample(example);
        //    if (!lyUsers.isEmpty()) {
        //        LyUser lyUser = lyUsers.get(0);
        //        UserThreadLocal.set(lyUser);
        //    } else {
        //        //todo 抛出异常
        //        throw new BizException(ApiCodeEnum.PLEASE_REGISTER);
        //    }
        //}
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
