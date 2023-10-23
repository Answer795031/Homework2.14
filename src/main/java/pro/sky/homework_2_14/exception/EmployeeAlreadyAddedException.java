package pro.sky.homework_2_14.exception;

public class EmployeeAlreadyAddedException extends RuntimeException{

    public EmployeeAlreadyAddedException(String message){
        super(message);
    }
}