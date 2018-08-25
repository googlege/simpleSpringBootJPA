package de.homedev.springboot.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.homedev.springboot.jpa.dao.UserDao;
import de.homedev.springboot.jpa.entity.UserEntity;

@Service(IUserService.SERVICE_NAME)
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class UserServiceImpl implements IUserService {
	// private static Logger log =
	// LoggerFactory.getLogger(UserServiceImpl.class);

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
