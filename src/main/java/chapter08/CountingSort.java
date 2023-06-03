package chapter08;

import util.ArrayUtils;

/**
 * @Description: 计数排序
 * @Author xiaomingcong
 * @date 2021/5/11 3:33 下午
 * Version 1.0
 */
public class CountingSort {

    public static void main(String[] args) {
        int[] a = ArrayUtils.radomArray(20,20);
        ArrayUtils.printFormat(a);
        int[] b = solution(a);
        ArrayUtils.printFormat(b);
    }

    public static int[] solution(int[] a){

        int max = 0;
        for (int i = 0; i < a.length; i++) {
            if(a[i] > max){
                max = a[i];
            }
        }
        int[] c = new int[max + 1];
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            c[a[i]] = c[a[i]] + 1;
        }
        for (int i = 1; i < c.length; i++) {
            c[i] = c[i] + c[i - 1];
        }
        for (int i = a.length - 1; i >= 0 ; i--) {
            b[c[a[i]] - 1] = a[i];
            c[a[i]] = c[a[i]] - 1;
        }

        return b;
    }


}
