package com.chen.test.test;

import android.os.Handler;
import android.os.Looper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by chenxianglin on 2018/3/14.
 * Class note:
 */

public class test {

    public volatile int inc = 0;

    //synchronized
    public void increase() {
        inc++;
    }

    public static void main(String[] args) {
//        staticFunction();
//        final test t = new test();
//        for (int i = 0; i < 10; i++) {
//            new Thread() {
//                @Override
//                public void run() {
//                    for (int j = 0; j < 1000; j++) {
//                        t.increase();
//                    }
//                }
//            }.start();
//        }
//
//        while (Thread.activeCount() > 2) {
//            Thread.yield();
//        }
//        System.out.println(t.inc);
        //直接复制则都为true;而new Integer(1),则==为false，说明创建了两个对象，地址不一样
//        Integer a = 1;
//        Integer b = 1;
//        System.out.println("a==b=" + (a == b) + "a.equals(b)=" + a.equals(b));
//        quickSort(new int[]{1, 4, 3, 5, 6, 2, 8, 9, 0}, 0, 8);
        test t = new test();
        t.thread();
    }

    int i;
    String s = "12121212121212";

    public void thread() {
        System.out.println("s=length=" + s.length());
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                while (i < s.length()) {
                    print("Thread1");
                }
            }
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                while (i < s.length()) {
                    print("Thread2");
                }
            }
        };
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();
        thread2.start();
    }

    public synchronized void print(String name) {
        System.out.println(name + ":" + "ready");
        if (i < s.length()) {
            System.out.println(name + ":" + s.substring(i, i + 1) + "=i=" + i);
            i++;
        }
    }


    public void lock() {
        AtomicInteger integer = new AtomicInteger(4);
        int a = integer.decrementAndGet();
        System.out.println("a=" + a);
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        condition.signal();
        condition.signalAll();
        lock.lock();
        lock.unlock();

        Map<String, String> map = new HashMap<>();
        map.keySet();
        Set<Map.Entry<String, String>> set = map.entrySet();
        for (Map.Entry<String, String> entry : set) {
            String key = entry.getKey();
            String vlue = entry.getValue();
        }

        Set<String> strings = new HashSet<>();
        strings.add("string");
        strings.remove("string");
        //第一种
        for (String s : strings) {
            System.out.println("value=" + s);
        }
        //第二种
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String value = iterator.next();
        }
    }

    public static void selectSort(int array[]) {
        StringBuffer buffer = new StringBuffer();
        int i, j, t = 0;
        for (i = 1; i < array.length; i++) {
            t = array[i];
            for (j = i - 1; j >= 0 && t > array[j]; j--) {
                array[j + 1] = array[j];
                array[j] = t;
            }
        }

        for (int k = 0; k < array.length; k++) {
            buffer.append(array[k] + ",");
        }
        System.out.println(buffer.toString());
    }

    public static void quickSort(int array[], int low, int high) {// 传入low=0，high=array.length-1;
        int pivot, p_pos, i, t;// pivot->位索引;p_pos->轴值。
        if (low < high) {
            p_pos = low;
            pivot = array[p_pos];
            for (i = low + 1; i <= high; i++)
                if (array[i] > pivot) {
                    p_pos++;
                    t = array[p_pos];
                    array[p_pos] = array[i];
                    array[i] = t;
                }
            t = array[low];
            array[low] = array[p_pos];
            array[p_pos] = t;
            System.out.println("t=" + t + "=p_pos=" + p_pos + ",pos=" + array[p_pos] + ",=pivot=" + pivot + ",low=" + array[low]);
            // 分而治之
            quickSort(array, low, p_pos - 1);// 排序左半部分
            quickSort(array, p_pos + 1, high);// 排序右半部分
        }
    }

//    static test st = new test();
//
//    static {
//        System.out.println("1");
//    }
//
//    {
//        System.out.println("2");
//    }
//
//    test() {
//        System.out.println("3");
//        System.out.println("a=" + a + ",b=" + b);
//    }
//
//    public static void staticFunction() {
//        System.out.println("4");
//        System.out.println("b=" + b);
//    }
//
//    int a = 110;
//    static int b = 112;
//
//    static int i = 123;//放在下面静态块后面，会出现illegal forward reference,非法调用
//
//    static {
//        i = 0;
//        System.out.println("i==" + i);
//    }

    public void a() {
        new Handler().post(null);
        Thread.interrupted();
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        condition.signalAll();
        synchronized (lock) {

        }
        new Thread() {
            @Override
            public void run() {
                super.run();
                Looper.prepare();
                Looper.loop();
            }
        };
    }
}
