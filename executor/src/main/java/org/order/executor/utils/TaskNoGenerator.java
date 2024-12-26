package org.order.executor.utils;

import org.order.common.enums.TaskTypeEnum;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

/**
 * TODO
 *
 * @author chaobo
 * @date 2024/12/25
 */
public class TaskNoGenerator {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    private static final String TASK_NO_FORMAT = "%s%s%s";



    public static String generate(TaskTypeEnum taskType) {
        LocalDateTime now = LocalDateTime.now();
        String datePart = formatter.format(now);
        Integer randomPart = ThreadLocalRandom.current().nextInt(0, 10000);
        return String.format(TASK_NO_FORMAT, taskType.getPrefix(), formatter.format(now), randomPart);
    }

}
