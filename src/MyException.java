import java.io.IOException;


public class MyException extends IOException {
  public MyException(String message, Throwable ex) {
    super(message, ex);
  }
}


