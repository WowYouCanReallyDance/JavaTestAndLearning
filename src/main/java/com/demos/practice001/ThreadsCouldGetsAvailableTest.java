package com.demos.practice001;

import com.demos.annotations.RunnerMember;
import com.demos.annotations.RunnerTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RunnerMember
public class ThreadsCouldGetsAvailableTest {

    private Map<String, Thread> pool = new HashMap<>(2000);

    @RunnerTask(false)
    public void tack() {
        for (int i = 0; i < 10; i++) {
            Thread th = new Thread(() -> {
                while (true) {
                    UUID.randomUUID();
                }
            });
            th.setName("TestWorkThread-" + i);
            pool.put(th.getName(), th);
            th.start();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println(pool.size());
            System.out.println(Runtime.getRuntime().maxMemory() / 1024.0 / 1024.0 + "MB");
            System.out.println(Runtime.getRuntime().freeMemory() / 1024.0 / 1024.0 + "MB");
            System.out.println(Runtime.getRuntime().totalMemory() / 1024.0 / 1024.0 + "MB");
        }));
    }
}
