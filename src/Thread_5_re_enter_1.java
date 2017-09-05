/**
 * Created by admin on 2017/8/25.
 *
 * 锁重入
 *
 */
public class Thread_5_re_enter_1 {


    public synchronized void func_1() {
        System.out.println("func_1 ");
        func_2();
    }

    public synchronized void func_2() {
        System.out.println("func_2 ");
        func_3();
    }

    public synchronized void func_3() {
        System.out.println("func_3 ");
    }

    public static void main(String[] arge) throws Exception {

        final Thread_5_re_enter_1 thread_5_re_enter = new Thread_5_re_enter_1();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                thread_5_re_enter.func_1();
            }
        });
        t1.start();
    }
}