/**
 * Created by admin on 2017/8/24.
 *
 * 锁对象与同步异步简单实例
 *
 * 1:当线程 t1 调用 test1() 时 会获得  temp 这个对象作为锁 ，
 *   而此时 test2() 方法未添加 synchronized 关键字 。这时 test1()和test2()可以同时执行
 * 2:当 test2() 方法添加 synchronized 后 t1 ,t2 线程获得的锁对象都是 temp 这时会发生锁竞争问题，
 *   未竞争到的会等待获得锁的线程释放锁在执行
 * 3:加了 synchronized 关键字的 test1() 方法无法并发访问-可以以此来达到资源同步的目的(资源读写)
 *   没有加 synchronized 关键字的 test2() 方法可以并发访问-但不能达到资源同步(不能进行资源读写-这个其实可以通过编程方式来实现同步但以后再说)
 */
public class Thread_3_Synchronized_Asynchronized {



    public synchronized void test1() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(4000);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public synchronized void test2() {

        try {
            System.out.println(Thread.currentThread().getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        final Thread_3_Synchronized_Asynchronized temp = new Thread_3_Synchronized_Asynchronized();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                temp.test1();
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                temp.test2();
            }
        },"t2");
        t1.start();
        t2.start();
    }
}
