package com.panel.LRapp.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Mail {
    private String to;
    private String code;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Mail(String to, String code) {
        this.to = to;
        this.code = code;
    }
}
