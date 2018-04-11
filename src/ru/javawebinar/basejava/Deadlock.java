package ru.javawebinar.basejava;

public class Deadlock {
    private static Integer account1 = 100;
    private static Integer account2 = 50;

    public static void main(String[] args){


        Thread thread1 = new Thread(() -> {
            transfer(account1, account2, 50);
        });

         Thread thread2 = new Thread(() -> {
            transfer(account2, account1, 20);
        });

         thread1.start();
         thread2.start();
    }

    private static void transfer(Integer accFrom, Integer accTo, Integer sum) {
        try {
            synchronized (accFrom) {
                Thread.sleep(100);
                synchronized (accTo) {
                    accFrom = accFrom - sum;
                    accTo = accTo + sum;
                }
            }
            System.out.println("Transer finished");
        } catch (InterruptedException e) {

        }
    }
}
