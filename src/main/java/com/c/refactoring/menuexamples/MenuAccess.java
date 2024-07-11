package com.c.refactoring.menuexamples;

import java.util.Arrays;
import java.util.List;

public class MenuAccess {

    public void setAuthorizationsInEachMenus(List<MenuItem> menuItemsList, Role[] roles) {
        if (isRoleNull(roles)) return;
        menuItemsList.forEach(menuItem -> setAccessForMenuItems(menuItem, roles));
    }

    private void setAccessForMenuItems(MenuItem menuItem, Role[] roles) {
        if (hasAccess(roles, menuItem.getReadAccessRole())) {
            setMenuItemPermission(menuItem, Constants.READ);
        }
        if (hasAccess(roles, menuItem.getWriteAccessRole())) {
            setMenuItemPermission(menuItem, Constants.WRITE);
        }
    }

    private static boolean hasAccess(Role[] roles, String access) {
        return Arrays.stream(roles).anyMatch(role -> role.getName().equals(access));
    }

    private void setMenuItemPermission(MenuItem menuItem, String permission) {
        menuItem.setAccess(permission);
        menuItem.setVisible(true);
    }

    private boolean isRoleNull(Role[] roles) {
        return roles == null;
    }

}
