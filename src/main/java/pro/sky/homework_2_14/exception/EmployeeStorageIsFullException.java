package pro.sky.homework_2_14.exception;

public class EmployeeStorageIsFullException extends RuntimeException{

    public EmployeeStorageIsFullException(String message){
        super(message);
    }
}