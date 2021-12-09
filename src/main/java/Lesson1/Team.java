package Lesson1;

import java.io.Console;
import java.util.Random;

public class Team {
    public String Title; // Имя команды
    public Member[] Members; // Члены команды, объекты класса Member

    public Team(String title, String[] names){ // В конструктор передаю название команды и список имён игроков
        this.Title = title;
        this.Members = new Member[names.length];
        Random rnd = new Random();
        // Случайным образом задаю способности игроков проходить препятствия
        for (int i = 0; i < names.length; i++){
            this.Members[i] = new Member(names[i], rnd.nextInt(10));
        }
    }

    public void showResults(){ // Показывает игроков, прошедших всю дистанцию
        for (Member m: Members ) {
            if (m.Passed)
            System.out.println(m.Name + ", " + m.Ability + " - passed.");
        }
    }

    public void showAll(){ // Показывает всех игроков
        for (Member m: Members ) {
            System.out.println(m.Name + ", " + m.Ability + ((m.Passed)? " - passed." : " - not passed."));
        }
    }
}
