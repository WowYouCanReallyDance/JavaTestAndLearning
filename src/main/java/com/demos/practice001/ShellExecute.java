package com.demos.practice001;

import com.demos.annotations.RunnerMember;
import com.demos.annotations.RunnerTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RunnerMember
public class ShellExecute {

    @RunnerTask
    public void task() throws IOException {
        Process exec = Runtime.getRuntime().exec(new String[] { "ls", "-l" });
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(exec.getInputStream())
        );
        String result = reader.lines().reduce("", (r, s) -> r + s + "\n");
        System.out.println(result);
    }
}
