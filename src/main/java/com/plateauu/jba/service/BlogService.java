package com.plateauu.jba.service;

import com.plateauu.jba.entity.Blog;
import com.plateauu.jba.entity.User;
import com.plateauu.jba.repository.BlogRepository;
import com.plateauu.jba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    public void save(Blog blog){

        blogRepository.save(blog);

    }

    public void save(Blog blog, String name) {
        User user = userRepository.findByName(name);
        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void delete(int id) {
        blogRepository.delete(id);
    }
}
