package com.mirror.project.infra.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        int statusCode,
        String message,
        Map<String, String> errors,
        LocalDateTime timestamp
) {
}
