package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import web.bean.User;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by AaahhhXin on 2016/6/13.
 */
@Repository
public class UserDao {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void insert(User user) {
        String sql = "insert into user(username,password) values(:username,:password)";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    public User selectuserbyusername(User user) {
        String sql = "select * from user where username = :username";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
        List<User> userList = namedParameterJdbcTemplate.query(sql, sqlParameterSource, new BeanPropertyRowMapper<>(User.class));
        if (userList.size() == 0) {
            return null;
        } else {
            return userList.get(0);
        }
    }
    public User selectUserByUserID(User user){
        String sql = "select * from user where userid = :userid";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
        List<User> userList = namedParameterJdbcTemplate.query(sql,sqlParameterSource,new BeanPropertyRowMapper<>(User.class));
        if(userList.size() == 0){
            return null;
        }else {
            return userList.get(0);
        }
    }
    public void update(User user) {
        String sql = "UPDATE USER SET password=:password WHERE userid=:userid";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }
}
