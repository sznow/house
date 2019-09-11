package com.mooc.house.thread;

public class Run {
    private static SynchronizeObject<Object> syn = new SynchronizeObject<>();
    public static void test(String code){
        syn.run(code, new Runnable() {
            @Override
            public Object run(Object obj) {
                return null;
            }
        });
    }
    public static void main(String[] args) {
        Thread thread1 = new Thread(new java.lang.Runnable() {
            @Override
            public void run() {
                    Run.test("111111111111");
            }
        });
        Thread thread2 = new Thread(new java.lang.Runnable() {
            @Override
            public void run() {
                    Run.test("111111111111");
            }
        });

        thread1.start();
        thread2.start();
    }
}
