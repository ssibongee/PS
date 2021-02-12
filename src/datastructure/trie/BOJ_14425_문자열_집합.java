package datastructure.trie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ_14425_문자열_집합 {

    public static Node root = new Node();

    public static class Node {
        TreeMap<Character, Node> alpha = new TreeMap<>();
        boolean isTerminal;
    }

    public static void insert(String key) {
        Node node = root;

        for (char c : key.toCharArray()) {
            if (!node.alpha.containsKey(c)) {
                node.alpha.put(c, new Node());
            }
            node = node.alpha.get(c);
        }
        node.isTerminal = true;
    }

    public static boolean isExist(String key) {
        Node node = root;

        for (char c : key.toCharArray()) {
            if (!node.alpha.containsKey(c)) {
                return false;
            }
            node = node.alpha.get(c);
        }

        return node.isTerminal;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            insert(br.readLine());
        }


        long ret = 0;
        for (int i = 0; i < m; i++) {
            if (isExist(br.readLine())) {
                ret += 1;
            }
        }

        System.out.println(ret);

    }
}
