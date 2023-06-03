package chapter04分治策略;

import util.MatrixUtils;
import util.NumberUtils;
import org.junit.Test;

/**
 * @Description: 矩阵乘法的Strassen算法
 * @Autohr xiaomingcong
 * @date 2021/4/11 12:28 下午
 * Version 1.0
 */
public class Strassen {
    @Test
    public void test() throws InterruptedException {
        int[][] a = MatrixUtils.randomNegativeNumberMatrix(5,5,100);
        Thread.sleep(1000);
        int[][] b = MatrixUtils.randomNegativeNumberMatrix(5,5,100);
        MatrixUtils.printf(MatrixUtils.plus(a,b));
        int[][] c = expandMatrix(a);
        int[][] d = expandMatrix(b);

        System.out.println();
        MatrixUtils.printf(MatrixUtils.plus(c,d));

        System.out.println();
        MatrixUtils.printf(solution(a,b));

    }

    public static int[][] solution(int[][] a,int[][] b){
        if(a.length < NumberUtils.getLeastAboveNumInPow2(a.length)){
            int[][] c,d;
            c = expandMatrix(a);
            d = expandMatrix(b);
            int[][] e = new int[a.length][a.length];
            int[][] f = solution1(c,d,0,0,0,0,NumberUtils.getLeastAboveNumInPow2(a.length));
            for(int i = 0; i < a.length; i++){
                for(int j = 0; j < a.length; j++){
                    e[i][j] = f[i][j];
                }
            }
            return e;
        }else{

            return solution1(a,b,0,0,0,0,a.length);
        }
    }

    public static int[][] solution1(int[][] a, int[][] b, int x1, int y1, int x2, int y2, int length){
        if(length == 2){
            return MatrixUtils.plus(a,b,x1,y1,x2,y2,2);
        }

        int[][] c11,c12,c21,c22;
        int[][] s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,p1,p2,p3,p4,p5,p6,p7;
        int middle = length / 2;

        int[][] A11,A12,A21,A22,B11,B12,B21,B22;



        s1 = MatrixUtils.decrease(b,b,x2,y2 + middle,x2 + middle,y2 + middle,middle);//B12 - B22
        s2 = MatrixUtils.add(a,a,x1,y1,x1,y1 + middle,middle);//A11 + A12
        s3 = MatrixUtils.add(a,a,x1 + middle,y1,x1 + middle,y1 + middle,middle);//A21 + A22
        s4 = MatrixUtils.decrease(b,b,x2 + middle,y2,x2,y2,middle);//B21 - B11
        s5 = MatrixUtils.add(a,a,x1,y1,x1 + middle,y1 + middle,middle);//A11 + A22
        s6 = MatrixUtils.add(b,b,x2,y2,x2 + middle,y2 + middle,middle);//B11 + B22
        s7 = MatrixUtils.decrease(a,a,x1,y1 + middle,x1 + middle,y1 + middle,middle);//A12 - A22
        s8 = MatrixUtils.add(b,b,x2 + middle,y2,x2 + middle,y2 + middle,middle);//B21 + B22
        s9 = MatrixUtils.decrease(a,a,x1,y1,x1 + middle,y1,middle);//A11 - A21
        s10 = MatrixUtils.add(b,b,x2,y2,x2,y2 + middle,middle);//B11 + B12

//        p1 = MatrixUtils.plus(a,s1,x1,y1,0,0,middle);//A11 * s1
//        p2 = MatrixUtils.plus(s2,b,0,0,x2 + middle,y2 + middle,middle);//s2 * B22
//        p3 = MatrixUtils.plus(s3,b,0,0,x2,y2,middle);//s3 * B11
//        p4 = MatrixUtils.plus(a,s4,x1 + middle,y1 + middle,0,0,middle);//A22 * s4
//        p5 = MatrixUtils.plus(s5,s6);//s5 * s6
//        p6 = MatrixUtils.plus(s7,s8);//s7 * s8
//        p7 = MatrixUtils.plus(s9,s10);//s9 * s10

        p1 = solution1(a,s1,x1,y1,0,0,middle);//A11 * s1
        p2 = solution1(s2,b,0,0,x2 + middle,y2 + middle,middle);//s2 * B22
        p3 = solution1(s3,b,0,0,x2,y2,middle);//s3 * B11
        p4 = solution1(a,s4,x1 + middle,y1 + middle,0,0,middle);//A22 * s4
        p5 = solution1(s5,s6,0,0,0,0,middle);//s5 * s6
        p6 = solution1(s7,s8,0,0,0,0,middle);//s7 * s8
        p7 = solution1(s9,s10,0,0,0,0,middle);//s9 * s10

        c11 = MatrixUtils.add(MatrixUtils.decrease(MatrixUtils.add(p5,p4),p2),p6);//p5 + p4 - p2 + p6
        c12 = MatrixUtils.add(p1,p2);//p1 + p2
        c21 = MatrixUtils.add(p3,p4);//p3 + p4
        c22 = MatrixUtils.decrease(MatrixUtils.decrease(MatrixUtils.add(p5,p1),p3),p7);//p5 + p1 - p3 - p7

//        MatrixUtils.printf(c11);
//        System.out.println();
//        MatrixUtils.printf(c12);
//        System.out.println();
//        MatrixUtils.printf(c21);
//        System.out.println();
//        MatrixUtils.printf(c22);
//        System.out.println();
        int[][] result = new int[length][length];
        for(int i = 0,i1 = 0; i < middle; i++,i1++){
            for (int j = 0,j1 = 0; j < middle; j++,j1++){
                result[i1][j1] = c11[i][j];
            }
        }
        for(int i = 0,i1 = 0; i < middle; i++,i1++){
            for (int j = 0,j1 = middle; j < middle; j++,j1++){
                result[i1][j1] = c12[i][j];
            }
        }
        for(int i = 0,i1 = middle; i < middle; i++,i1++){
            for (int j = 0,j1 = 0; j < middle; j++,j1++){
                result[i1][j1] = c21[i][j];
            }
        }
        for(int i = 0,i1 = middle; i < middle; i++,i1++){
            for (int j = 0,j1 = middle; j < middle; j++,j1++){
                result[i1][j1] = c22[i][j];
            }
        }

//        MatrixUtils.printf(result);
//        System.out.println("------------------");
        return result;
    }


    public static int[][] expandMatrix(int[][] a){
        int n = NumberUtils.getLeastAboveNumInPow2(a.length);
        int[][] c = new int[n][n];
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < a.length; j++){
                c[i][j] = a[i][j];
            }
        }
        return c;
    }

}
