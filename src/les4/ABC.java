package les4;

public class ABC {
    static Object mon = new Object();
    static volatile char letter = 'C';

    public static void main(String[] args) {
        new Thread(() -> {
           try {
               for (int i = 0; i < 5; i++) {
                   synchronized (mon) {
                       while (letter != 'A') {
                           mon.wait();
                       }
                       System.out.print("A");
                       letter = 'B';
                       mon.notifyAll();
                   }
               }
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
        }).start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (mon) {
                        while (letter != 'B') {
                            mon.wait();
                        }
                        System.out.print("B");
                        letter = 'C';
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (mon) {
                        while (letter != 'C') {
                            mon.wait();
                        }
                        System.out.print("C");
                        letter = 'A';
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}


