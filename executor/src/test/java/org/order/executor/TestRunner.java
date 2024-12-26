package org.order.executor;

import java.util.concurrent.CompletableFuture;

/**
 * TODO
 *
 * @author chaobo
 * @date 2024/12/26
 */
public class TestRunner {

    public static void main(String[] args) {
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Task 1 complete");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1500);
                System.out.println("Task 2 complete");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        CompletableFuture<Void> allOf = CompletableFuture.allOf(future1, future2);

        // 不阻塞当前线程，任务完成后执行回调
        allOf.thenRun(() -> {
            System.out.println("All tasks completed.");
        });

        // 继续执行其他任务，不会阻塞当前线程
        System.out.println("Main thread is not blocked.");

        try {
            Thread.sleep(3000);
            System.out.println("Main thread sleep");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
