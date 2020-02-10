package app.service.impl;

import app.dao.impl.UserDaoImpl;
import app.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserDaoImpl daoMock;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testGetUser() {
        //Given
        User expected = new User();
        expected.setId(1L);
        expected.setUserName("Archi");
        expected.setPassword("112");
        when(daoMock.getUserByName("Archi")).thenReturn(Optional.of(expected));
        //When
        User actual = userService.getUser("Archi");
        //Then
        assertEquals(expected, actual);
    }

    @Test(expected = org.hibernate.service.spi.ServiceException.class)
    public void testGetUserUnsuccessful() {
        //Given
        when(daoMock.getUserByName("David")).thenReturn(Optional.empty());
        //When
        User actual = userService.getUser("David");
    }

    @Test
    public void testCreate() {
        //Given
        User expected = new User();
        expected.setId(3L);
        expected.setUserName("David");
        expected.setPassword("111");
        when(daoMock.getUserByName("David")).thenReturn(Optional.of(expected));
        //When
        User actual = userService.getUser("David");
        //Then
        assertEquals(expected, actual);
    }

    @Test(expected = org.hibernate.service.spi.ServiceException.class)
    public void testUnsuccessfulCreate() {
        //Given
        when(daoMock.getUserByName("Dav")).thenReturn(Optional.empty());
        //When
        User actual = userService.getUser("Dav");
    }
}