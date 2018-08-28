package de.homedev.springboot.jpav4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.homedev.springboot.jpav4.config.MyClass;

@Service(IUserService.SERVICE_NAME)
public class UserServiceImpl implements IUserService {
	// private static Logger log =
	// LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private MyClass myClass;

	@Override
	public void printMessage() {
		System.err.println(myClass.getCalledFrom());

	}

}
