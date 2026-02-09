package thread.sync.test;

public class ThreadTest3 {
    static class NumberPrinter {
        private int current = 1;

        public synchronized void print(int number) {
            while (current != number) {
                try {
                    wait();
                }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            IO.println(number);
            current++;
            notifyAll();
        }
    }

    static void main() {
        NumberPrinter printer = new NumberPrinter();
        for (int i = 1; i <= 10; i++) {
            int num = i;
            new Thread(() -> printer.print(num)).start();
        }
    }
}
