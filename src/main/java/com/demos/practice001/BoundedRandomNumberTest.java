package com.demos.practice001;

import com.demos.annotations.RunnerMember;
import com.demos.annotations.RunnerTask;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@RunnerMember
public class BoundedRandomNumberTest {
    @RunnerTask
    public void task() {
        Random random = new Random();

        for (int i : IntStream.range(0, 10).toArray()) {
            System.out.print(random.nextInt(5, 10) + StringUtils.SPACE);
        }
        System.out.println();

        ThreadLocalRandom thRandom = ThreadLocalRandom.current();

        for (int i : IntStream.range(0, 10).toArray()) {
            System.out.print(thRandom.nextInt(5, 10) + StringUtils.SPACE);
        }
        System.out.println();
    }
}
