package ma.fukuro192.yabai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.fukuro192.yabai.entity.BlogPost;

public interface BlogPostRepository extends JpaRepository<BlogPost, String> {
    
}
