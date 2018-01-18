package ru.job4j.mt.jmm;

/**
 * Represents visibility issue.
 *
 * @author Alexey Aleshin
 * @version $id\$
 * @since 18.01.2018
 */
public class VisibilityIssueDemo {

    /**
     * Some unsafe variable.
     */
    private static int value = 0;

    /**
     * Runs two threads one to write value and one to read it.
     *
     * @param args args
     * @throws InterruptedException exception
     */
    public static void main(String[] args) throws InterruptedException {
        Thread th1 = new Thread(() -> {
            int tmp = 0;
            while (true) {
                if (tmp != value) {
                    tmp = value;
                    System.out.printf("th1: Value is: %d\n", value);
                }

            }
        });

        Thread th2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                value = value + 1;
                System.out.printf("th2: Value is: %d\n", value);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(0);
        });

        th1.start();
        th2.start();

        th1.join();
        th2.join();

    }
}
