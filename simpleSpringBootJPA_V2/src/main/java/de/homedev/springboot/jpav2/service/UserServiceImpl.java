package de.homedev.springboot.jpav2.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.homedev.springboot.jpav2.dao.UserDao;
import de.homedev.springboot.jpav2.entity.UserEntity;

@Service(IUserService.SERVICE_NAME)
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class UserServiceImpl implements IUserService {
    private static Logger log = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private UserDao userDao;

    @Override
    public List<UserEntity> save(List<UserEntity> list) {
        return userDao.saveAll(list);
    }

    @Override
    public UserEntity findByUsernameAndPassword(String username, String userPassword) {
        List<UserEntity> list = userDao.findByUsernameAndPassword(username, userPassword);
        if (!list.isEmpty()) {
            UserEntity r = list.get(0);
            return r;
        }
        return null;
    }

    @Override
    public void deleteAll() {
        userDao.deleteAll();
    }

}
