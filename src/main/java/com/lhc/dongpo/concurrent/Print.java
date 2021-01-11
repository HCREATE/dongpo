package com.lhc.dongpo.concurrent;

/**
 * Description:
 * User: jt.hao
 * Date: 2021-01-11
 * Time: 13:40
 */
public class Print {
    /*
        问题：启动3个线程打印递增的数字, 线程1先打印1,2,3,4,5, 然后是线程2打印6,7,8,9,10, 然后是线程3打印11,12,13,14,15. 接着再由线程1打印16,17,18,19,20....以此类推,
        直到打印到75.  程序的输出结果应该为:

        线程1: 1
        线程1: 2
        线程1: 3
        线程1: 4
        线程1: 5

        线程2: 6
        线程2: 7
        线程2: 8
        线程2: 9
        线程2: 10

        ...

        线程3: 71
        线程3: 72
        线程3: 73
        线程3: 74
        线程3: 75
    */


    public static void main(String[] args){
        Object o = new Object();

        new Thread(new PrintRunnable(o, 1)).start();
        new Thread(new PrintRunnable(o, 2)).start();
        new Thread(new PrintRunnable(o, 3)).start();
    }

}

class PrintRunnable implements Runnable{
    private final Object o;
    private int threadId;
    private static volatile int num = 1;

    PrintRunnable(Object o, int threadId){
        this.o = o;
        this.threadId = threadId;
    }

    @Override
    public void run() {
        while(num < 65){
            synchronized (o){
                while(num/5%3 + 1 != threadId){
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.print("线程"+threadId+": ");
                for(int i=0; i<5; i++) {
                    System.out.print(num + ",");
                    num ++;
                }
                System.out.println();

                o.notifyAll();
            }
        }
    }
}
