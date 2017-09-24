import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/30.
 */
public class Thread_8_communication_1 {

    volatile static List list = new ArrayList();

    void add() {
        list.add("test");
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        final Thread_8_communication_1 tc = new Thread_8_communication_1();
        final Object lock = new Object();

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {

                synchronized (lock) {
                    try {
                        for (int i = 0; i < 10; i++) {

                            tc.add();
                            System.out.println("当前线程:" + Thread.currentThread().getName() + " 添加了一个元素");
                            Thread.sleep(500);
                            if (tc.size() == 5) {
                                System.out.println("已经发出通知");
                                lock.notify();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {

                synchronized (lock){

                    if (tc.size() != 5) {

                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("当前线程收到通知:" + Thread.currentThread().getName() + "list size = 5线程停止");
                        throw new RuntimeException();
                    }
                }
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}
