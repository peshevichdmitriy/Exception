import java.io.*;
import java.util.ArrayList;
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

    // Класс записи
    static class WriteObject {
        public void writeObjects(Object... objects) throws MyException {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("FILE.bin"))) {
                for (Object obj : objects) {
                    oos.writeObject(obj);
                }
                System.out.println("Данные успешно записаны");
            } catch (IOException e) {
                throw new MyException("Ошибка при записи", e);
            }
        }
    }

    // Класс чтения
    static class ReadObject {
        public List<Object> readAllObjects() throws MyException {
        List<Object> result = new ArrayList<>();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("FILE.bin"))) {
                while (true) {
                    Object obj = ois.readObject();
                    result.add(obj);
                }
            } catch (EOFException e) {
            } catch (IOException | ClassNotFoundException e) {
                throw new MyException("Ошибка при чтении файла", e);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        WriteObject writer = new WriteObject();
        ReadObject reader = new ReadObject();
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
