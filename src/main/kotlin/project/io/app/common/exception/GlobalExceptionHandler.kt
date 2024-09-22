package project.io.app.common.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingPathVariableException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.NoHandlerFoundException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    internal fun resolveIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(400)
            .body(ErrorResponse(400, "IllegalArgumentException."))
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    internal fun resolveMissingPathVariableException(ex: MethodArgumentTypeMismatchException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(400)
            .body(ErrorResponse(400, "MethodArgumentTypeMismatchException."))
    }

    @ExceptionHandler(MissingPathVariableException::class)
    internal fun resolveMissingPathVariableException(ex: MissingPathVariableException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(400)
            .body(ErrorResponse(400, "MissingPathVariableException."))
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    internal fun resolveMissingServletRequestParameterException(ex: MissingServletRequestParameterException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(400)
            .body(ErrorResponse(400, "MissingServletRequestParameterException."))
    }

    @ExceptionHandler(NoHandlerFoundException::class)
    internal fun resolveNoHandlerFoundException(ex: NoHandlerFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(404)
            .body(ErrorResponse(404, "NoHandlerFoundException."))
    }

    @ExceptionHandler(Exception::class)
    internal fun resolveException(ex: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(500)
            .body(ErrorResponse(500, "Internal server error."))
    }

}

internal data class ErrorResponse(
    val code: Int,
    val message: String,
)
