/**
 * Created by admin on 2017/8/25.
 *
 * 脏读:
 *      当t1线程设置完user_name变量时停顿4秒钟
 *      然后当主线程读取变量时会取到
 *      已经改变的user_name变量和未改变的user_pwd变量
 * 解决方法:
 *          1:两个方法都加上synchronized
 *            (共享当前类为锁对象则set_value(),和get_value顺序执行)
 */
public class Thread_4_Dirty_Read {

    private String user_name = "tom";
    private String user_pwd = "123";

    public synchronized void set_value(String name, String pwd) {
        System.out.println("--------set_value start -----------");
        this.user_name = name;
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.user_pwd = pwd;
        System.out.println("name = " + name);
        System.out.println("pwd = " + pwd);
        System.out.println("--------set_value end-----------");
    }

    public void get_value() {
        System.out.println("--------get_value start-----------");
        System.out.println("user_name = " + user_name);
        System.out.println("user_pwd = " + user_pwd);
        System.out.println("--------get_value end-----------");
    }

    public static void main(String[] args) throws Exception {

        final Thread_4_Dirty_Read dr = new Thread_4_Dirty_Read();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                dr.set_value("jreey", "456");
            }
        });
        t1.start();
        Thread.sleep(1000);
        dr.get_value();
    }
}
