import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteObject implements ObjectWriter {

    @Override
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
