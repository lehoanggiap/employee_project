package glh.libraries.filter.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BadRequestException extends RuntimeException{

    public BadRequestException(String msg){
        super(msg);
    }

}
