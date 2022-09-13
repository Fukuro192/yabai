package ma.fukuro192.yabai.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ma.fukuro192.yabai.entity.BlogPost;
import ma.fukuro192.yabai.exception.BlogPostNotFoundException;
import ma.fukuro192.yabai.repository.BlogPostRepository;

@RestController
@RequestMapping("/post")
public class BlogPostController {
    
    @PersistenceContext
    private EntityManager entityManager;
    private BlogPostRepository blogPostRepository;

    public BlogPostController(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @PostMapping("/upload")
    public BlogPost uploadOne(
        @RequestParam(name="text") MultipartFile textFile
    ) throws IOException {
        String text = new String(textFile.getBytes());
        BlogPost blogPost = new BlogPost(new Date(), text);
        blogPost = blogPostRepository.save(blogPost);
        return blogPost;
    }

    @PostMapping("/add")
    public BlogPost addOne(
        @RequestParam(name="text") String text
    ) {
        BlogPost blogPost = new BlogPost(new Date(), text);
        blogPost = blogPostRepository.save(blogPost);
        return blogPost;
    }

    @GetMapping("/{uuid}")
    public BlogPost getOne(@PathVariable(name="uuid") String uuid) throws BlogPostNotFoundException {
        BlogPost blogPost = blogPostRepository.findById(uuid)
            .orElseThrow(() -> new BlogPostNotFoundException("could not find post with id: " + uuid));
        return blogPost;
    }

    @GetMapping("/list")
    public List<BlogPost> getAll() {
        List<BlogPost> blogPosts = blogPostRepository.findAll();
        return blogPosts;
    }
    
    /**
     * update blog post by file uploading
     * @param uuid
     * @param textFile
     * @return
     * @throws Exception
     */
    @PostMapping("/{uuid}/update_upload")
    public BlogPost updateUploadOne(
        @PathVariable(name = "uuid") String uuid,
        @RequestParam(name="text") MultipartFile textFile
    ) throws IOException, BlogPostNotFoundException {
        String text = new String(textFile.getBytes());
        BlogPost blogPost = blogPostRepository.findById(uuid)
            .orElseThrow(() -> new BlogPostNotFoundException("could not find post with id: " + uuid));
        blogPost.setText(text);
        blogPost = blogPostRepository.save(blogPost);
        return blogPost;
    }

    @PostMapping("/{uuid}/update")
    public BlogPost updateOne(
        @PathVariable(name = "uuid") String uuid,
        @RequestParam(name="text") String text
    ) throws BlogPostNotFoundException {
        BlogPost blogPost = blogPostRepository.findById(uuid)
            .orElseThrow(() -> new BlogPostNotFoundException("could not find post with id: " + uuid));
        blogPost.setText(text);
        blogPost = blogPostRepository.save(blogPost);
        return blogPost;
    }

    @PostMapping("/{uuid}/delete")
    public String deleteOne(
        @PathVariable(name = "uuid") String uuid
    ) throws BlogPostNotFoundException {
        BlogPost blogPost = blogPostRepository.findById(uuid)
            .orElseThrow(() -> new BlogPostNotFoundException("could not find post with id: " + uuid));
        blogPostRepository.delete(blogPost);
        return "ok";
    }

}
