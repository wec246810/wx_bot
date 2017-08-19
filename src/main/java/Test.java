import org.springframework.core.OrderComparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Created by Y.S.K on 2017/8/5 in wx_bot.
 */
public class Test {
    public static void main(String[] args) {
//      IntStream.range(6,10).forEach(i -> System.out.println(i));

        //示例2，for与Intstream的比较
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        for(int i = 0; i < 5; i++) {
//            //i不能被访问，只能创建临时变量
//            int temp = i;
//            executorService.submit(new Runnable() {
//                public void run() {
//                    System.out.println("Running task " + temp);
//                }
//            });
//        }
//        executorService.shutdown();
        ExecutorService executorService1 = Executors.newFixedThreadPool(10);
        IntStream.range(0, 5)
                .forEach(i ->
                        executorService1.submit(new Runnable() {
                            public void run() {
                                System.out.println("Running task " + i);
                            }
                        }));
        executorService1.shutdown();

        //
        List name = Arrays.asList("peter", "anna", "mike", "xenia");
//        Collections.sort(name, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.compareTo(o2);
//            }
//        });
//        Collections.sort(name,(String a, String b) ->  b.compareTo(a) );
//        System.out.println(name);

    }

}
