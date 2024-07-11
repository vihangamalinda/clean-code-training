package com.c.refactoring.lock;

import java.util.Date;
import java.util.List;

public class UserLoginChecker {

    public static final int ONE_HOUR = 3600000;

    /**
     * {@inheritDoc}.
     */
    public Lock isUserAllowedToLogin(long id, String status,
                                     boolean firstScreen, User user, List list) {
        Date time = new Date();
        Lock lck = new Lock();
//        lck.setRead(false);
        if (list.isEmpty() || list.get(0) == null) return lck;


        Object[] access = (Object[]) list.get(0);
        String userId = (String) access[0];
        Date lockTimestamp = (Date) access[1];

        if (userId == null) return lck;

//        if (userId != null) {
        // message which is shown to the user
        String lockMsg = getLockMsg(userId);

        //if userID is present, the Lock time stamp will also be present
        //4800000 milliseconds equals to 1 1/2 hours.
        if (time.getTime() - lockTimestamp.getTime() > ONE_HOUR) {
            //New user gets lock only on first screen
            //If 1 1/2 hours expires when user is not on 1st screen then for same user lock can be refreshed.
            if (firstScreen
                    || userId.equalsIgnoreCase(user.getUserId())) {
                //to set the  access to write mode
                lck.setRead(false);
                return lck;
            }
            lck.setRead(true);
            //Only read access is permitted to other user
            lck.setLockReason(lockMsg);
            return lck;
        } else if (userId.equalsIgnoreCase(user.getUserId())) {
            // Locked By Same User, Write access
            lck.setRead(false);
            return lck;
        } else {
            lck.setRead(true);
            //Only Read Access is Permitted
            lck.setLockReason(lockMsg);
            return lck;
        }
    }

    private static String getLockMsg(String userId) {
        return Constants.LOCK_TEXT.replaceAll("@@USER@@",
                userId);
    }
}