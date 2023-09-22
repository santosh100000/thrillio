package com.thrillio.controllers;

import com.thrillio.constants.KidFriendlyStatus;
import com.thrillio.entities.BookMark;
import com.thrillio.entities.User;
import com.thrillio.services.BookMarkServices;

import java.sql.SQLException;

public class BookMarkController {

    private BookMarkController(){ }

    private static BookMarkController instance = new BookMarkController();

    public static BookMarkController getInstance(){
        return instance;
    }

    public void saveUserBookmark(User user, BookMark bookMark) {
        BookMarkServices.getInstance().saveUserBookmark(user, bookMark);
    }

    public void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyStatus, BookMark bookMark) throws SQLException {
       BookMarkServices.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookMark);
    }

    public void share(User user, BookMark bookMark) throws SQLException {
        BookMarkServices.getInstance().share(user, bookMark);
    }
}
