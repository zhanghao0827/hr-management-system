package per.hr.resource.manage.sys.service.impl;

import per.hr.resource.manage.sys.bean.User;
import per.hr.resource.manage.sys.dao.UserDao;
import per.hr.resource.manage.sys.dao.impl.UserDaoImpl;
import per.hr.resource.manage.sys.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    @Override
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    public List<User> findUsers(User user) {
        return userDao.findUsers(user);
    }

    @Override
    public boolean checkLoginName(String loginName) {
        return userDao.checkLoginName(loginName);
    }

    @Override
    public boolean checkUserState(int id, int state) {
        return userDao.checkUserState(id, state);
    }

    @Override
    public boolean deleteUser(int id) {
        return userDao.deleteUser(id);
    }

    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public boolean updateUserById(User user) {
        return userDao.updateUserById(user);
    }
}
