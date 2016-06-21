package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.bean.Tels;
import web.bean.User;
import web.dao.TelsDao;

import java.util.List;

/**
 * Created by AaahhhXin on 2016/6/17.
 */
@Service
public class TelsService {
    private TelsDao telsDao;
    @Autowired
    public void setTelsDao(TelsDao telsDao) {
        this.telsDao = telsDao;
    }
    public boolean addTels(Tels tels){
        Tels dbtels = telsDao.getTelsByNameAndNumber(tels);
        if(dbtels!=null){
            return false;
        }else {
            telsDao.insert(tels);
            return true;
        }
    }
    public List<Tels> showAllTels(User user){
        return  telsDao.getTelsByCreaterId(user);
    }
    public boolean deleteTels(Tels tels){
        Tels dbtels = telsDao.getTelsByTelsId(tels);
        if(dbtels==null){
            return false;
        }else {
            telsDao.delete(tels);
            return true;
        }
    }
    public boolean changeTels(Tels tels){
        Tels dbtels = telsDao.getTelsByNameAndNumber(tels);

        if(dbtels!=null){
            return false;
        }else {
            telsDao.update(tels);
            return true;
        }
    }
    public List<Tels> search(String keywords){return telsDao.getTelsByKeyWords(keywords);}
}
