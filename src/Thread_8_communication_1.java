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

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    for (int i = 0; i < 10; i++) {

                        tc.add();
                        System.out.println("当前线程:"+Thread.currentThread().getName()+" 添加了一个元素");
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
