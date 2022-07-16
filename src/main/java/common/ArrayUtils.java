package common;

import java.util.*;

/**
 * @Description: TODO
 * @Autohr xiaomingcong
 * @date 2020/12/7 9:23 下午
 * Version 1.0
 */
public class ArrayUtils {

    /**
       * @Description: 数组格式化输出
       * @Param  arr
       * @return java.lang.String
       * @Autohr xiaomingcong
       * @date 2020/12/7 10:02 下午
       * Version 1.0
    */
    public static  String  printFormat(int[] arr){
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<arr.length; i++){
            builder.append(arr[i] + ",");
            System.out.print(arr[i] + ",");
            if(i>0&&(i + 1)%10==0)
                System.out.println();
        }
        System.out.println();
        String s = builder.substring(0,builder.length()-1).toString();

        return s;
    }

    /**
     * @Description: 数组格式化输出
     * @Param  arr
     * @return java.lang.String
     * @Autohr xiaomingcong
     * @date 2020/12/7 10:02 下午
     * Version 1.0
     */
    public static  String  printFormat(Integer[] arr){
        int a[] = new int[arr.length];
        for(int i=0; i<arr.length; i++)
            a[i] = arr[i];
        return printFormat(a);
//        return builder.toString();
    }

    /**
       * @Description: 生成一个含有num个元素的随机数组，
       * @Param  num
       * @Param  max 数组不超过的最大值
       * @Pamam  distinct 数组元素是否唯一 ,默认不唯一
       * @return int[]
       * @Autohr xiaomingcong
       * @date 2020/12/7 10:02 下午
       * Version 1.0
    */
    public static int[] radomArray(int num,int max){
        return radomArray(num,max,false);
    }

    /**
     * @Description: 生成一个含有num个元素的随机数组，
     * @Param  num
     * @Param  max 数组不超过的最大值
     * @Pamam  distinct 数组元素是否唯一
     * @return int[]
     * @Autohr xiaomingcong
     * @date 2020/12/7 10:02 下午
     * Version 1.0
     */
    public static int[] radomArray(int num, int max, boolean distinct){

        Integer[] a = new Integer[num];
        Set<Integer> set = new HashSet<Integer>(num);
        Random random = new Random(new Date().getTime());
        if(distinct==true){
            while (set.size()<=num){
                set.add(random.nextInt(max));
            }
            a=set.toArray(a);
            int index1,index2,temp;
            for(int i=0; i<a.length; i++){
                index1 = random.nextInt(a.length);
                index2 = random.nextInt(a.length);
                temp = a[index1];
                a[index1] = a[index2];
                a[index2] = temp;
            }

        }else{
            for(int i=0; i<a.length; i++){
                a[i] = random.nextInt(max);
            }

        }
        int b[] = new int[a.length];
        for(int i=0; i<a.length; i++)
            b[i] = a[i];
        return b;
    }

    /**
       * @Description: 给定一个数组，判断是否已排序,返回一个整数
        * -2:错误输入；
        * -1：逆序；
        * 0：全等；
        * 1：正序;
        * 2：无序
       * @Param  a
       * @return int
       * @Autohr xiaomingcong
       * @date 2021/3/19 12:26 上午
       * Version 1.0
    */
    public static int isSort(int[] a){
        int leastStatus = 0;//上一轮比较状态
        if(a == null) return -2;
        if(a.length == 1) return 1;
        if(a.length == 2) return a[0]==a[1] ? 0 : (a[0] > a[1] ? -1 : 1);
        leastStatus = a[0]==a[1] ? 0 : (a[0] > a[1] ? -1 : 1);
        for(int i = 2; i < a.length; i++){
            if(a[i-1] < a[i]){
                if(leastStatus == 0){
                    leastStatus = 1;
                }else if(leastStatus == 1){
                    continue;
                }else{
                    return 0;
                }
            }else if(a[i-1] > a[i]){
                if(leastStatus == 0){
                    leastStatus = -1;
                }else if(leastStatus == -1){
                    continue;
                }else{
                    return 0;
                }
            }else{
                continue;
            }
        }
        return leastStatus;
    }


}
