package com.tttn.api.entities.request;

import com.tttn.api.entities.APIRequest;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class GetAllTransactionByDatePayLoad extends APIRequest {

    private String from;

    private String to;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "GetAllTransactionByDatePayLoad{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
