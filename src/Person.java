import java.io.*;
import java.util.List;

public class Person implements Serializable {
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        ObjectWriter writer = new WriteObject();
        ObjectReader reader = new ReadObject();
        Person P1 = new Person(22, "Misha");
        Person P2 = new Person(24, "Dima");

        try {
            writer.writeObjects(P1, P2, "Hello, world!", 123);

            List<Object> objects = reader.readAllObjects();
            System.out.println("Прочитано из файла: ");
            objects.stream()
                    .forEach(System.out::println);

        } catch (MyException e) {
            System.out.println("Ошибка при работе с файлом: "+e.getMessage());
        }
    }
}
