package chapter06;

import chapter04分治策略.MergeSort;
import common.ArrayUtils;

/**
 * @Description: 堆排序算法
 * @Autohr xiaomingcong
 * @date 2021/4/30 9:18 上午
 * Version 1.0
 */
public class HeapSort {

    public static void main(String[] args) throws InterruptedException {
        int[] a = ArrayUtils.radomArray(20,100);
        ArrayUtils.printFormat(a);
        heapSort(a);
//        ArrayUtils.printFormat(MergeSort.solution(a));
        ArrayUtils.printFormat(a);
        int[] b = ArrayUtils.radomArray(20,100);;
        ArrayUtils.printFormat(b);
        buildMaxHeapify(b,20);
        heapIncreaseKey(b,4,100);
        ArrayUtils.printFormat(b);
        int[] c = maxHeapInsert(b,99,20);
//        Thread.sleep(2000);
        ArrayUtils.printFormat(c);
    }

    public static void maxHeapify(int[] a,int i,int heapSize){
        int l = left(i);
        int r = right(i);
        int largest = i;
        if(l < heapSize && a[l] > a[i]){
            largest = l;
        }
        if(r < heapSize && a[r] > a[largest]){
            largest = r;
        }
        if(largest != i){
            int temp = a[i];
            a[i] = a[largest];
            a[largest] = temp;
            maxHeapify(a,largest,heapSize);
        }
    }

    public static void buildMaxHeapify(int[] a,int heapSize){
        if(a == null) return;
        int length = a.length;
        for(int i = length / 2; i >= 0; i--){
            maxHeapify(a,i,heapSize);
        }
    }

    public static void heapSort(int[] a){
        int temp = 0;
        buildMaxHeapify(a,a.length);
        int heapSize = a.length;
        for(int i = a.length - 1; i >= 1 ; i--){
            temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            heapSize = heapSize - 1;
            maxHeapify(a,0,heapSize);
        }
    }

    public static int left(int i){

        return 2 * (i + 1) - 1;
    }

    public static int right(int i){
        return 2 * (i + 1);
    }

    public static int parent(int i){
        return (int) Math.ceil(i / 2);
    }

    /**
       * @Description: 从已构建好的最大堆中获取最大值
       * @Param  a
       * @return int
       * @Autohr xiaomingcong
       * @date 2021/5/1 11:26 下午
       * Version 1.0
    */
    public static int maximum(int[] a){
        return a[0];
    }

    public static int heapExtraMax(int[] a,int heapSize){
        if(heapSize < 0){
            return -1;
        }
        int max = a[0];
        a[0] = a[heapSize];
        heapSize = heapSize - 1;
        maxHeapify(a,0,heapSize);
        return max;
    }

    /**
       * @Description: 在数组的i下标的值增大为key，key > a[i]
       * @Param  a
     * @param i
     * @param key
       * @return void
       * @Author xiaomingcong
       * @date 2021/5/1 11:47 下午
       * Version 1.0
    */
    public static void heapIncreaseKey(int[] a,int i,int key){
        if(key < a[i]){
            return;
        }
        a[i] = key;
        int temp = 0;
        while(i > 0 && a[parent(i)] < a[i]){
            temp = a[i];
            a[i] = a[parent(i)];
            a[parent(i)] = temp;
            i = parent(i);
        }
    }

    public static int[] maxHeapInsert(int[] a,int key,int heapSize){
        int[] b = a;
        if(heapSize + 1 > a.length){
            b = ArrayUtils.expansion(a);
        }
        b[heapSize] = -1;
        //heapSize = heapSize + 1;
        heapIncreaseKey(b,heapSize,key);
        return b;
    }

}
