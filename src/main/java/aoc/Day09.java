package aoc;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Day09 {
    public static void main(String[] args) {
        var matrix = Utils.INSTANCE.readInput(9)
                .stream()
                .map(String::chars)
                .map(s -> s.map(x -> x - 48).toArray())
                .toArray(int[][]::new);

        long risk = 0;

        for (int r = 1; r < matrix.length - 1; r++) {
            for (int c = 1; c < matrix[0].length - 1; c++) {
                if (matrix[r][c] < matrix[r][c - 1] && matrix[r][c] < matrix[r - 1][c]
                        && matrix[r][c] < matrix[r + 1][c] && matrix[r][c] < matrix[r][c + 1]) {
                    risk += matrix[r][c] + 1;
                }
            }
        }

        for (int c = 1; c < matrix.length - 1; c++) {
            if (matrix[0][c] < matrix[0][c - 1] && matrix[0][c] < matrix[1][c] && matrix[0][c] < matrix[0][c + 1]) {
                risk += matrix[0][c] + 1;
            }
        }

        int n = matrix.length - 1;
        for (int c = 1; c < matrix[0].length - 1; c++) {
            if (matrix[n][c] < matrix[n][c - 1] && matrix[n][c] < matrix[n - 1][c] && matrix[n][c] < matrix[n][c + 1]) {
                risk += matrix[n][c] + 1;
            }
        }

        for (int r = 1; r < matrix.length - 1; r++) {
            if (matrix[r][0] < matrix[r - 1][0] && matrix[r][0] < matrix[r + 1][0] && matrix[r][0] < matrix[r][1]) {
                risk += matrix[r][0] + 1;
            }
        }

        int m = matrix[0].length - 1;
        for (int r = 1; r < matrix.length - 1; r++) {
            if (matrix[r][m] < matrix[r][m - 1] && matrix[r][m] < matrix[r - 1][m] && matrix[r][m] < matrix[r + 1][m]) {
                risk += matrix[r][m] + 1;
            }
        }

        if (matrix[0][0] < matrix[0][1] && matrix[0][0] < matrix[1][0]) {
            risk += matrix[0][0] + 1;
        }

        if (matrix[0][m] < matrix[0][m - 1] && matrix[0][m] < matrix[1][m]) {
            risk += matrix[0][m] + 1;
        }

        if (matrix[n][0] < matrix[n][1] && matrix[n][0] < matrix[n - 1][0]) {
            risk += matrix[n][0] + 1;
        }

        if (matrix[n][m] < matrix[n - 1][m] && matrix[n][m] < matrix[n][m - 1]) {
            risk += matrix[n][m] + 1;
        }

        System.out.println(risk);

    }
}