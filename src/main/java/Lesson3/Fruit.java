package Lesson3;

public class Fruit implements Comparable<Fruit>{
    public double weight;
    public Fruit(double weight){
        this.weight = weight;
    }

    @Override
    public int compareTo(Fruit o) {
        return (this.weight < o.weight)? -1 : (this.weight == o.weight)? 0 : 1 ;
    }
}
