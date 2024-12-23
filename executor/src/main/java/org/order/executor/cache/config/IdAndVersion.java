package org.order.executor.cache.config;

/**
 * id 和版本号， 用于标识配置
 *
 * @author chaobo
 * @date 2024/12/19
 */
public interface IdAndVersion {

    Long id();

    Integer version();

}
