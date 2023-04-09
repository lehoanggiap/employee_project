package glh.libraries.filter.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO <T> {

    private HttpStatus status;

    private String msg;

    private T data;

    private List<T> dataList;

    @Builder.Default
    private long timestamp = System.currentTimeMillis();
}
