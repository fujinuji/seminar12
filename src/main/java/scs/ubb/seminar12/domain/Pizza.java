package scs.ubb.seminar12.domain;

public class Pizza {
    private Float price;
    private String name;
    private Integer cookedPercentage;
    private boolean isOrdered = false;

    public Pizza(Float price, String name) {
        this.price = price;
        this.name = name;
    }

    public synchronized void cookAndDeliver() {
        cookedPercentage = 0;
        while (!isOrdered) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (cookedPercentage <= 100) {
            System.out.println(cookedPercentage.toString() + "% cooked...");
            try {
                Thread.sleep(10);
                cookedPercentage += 10;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Pizza is cooked, waiting for payment!");
        notifyAll();
        try {
            wait();
            System.out.println("Pizza has been paid <3");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("This Thread is dead over. Enjoy your pizza <3");
    }

    public synchronized void oderPizza() {
        System.out.println("Ordering a pizza");
        isOrdered = true;
        notifyAll();
        try {
            wait();
            System.out.println("Finding change...");
            Thread.sleep(10);
            notifyAll();
            Thread.sleep(10);
            System.out.println("This Thread Is also dead. I'm happy with my pizza <3");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}


