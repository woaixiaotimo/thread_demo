/**
 * Created by admin on 2017/8/24.
 * 线程安全问题
 */
public class Thread_1_easy_synchronize extends Thread {

    int count = 5;

    //不加synchronized会出现线程不安全问题
    public synchronized void run() {
        count--;
        System.out.println(this.currentThread().getName() + " count = " + count);

    }

    public static void main(String[] args) {

        Thread_1_easy_synchronize temp = new Thread_1_easy_synchronize();

        Thread t1 = new Thread(temp, "t1");
        Thread t2 = new Thread(temp, "t2");
        Thread t3 = new Thread(temp, "t3");
        Thread t4 = new Thread(temp, "t4");
        Thread t5 = new Thread(temp, "t5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
