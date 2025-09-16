package week02_bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12100_sohee {
    static int[][] array;
    static int size;
    static int maxTile = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());

        array = new int[size][size];
        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < size; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        play2048(array, 0);

        System.out.println(maxTile);
    }

    private static void play2048(int[][] arr, int cnt) {
        if (cnt == 5) { // 5번 이동했으면 최대값 갱신 후 종료
            maxTile = Math.max(maxTile, findMax(arr));
            return;
        }

        int[][] copy;

        copy = cloneArray(arr);
        moveToStart(copy);
        play2048(copy, cnt + 1);

        copy = cloneArray(arr);
        moveToEnd(copy);
        play2048(copy, cnt + 1);

        copy = cloneArray(arr);
        moveToTop(copy);
        play2048(copy, cnt + 1);

        copy = cloneArray(arr);
        moveToBottom(copy);
        play2048(copy, cnt + 1);
    }

    private static int[][] cloneArray(int[][] arr) {
        int[][] copy = new int[size][size];
        for (int i = 0; i < size; i++)
            copy[i] = arr[i].clone();
        return copy;
    }

    private static int findMax(int[][] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (arr[i][j] > max) max = arr[i][j];
        return max;
    }

    private static void moveToStart(int[][] A) {
        for (int i = 0; i < size; i++) {
            int[] newRow = new int[size];
            boolean[] merged = new boolean[size]; // 중복으로 합쳐지지 않기 위해... 하 이 오류때문에 진짜 시간개씀
            int idx = 0;                  // 새 배열 채울 인덱스
            for (int j = 0; j < size; j++) {
                if (A[i][j] != 0) { // 0은 무시
                    if (newRow[idx] == 0) newRow[idx] = A[i][j]; // 빈칸이면 넣기
                    else if (newRow[idx] == A[i][j] && !merged[idx]) {
                        newRow[idx++] *= 2; // 같으면 합치기
                        merged[idx] = true;
                    } else newRow[++idx] = A[i][j]; // 다르면 다음 칸에 넣기
                }
            }
            A[i] = newRow; // 이동 후로 업데이트
        }
    }

    private static void moveToEnd(int[][] A) {
        for (int i = 0; i < size; i++) {
            int[] newRow = new int[size];
            boolean[] merged = new boolean[size];
            int idx = size - 1; // 오른쪽 끝부터 채움
            for (int j = 0; j < size; j++) {
                if (A[i][j] != 0) {
                    if (newRow[idx] == 0) newRow[idx] = A[i][j];
                    else if (newRow[idx] == A[i][j] && !merged[idx]) {
                        newRow[idx--] *= 2;
                        merged[idx] = true;
                    } else newRow[--idx] = A[i][j];
                }
            }
            A[i] = newRow;
        }
    }

    private static void moveToTop(int[][] A) {
        for (int i = 0; i < size; i++) {
            int[] newCol = new int[size];
            int idx = 0;
            boolean[] merged = new boolean[size];
            for (int j = 0; j < size; j++) {
                if (A[j][i] != 0) {
                    if (newCol[idx] == 0) newCol[idx] = A[j][i];
                    else if (newCol[idx] == A[j][i] && !merged[idx]){
                        newCol[idx++] *= 2;
                        merged[idx] = true;
                    }
                    else newCol[++idx] = A[j][i];
                }
            }
            for (int j = 0; j < size; j++)
                A[j][i] = newCol[j];
        }
    }

    private static void moveToBottom(int[][] A) {
        for (int i = 0; i < size; i++) {
            int[] newCol = new int[size];
            int idx = size - 1;
            boolean[] merged = new boolean[size];
            for (int j = 0; j < size; j++) {
                if (A[j][i] != 0) {
                    if (newCol[idx] == 0) newCol[idx] = A[j][i];
                    else if (newCol[idx] == A[j][i] && !merged[idx]) {
                        newCol[idx--] *= 2;
                        merged[idx] = true;
                    }
                    else newCol[--idx] = A[j][i];
                }
            }
            for (int j = 0; j < size; j++)
                A[j][i] = newCol[j];
        }
    }
}