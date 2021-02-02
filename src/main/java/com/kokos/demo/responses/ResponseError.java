package com.kokos.demo.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "ResponseError", description = "Объект обертка ошибок")
public class ResponseError {

    private String errorName;

    private String errorCode;

    private Map<String, Object> errorVars = null;

    public ResponseError(String errorCode,  String errorName) {
        this.errorName = errorName;
        this.errorCode = errorCode;
    }

    public ResponseError(String errorCode, String errorName, Map<String, Object> errorVars) {
        this.errorName = errorName;
        this.errorCode = errorCode;
        if(errorVars != null && !errorVars.isEmpty())
            this.setErrorVars(errorVars);
    }

}
