import java.io.*;
import java.util.*;

public class BOJ12100_joshcho{
    static int N;
    static int[][] matrix;
    static int maxValue = 0;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        matrix = new int[N][N];

        for(int i=0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j < N; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        movement(0, matrix);
        System.out.println(maxValue);
        br.close();
    }

    // count는 이동한 횟수, map은 현재 이동한 뒤의 2048 블록들의 상태값을 저장한 배열
    private static void movement(int count, int[][] map){

        // 5번 이동했다면 전체 map에서 최대값을 찾고 반환
        if(count == 5){
            findMax(map);
            return;
        }

        // 현재 map을 그대로 사용하면 값이 바뀌므로 copy해서 해당 배열에서 이동을 시킨다.
        int[][] copy = new int[N][N];
        for(int i = 0; i < N; i++){
            copy[i] = map[i].clone();
        }

        // 1, 2, 3, 4를 통해 각 방향으로 copy 배열을 이동시킨다.
        for(int i = 1; i <= 4; i++){
            move(i, copy);

            // 이동 횟수를 추가하고 copy 배열을 전달한다.
            movement(count + 1, copy);

            // 전달되며 이동한 경우 다음 이동 때 다시 사용할 수 없으므로 다시 복사한다.
            for(int j = 0; j < N; j++){
                copy[j] = map[j].clone();
            }
        }
    }

    // 2048에서 실제 블록을 이동시키는 함수
    private static void move(int direction, int[][] map){
        // 동쪽으로 이동
        if(direction == 1){
            for(int row=0; row < N; row++){
                int index = N-1;
                int block = 0;
                for(int column = N-1; column >= 0; column--){
                    if(map[row][column] != 0){
                        if(block == map[row][column]){
                            map[row][index + 1] = block*2;
                            block = 0;
                            map[row][column] = 0;
                        } else {
                            block = map[row][column];
                            map[row][column] = 0;
                            map[row][index] = block;
                            index--;
                        }
                    }
                }
            }
            // 서쪽으로 이동
        } else if(direction == 2){
            for(int row=0; row < N; row++){
                int index = 0;
                int block = 0;

                for(int column = 0; column < N; column++){
                    if(map[row][column] != 0){
                        if(block == map[row][column]){
                            map[row][index - 1] = block*2;
                            block = 0;
                            map[row][column] = 0;
                        } else {
                            block = map[row][column];
                            map[row][column] = 0;
                            map[row][index] = block;
                            index++;
                        }
                    }
                }
            }
            // 남쪽으로 이동
        } else if(direction == 3){
            for(int column = 0; column < N; column++){
                int index = N - 1;
                int block = 0;
                for(int row=N-1; row >= 0; row--){
                    if(map[row][column] != 0){
                        if(block == map[row][column]){
                            map[index + 1][column] = block*2;
                            block = 0;
                            map[row][column] = 0;
                        } else {
                            block = map[row][column];
                            map[row][column] = 0;
                            map[index][column] = block;
                            index--;
                        }
                    }
                }
            }
            // 북쪽으로 이동
        } else {
            for(int column = 0; column < N; column++){
                int index = 0;
                int block = 0;
                for(int row=0; row < N; row++){
                    if(map[row][column] != 0){
                        if(block == map[row][column]){
                            map[index - 1][column] = block*2;
                            block = 0;
                            map[row][column] = 0;
                        } else {
                            block = map[row][column];
                            map[row][column] = 0;
                            map[index][column] = block;
                            index++;
                        }
                    }
                }
            }
        }
    }

    // 최대값을 찾는 메소드
    private static void findMax(int[][] map){
        for(int i=0; i < N; i++){
            for(int j=0; j < N; j++){
                if(map[i][j] > maxValue){
                    maxValue = map[i][j];
                }
            }
        }
    }
}

