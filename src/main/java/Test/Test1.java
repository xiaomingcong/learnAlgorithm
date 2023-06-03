package Test;

import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import sun.nio.ch.ThreadPool;

/**
 * @Description: TODO
 * @Author xiaomingcong
 * @date 2023/5/7 08:29
 *         Version 1.0
 */
public class Test1 {

    public static void main(String[] args) {
//        new ThreadPoolExecutor(3,6,100, TimeUnit.SECONDS,new SynchronousQueue<>());
        System.out.println(Integer.toBinaryString(0));
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String a = in.nextLine();
            // String str = String.valueOf(a);

        }
    }


}
