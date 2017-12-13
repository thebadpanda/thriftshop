package com.shop.thrift.Services.impl;

import com.shop.thrift.Entity.Basket;
import com.shop.thrift.Entity.Item;
import com.shop.thrift.Entity.Role;
import com.shop.thrift.Entity.Users;
import com.shop.thrift.Repository.BasketRepository;
import com.shop.thrift.Repository.ItemRepository;
import com.shop.thrift.Repository.UsersRepository;
import com.shop.thrift.Services.UsersService;
//import com.shop.thrift.Specification.UsersSpecification;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.shop.thrift.dto.Filter.BasicFilter;
import com.shop.thrift.dto.Filter.ItemFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UsersServiceImpl implements UserDetailsService, UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BasketRepository basketRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return usersRepository.findByUsername(username);
    }

    @Override
    public void save(Users users) {
        users.setPassword(encoder.encode(users.getPassword()));
        users.setRole(Role.ROLE_USER);
        usersRepository.save(users);
    }

    @PostConstruct
    public void admin() {
        Users users = usersRepository.findByUsername("admin");
        if (users == null) {
            users = new Users();
            users.setEmail("");
            users.setPassword(encoder.encode("admin"));
            users.setRole(Role.ROLE_ADMIN);
            users.setUsername("admin");
            usersRepository.save(users);
        }
    }

    @Override
    public void sendMail(String content, String email, String mailBody) {
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getDefaultInstance(properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                "arsen4yk@gmail.com", "barsik1488");
                    }
                });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("arsen4yk@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                    email));
            message.setSubject(content, "UTF-8");
            message.setText(mailBody);
            Transport.send(message);
        } catch (MessagingException е) {
            е.printStackTrace();
        }
    }


    @Override
    public int createNewUser() {
        return usersRepository.saveAndFlush(new Users()).getId();
    }

    @Override
    public Users findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void addToBasket(int usersId, int itemId) {
        Users users = usersRepository.findOne(usersId);
        Basket basket = users.getBasket();
        if(basket==null){
            basket = basketRepository.save(new Basket());
            users.setBasket(basket);
        }
        Item item = itemRepository.findOne(itemId);
        basket.add(item);
    }

    @Override
    @Transactional
    public void removeToBasket(int usersId, int itemId) {
        Users users = usersRepository.findOne(usersId);
        Basket basket = users.getBasket();
        Item item = itemRepository.findOne(itemId);
        basket.remove(item);
    }

    @Override
    @Transactional
    public void removeAllToBasket(int usersId) {
        Users users = usersRepository.findOne(usersId);
        Basket basket = users.getBasket();
        List<Item> item = itemRepository.findAll();
        basket.removeAll(item);
    }

    @Override
    public List<Users> findAll(BasicFilter filter, Pageable pageable) {
        return usersRepository.findAll();
    }

    @Override
    public void delete(int id) {
        usersRepository.delete(id);
    }

    @Override
    public Users findOne(int id){
        return usersRepository.findOne(id);
    }

    @Override
    public Users findOne(String username){
        return usersRepository.findByUsername(username);
    }

//    @Override
//    public Page<Users> findAll(BasicFilter filter, Pageable pageable){
//        return usersRepository.findAll(new UsersService(filter), pageable);
//    }
//
//    @Override
//    public void update(Users users){
//        usersRepository.update(users);
//    }


}

