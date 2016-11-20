package com.plateauu.jba.repository;

import com.plateauu.jba.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ItemRepository extends JpaRepository<Item, Integer> {
    
}
