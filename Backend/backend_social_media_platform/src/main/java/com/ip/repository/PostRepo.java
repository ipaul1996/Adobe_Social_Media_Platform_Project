package com.ip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ip.model.Post;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer>{

}
