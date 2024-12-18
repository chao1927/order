package org.order.application.command;

/**
 * @author chaobo
 * @date 2024/11/9 15:25
 */
public interface CommandHandler<C> {

    void handle(C command);

}
