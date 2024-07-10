package com.c.refactoring.menuexamples;

import java.util.List;

public class MenuAccess {

    public void setAuthorizationsInEachMenus(
            List<MenuItem> menuItemsList, Role[] roles) {

        for (int i = 0; i < menuItemsList.size(); i++) {
            MenuItem menuItem = menuItemsList.get(i);
            if (!isRoleNull(roles)) {
                for (int j = 0; j < roles.length; j++) {
                    String roleType = roles[j].getName();
                    if (haveReadAccess(roleType, menuItem.getReadAccessRole()) && !Constants.WRITE.equals(menuItem.getAccess())) {
                        setMenuItemPermission(menuItem, Constants.READ);
                    } else {
                        if (haveWriteAccess(roleType, menuItem.getWriteAccessRole())) {
                            setMenuItemPermission(menuItem, Constants.WRITE);
                        }
                    }
                }

            }

        }

    }

    private void setMenuItemPermission(MenuItem menuItem, String permission) {
        menuItem.setAccess(permission);
        menuItem.setVisible(true);
    }

    private boolean haveWriteAccess(String roleType, String writeAccessRole) {
        return roleType.equals(writeAccessRole);
    }

    private boolean haveReadAccess(String roleType, String readAccessRole) {
        return roleType.equals(readAccessRole);
    }

    private boolean isRoleNull(Role[] roles) {
        return roles == null;
    }

}
