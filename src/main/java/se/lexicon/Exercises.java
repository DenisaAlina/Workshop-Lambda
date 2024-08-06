package se.lexicon;

import se.lexicon.data.DataStorage;
import se.lexicon.model.Gender;
import se.lexicon.model.Person;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;

    /*
       TODO:  1.	Find everyone that has firstName: “Erik” using findMany().
    */
    public static void exercise1(String message) {
        System.out.println(message);
        List<Person> persons = storage.findMany(p -> p.getFirstName().equalsIgnoreCase("Erik"));
        persons.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        TODO:  2.	Find all females in the collection using findMany().
     */
    public static void exercise2(String message) {
        System.out.println(message);
        List<Person> females = storage.findMany(p -> p.getGender() == Gender.FEMALE);
        females.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        TODO:  3.	Find all who are born after (and including) 2000-01-01 using findMany().
     */
    public static void exercise3(String message) {
        System.out.println(message);
        List<Person> persons = storage.findMany(p -> p.getBirthDate().isAfter(LocalDate.of(2000, 1, 1)) ||
                p.getBirthDate().isEqual(LocalDate.of(2000, 1, 1)));
        persons.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        TODO: 4.	Find the Person that has an id of 123 using findOne().
     */
    public static void exercise4(String message) {
        System.out.println(message);
        Person person = storage.findOne(p -> p.getId() == 123);
        System.out.println(person);
        System.out.println("----------------------");

    }

    /*
        TODO:  5.	Find the Person that has an id of 456 and convert to String with following content:
            “Name: Nisse Nilsson born 1999-09-09”. Use findOneAndMapToString().
     */
    public static void exercise5(String message) {
        System.out.println(message);
        String personString = storage.findOneAndMapToString(p -> p.getId() == 456,
                p -> "Name: " + p.getFirstName() + " " + p.getLastName() + " born " + p.getBirthDate());
        System.out.println(personString);
        System.out.println("----------------------");
    }

    /*
        TODO:  6.	Find all male people whose names start with “E” and convert each to a String using findManyAndMapEachToString().
     */
    public static void exercise6(String message) {
        System.out.println(message);
        List<String> names = storage.findManyAndMapEachToString(p -> p.getGender() == Gender.MALE && p.getFirstName().startsWith("E"),
                p -> p.getFirstName() + " " + p.getLastName());
        names.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        TODO:  7.	Find all people who are below age of 10 and convert them to a String like this:
            “Olle Svensson 9 years”. Use findManyAndMapEachToString() method.
     */
    public static void exercise7(String message) {
        System.out.println(message);
        LocalDate today = LocalDate.now();
        List<String> children = storage.findManyAndMapEachToString(
                p -> today.minusYears(10).isBefore(p.getBirthDate()),
                p -> p.getFirstName() + " " + p.getLastName() + " " + (today.getYear() - p.getBirthDate().getYear()) + " years"
        );
        children.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        TODO:  8.	Using findAndDo() print out all people with firstName “Ulf”.
     */
    public static void exercise8(String message) {
        System.out.println(message);
        storage.findAndDo(p -> p.getFirstName().equalsIgnoreCase("Ulf"), System.out::println);
        System.out.println("----------------------");
    }

    /*
        TODO:  9.	Using findAndDo() print out everyone who have their lastName contain their firstName.
     */
    public static void exercise9(String message) {
        System.out.println(message);
        storage.findAndDo(p -> p.getLastName().contains(p.getFirstName()), System.out::println);
        System.out.println("----------------------");
    }

    /*
        TODO:  10.	Using findAndDo() print out the firstName and lastName of everyone whose firstName is a palindrome.
     */
    public static void exercise10(String message) {
        System.out.println(message);
        storage.findAndDo(
                p -> new StringBuilder(p.getFirstName()).reverse().toString().equalsIgnoreCase(p.getFirstName()),
                p -> System.out.println(p.getFirstName() + " " + p.getLastName())
        );
        System.out.println("----------------------");
    }

    /*
        TODO:  11.	Using findAndSort() find everyone whose firstName starts with A sorted by birthdate.
     */
    public static void exercise11(String message) {
        System.out.println(message);
        List<Person> sortedPersons = storage.findAndSort(p -> p.getFirstName().startsWith("A"), Comparator.comparing(Person::getBirthDate));
        sortedPersons.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        TODO:  12.	Using findAndSort() find everyone born before 1950 sorted reversed by lastest to earliest.
     */
    public static void exercise12(String message) {
        System.out.println(message);
        List<Person> sortedPersons = storage.findAndSort(p -> p.getBirthDate().isBefore(LocalDate.of(1950, 1, 1)),
                Comparator.comparing(Person::getBirthDate).reversed());
        sortedPersons.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        TODO:  13.	Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message) {
        System.out.println(message);
        List<Person> sortedPersons = storage.findAndSort(
                (p1, p2) -> {
                    int lastNameComparison = p1.getLastName().compareTo(p2.getLastName());
                    if (lastNameComparison != 0) return lastNameComparison;
                    int firstNameComparison = p1.getFirstName().compareTo(p2.getFirstName());
                    if (firstNameComparison != 0) return firstNameComparison;
                    return p1.getBirthDate().compareTo(p2.getBirthDate());
                }
        );
        sortedPersons.forEach(System.out::println);
        System.out.println("----------------------");
    }
}
