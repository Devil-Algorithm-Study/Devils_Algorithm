package week02_bruteforce;

import java.io.*;
import java.util.*;

public class BOJ12100_jiyun {

    static String[] direcction = {"up", "down", "right", "left"};
    static int n;
    static int max;
    static boolean found = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        int[][] graph = new int[n][n];

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(graph, 0);
        System.out.println(max);

    }

    static void dfs(int[][] graph, int depth) {

        if (depth == 5) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    max = Math.max(max, graph[i][j]);
                }
            }
            return;
        }

        for (String d : direcction) {
            int[][] copy_graph = new int[n][n];
            for (int j = 0; j < n; j++) {
                System.arraycopy(graph[j], 0, copy_graph[j], 0, n);
            }

//            switch (d) {
//                case "up" -> move_up(copy_graph);
//                case "down" -> move_down(copy_graph);
//                case "left" -> move_left(copy_graph);
//                default -> move_right(copy_graph);
//            }

            switch (d) {
                case "up":
                    move_up(copy_graph); break;
                case "down":
                    move_down(copy_graph); break;
                case "left":
                    move_left(copy_graph); break;
                case "right":
                    move_right(copy_graph); break;
            }

            dfs(copy_graph, depth + 1);
        }

    }

    static void move_up(int[][] copy_graph) {
        for (int y = 0; y<n; y++) {
            int writeX = 0;
            int prev = 0;

            for (int x=0; x<n; x++) {
                if (copy_graph[x][y]!=0) {
                    if (prev==0) {
                        prev = copy_graph[x][y];
                    }
                    else if (prev == copy_graph[x][y]) {
                        copy_graph[writeX++][y] = prev*2;
                        prev = 0;
                    }
                    else {
                        copy_graph[writeX++][y] = prev;
                        prev = copy_graph[x][y];
                    }
                    copy_graph[x][y] = 0;
                }
            }

            if (prev!=0) {
                copy_graph[writeX][y] = prev;
            }
        }
    }

    static void move_down(int[][] copy_graph) {
        for (int y = 0; y<n; y++) {
            int writeX = n-1;
            int prev = 0;

            for (int x=n-1; x>=0; x--) {
                if (copy_graph[x][y]!=0) {
                    if (prev==0) {
                        prev = copy_graph[x][y];
                    }
                    else if (prev == copy_graph[x][y]) {
                        copy_graph[writeX--][y] = prev*2;
                        prev = 0;
                    }
                    else {
                        copy_graph[writeX--][y] = prev;
                        prev = copy_graph[x][y];
                    }
                    copy_graph[x][y] = 0;
                }
            }

            if (prev!=0) {
                copy_graph[writeX][y] = prev;
            }
        }
    }

    static void move_left(int[][] copy_graph) {
        for (int x=0; x<n; x++) {
            int writeY = 0;
            int prev = 0;

            for (int y=0; y<n; y++) {
                if (copy_graph[x][y]!=0) {
                    if (prev==0) {
                        prev = copy_graph[x][y];
                    }
                    else if (prev == copy_graph[x][y]) {
                        copy_graph[x][writeY++] = prev*2;
                        prev = 0;
                    }
                    else {
                        copy_graph[x][writeY++] = prev;
                        prev = copy_graph[x][y];
                    }
                    copy_graph[x][y] = 0;
                }
            }

            if (prev!=0) {
                copy_graph[x][writeY] = prev;
            }
        }
    }
    static void move_right(int[][] copy_graph) {
        for (int x=0; x<n; x++) {
            int writeY = n-1;
            int prev = 0;

            for (int y=n-1; y>=0; y--) {
                if (copy_graph[x][y]!=0) {
                    if (prev==0) {
                        prev = copy_graph[x][y];
                    }
                    else if (prev == copy_graph[x][y]) {
                        copy_graph[x][writeY--] = prev*2;
                        prev = 0;
                    }
                    else {
                        copy_graph[x][writeY--] = prev;
                        prev = copy_graph[x][y];
                    }
                    copy_graph[x][y] = 0;
                }
            }

            if (prev!=0) {
                copy_graph[x][writeY] = prev;
            }
        }

    }


}
