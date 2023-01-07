package com.demos.practice001;

import com.demos.annotations.RunnerMember;
import com.demos.annotations.RunnerTask;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigInteger;

@RunnerMember
public class FibonacciBenchmark2 {

    static final BigInteger ONE = BigInteger.ONE;
    static final BigInteger TWO = BigInteger.TWO;

    @RunnerTask
    public void task() {
        long number = 40L;
        int count = 10;
        Pair<Long, Number> result1 = benchmark(number, count);
        System.out.println(
                StringUtils.join(
                        "*************************************************", "\n",
                        "Cost time: ", result1.getLeft(), "ms", "\n",
                        "Fibonacci result: ", result1.getRight()
                )
        );

        Pair<Long, Number> result2 = benchmark(BigInteger.valueOf(number), count);
        System.out.println(
                StringUtils.join(
                        "*************************************************", "\n",
                        "Cost time: ", result2.getLeft(), "ms", "\n",
                        "Fibonacci result: ", result2.getRight()
                )
        );
    }

    Pair<Long, Number> benchmark(Number number, final int count) {
        Number result = null;
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            result = (Number) fibonacci(number);
        }
        long end = System.currentTimeMillis();
        return Pair.of((end - start) / count, result);
    }

    <T extends Comparable<T>> Comparable<?> fibonacci(Number number) {
        if (number instanceof BigInteger) {
            if (gt((BigInteger) number, TWO)) {
                return ((BigInteger) fibonacci(((BigInteger) number).subtract(ONE)))
                        .add((BigInteger) fibonacci(((BigInteger) number).subtract(TWO)));
            } else {
                return ONE;
            }
        } else if (number instanceof Long) {
            if (gt((Long) number, 2L)) {
                return ((Long) fibonacci(((Long) number) - 1L))
                        + ((Long) fibonacci(((Long) number) - 2L));
            } else {
                return 1L;
            }
        }
        return null;
    }

    <T extends Comparable<T>> boolean gt(T a, T b) {
        Preconditions.checkArgument(a != null && b != null, "Arguments can not be null!");
        return a.compareTo(b) > 0;
    }
}
