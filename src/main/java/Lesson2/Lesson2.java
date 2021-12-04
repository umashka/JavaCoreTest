package Lesson2;

public class Lesson2 {
    public static double toIntAndSumArray(String[][] arr) throws MyArraySizeException{
        if ((arr.length != 4) || (arr[0].length != 4)) {throw new MyArraySizeException("Array size must be 4x4!");}
        double sum = 0;
        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr[0].length; j++) {
                try {
                    sum += Double.parseDouble(arr[i][j]);
                } catch (NumberFormatException ex) {
                    throw new MyArrayDataException("Cannot convert value to double", i, j);
                }
            }
        return sum;
    }


    public static void main(String[] args){
        try {
            String[][] correctArray =
                    {
                            {"12", "23", "34", "45"},
                            {"56", "67", "78", "89"},
                            {"98", "87", "76", "65"},
                            {"54", "43", "32", "21"}
                    };
            String[][] incorrectArray1 =
                    {
                            {"12", "23", "34", "45", "1"},
                            {"56", "67", "78", "89", "2"},
                            {"98", "87", "76", "65", "3"},
                            {"54", "43", "32", "21", "4"}
                    };
            String[][] incorrectArray2 =
                    {
                            {"12", "23", "34", "45"},
                            {"56", "ow!", "78", "89"},
                            {"98", "87", "76", "65"},
                            {"54", "43", "32", "21"}
                    };
            double d = toIntAndSumArray(correctArray);

            // Запускаю попеременно то эту строку, то следующую:
            // double d1 = toIntAndSumArray(incorrectArray1);
            double d2 = toIntAndSumArray(incorrectArray2);
            System.out.println(d);
        }
        catch (MyArraySizeException ex)
        {
            System.out.println(ex.getMessage());
            // Выведено на консоль:
            // Array size must be 4x4!
        }
        catch (MyArrayDataException ex)
        {
            System.out.println(ex.getMessage() + ": (" + ex.getI() + ", " + ex.getJ() + ").");
            // Выведено на консоль:
            // Cannot convert value to double: (1, 1).
        }
    }
}
