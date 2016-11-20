package com.plateauu.jba.repository;

import com.plateauu.jba.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BlogRepository extends JpaRepository<Blog, Integer> {
    
}
