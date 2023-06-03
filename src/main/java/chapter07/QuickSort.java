package chapter07;

import util.ArrayUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;

/**
 * @Description: 快速排序
 * @Author xiaomingcong
 * @date 2021/5/10 9:42 上午
 * Version 1.0
 */
public class QuickSort {

//    private static Random random = new Random(new Date().getTime());

    private static Random random = new Random(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

    public static void main(String[] args) {
        int[] a = ArrayUtils.radomArray(20,100);
        ArrayUtils.printFormat(a);
        solution(a);
        ArrayUtils.printFormat(a);
    }

    public static void solution(int[] a){
        int l = 0,r = a.length - 1;
        partition(a,l,r);
    }

    public static void partition(int[] a,int l,int r){
        if(l >= r) return ;
//        Random random = new Random(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

        int x = random.nextInt(r - l) + l;
//        System.out.println(x - l);
        int temp = a[x];
        a[x] = a[l];
        a[l] = temp;
        int i = l,j = r;
        while(i < j){
            if(a[j] < temp){
                a[i++] = a[j];
                while(i < j){
                    if(a[i] > temp){
                        a[j--] = a[i];
                        break;
                    }else{
                        i++;
                    }
                }
            }else{
                j--;
            }
        }
        a[i] = temp;
//        int q = (l + r) / 2;
        partition(a,l,i);
        partition(a,i + 1,r);
    }
}
