// 1. 要求线程a执行完才开始线程b，线程b执行完才开始线程c

import java.util.concurrent.CountDownLatch;

public class Q01_ThreadOrder {
     static volatile boolean found=false;
    public static void main(String[] args) {
    Thread thread1=new Thread(new Task("THREAD1",10));
    Thread thread2=new Thread(new Task("THREAD2",10));
    Thread thread3=new Thread(new Task("THREAD3",10));


    /*
     * join()方法的作用：
    join()是Thread类的final方法，用于让当前线程等待调用join()的线程执行完成
    底层原理：join()内部使用wait()方法，当线程结束时JVM会调用notifyAll()
    可以指定超时时间：join(long millis)

    join()会抛出InterruptedException，需要捕获
如果线程已经结束，join()会立即返回
这种方式由主线程控制顺序，不是线程间协作
     */
        // try {
        //     thread1.start();
        //     thread1.join(1);
        //     thread2.start();
        //     thread2.join(10);
        //     thread3.start();
        //     thread3.join(13);
        // } catch (Exception e) {
        //     // TODO: handle exception
        // }
//---------------------------------------
        int i=0;
        int target=800;
        CountDownLatch latch1=new CountDownLatch(1);
         CountDownLatch latch2=new CountDownLatch(1);
          CountDownLatch latch3=new CountDownLatch(1);
     
        Thread t1=new Thread(()->{
            for (int j = i; j < 10; j++) {
                 if(found) break;
                System.out.println(j);
                if(j==target){
                    System.out.println("找到目标，0-10");
                    found=true;
                    latch3.countDown();
                 }
            }
              latch1.countDown();
        });

          Thread t2=new Thread(()->{

            try{latch1.await();}
            catch(InterruptedException e){
                e.printStackTrace();
            }
            for (int j = i; j < 100; j++) {
                 if(found) break;
                System.out.println(j);
                if(j==target){
                    System.out.println("找到目标，10-100");
                     found=true;
                     latch3.countDown();
                 
                }
            }
              latch2.countDown();
        });
          Thread t3=new Thread(()->{

             try{latch2.await();}
            catch(InterruptedException e){
                e.printStackTrace();
            }
            for (int j = i; j < 1000; j++) {
                if(found) break;
                  System.out.println(j);
                if(j==target){
                 System.out.println("找到目标，100-1000");
                  found=true;
                  latch3.countDown();
                }
            }
        });
        Thread t4=new Thread(()->{
        
               try {
                latch3.await();
                   System.out.println("已经结束了！");
               } catch (Exception e) {
               e.printStackTrace();
               } 
               
         


        });

            t1.start();
            t2.start();
            t3.start();
            t4.start();


    }


    public static class Task implements Runnable{
        String threadName;
        int num;
        Task(String s,int n){
            this.threadName=s;
            this.num=n;
        }

        @Override
        public void run() {
            for (int i = 0; i < num ; i++) {
                System.out.println(threadName+"-->");
                try{
                    Thread.sleep(i);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                
            }
            System.out.println(threadName+"FINISHED");
         
        }

      

    }

}
