package Lesson5;

public class Lesson5 {
    public static void main(String[] args){
        String[] names = {"Day", "Apple", "Peach", "Grapes", "Orange", "Kiwi", "Lemon"};
        int[][] days =
                {
                        {1, 23, 34, 45, 53, 69, 14},
                        {2, 67, 78, 89, 98, 48, 13},
                        {3, 87, 76, 65, 93, 12, 90},
                        {4, 43, 32, 21, 32, 61, 28}
                };
        AppData appData = new AppData(names, days);
        appData.saveToCSVFile("test.csv");

        AppData appData2 = new AppData(null, null);
        //appData2.loadFromCSVFile("test.csv");
        appData2.readCSVFile("test.csv");

        // Выведено на консоль:
        // [[Day, Apple, Peach, Grapes, Orange, Kiwi, Lemon],
        // [1, 23, 34, 45, 53, 69, 14],
        // [2, 67, 78, 89, 98, 48, 13],
        // [3, 87, 76, 65, 93, 12, 90],
        // [4, 43, 32, 21, 32, 61, 28]]
    }
}
