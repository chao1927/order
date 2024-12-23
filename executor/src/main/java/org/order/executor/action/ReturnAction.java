package org.order.executor.action;

/**
 * 有返回值的 action
 *
 * @author chaobo
 * @date 2024/12/18
 */
public interface ReturnAction<T> {

    String name();

    T action();

}
