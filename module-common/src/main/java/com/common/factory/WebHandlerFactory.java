package com.common.factory;

import com.common.base.BizHelper;
import com.common.handler.AbstractHandler;
import com.common.handler.DecryptHandler;
import com.common.handler.ParamsValidateHandler;
import com.common.handler.TargetHandler;

/**
 * @author 工厂模式实现拦截器链
 * @Desc
 */
public class WebHandlerFactory implements Factory<AbstractHandler> {

    //防止指令重排
    private volatile static WebHandlerFactory factory;
    private AbstractHandler handler;

    /**
     * 私有构造方法创建实例
     */
    private WebHandlerFactory() {
        this.handler = createObject();
    }


    /**
     * 单例模式唯一对外接口
     *
     * @return
     */
    public static WebHandlerFactory getInstance() {
        if (factory == null) {
            synchronized (WebHandlerFactory.class) {
                if (factory == null) {
                    factory = new WebHandlerFactory();
                }
            }
        }
        return factory;
    }

    /**
     * 实现拦截器链
     * 一般顺序是 登录拦截>校验签名>参数解密>参数校验(hibernate.validator参数校验)
     * 因为用户token一般放在请求头里
     * 参数放在请求体中需要解密
     *
     * @return
     */
    private static AbstractHandler createObject() {
        DecryptHandler decryptHandler = new DecryptHandler(1);
        ParamsValidateHandler paramsValidateHandler = new ParamsValidateHandler(2);
        TargetHandler targetHandler = new TargetHandler(3);
        return BizHelper.createHandlerChain(decryptHandler, paramsValidateHandler, targetHandler);
    }


    /**
     * 返回创建的工厂
     *
     * @return
     */
    @Override
    public AbstractHandler getObject() {
        return this.handler;
    }
}
