package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.bean.User;
import web.dao.UserDao;

/**
 * Created by AaahhhXin on 2016/6/15.
 */
@Service
public class UserService {
    private UserDao userDao;
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    public boolean register(User user){
        if(userDao.selectuserbyusername(user)==null){
            userDao.insert(user);
            return true;
        }else{
            return false;
        }
    }

    public int login(User user){
        User  userindb = userDao.selectuserbyusername(user);
        if(userindb==null){
            return -1;//无用户
        }else if(!userindb.getPassword().equals(user.getPassword())){
            return -2;//密码错误
        }else{
            return userindb.getUserid();
        }
    }
    public int changepassword(User user,String newpassword){
        User  userindb = userDao.selectUserByUserID(user);
        if(userindb==null){
            return -1;//无用户
        }else if(!userindb.getUsername().equals(user.getPassword())){//username老密码，password新密码
            return -2;//密码错误
        }else{
            user.setPassword(newpassword);
            userDao.update(user);
            return user.getUserid();
        }
    }

}
