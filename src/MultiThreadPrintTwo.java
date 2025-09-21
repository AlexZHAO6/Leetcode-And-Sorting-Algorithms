public class MultiThreadPrintTwo {

    // 共享变量，表示当前应该打印哪个字母
    private static int state = 0;

    // 共享对象，作为锁和通信的媒介
    private static final Object lock = new Object();

    public static void main(String[] args) {
        // 创建三个线程
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(state <= 100){
                        synchronized (lock) {
                            // 判断是否轮到自己执行
                            if (state % 2 == 0) {
                                // 修改状态
                                state++;
                                if(state > 100){
                                    lock.notifyAll();
                                    break;
                                }
                                // 打印字母
                                System.out.println("Thread_1: " + state);
                                // 唤醒下一个线程
                                lock.notifyAll();
                            }

                            // 不是则等待
                            lock.wait();
                        }
                    }
                    // 获取锁

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(state <= 100){
                        synchronized (lock) {
                            // 判断是否轮到自己执行
                            if (state % 2 == 1) {
                                // 修改状态
                                state++;
                                if(state > 100){
                                    lock.notifyAll();
                                    break;
                                }
                                // 打印字母
                                System.out.println("Thread_2: " + state);
                                // 唤醒下一个线程
                                lock.notifyAll();
                            }

                            // 不是则等待
                            lock.wait();
                        }
                    }
                    // 获取锁

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 启动三个线程
        threadA.start();
        threadB.start();
    }
}
