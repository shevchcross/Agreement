package me.alexeyshevchenko.agreement_backend.dto;

/**
 * Created by ${Aleksey} on 06.08.2018.
 */
public class ErrorDTO {
    private final int code;
    private final int status;
    private final String message;

    public ErrorDTO(int code, int status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
