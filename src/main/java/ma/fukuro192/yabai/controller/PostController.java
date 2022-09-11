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

import ma.fukuro192.yabai.entity.Post;
import ma.fukuro192.yabai.repository.PostRepository;

@RestController
@RequestMapping("/post")
public class PostController {
    
    @PersistenceContext
    private EntityManager entityManager;
    private PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @PostMapping("/upload")
    public Post uploadOne(
        @RequestParam(name="text") MultipartFile textFile
    ) throws IOException {
        String text = new String(textFile.getBytes());
        Post post = new Post(new Date(), text);
        post = postRepository.save(post);
        return post;
    }

    @PostMapping("/add")
    public Post addOne(
        @RequestParam(name="text") String text
    ) {
        Post post = new Post(new Date(), text);
        post = postRepository.save(post);
        return post;
    }

    @GetMapping("/{uuid}")
    public Post getOne(@PathVariable(name="uuid") String uuid) throws Exception {
        Post post = postRepository.findById(uuid).orElseThrow(() -> new Exception("could not find post with id: " + uuid));
        return post;
    }

    @GetMapping("/list")
    public List<Post> getAll() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }
    
    /**
     * update post by file uploading
     * @param uuid
     * @param textFile
     * @return
     * @throws Exception
     */
    @PostMapping("/{uuid}/update_upload")
    public Post updateUploadOne(
        @PathVariable(name = "uuid") String uuid,
        @RequestParam(name="text") MultipartFile textFile
    ) throws Exception {
        String text = new String(textFile.getBytes());
        Post post = postRepository.findById(uuid).orElseThrow(() -> new Exception("could not find post with id: " + uuid));
        post.setText(text);
        post = postRepository.save(post);
        return post;
    }

    @PostMapping("/{uuid}/update_upload")
    public Post updateOne(
        @PathVariable(name = "uuid") String uuid,
        @RequestParam(name="text") String text
    ) throws Exception {
        Post post = postRepository.findById(uuid).orElseThrow(() -> new Exception("could not find post with id: " + uuid));
        post.setText(text);
        post = postRepository.save(post);
        return post;
    }

    @PostMapping("/{uuid}/delete")
    public String deleteOne(
        @PathVariable(name = "uuid") String uuid
    ) throws Exception {
        Post post = postRepository.findById(uuid).orElseThrow(() -> new Exception("could not find post with id: " + uuid));
        postRepository.delete(post);
        return "ok";
    }

}
