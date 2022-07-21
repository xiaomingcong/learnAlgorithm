package chapter04分治策略;

import common.ArrayUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Description: 归并排序
 * @Autohr xiaomingcong
 * @date 2021/3/19 12:23 上午
 * Version 1.0
 */
public class MergeSort {

    @Test
    public void test(){
        int[] a = ArrayUtils.radomArray(20,100);
        ArrayUtils.printFormat(a);
        //ArrayUtils.printFormat(Arrays.copyOfRange(a,1,2));
        int[] b = solution(a);
        ArrayUtils.printFormat(b);
        System.out.println(ArrayUtils.isSort(b));
    }


    public static int[] solution(int[] a){
        //System.out.println(a.length);
        if(a.length > 1){
            return merge(solution(Arrays.copyOfRange(a,0,(a.length + 1)/ 2 )),
                    solution(Arrays.copyOfRange(a,(a.length + 1) / 2 , a.length )));
        }
        return a;

    }

    public static int[] merge(int[] a, int[] b){
        int[] c = new int[a.length + b.length];
        int al = a.length;
        int bl = b.length;
        int i =0, j = 0, k = 0;
        while (i < al && j < bl){
            if(a[i] <= b[j]){
                c[k++] = a[i++];
            }else{
                c[k++] = b[j++];
            }
        }
        while (i < al){
            c[k++] = a[i++];
        }
        while (j < bl){
            c[k++] = b[j++];
        }
        return c;
    }
}
