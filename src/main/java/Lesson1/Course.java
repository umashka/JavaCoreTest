package Lesson1;

import java.util.Random;

public class Course {
    public int[] barriers; // Массив препятствий, генерим случайным образом в конструкторе

    public  Course(int barriersCount){
        barriers = new int[barriersCount];
        Random rnd = new Random();
        for (int i = 0; i < barriers.length; i++){
            barriers[i] = rnd.nextInt(8);
        }
    }

    public void doIt(Team team){
        for (Member m: team.Members) {
            Boolean passed = false;
            for (int i: barriers) {
                passed = (m.Ability >= i)? true : false;
                if (passed == false) break;
            }
            m.Passed = passed;
        }
    }

    // Метод показывает все препятствия
    public void showBarriers(){
        System.out.print("Barriers: ");
        for (int i: barriers) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
