package com.plateauu.jba.service;

import com.plateauu.jba.entity.Blog;
import com.plateauu.jba.entity.User;
import com.plateauu.jba.repository.BlogRepository;
import com.plateauu.jba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("#blog.user.name == authentication.name or hasRole('ADMIN')")
    public void delete(@P("blog") Blog blog) {
        blogRepository.delete(blog);
    }

    public Blog findOne(int id) {
        return blogRepository.findOne(id);
    }
}
