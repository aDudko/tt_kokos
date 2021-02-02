package com.kokos.demo.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.StringUtils;

import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "ResponsePage", description = "Объект обертка для всех ответов")
public class ResponsePage<T> {

    @XmlElementWrapper(name = "items")
    private List<T> items = new ArrayList<>();

    private ResponseError error;

    @JsonIgnore
    private List<ResponseError> errors = new ArrayList<>();

    private String msg;

    private boolean success = true;

    public ResponsePage(boolean success) {
        this.success = success;
    }

    public ResponsePage(List<T> items) {
        if (items == null){
            this.items = new ArrayList<>();
        } else {
            this.items = items;
        }
    }

    public ResponsePage(List<T> items, boolean success) {
        if (items == null){
            this.items = new ArrayList<>();
        } else {
            this.items = items;
        }
        this.success = success;
    }

    public ResponsePage(T item) {
        this.items = Collections.singletonList(item);
    }

    public ResponsePage(T item, boolean success) {
        this.items = Collections.singletonList(item);
        this.success = success;
    }

    public ResponsePage(String error) {
        if(!StringUtils.isEmpty(error)) {
            this.error = new ResponseError("0", error);
            this.errors.add(new ResponseError("0", error));
            this.msg = error;
        }
        this.success = false;
    }

    public ResponsePage(ResponseError error) {
        if(error != null) {
            this.errors.add(error);
            this.error = error;
            this.msg = error.getErrorName();
            this.success = false;
        }
    }

    public void addError(String error) {
        if(!StringUtils.isEmpty(error)) {
            this.errors.add(new ResponseError("0", error));
            this.error = new ResponseError("0", error);
            this.msg = error;
        }
        this.success = false;
    }

    public void addError(ResponseError error) {
        if(error != null) {
            this.errors.add(error);
            this.error = error;
            this.msg = error.getErrorName();
        }
        this.success = false;
    }
}

