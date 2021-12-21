package Lesson4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2. Написать простой класс «Телефонный Справочник», который хранит в себе список фамилий
// и телефонных номеров. В этот телефонный справочник с помощью метода add() можно добавлять записи,
// а с помощью метода get() искать номер телефона по фамилии.
// Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
// тогда при запросе такой фамилии должны выводиться все телефоны.
public class PhoneBook {
    private Map<Integer, String> phonebook = new HashMap<Integer, String>();

    public void add(String name, Integer phone){
        phonebook.put(phone, name);
    }

    public List<Integer> get(String name){
        List<Integer> result = new ArrayList<>();
        boolean find = false;
        for (Map.Entry<Integer, String> s: phonebook.entrySet()) {
            if (s.getValue().equals(name))
            {
                result.add(s.getKey());
                find = true;
            }
        }
        if (find == true) return  result; else return null;
    }
}
