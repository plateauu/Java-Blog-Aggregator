package com.plateauu.jba.service;

import com.plateauu.jba.entity.Blog;
import com.plateauu.jba.entity.Item;
import com.plateauu.jba.entity.User;
import com.plateauu.jba.repository.BlogRepository;
import com.plateauu.jba.repository.ItemRepository;
import com.plateauu.jba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ItemRepository itemRepository;

    public List<User> findAll() {
        return userRepository.findAll();

    }

    public User findOne(int id) {
        return userRepository.findOne(id);
    }

    @Transactional
    public User findOneWithBlogs(int id) {
        User user = findOne(id);
        List<Blog> blogs = blogRepository.findByUser(user);
        for (Blog blog : blogs) {
            List<Item> items = itemRepository.findByBlog(blog, new PageRequest(0, 10, Sort.Direction.DESC, "publishedDate"));
            blog.setItems(items);

        }
        user.setBlogs(blogs);
        return user;
    }
}
