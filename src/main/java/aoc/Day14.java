package aoc;

import java.util.function.Function;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.*;

public class Day14 {

    public static void main(String[] args) {

        var lines = Utils.INSTANCE.readInput(14);
        var initial = lines.get(0);

        var rules = lines
                .stream()
                .skip(2)
                .map(s -> s.split(" -> "))
                .collect(toMap(s -> s[0], s -> s[1]));

        var current = initial;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < current.length() - 1; j++) {
                char first = current.charAt(j);
                char second = current.charAt(j + 1);
                String pattern = String.valueOf(first) + second;
                builder.append(first);
                builder.append(rules.getOrDefault(pattern, ""));
            }
            builder.append(current.charAt(current.length() - 1));
            current = builder.toString();
            builder.delete(0, builder.length());
        }

        var result = current
                .chars()
                .boxed()
                .collect(groupingBy(identity(), counting()));

        long max = Long.MIN_VALUE, min = Long.MAX_VALUE;
        for (long count: result.values()) {
            if (count > max) {
                max = count;
            }
            if (count < min) {
                min = count;
            }
        }
        System.out.println(max - min);
    }
}
