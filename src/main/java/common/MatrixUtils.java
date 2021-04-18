package common;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;

/**
 * @Description: 矩阵工具类
 * @Autohr xiaomingcong
 * @date 2021/4/11 12:04 上午
 * Version 1.0
 */
public class MatrixUtils {

    /**
       * @Description: 带负数的m x n矩阵
       * @Param  m
     * @param n
     * @param max 矩阵最大值
       * @return int[][]
       * @Autohr xiaomingcong
       * @date 2021/4/11 12:22 上午
       * Version 1.0
    */
    public static  int[][] randomNegativeNumberMatrix(int m,int n,int max){
        int[][] a= new int[m][n];
        int jud = max / 2;
        int temp;
        Random random = new Random(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
        Random random2 = new Random(LocalDateTime.now().toEpochSecond(ZoneOffset.of("-8")));
        for(int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                temp = random.nextInt(max);
                a[i][j] = random2.nextInt(max) > jud ? temp : -temp;
            }
        }
        return a;
    }

    /**
       * @Description: 全是正数的m x n矩阵
       * @Param  m
     * @param n
     * @param max 矩阵元素的最大值
       * @return int[][]
       * @Autohr xiaomingcong
       * @date 2021/4/11 12:23 上午
       * Version 1.0
    */
    public static  int[][] randomMatrix(int m,int n,int max){
        int[][] a= new int[m][n];
        Random random = new Random(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
        for(int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                a[i][j] = random.nextInt(max);
            }
        }
        return a;
    }

    /**
       * @Description: 给定两个m x n的矩阵，返回相加的结果
       * @Param  a
       * @param b
       * @return int[][]
       * @Autohr xiaomingcong
       * @date 2021/4/11 12:28 上午
       * Version 1.0
    */
    public static int[][] add(int[][] a,int[][] b){
        int m = a.length;
        int n = a[0].length;
        int c[][] = new int[m][n];
        for (int i = 0; i < m; i++){
            for(int j = 0;j < n; j++){
                c[i][j] = a[i][j] + b[i][j];
            }
        }
        return c;
    }

    /**
       * @Description: 给定两个m x n的矩阵，返回a-b的结果
       * @Param  a
     * @param b
       * @return int[][]
       * @Autohr xiaomingcong
       * @date 2021/4/11 12:28 上午
       * Version 1.0
    */
    public static int[][] decrease(int[][] a,int[][] b){
        int m = a.length;
        int n = a[0].length;
        int c[][] = new int[m][n];
        for (int i = 0; i < m; i++){
            for(int j =0;j < n; j++){
                c[i][j] = a[i][j] - b[i][j];
            }
        }
        return c;
    }

    /**
       * @Description: 爆破法返回（m x n） * （n x m）的矩阵的积
       * @Param  a
       * @param b
       * @return int[][]
       * @Autohr xiaomingcong
       * @date 2021/4/11 12:55 上午
       * Version 1.0
    */
    public static int[][] plus(int[][] a,int[][] b){
        int m = a.length;
        int n = a[0].length;
        int[][] c = new int[m][n];
        int sum = 0;
        for (int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                sum = 0;
                for(int k = 0; k < n; k++){
                    sum += a[i][k] * b[k][j];
                }
                c[i][j] = sum;
            }
        }
        return c;
    }

    /**
       * @Description: 爆破法返回矩阵的子矩阵的积 (x1,y1)第一个子矩阵左上角的点，(x2,y2)第二个子矩阵左下角的点
       * @Param  a
     * @param b
     * @param x1
     * @param y1
     * @param x2
     * @param y2
       * @return int[][]
       * @Autohr xiaomingcong
       * @date 2021/4/11 12:12 下午
       * Version 1.0
    */
    public static int[][] plus(int[][] a,int[][] b,int x1,int y1,int x2,int y2,int length){
//        int m = a.length;
//        int n = a[0].length;

        int[][] c = new int[length][length];
        int sum = 0;
        for (int i = x1,i1 = x2,cx = 0; cx < length; i++,i1++,cx++){
            for(int j = y1,j1 = y2,cy = 0; cy < length; j++,j1++,cy++){
                sum = 0;
                for(int k1 = y1,k2 = y2,l = 0; l < length; k1++,k2++,l++){
                    sum += a[i][k1] * b[k2][j1];
                }
                c[cx][cy] = sum;
            }
        }
        return c;
    }

    /**
     * @Description: 爆破法返回矩阵的子矩阵的和 (x1,y1)第一个子矩阵左上角的点，(x2,y2)第二个子矩阵左下角的点
     * @Param  a
     * @param b
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return int[][]
     * @Autohr xiaomingcong
     * @date 2021/4/11 12:12 下午
     * Version 1.0
     */
    public static int[][] add(int[][] a,int[][] b,int x1,int y1,int x2,int y2,int length){
//        int m = a.length;
//        int n = a[0].length;
        int[][] c = new int[length][length];
//        int sum = 0;
        for (int i = x1,i1 = x2,cx = 0; cx < length; i++,i1++,cx++){
            for(int j = y1,j1 = y2,cy = 0; cy < length; j++,j1++,cy++){

                c[cx][cy] = a[i][j] + b[i1][j1];
            }
        }
        return c;
    }

    /**
     * @Description: 爆破法返回矩阵的子矩阵相减的结果 (x1,y1)第一个子矩阵左上角的点，(x2,y2)第二个子矩阵左下角的点
     * @Param  a
     * @param b
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return int[][]
     * @Autohr xiaomingcong
     * @date 2021/4/11 12:12 下午
     * Version 1.0
     */
    public static int[][] decrease(int[][] a,int[][] b,int x1,int y1,int x2,int y2,int length){
//        int m = a.length;
//        int n = a[0].length;
        int[][] c = new int[length][length];
//        int sum = 0;
        for (int i = x1,i1 = x2,cx = 0; cx < length; i++,i1++,cx++){
            for(int j = y1,j1 = y2,cy = 0; cy < length; j++,j1++,cy++){

                c[cx][cy] = a[i][j] - b[i1][j1];
            }
        }
        return c;
    }

    /**
       * @Description: 打印矩阵
       * @Param  a
       * @return void
       * @Autohr xiaomingcong
       * @date 2021/4/11 12:33 下午
       * Version 1.0
    */
    public static void printf(int[][] a){
        int m = a.length;
        int n = a[0].length;
        for(int i = 0; i < m; i++){
            System.out.println();
            for(int j= 0; j < n; j++){
                System.out.print(a[i][j]);
                for(int t = 8-String.valueOf(a[i][j]).length(); t > 0; t--){
                    System.out.print(" ");
                }
            }
        }
    }
}
