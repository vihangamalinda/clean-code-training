package com.c.refactoring.lock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class UserLoginCheckerTest {
    UserLoginChecker userLoginChecker = new UserLoginChecker();

    @Test
    public void testisUserAllowedToLogin_DifferentUserTriesImmediatelyAfter() {
        Object[] access = getAccess("TEST_USER_ID_1", new Date());
        User user = new User("TEST_USER_ID_2");
        Lock lock = userLoginChecker.isUserAllowedToLogin(10, "NOT_USED", true, user, Arrays.asList(new Object[][]{access}));
        assertTrue(lock.isReadAccess());
        assertNotNull(lock.getLockReason());
    }

    @Test
    public void testisUserAllowedToLogin_SameUserReturnsToFirstScreen() {
        Object[] access = getAccess("TEST_USER_ID", new Date());
        User user = new User("TEST_USER_ID");
        Lock lock = userLoginChecker.isUserAllowedToLogin(10, "NOT_USED", true, user, Arrays.asList(new Object[][]{access}));
        assertFalse(lock.isReadAccess());
        assertNull(lock.getLockReason());
    }

    @Test
    public void testisUserAllowedToLogin_SameUserReturnsToSecondScreen() {
        Object[] access = getAccess("TEST_USER_ID", new Date());
        User user = new User("TEST_USER_ID");
        Lock lock = userLoginChecker.isUserAllowedToLogin(10, "NOT_USED", false, user, Arrays.asList(new Object[][]{access}));
        assertFalse(lock.isReadAccess());
        assertNull(lock.getLockReason());
    }

    @Test
    public void testisUserAllowedToLogin_User2TriesToLoginToFirstScreen3hoursAfterUser1() {
        Object[] access = getAccess("TEST_USER_ID_1", threeHoursBefore());
        User user = new User("TEST_USER_ID_2");
        Lock lock = userLoginChecker.isUserAllowedToLogin(10, "NOT_USED", true, user, Arrays.asList(new Object[][]{access}));
        assertFalse(lock.isReadAccess());
        assertNull(lock.getLockReason());
    }

    @Test
    public void testisUserAllowedToLogin_User2TriesToLoginToSecondScreen3hoursAfterUser1() {
        Object[] access = getAccess("TEST_USER_ID_1", threeHoursBefore());
        User user = new User("TEST_USER_ID_2");
        Lock lock = userLoginChecker.isUserAllowedToLogin(10, "NOT_USED", false, user, Arrays.asList(new Object[][]{access}));
        assertTrue(lock.isReadAccess());
        assertNotNull(lock.getLockReason());
    }

    private static Object[] getAccess(String userId, Date dateTime) {
        return new Object[]{userId, dateTime};
    }

    public Date threeHoursBefore() {
        Date now = new Date();
        return new Date(now.getTime() - 3 * 60 * 60 * 1000);
    }

}
