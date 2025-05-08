import java.io.IOException;


public class MyException extends IOException {
  public MyException(String message, Throwable source) {
    super(message, source);
  }
}


