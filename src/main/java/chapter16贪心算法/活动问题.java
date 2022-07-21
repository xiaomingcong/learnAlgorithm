package chapter16贪心算法;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Description: TODO
 * @Author xiaomingcong
 * @date 2021/6/25 12:44 下午
 * Version 1.0
 */
public class 活动问题 {

    public static void main(String[] args) {
        int[] s = new int[]{1,3,0,5,3,5,6,8,8,2,12};
        int[] f = new int[]{4,5,6,7,9,9,10,11,12,14,15};
        ArrayList<Integer> acts = solution(s,f);
        for (int num :acts) {
            System.out.println(num);
        }
    }

    /**
       * @Description: 假设有一个活动集合s，这些活动使用同一个资源，而这个资源某一时刻只能提供一个活动使用，每个活动都有一个开始时间和一个结束时间
     * 选出一个最大兼容集，假设已经按活动结束时间排序好
       * @Param  s 活动的开始时间
     * @param f 活动的结束时间
       * @return int[]
       * @Author xiaomingcong
       * @date 2021/6/25 12:45 下午
       * Version 1.0
    */
    public static ArrayList<Integer> solution(int[] s, int[] f){
        ArrayList<Integer> act = new ArrayList<>();
        act.add(0);
        for (int i = 1; i < s.length; i++) {
            if (s[i] >= f[act.get(act.size() - 1)]){
                act.add(i);
            }
        }
        return act;
    }
}
