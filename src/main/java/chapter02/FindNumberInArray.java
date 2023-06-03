package chapter02;


import util.ArrayUtils;

/**
 * @Description: 查找元素是否存在数组中
 * @Autohr xiaomingcong
 * @date 2020/12/7 9:50 下午
 * Version 1.0
 */
public class FindNumberInArray {

    /**
       * @Description: 查找元素是否存在数组中，存在时返回元素所在数组下标
       * @Param  a 输入数组（无序数组）
       * @param num   用来匹配的元素
       * @return java.lang.Integer
       * @Autohr xiaomingcong
       * @date 2020/12/7 9:53 下午
       * Version 1.0
    */
    public static Integer execute(int[] a,int num){
        for(int i=0; i<a.length; i++){
            if(num==a[i]){
                return i;
            }
        }
        return null;
    }

    /*
    从左到右依次跟数组中的元素相比较，如果相等则直接返回下标

     */
    public static void main(String[] args) {
        int []a = ArrayUtils.radomArray(69,100,false);
        ArrayUtils.printFormat(a);
        System.out.println();
        System.out.println(FindNumberInArray.execute(a,67));
    }

}
