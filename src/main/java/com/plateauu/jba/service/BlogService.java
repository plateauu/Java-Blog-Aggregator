package com.plateauu.jba.service;

import com.plateauu.jba.entity.Blog;
import com.plateauu.jba.entity.Item;
import com.plateauu.jba.entity.User;
import com.plateauu.jba.exception.RssException;
import com.plateauu.jba.repository.BlogRepository;
import com.plateauu.jba.repository.ItemRepository;
import com.plateauu.jba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RssService rssService;

    @Autowired
    private ItemRepository itemRepository;

    public void saveItems(Blog blog) {
        try {
            List<Item> items = rssService.getItems(blog.getUrl());
            for (Item item : items) {
                Optional<Item> previousItems = Optional.ofNullable(itemRepository.findByBlogAndLink(blog, blog.getUrl()));
                if (!previousItems.isPresent()) {
                    item.setBlog(blog);
                    itemRepository.save(item);
                }
            }

        } catch (RssException e) {
            e.printStackTrace();
        }
    }

    public void save(Blog blog, String name) {
        User user = userRepository.findByName(name);
        blog.setUser(user);
        blogRepository.save(blog);
        saveItems(blog);

    }

    @PreAuthorize("#blog.user.name == authentication.name or hasRole('ADMIN')")
    public void delete(@P("blog") Blog blog) {
        blogRepository.delete(blog);
    }

    public Blog findOne(int id) {
        return blogRepository.findOne(id);
    }
}
