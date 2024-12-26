package org.order.executor.cache;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatterBenchmark {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    public void testDateFormat() {
        LocalDateTime now = LocalDateTime.now();

        // 使用 DateTimeFormatter.ofPattern()
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            now.format(formatter);
        }
        long duration = System.currentTimeMillis() - start;
        System.out.println("Using ofPattern() took: " + duration + " ms");

        // 使用 ThreadLocal 缓存的 DateTimeFormatter
        start = System.currentTimeMillis();
        ThreadLocal<DateTimeFormatter> threadLocalFormatter = ThreadLocal.withInitial(() -> DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        for (int i = 0; i < 1000000; i++) {
            now.format(threadLocalFormatter.get());
        }
        duration = System.currentTimeMillis() - start;
        System.out.println("Using ThreadLocal took: " + duration + " ms");
    }
}
