package ma.fukuro192.yabai.exception;

public class BlogPostNotFoundException extends RuntimeException {

    public BlogPostNotFoundException(String message) {
        super(message);
    }

    public BlogPostNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
