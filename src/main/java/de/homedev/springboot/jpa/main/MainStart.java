
package de.homedev.springboot.jpa.main;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import de.homedev.springboot.jpa.config.DbConfig;
import de.homedev.springboot.jpa.entity.UserEntity;
import de.homedev.springboot.jpa.entity.UserInfoEntity;
import de.homedev.springboot.jpa.entity.UserRightEntity;
import de.homedev.springboot.jpa.service.IUserService;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
@SpringBootApplication
@Import({ DbConfig.class })
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = false)
public class MainStart {
    private static final Logger log = LoggerFactory.getLogger(MainStart.class);

    public static void main(String[] args) {
        try {
            ConfigurableApplicationContext ctx = SpringApplication.run(MainStart.class, args);
            String username = "musterman";
            String password = "admin";
            IUserService fassade = (IUserService) ctx.getBean(IUserService.SERVICE_NAME);
            UserEntity entity = new UserEntity();
            entity.setPassword(password);
            entity.setUsername(username);
            UserInfoEntity info = new UserInfoEntity();
            info.setName("Musterman");
            info.setVorname("Robert");
            info.setEmail("Robert.Musterman@google.com");
            info.setUserEntity(entity);
            entity.setUserInfo(info);
            List<UserRightEntity> userRightsList = new ArrayList<UserRightEntity>(10);
            UserRightEntity right = new UserRightEntity();
            right.setUserRight(1);
            right.setUserEntity(entity);
            userRightsList.add(right);
            right = new UserRightEntity();
            right.setUserRight(2);
            right.setUserEntity(entity);
            userRightsList.add(right);
            entity.setUserRightsList(userRightsList);
            List<UserEntity> list = new ArrayList<UserEntity>(1);
            list.add(entity);
            fassade.save(list);
            entity = fassade.findByUsernameAndPassword(username, password);

            log.info("entity username:" + entity.getUsername() + "; entity password:" + entity.getPassword());
            // READING UserInfo and List<UserRightEntity> CAUSE LAZY LOADING
            // EXCEPTION ON THIS PLACE
            // POSIBLE SOLUTIONS
            // 1.Sent JPA Graph as parameter in function
            // findByUsernameAndPassword
            // 2.Reading data before entity "UserEntity" was detached
            // 3.Reading UserInfo and List<UserRightEntity> with other service
            // functions
            // !!First solution is the best!!

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
