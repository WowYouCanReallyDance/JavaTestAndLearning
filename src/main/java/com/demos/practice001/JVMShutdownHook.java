package com.demos.practice001;

import com.demos.annotations.RunnerMember;
import com.demos.annotations.RunnerTask;

@RunnerMember
public class JVMShutdownHook {
    static long start = System.currentTimeMillis();

    @RunnerTask
    public void task() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            long end = System.currentTimeMillis();
            System.out.println(end - start);
        }));
    }
}
