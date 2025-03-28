package com.example.backend.repository;

import com.example.backend.entity.PostTag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostTagRepository extends CrudRepository<PostTag, Long> {
}
