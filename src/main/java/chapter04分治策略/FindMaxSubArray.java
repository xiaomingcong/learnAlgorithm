package chapter04分治策略;

import common.ArrayUtils;
import org.junit.Test;

/**
 * @Description: 最大子数组
 * @Autohr xiaomingcong
 * @date 2021/4/9 11:31 下午
 * Version 1.0
 */
public class FindMaxSubArray {
    @Test
    public void test(){
        int[] a = {13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
//        int[] a = ArrayUtils.radomArray(40,100);
        Result r = solution(a,0,a.length - 1);
        System.out.println("left:" + r.left +"; right:" + r.right + "; sum:" + r.sum);

    }

    public Result solution(int[] a,int left,int right){
        if(left > right){
            return new Result(-1,-1,0);
        }
        if(left == right){
            return new Result(left,right,a[left]);
        }
        Result r1 = solution(a,left,(left + right) / 2);
        Result r2 = solution(a,(left + right) / 2 + 1,right);
        Result sum = findMaxCrossingSubarray(a,left,right);
        //Result r1 = getSum(a,left,(left + right) / 2);
        //Result r2 = getSum(a,(left + right) / 2,right);
        //Result sum = new Result(left,right,r1.sum + r2.sum);
        if(r1.sum >= r2.sum && r1.sum >= sum.sum){
            return r1;
        }else if(r2.sum >= r1.sum && r2.sum >= sum.sum){
            return r2;
        }else{
            return sum;
        }

    }

    public Result findMaxCrossingSubarray(int[] a,int left,int right){
        int middle = (left + right) / 2;
        Result result = new Result();
        int left_sum = -Integer.MAX_VALUE;
        int right_sum = -Integer.MAX_VALUE;
        int sum = 0;
        result.left = left;
        result.right = right;
        for(int i = middle + 1;i <= right;i++){
            sum = sum + a[i];
            if(sum > right_sum){
                right_sum = sum;
                result.right = i;
            }
        }
        sum = 0;
        for(int i = middle;i >= 0 ;i--){
            sum = sum + a[i];
            if(sum > left_sum){
                left_sum = sum;
                result.left = i;
            }
        }
        result.sum = left_sum + right_sum;
        return result;
    }

    private class Result{
        private int left;
        private int right;
        private int sum;

        public Result(){

        }
        public Result(int sum){
            this.sum =sum;
        }
        public Result(int left,int right,int sum){
            this.left= left;
            this.right = right;
            this.sum = sum;
        }
    }

}
