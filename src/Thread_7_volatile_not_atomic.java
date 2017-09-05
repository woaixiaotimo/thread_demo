import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by admin on 2017/8/29.
 * volatile:
 * 1.多次执行该线程并不能保证执行结果一致,说明关键字并不具备原子操作
 * 2.AtomicInteger是线程安全的，最后结果多次执行均一致(注意:Atomic只能保证当前调用方法是原子操作，不能保证多方法调用都是原子操作)
 */
public class Thread_7_volatile_not_atomic extends Thread {

    //1
    //private static volatile int count;
    //2-初始化为0
    private static volatile AtomicInteger count = new AtomicInteger(0);

    private static void addCount() {
        for (int i = 0; i < 1000; i++) {
            //1
            //count++;
            //2--
            count.incrementAndGet();//++
        }
        System.out.println("count = " + count);
    }

    public void run() {
        addCount();
    }


    public static void main(String[] args) throws InterruptedException {

        Thread_7_volatile_not_atomic[] arr = new Thread_7_volatile_not_atomic[10];

        for (int i = 0; i < 10; i++) {
            arr[i] = new Thread_7_volatile_not_atomic();
        }

        for (int i = 0; i < 10; i++) {
            arr[i].start();
        }
    }
}
