public class DeadLockTest {
    public static final Object resource1 = new Object();
    public static final Object resource2 = new Object();

    public static void main(String[] args) {
        ThreadOne thread1 = new ThreadOne();
        ThreadTwo thread2 = new ThreadTwo();


        thread1.start();
        thread2.start();
    }

}

class ThreadOne extends Thread{

    @Override
    public void run() {

        synchronized (DeadLockTest.resource1){
            System.out.println("get resource1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            synchronized (DeadLockTest.resource2){
                System.out.println("get resource2");
            }
        }

        System.out.println("finished");
    }
}

class ThreadTwo extends Thread{

    @Override
    public void run() {
        synchronized (DeadLockTest.resource2){
            System.out.println("get resource2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            synchronized (DeadLockTest.resource1){
                System.out.println("get resource1");
            }
        }

        System.out.println("finished");
    }
}
