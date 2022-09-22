package com.common.handler;

import javax.servlet.http.HttpServletRequest;

/**
 * 抽象类的有参构造,子类需实现
 *
 * @author 链式拦截抽象类, 实现顺序重排
 * @Desc
 */
public abstract class AbstractHandler<T> implements Comparable<AbstractHandler> {
    protected int order;
    protected AbstractHandler next;

    /**
     * 可传入T类型参数
     *
     * @param request
     * @param t
     * @return
     */
    public abstract Object handler(HttpServletRequest request, T t) throws Throwable;

    protected Object handleNext(HttpServletRequest request, T t, Object result) throws Throwable {
        if (null != this.next) {
            return next.handler(request, t);
        } else {
            return result;
        }
    }

    //构造
    public AbstractHandler(int order) {
        this.order = order;
    }

    //set
    public void setNext(AbstractHandler handler) {
        this.next = handler;
    }

    //get
    public AbstractHandler getNext() {
        return this.next;
    }

    @Override
    public int compareTo(AbstractHandler o) {
        return this.order - o.order;
    }
}
