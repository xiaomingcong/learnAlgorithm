package common;

/**
 * @Description: TODO
 * @Autohr xiaomingcong
 * @date 2021/4/11 3:35 下午
 * Version 1.0
 */
public class NumberUtils {

    /**
     * @Description: 获得n的最近的一个大于n的2的幂次的数
     * @Param  n
     * @return int
     * @Autohr xiaomingcong
     * @date 2021/4/11 3:33 下午
     * Version 1.0
     */
    public static int getLeastAboveNumInPow2(int n){
        int res = 2;
        while(res < n){
            res *= 2;
        }
        return res;
    }
}
