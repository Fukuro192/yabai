package ma.fukuro192.yabai.exception.response;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class BlogPostNotFoundResponse {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    public BlogPostNotFoundResponse(
        String message,
        HttpStatus httpStatus,
        ZonedDateTime timestamp
    ) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    /**
     * @return String return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return HttpStatus return the httpStatus
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * @return ZonedDateTime return the timestamp
     */
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

}
