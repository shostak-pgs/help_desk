package help_desk_app.service;

import help_desk_app.dao.UserDao;
import help_desk_app.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUserById(Long id) {
        return userDao.getOne(id);
    }
}
