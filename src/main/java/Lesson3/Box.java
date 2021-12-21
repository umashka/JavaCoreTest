package Lesson3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Box <T extends Fruit> implements Comparable<Box<T>>{
    private List<T> items = new ArrayList<>();

    public List<T> getItems(){
        return items;
    }

    public void add(T... obj){
            items.addAll(Arrays.asList(obj));
    }

    public double getWeight(){
        double result = 0;
        for (T i: items) {
             result += i.weight;
        }
        return result;
    }

    public void pourover (Box<T> o){
        for (T i: o.getItems()) {
            this.add(i);
        }
    }

    @Override
    public int compareTo(Box<T> o) {
        return (this.getWeight() < o.getWeight())? -1 : (this.getWeight() == o.getWeight())? 0 : 1 ;
    }
}
