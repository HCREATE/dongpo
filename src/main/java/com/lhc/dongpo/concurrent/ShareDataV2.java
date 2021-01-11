package com.lhc.dongpo.concurrent;

/**
 * Description:
 * User: jt.hao
 * Date: 2021-01-11
 * Time: 17:24
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  问题：生产者消费者模型，是互联网应用模型中比较经典的模型。
 *  要求实现这一模型，并实例化3个生产者和3个消费者，保证在不主动停止程序的前提下，生产和消费能够一直进行
 * （即当消费者无商品可消费的时候，等待生产者生产；生产者到达生产上限时候，停止生产），注意考虑线程安全。编程语言不限。
 */
public class ShareDataV2 {

    public static AtomicInteger atomicInteger = new AtomicInteger();
    public volatile boolean flag = true;
    public static final int MAX_COUNT = 10;
    public static final List<Integer> pool = new ArrayList<>();

    public void produce() {
        // 判断，干活，通知
        while (flag) {
            // 每隔 10 毫秒生产一个商品
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            synchronized (pool) {
                //池子满了，生产者停止生产
                while (pool.size() == MAX_COUNT) {
                    try {
                        System.out.println("pool is full, wating...");
                        pool.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //干活
                pool.add(atomicInteger.incrementAndGet());
                System.out.println("produce number:" + atomicInteger.get() + "\t" + "current size:" + pool.size());
                //通知
                pool.notifyAll();
            }
        }
    }

    public void consumue() {
        // 判断，干活，通知
        while (flag) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            synchronized (pool) {
                //池子满了，生产者停止生产
                while (pool.size() == 0) {
                    try {
                        System.out.println("pool is empty, wating...");
                        pool.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //干活
                int temp = pool.get(0);
                pool.remove(0);
                System.out.println("cousume number:" + temp + "\t" + "current size:" + pool.size());
                //通知
                pool.notifyAll();
            }
        }
    }

    public void stop() {
        flag = false;
    }

    public static void main(String[] args) {
        ShareDataV2 shareDataV2 = new ShareDataV2();
        new Thread(() -> {
            shareDataV2.produce();
        }, "AAA").start();

        new Thread(() -> {
            shareDataV2.consumue();
        }, "BBB").start();

        new Thread(() -> {
            shareDataV2.produce();
        }, "AA").start();

        new Thread(() -> {
            shareDataV2.consumue();
        }, "BB").start();

        new Thread(() -> {
            shareDataV2.produce();
        }, "A").start();

        new Thread(() -> {
            shareDataV2.consumue();
        }, "B").start();
    }
}