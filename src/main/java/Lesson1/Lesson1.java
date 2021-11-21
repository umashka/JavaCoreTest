package Lesson1;

public class Lesson1 {
    public static void main(String[] args){
        System.out.println("Hello");

        Course course = new Course(10);
        course.showBarriers();

        String[] names = {"Mike", "Sonya", "Kate", "Eduard", "Kassie", "Tony", "Zoe", "Jhon", "Bamby"};
        Team team = new Team("BestTeam", names);

        course.doIt(team);

        System.out.println("---- Passed:");
        team.showResults();
        System.out.println("---- Total:");
        team.showAll();

        // Выведено на консоли:
        //Hello
        //Barriers: 0 1 0 1 3 3 3 6 3 6
        //---- Passed:
        //Mike, 6 - passed.
        //Sonya, 6 - passed.
        //Tony, 6 - passed.
        //Jhon, 7 - passed.
        //---- Total:
        //Mike, 6 - passed.
        //Sonya, 6 - passed.
        //Kate, 5 - not passed.
        //Eduard, 2 - not passed.
        //Kassie, 3 - not passed.
        //Tony, 6 - passed.
        //Zoe, 1 - not passed.
        //Jhon, 7 - passed.
        //Bamby, 4 - not passed.
    }
}
