package chapter02;

import common.ArrayUtils;

/**
   * @Description: 插入排序算法
   * @Autohr xiaomingcong
   * @date 2020/12/3 10:48 下午
   * Version 1.0
*/
public class InsertionSort {

    /**
       * @Description: 执行插入排序，输入乱序数组a，返回数组a，且数组中的元素有序排列（降序排序）
       * @Param  a
       * @return int[]
       * @Autohr xiaomingcong
       * @date 2020/12/3 10:49 下午
       * Version 1.0
    */
    public static int[] execute(int[] a){
        int temp;
        for(int i=0; i<a.length ;i++){
            for(int j=0; j<i; j++){
                if(a[j]<a[i]){
                    temp=a[j];
                    a[j]=a[i];
                    a[i]=temp;
                }
            }
        }
        return a;
    }

    /**
     * @Description: TODO
     * 设j为数组a的下标，从j等于0开始递增，子数组a[0,j-1]每次a[0，j-1]都是已经排序好的，然后a[j]与a[0,j-1]中的逐个元素对比，
     * 当a[j]大于该元素时，a[j]取代该元素的位置，之后的元素依次后移。
     *
     * 初始化：子数组a[0,j-1]，当j=0时，只有一个元素，循环不变式成立
     * 保持：当后面的元素插入时，原来的子数组已经排序好，然后插入的时候逐个比较，找到自己的位置
     * 终止：在j=a.length-1的时候终止
     *


    */

    public static void main(String[] args) {
        int[] a = {56,3,7,8,9,1,67,2,5};
        ArrayUtils.printFormat(a);
        System.out.println();
        ArrayUtils.printFormat(InsertionSort.execute(a));
        System.out.println();


    }
}
