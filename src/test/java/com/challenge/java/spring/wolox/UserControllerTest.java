package com.challenge.java.spring.wolox;

import com.challenge.java.spring.wolox.controller.UserController;
import com.challenge.java.spring.wolox.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class UserControllerTest {

    @Test
    public void test001UsercontrollerFindUserById1AndReturnedIt() {
        UserController userController = new UserController();
        User user = userController.getUser(1).getBody();

        Assert.assertNotNull(user);
        Assert.assertEquals(user.getId(), new Integer(1));
    }

    @Test
    public void test001UserControllerFindAllUsers(){
        UserController userController = new UserController();
        List<User> users = userController.getUsers().getBody();

        Assert.assertTrue(users.size() > 0);

    }
}
