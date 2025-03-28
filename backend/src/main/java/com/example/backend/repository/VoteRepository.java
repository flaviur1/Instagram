package com.example.backend.repository;


import com.example.backend.entity.Vote;
import com.example.backend.entity.VoteId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends CrudRepository<Vote, VoteId> {
}
