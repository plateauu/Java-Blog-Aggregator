package com.plateauu.jba.repository;

import com.plateauu.jba.entity.Blog;
import com.plateauu.jba.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BlogRepository extends JpaRepository<Blog, Integer> {

    List<Blog> findByUser(User user);

}
