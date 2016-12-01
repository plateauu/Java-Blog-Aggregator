package com.plateauu.jba.repository;

import com.plateauu.jba.entity.Blog;
import com.plateauu.jba.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findByBlog(Blog blog);
    
}
