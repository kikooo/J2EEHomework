package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Repository;
import web.bean.Tels;
import web.bean.User;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by AaahhhXin on 2016/6/17.
 */
@Repository
public class TelsDao {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Tels> getTelsByCreaterId(User user) {
        String sql = "select * from tels where creatorid = :userid";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
        List<Tels> telsList = namedParameterJdbcTemplate.query(sql, sqlParameterSource, new BeanPropertyRowMapper<>(Tels.class));
        return telsList;
    }
    public Tels getTelsByNameAndNumber(Tels tels){
        String sql ="select * from tels where telsname= :telsname and telnumber= :telnumber and creatorid = :creatorid";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(tels);
        List<Tels> telsList = namedParameterJdbcTemplate.query(sql,sqlParameterSource,new BeanPropertyRowMapper<>(Tels.class));
        if(telsList.isEmpty()){
            return null;
        }else {
            return telsList.get(0);
        }
    }
    public  List<Tels> getTelsByKeyWords(String keywords){
        String sql ="select * from tels where telsname like :keywords or telnumber like :keywords";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("keywords","%"+keywords+"%");
        List<Tels> telsList = namedParameterJdbcTemplate.query(sql,sqlParameterSource,new BeanPropertyRowMapper<>(Tels.class));
        return telsList;
    }
    public Tels getTelsByTelsId(Tels tels) {
        String sql = "select * from tels where telsid = :telsid";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(tels);
        List<Tels> telsList = namedParameterJdbcTemplate.query(sql, sqlParameterSource, new BeanPropertyRowMapper<>(Tels.class));
        if(telsList.isEmpty()){
            return null;
        }else {
            return telsList.get(0);
        }
    }

    public void delete(Tels tels) {
        String sql = "delete from tels where telsid = :telsid";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(tels);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    public void insert(Tels tels) {
        String sql = "insert into tels(telsname,telnumber,creatorid) values(:telsname,:telnumber,:creatorid)";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(tels);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    public void update(Tels tels) {
        String sql = "UPDATE tels SET telsname=:telsname,telnumber=:telnumber WHERE telsid=:telsid";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(tels);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

}
