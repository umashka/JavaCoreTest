package Lesson2;

public class MyArrayDataException extends NumberFormatException {
    private int i;
    private int j;
    private String message;
    public int getI(){return i;}
    public int getJ(){return j;}
    public String getMessage() {return  message;}
    public MyArrayDataException(String message, int i, int j){
        this.i = i;
        this.j = j;
        this.message = message;
    }
}
