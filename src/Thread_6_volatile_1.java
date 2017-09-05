/**
 * Created by admin on 2017/8/28.
 * 前置知识点:
 *           1.jdk1.5之后线程执行期间会把所需要的变量复制一份到该线程所属的内存空间
 *
 * volatile:
 *           1.如果线程所使用的变量使用volatile进行修饰，那么变量被改变时强制线程去jdk主引擎内存中去读取该变量的值
 *           2.该关键字只具备变量的多个线程可见性，并不具备可见性
 * 该类表现:
 *         1.如果is_running变量不被volatile修饰则线程t1不会停止
 *           (原理:t1线程读取的 is_running 是主引擎内存空间在一开始启动时复制给该线程所属内存空间的is_running的值，
 *            也就是说 在t1启动时 is_running 会被复制给 t1 所在的内存空间,当外界的is_running被改变时内部的不会被改变)
 *         2.如果is_running变量被volatile修饰则线程t1会停止
 *           (原理:当is_running被改变)
 */
public class Thread_6_volatile_1 extends Thread {

    //
    private volatile boolean is_running = true;

    void set_runnning(boolean is_running) {
        this.is_running = is_running;
    }

    public void run() {
        System.out.println("进入run方法");
        while (is_running) {
            //
        }
        System.out.println("线程停止");
    }

    public static void main(String[] args) throws InterruptedException {

        Thread_6_volatile_1 t1 = new Thread_6_volatile_1();

        t1.start();
        Thread.sleep(3000);
        t1.set_runnning(false);
        System.out.println("is_running的值已经被设置了false");
        Thread.sleep(1000);
        System.out.println(t1.is_running);

    }
}
