package Lesson3;

public class Lesson3 {
    public static <T> void swap(T[] arr, int from, int to){
        T temp = arr[to];
        arr[to] = arr[from];
        arr[from] = temp;
    }

    public static void main(String[] args){
        // 1.
        String[] names = {"Kate", "Peter", "Santa", "Molly"};
        System.out.println(names[1] + ", " + names[2]);
        swap(names, 1, 2);
        System.out.println(names[1] + ", " + names[2]);

        // 2d.
        Apple[] apples = {new Apple(0.5), new Apple(0.2), new Apple(0.8)};
        Box<Fruit> appleBox = new Box<Fruit>();
        appleBox.add(apples);
        System.out.println("AppleBox weight = " + appleBox.getWeight());

        Orange[] oranges = {new Orange(0.8), new Orange(0.6), new Orange(0.9)};
        Box<Fruit> orangeBox = new Box<Fruit>();
        orangeBox.add(oranges);
        System.out.println("OrangeBox weight = " + orangeBox.getWeight());

        // 2e.
        if (appleBox.compareTo(orangeBox) < 0) System.out.println("AppleBox less then OrangeBox");

        // 2f.
        Apple[] apples1 = {new Apple(0.3), new Apple(0.2)};
        Box<Fruit> appleBox1 = new Box<Fruit>();
        appleBox1.add(apples1);
        appleBox.pourover(appleBox1); // пересыпаем содержимое созданной коробки
        System.out.println("AppleBox new weight = " + appleBox.getWeight());

        // Итого на консоль выведено:
        // Peter, Santa
        // Santa, Peter
        // AppleBox weight = 1.5
        // OrangeBox weight = 2.3
        // AppleBox less then OrangeBox
        // AppleBox new weight = 2.0
    }
}
