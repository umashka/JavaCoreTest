package Lesson4;

import java.util.*;
import java.util.stream.Collectors;

public class Lesson4 {
    public static void main(String[] args) {
        // 1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
        // Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
        // Посчитать, сколько раз встречается каждое слово.
        String[] names = {"Kate", "Peter", "Peter", "Santa", "Molly", "Molly", "Molly",
                "Toby", "Kamila", "Kamila", "Peter", "Santa", "Molly"};

        // Попробую set
        Set<String> strSet = Arrays.stream(names).collect(Collectors.toSet());
        System.out.println(strSet);
        // Выведено на консоль:
        // [Santa, Kamila, Kate, Toby, Molly, Peter]

        //Map<String, Integer> strMap = strSet.stream().collect(Collectors.toMap(String::valueOf,o -> 0));

        Set<String> StrLinked = new LinkedHashSet<>();
        for (String s: names) {
            StrLinked.add(s);
        }
        for (String str : StrLinked) {
            int count = 0;
            for (String s : names) {
                if(str.equals(s)) count++;
            }
            System.out.println("\"" + str + "\"" + ": " + count + " times.");
        }
        // Выведено на консоль:
        // "Kate": 1 times.
        // "Peter": 3 times.
        // "Santa": 2 times.
        // "Molly": 4 times.
        // "Toby": 1 times.
        // "Kamila": 2 times.

        // 2. Про телефонную книгу:
        PhoneBook phb = new PhoneBook();
        phb.add("Ivanov", 9816734);
        phb.add("Ivanov", 1273465);
        phb.add("Ivanov", 7902341);
        phb.add("Petrov", 3901234);
        phb.add("Sidorov", 2371093);

        System.out.println(phb.get("Ivanov"));
        // Выведено на консоль:
        // [1273465, 9816734, 7902341]
    }
}
