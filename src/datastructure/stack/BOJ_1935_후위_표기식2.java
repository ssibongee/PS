package datastructure.stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_1935_후위_표기식2 {

    public static double calculate(char symbol, double a, double b) {
        switch (symbol) {
            case '*':
                return a * b;
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '/':
                return a / b;
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        char[] expression = br.readLine().toCharArray();
        double[] alpha = new double[26];

        for (int i = 0; i < n; i++) {
            alpha[i] = Double.parseDouble(br.readLine());
        }

        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < expression.length; i++) {
            if (Character.isAlphabetic(expression[i])) {
                stack.add(alpha[expression[i] - 'A']);
            } else {
                double b = stack.pop();
                double a = stack.pop();
                stack.add(calculate(expression[i], a, b));
            }
        }

        System.out.printf("%.2f",stack.peek());
    }
}
