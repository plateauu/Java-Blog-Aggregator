package com.plateauu.jba.service;

import com.plateauu.jba.entity.Blog;
import com.plateauu.jba.entity.Role;
import com.plateauu.jba.entity.User;
import com.plateauu.jba.repository.BlogRepository;
import com.plateauu.jba.repository.ItemRepository;
import com.plateauu.jba.repository.RoleRepository;
import com.plateauu.jba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class InitDBService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostConstruct
    public void init(){

        if (roleRepository.findByName("ROLE_ADMIN") == null) {

            Role roleUser = new Role();
            roleUser.setName("ROLE_USER");
            roleRepository.save(roleUser);

            Role roleAdmin = new Role();
            roleAdmin.setName("ROLE_ADMIN");
            roleRepository.save(roleAdmin);

            User userAdmin = new User();
            userAdmin.setName("admin");
            userAdmin.setPassword(encoder.encode("admin"));
            userAdmin.setEnabled(true);
            List<Role> roles = new ArrayList<>();
            roles.add(roleAdmin);
            roles.add(roleUser);
            userAdmin.setRoles(roles);
            userRepository.save(userAdmin);

            Blog blogJavaVids = new Blog();
            blogJavaVids.setName("JavaVids");
            blogJavaVids.setUrl("http://feeds.feedburner.com/javavids?format=xml");
            blogJavaVids.setUser(userAdmin);
            blogRepository.save(blogJavaVids);

//        Item item1 = new Item();
//        item1.setBlog(blogJavaVids);
//        item1.setTitle("first");
//        item1.setLink("http://www.javavids.com");
//        item1.setPublishedDate(new Date());
//        itemRepository.save(item1);
//
//        Item item2 = new Item();
//        item2.setBlog(blogJavaVids);
//        item2.setTitle("second");
//        item2.setLink("http://www.javavids.com");
//        item2.setPublishedDate(new Date());
//        itemRepository.save(item2);

        }
    }

}
