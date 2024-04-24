package com.openclassrooms.mddapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {

	List<Post> findByTopic(Topic topic);

}
