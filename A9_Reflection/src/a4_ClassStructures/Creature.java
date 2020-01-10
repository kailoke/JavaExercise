package a4_ClassStructures;

import java.io.Serializable;

public class Creature<T> implements Serializable {
    private char gender;
    public double weight;

    public Creature() {
    }

    private Creature(char gender) {
        this.gender = gender;
    }

    public Creature(char gender, double weight) {
        this.gender = gender;
        this.weight = weight;
    }

    private void breath(){
        System.out.println("生物呼吸");
    }

    public void eat(){
        System.out.println("生物吃");
    }
}
