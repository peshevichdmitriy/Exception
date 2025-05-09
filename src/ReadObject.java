import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadObject implements ObjectReader {


    @Override
    public List<Object> readAllObjects() throws MyException {
        List<Object> result = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("FILE.bin"))) {
            while (true) {
                Object obj = ois.readObject();
                result.add(obj);
            }
        } catch (EOFException e) {
            // Конец файла — ожидаемое поведение
        } catch (IOException | ClassNotFoundException e) {
            throw new MyException("Ошибка при чтении файла", e);
        }
        return result;
    }
}
