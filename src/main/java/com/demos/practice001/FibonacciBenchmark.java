package com.demos.practice001;

import com.demos.annotations.RunnerMember;
import com.demos.annotations.RunnerTask;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigInteger;

@RunnerMember
public class FibonacciBenchmark {

    static final BigInteger ONE = BigInteger.ONE;
    static final BigInteger TWO = BigInteger.TWO;

    @RunnerTask(false)
    public void task() {
        long number = 40L;
        int count = 10;
        Pair<Long, Long> result1 = benchmark(number, count);
        System.out.println(
                StringUtils.join(
                        "*************************************************", "\n",
                        "Cost time: ", result1.getLeft(), "ms", "\n",
                        "Fibonacci result: ", result1.getRight()
                )
        );

        Pair<Long, BigInteger> result2 = benchmark(BigInteger.valueOf(number), count);
        System.out.println(
                StringUtils.join(
                        "*************************************************", "\n",
                        "Cost time: ", result2.getLeft(), "ms", "\n",
                        "Fibonacci result: ", result2.getRight()
                )
        );
    }

    Pair<Long, Long> benchmark(long number, final int count) {
        long result = 0L;
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            result = fibonacci(number);
        }
        long end = System.currentTimeMillis();
        return Pair.of((end - start) / count, result);
    }

    Pair<Long, BigInteger> benchmark(BigInteger number, final int count) {
        BigInteger result = BigInteger.ZERO;
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            result = fibonacci(number);
        }
        long end = System.currentTimeMillis();
        return Pair.of((end - start) / count, result);
    }

    long fibonacci(long number) {
        if (number > 2) {
            return fibonacci(number - 1) + fibonacci(number - 2);
        } else {
            return 1;
        }
    }

    BigInteger fibonacci(BigInteger number) {
        if (gt(number, TWO)) {
            return fibonacci(number.subtract(ONE)).add(fibonacci(number.subtract(TWO)));
        } else {
            return ONE;
        }
    }

    boolean lt(BigInteger a, BigInteger b) {
        return a.compareTo(b) < 0;
    }

    boolean gt(BigInteger a, BigInteger b) {
        return a.compareTo(b) > 0;
    }

    boolean eq(BigInteger a, BigInteger b) {
        return a.compareTo(b) == 0;
    }

}
