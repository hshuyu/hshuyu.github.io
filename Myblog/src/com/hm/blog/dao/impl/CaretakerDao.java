package com.hm.blog.dao.impl;



import com.hm.blog.dao.ICaretakerDao;
import com.hm.blog.dao.util.SQLCommand;
import com.hm.blog.entity.Caretaker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 * 管理员增删改查
 */
public class CaretakerDao implements ICaretakerDao {
    /**
     * 按条件查找
     * @param caretaker
     * @return
     */
    @Override
    public Caretaker queryByInfo(Caretaker caretaker) {
        Caretaker caretaker_ = null;
        SQLCommand sqlCommand = new SQLCommand();
        String sql = "select caretaker1_code, caretaker1_name, caretaker1_id_card, caretaker1_tel, caretaker1_pwd, caretaker1_avatar, caretaker1_loginstatus from caretaker_ where caretaker1_code=?";
        ResultSet resultSet = sqlCommand.query(sql,caretaker.getCaretakerCode());
        try {
            if (resultSet.next()){
                caretaker_ = new Caretaker();
                caretaker_.setCaretakerCode(resultSet.getInt("caretaker1_code"));
                caretaker_.setCaretakerName(resultSet.getString("caretaker1_name"));
                caretaker_.setCaretakerId(resultSet.getString("caretaker1_id_card"));
                caretaker_.setCaretakerTel(resultSet.getString("caretaker1_tel"));
                caretaker_.setCaretakerPwd(resultSet.getString("caretaker1_pwd"));
                caretaker_.setCaretakerAvatar(resultSet.getString("caretaker1_avatar"));
                caretaker_.setCaretakerLoginStatus(resultSet.getInt("caretaker1_loginstatus"));
            }
            return caretaker_;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            sqlCommand.close();
        }
        return null;
    }

    /**
     * 查询所有
     * @param caretaker
     * @return
     */
    @Override
    public List<Caretaker> queryAll(Caretaker caretaker) {
        List<Caretaker> caretakers = new Vector<>();
        SQLCommand sqlCommand = new SQLCommand();
        String sql = "select caretaker1_code, caretaker1_name, caretaker1_id_card, caretaker1_tel, caretaker1_pwd, caretaker1_avatar, caretaker1_loginstatus from caretaker_";
        ResultSet resultSet = sqlCommand.query(sql);
        try {
            Caretaker caretaker_ = null;
            while (resultSet.next()){
                caretaker_ = new Caretaker();
                caretaker_.setCaretakerCode(resultSet.getInt("caretaker1_code"));
                caretaker_.setCaretakerName(resultSet.getString("caretaker1_name"));
                caretaker_.setCaretakerId(resultSet.getString("caretaker1_id_card"));
                caretaker_.setCaretakerTel(resultSet.getString("caretaker1_tel"));
                caretaker_.setCaretakerPwd(resultSet.getString("caretaker1_pwd"));
                caretaker_.setCaretakerAvatar(resultSet.getString("caretaker1_avatar"));
                caretaker_.setCaretakerLoginStatus(resultSet.getInt("caretaker1_loginstatus"));
                caretakers.add(caretaker_);
            }
            return caretakers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加
     * @param caretaker
     * @return
     */
    @Override
    public int insert(Caretaker caretaker) {
        String sql = "insert into caretaker_ (caretaker1_code, caretaker1_name,caretaker1_id_card , caretaker1_tel, caretaker1_pwd,caretaker1_avatar, caretaker1_loginstatus) values(BCARE.nextval,?,?,?,?,?)";
        SQLCommand sqlCommand = new SQLCommand();
        int res = sqlCommand.execute(sql,caretaker.getCaretakerName(),caretaker.getCaretakerId(),caretaker.getCaretakerTel(),caretaker.getCaretakerPwd(),caretaker.getCaretakerAvatar(),caretaker.getCaretakerLoginStatus());
        return res;
    }

    /**
     * 更新
     * @param caretaker
     * @return
     */
    @Override
    public int update(Caretaker caretaker) {
        String sql = "update caretaker_\n" +
                "   set caretaker1_name = ?,\n" +
                "       caretaker1_tel = ?,\n" +
                "       caretaker1_pwd = ?,\n" +
                "       caretaker1_avatar = ?\n" +
                " where caretaker1_code = ?";
        SQLCommand sqlCommand = new SQLCommand();
        int res = sqlCommand.execute(sql,caretaker.getCaretakerName(),caretaker.getCaretakerTel(),caretaker.getCaretakerPwd(),caretaker.getCaretakerAvatar(),caretaker.getCaretakerCode());
        return res;
    }

    /**
     * 删除
     * @param caretaker
     * @return
     */
    @Override
    public int delete(Caretaker caretaker) {
        String sql = "    delete caretaker_\n" +
                "    where caretaker1_code = ?";
        SQLCommand sqlCommand = new SQLCommand();
        int res = sqlCommand.execute(sql,caretaker.getCaretakerCode());
        return res;
    }

    @Override
    public Caretaker queryByInfos(Caretaker caretaker) {
        Caretaker caretaker_ = null;
        SQLCommand sqlCommand = new SQLCommand();
        String sql = "select caretaker1_code, caretaker1_name, caretaker1_id_card, caretaker1_tel, caretaker1_pwd, caretaker1_avatar, caretaker1_loginstatus from caretaker_ where caretaker1_name=?";
        ResultSet resultSet = sqlCommand.query(sql,caretaker.getCaretakerName());
        try {
            if (resultSet.next()){
                caretaker_ = new Caretaker();
                caretaker_.setCaretakerCode(resultSet.getInt("caretaker1_code"));
                caretaker_.setCaretakerName(resultSet.getString("caretaker1_name"));
                caretaker_.setCaretakerId(resultSet.getString("caretaker1_id_card"));
                caretaker_.setCaretakerTel(resultSet.getString("caretaker1_tel"));
                caretaker_.setCaretakerPwd(resultSet.getString("caretaker1_pwd"));
                caretaker_.setCaretakerAvatar(resultSet.getString("caretaker1_avatar"));
                caretaker_.setCaretakerLoginStatus(resultSet.getInt("caretaker1_loginstatus"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            sqlCommand.close();
        }

        return caretaker_;
    }

    @Override
    public int update1(Caretaker caretaker) {
        String sql = "update caretaker_\n" +
                "   set caretaker1_name = ?,\n" +
                "       caretaker1_tel = ?,\n" +
                "       caretaker1_pwd = ?\n" +
                " where caretaker1_code = ?";
        SQLCommand sqlCommand = new SQLCommand();
        int res = sqlCommand.execute(sql,caretaker.getCaretakerName(),caretaker.getCaretakerTel(),caretaker.getCaretakerPwd(),caretaker.getCaretakerCode());
        return res;
    }

    //12
    @Override
    public Caretaker upStatus(Caretaker caretaker1) {
        String sql = "update caretaker_\n" +
                "   set caretaker1_loginstatus = 1\n" +
                " where caretaker1_code = ?";
        SQLCommand sqlCommand = new SQLCommand();
        sqlCommand.execute(sql,caretaker1.getCaretakerCode());
        return caretaker1;
    }
    @Override
    public Caretaker downStatus(Caretaker caretaker1) {
        String sql = "update caretaker_\n" +
                "   set caretaker1_loginstatus = 0\n" +
                " where caretaker1_code = ?";
        SQLCommand sqlCommand = new SQLCommand();
        sqlCommand.execute(sql,caretaker1.getCaretakerCode());
        return caretaker1;
    }
}
