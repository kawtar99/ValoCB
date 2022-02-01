package exception;

import entity.Product;

public class ProductNotFountException extends RuntimeException{

    public ProductNotFountException(String msg){
        super(msg);
    }
}
