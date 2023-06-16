package stream_task2;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList(
                "Jack", "Connor", "Harry", "George", "Samuel", "John"
        );
        List<String> families = Arrays.asList(
                "Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown"
        );

        Collection<Person> persons = new ArrayList<>();

        //Заполнение массива жителей
        for (int i = 0; i < 3000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        //Количество несовершеннолетних жителей
        long minorsCount = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();

        //Количество призывников
        List<String> recruitsList = persons.stream()
                .filter(person -> person.getAge() >= 18 && person.getAge() < 27)
                .map(Person::getFamily).toList();

        //Количество потенциальных работоспособных жителей
        List<Person> workersList = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() < (person.getSex() == Sex.MAN ? 65 : 60))
                .sorted(Comparator.comparing(person -> person.getFamily()))
                .collect(Collectors.toList());
        
    }


}
