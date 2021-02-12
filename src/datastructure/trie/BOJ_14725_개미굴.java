package datastructure.trie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14725_개미굴 {

    public static int n;
    public static Node root = new Node();
    public static StringBuilder sb = new StringBuilder();

    public static class Node {
        TreeMap<String, Node> words = new TreeMap<>();
        boolean isTerminal;
    }

    public static void dfs(int index, Node node) {

        Iterator<String> keys = node.words.keySet().iterator();

        while(keys.hasNext()) {
            for (int i = 0; i < index; i++) {
                sb.append("--");
            }
            String key = keys.next();
            sb.append(key).append("\n");
            dfs(index + 1, node.words.get(key));
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            Node node = root;
            for (int j = 0; j < k; j++) {
                String key = st.nextToken();
                if(!node.words.containsKey(key)) {
                    node.words.put(key, new Node());
                }
                node = node.words.get(key);
            }
        }
        dfs(0, root);

        System.out.println(sb);

    }
}
