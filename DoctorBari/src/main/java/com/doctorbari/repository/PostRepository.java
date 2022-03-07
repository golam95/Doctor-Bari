package com.doctorbari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.doctorbari.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
