package ru.gurtovenko.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.gurtovenko.dao.UserDAO;
import ru.gurtovenko.model.User;

import java.util.List;

@Component
public class JdbcTemplateUserDAO implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getAll() {
        return jdbcTemplate.query("select * from users",
                new BeanPropertyRowMapper<>(User.class));
//        return jdbcTemplate.query("select * from users", (rs, rowNum) -> {
//            User user = new User();
//            user.setName(rs.getString(1));
//            user.setSurname(rs.getString(2));
//            user.setEmail(rs.getString(3));
//            return user;
//        });
    }

    public User getOne(String email) {
        try{
            return jdbcTemplate.queryForObject("select * from users where email = ?",
                    new Object[] { email },
                    new BeanPropertyRowMapper<>(User.class));
        }
        catch (Exception e){
            return null;
        }
    }

    public void add(User user) {
        jdbcTemplate.update("insert into users values(?,?,?)",
                user.getName(), user.getSurname(), user.getEmail());
    }
}
