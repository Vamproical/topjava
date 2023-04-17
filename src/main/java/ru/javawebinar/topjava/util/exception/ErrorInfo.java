package ru.javawebinar.topjava.util.exception;

import java.util.List;
import java.util.Objects;

public class ErrorInfo {
    private String url;
    private ErrorType type;
    private List<String> details;

    public ErrorInfo() {

    }
    public ErrorInfo(CharSequence url, ErrorType type, String... details) {
        this.url = url.toString();
        this.type = type;
        this.details = List.of(details);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setType(ErrorType type) {
        this.type = type;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorInfo info = (ErrorInfo) o;
        return Objects.equals(url, info.url) && type == info.type && Objects.equals(details, info.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, type, details);
    }
}