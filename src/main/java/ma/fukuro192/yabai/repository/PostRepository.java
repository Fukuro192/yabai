package ma.fukuro192.yabai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.fukuro192.yabai.entity.Post;

public interface PostRepository extends JpaRepository<Post, String> {
    
}
