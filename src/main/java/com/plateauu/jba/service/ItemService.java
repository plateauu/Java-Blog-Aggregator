package com.plateauu.jba.service;


import com.plateauu.jba.entity.Item;
import com.plateauu.jba.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item>  findFirstTen(){
         return itemRepository.findAll(new PageRequest(0, 10, Sort.Direction.DESC, "publishedDate"))
                 .getContent();

    }
}
