package per.hr.resource.manage.sys.service;

import per.hr.resource.manage.sys.bean.User;

import java.util.List;

public interface UserService {
    User login(User user);

    List<User> findUsers(User user);

    boolean checkLoginName(String loginName);

    boolean checkUserState(int id, int state);

    boolean deleteUser(int id);

    boolean addUser(User user);

    User getUserById(int id);

    boolean updateUserById(User user);
}
