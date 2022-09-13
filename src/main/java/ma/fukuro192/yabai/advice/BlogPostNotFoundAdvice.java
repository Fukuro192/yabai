package ma.fukuro192.yabai.advice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import ma.fukuro192.yabai.exception.BlogPostNotFoundException;
import ma.fukuro192.yabai.exception.response.BlogPostNotFoundResponse;

@ControllerAdvice
public class BlogPostNotFoundAdvice {
    
    @ResponseBody
    @ExceptionHandler(BlogPostNotFoundException.class)
    public ResponseEntity<BlogPostNotFoundResponse> handleBlogPostNotFoundException
    (BlogPostNotFoundException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        BlogPostNotFoundResponse response = new BlogPostNotFoundResponse
            (e.getMessage(), badRequest, ZonedDateTime.now(ZoneId.of("Z")));
        
        return new ResponseEntity<BlogPostNotFoundResponse>(response, badRequest);
    }
}
