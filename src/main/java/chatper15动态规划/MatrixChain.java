package chatper15动态规划;

/**
 * @Description: 矩阵链乘法
 * @Author xiaomingcong
 * @date 2021/6/24 3:19 上午
 * Version 1.0
 */
public class MatrixChain {

    public static void main(String[] args) {
        int[] p = new int[]{30,35,15,5,10,20,25};
        matrixChainOrder(p);
    }

    /**
       * @Description: TODO
       * @Param  p p[0]为第一个矩阵的row，p[1]为第一个矩阵的column和第二个矩阵的row以此类推
       * @return int
       * @Author xiaomingcong
       * @date 2021/6/24 3:27 上午
       * Version 1.0
    */
    public static int matrixChainOrder(int[] p){
        int[][] w = new int[p.length][p.length];//w[i][j]存放的所需的最少乘法次数
        int[][] s = new int[p.length][p.length];//用来保存w[i][j]时的分隔位置
        for(int j = 2; j < p.length; j++){
            for(int i = j - 2; i >= 0; i--){
                w[i][j] = Integer.MAX_VALUE;
            }
        }
        int s1,s2,s3,min;
        for(int j = 2; j < p.length; j++){

            for(int i = j - 2; i >= 0; i--){

                for (int k = i + 1; k < j; k++) {
                    s1 = w[i][k] + w[k][j] + p[i] * p[k] * p[j];
                    if(w[i][j] > s1){
                        w[i][j] = s1;
                        s[i][j] = k;
                    }
                }
            }
        }

        System.out.println("所需最少乘法次数为： " + w[0][p.length - 1]);
        return w[0][p.length - 1];
    }
}
