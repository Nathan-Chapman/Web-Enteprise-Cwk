package nathanchapman.cwk3.bus;

public class BusinessException extends Exception {

    public BusinessException() {
    }

    public BusinessException(String string) {
        super(string);
    }

    public BusinessException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
    
}
