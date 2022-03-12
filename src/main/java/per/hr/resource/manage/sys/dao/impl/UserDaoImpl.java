package per.hr.resource.manage.sys.dao.impl;

import per.hr.resource.manage.sys.bean.User;
import per.hr.resource.manage.sys.dao.UserDao;
import per.hr.resource.manage.sys.utils.JDBCUtils;

import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public User login(User user) {
        return JDBCUtils.queryOne("select * from t_user where userLoginName=? and userPwd=? and delState=0", User.class, user.getUserLoginName(), user.getUserPwd());
    }

    @Override
    public List<User> findUsers(User user) {
        String userName = user.getUserName();
        Integer state = user.getState();
        StringBuffer sb = new StringBuffer("select * from t_user where delState=0");
        if (userName != null && !"".equals(userName)) {
            //查询userName ，注意拼接空格，单引号
            sb.append(" and userName like '%" + userName + "%'");
//            sb.append(" and userName like ?");
        }
        if (user.getState() != -1) {
            //条件查询
            sb.append(" and state=" + state);
        }
        String sql = sb.toString();
        //条件不同，不能确定占位符个数
        return JDBCUtils.queryList(sql, User.class);
    }

    @Override
    public boolean checkLoginName(String loginName) {
        List<User> users = JDBCUtils.queryList("select * from t_user where userLoginName=?", User.class, loginName);
        if (users.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkUserState(int id, int state) {
        return JDBCUtils.update("update t_user set state=? where userId=?", state, id);
    }

    @Override
    public boolean deleteUser(int id) {
        return JDBCUtils.update("update t_user set delState=1 where userId=?", id);
    }

    @Override
    public boolean addUser(User user) {
        return JDBCUtils.dml("insert into t_user values (null, ?, ?, ?, ?, now(), 0)",
                user.getUserLoginName(), user.getUserPwd(), user.getUserName(), user.getState());
    }

    @Override
    public User getUserById(int id) {
        return JDBCUtils.queryOne("select * from t_user where userId=?", User.class, id);
    }

    @Override
    public boolean updateUserById(User user) {
        return JDBCUtils.update("update t_user set userLoginName=?,userPwd=?,userName=?,state=? where userId=?", user.getUserLoginName(), user.getUserPwd(), user.getUserName(), user.getState(),user.getUserId());
    }


}
