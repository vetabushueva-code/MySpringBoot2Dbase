package ru.bushuevavioletta.MySpringBoot2Dbase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Ответ об успехе или неуспехе операции.
 * success – признак успешного выполнения, message – описание результата,
 * data – данные, которые вернула операция (может быть null).
 */
@Data
@AllArgsConstructor
public class OperationResponse<T> {

    private boolean success;
    private String message;
    private T data;

    public static <T> OperationResponse<T> ok(String message, T data) {
        return new OperationResponse<>(true, message, data);
    }

    public static <T> OperationResponse<T> fail(String message) {
        return new OperationResponse<>(false, message, null);
    }
}
