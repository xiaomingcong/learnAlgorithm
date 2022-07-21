package chatper15动态规划;

/**
 * @Description: 最长公共子序列
 * @Author xiaomingcong
 * @date 2021/6/24 5:08 上午
 * Version 1.0
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        String y = "bdcaba";
        String x = "abcbdab";
        solution(x,y);
    }

    /**
       * @Description: 返回a，b字符串的最长公共子序列
       * @Param  x
     * @param y
       * @return java.lang.String
       * @Author xiaomingcong
       * @date 2021/6/24 5:46 上午
       * Version 1.0
    */
    public static String solution(String x,String y){
        int m = x.length(), n = y.length();
        int[][] b = new int[m + 1][n + 1];
        int[][] c = new int[m + 1][n + 1];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if(x.charAt(i - 1) == y.charAt(j - 1)){
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = 1;//相当于↖️
                }else if(c[i - 1][j] >= c[i][j-1]){
                    c[i][j] = c[i - 1][j];
                    b[i][j] = 2;//相当于⬆️
                }else {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = 3;//相当于⬅️
                }
            }
        }
        printLcs(b,x,m,n);

        return null;
    }

    public static void printLcs(int[][] b,String x,int i,int j){
        if (i == 0 || j == 0){
            return;
        }
        if(b[i][j] == 1){
            printLcs(b,x,i - 1,j - 1);
            System.out.println(x.charAt(i - 1));
        }else if(b[i][j] == 2){
            printLcs(b,x,i - 1,j);
        }else {
            printLcs(b,x,i,j - 1);
        }
    }

}
