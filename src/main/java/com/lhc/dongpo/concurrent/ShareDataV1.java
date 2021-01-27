package com.lhc.dongpo.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 * User: jt.hao
 * Date: 2021-01-11
 * Time: 16:03
 */

/**
 * 问题：生产者消费者模型，是互联网应用模型中比较经典的模型。
 * 要求实现这一模型，并实例化3个生产者和3个消费者，保证在不主动停止程序的前提下，生产和消费能够一直进行
 * （即当消费者无商品可消费的时候，等待生产者生产；生产者到达生产上限时候，停止生产），注意考虑线程安全。编程语言不限。
 */
public class ShareDataV1 {
    private static final int MAX_CAPACITY = 10; //阻塞队列容量
    private static BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(MAX_CAPACITY); //阻塞队列
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    public void produce() throws InterruptedException {
        while (true) {
            boolean retvalue = blockingQueue.offer(atomicInteger.incrementAndGet(), 2, TimeUnit.SECONDS);
            if (retvalue == true) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + atomicInteger.get() + "成功" + "资源队列大小= " + blockingQueue.size());
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + atomicInteger.get() + "失败" + "资源队列大小= " + blockingQueue.size());

            }
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public void consume() throws InterruptedException {
        Integer result = null;
        while (true) {
            result = blockingQueue.poll(2, TimeUnit.SECONDS);
            if (null == result) {
                Thread.sleep(5000);
            }
            System.out.println(Thread.currentThread().getName() + "\t 消费" + result + "成功" + "\t\t" + "资源队列大小= " + blockingQueue.size());
            Thread.sleep(1500);
        }

    }


    public static void main(String[] args) {
        ShareDataV1 v1 = new ShareDataV1();
        new Thread(() -> {
            try {
                v1.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                v1.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AA").start();

        new Thread(() -> {
            try {
                v1.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();

        new Thread(() -> {
            try {
                v1.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(() -> {
            try {
                v1.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BB").start();

        new Thread(() -> {
            try {
                v1.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BBB").start();

    }
}
