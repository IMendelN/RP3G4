package store.utils;

import javax.servlet.http.HttpSession;

import store.models.User;

public class Attribute {
    public static void setUserAttributes(HttpSession session, User user) {
        session.setAttribute("role", user.getRole().VALUE);
        session.setAttribute("auth", true);
        session.setAttribute("userId", user.getId());
    }
}
