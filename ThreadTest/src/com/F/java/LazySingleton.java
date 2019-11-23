package com.F.java;

public class LazySingleton {
    public static void main(String[] args) {

    }
}

class Singleton {
    private Singleton() {}
    private static Singleton instance = null;
    public static Singleton getInstance() {
        if(instance != null){
            return instance;
        }
        synchronized(Singleton.class) {
            if (instance == null) {
                instance = new Singleton();
            }
            return instance;
        }
    }
}
