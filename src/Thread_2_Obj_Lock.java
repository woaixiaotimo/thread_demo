/**
 * Created by admin on 2017/8/24.
 * 锁对象
 *
 * 关键字 synchronized 取得的锁都是对象锁 而不是把一段代码当作锁
 * 如果 print_num 方法没有加 static 进行修饰 那么它会取得当前对象作为锁,也就是当该方法的对象被创建多次时也就会产生多个锁
 * 如果 print_num 方法加上 static 修饰表示锁定当前类也就是类一级别的锁，独占当前类。也叫独占锁
 */
public class Thread_2_Obj_Lock {



    static  int num = 0;

    public static synchronized void print_num(String tag) {
        try {
            if (tag.equals("a")) {
                num = 100;
                System.out.println("tag a , set num over ");
                Thread.sleep(1000);
            } else {
                num = 200;
                System.out.println("tag b , set num over ");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("tag = " + tag + " num = " + num);
    }


    public static void main(String[] args) {

        final Thread_2_Obj_Lock m1 = new Thread_2_Obj_Lock();
        final Thread_2_Obj_Lock m2 = new Thread_2_Obj_Lock();

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                m1.print_num("a");
            }
        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                m2.print_num("b");
            }
        });

        t1.start();
        t2.start();
    }
}
