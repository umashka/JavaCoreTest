package Lesson1;

public class Member {
    public String Name; // Имя
    public int Ability; // Способность проходить препятствия
    public boolean Passed; // Прошел дистанцию, или нет. По умолчанию не прошел
    public Member(String name, int ability){
        this.Ability = ability;
        this.Name = name;
        this.Passed = false;
    }
}
