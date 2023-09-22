package com.thrillio;

import com.thrillio.bgJobs.WebPageDownloaderTask;
import com.thrillio.dataStore.DataStore;
import com.thrillio.entities.BookMark;
import com.thrillio.entities.User;
import com.thrillio.services.BookMarkServices;
import com.thrillio.services.UserService;

import java.sql.SQLException;
import java.util.List;

public class Launch {
    private  static List<User> users;
    private static List<List<BookMark>> bookMarks;

    public static void loadData(){
        System.out.println("1. Loading Data");
        DataStore.loadData();

        users = UserService.getInstance().getUsers();
        bookMarks= BookMarkServices.getInstance().getBookMarks();


    }



    private static void printBookMarkData() {
        for(List<BookMark> bookMarkList: bookMarks){
            for(BookMark bookMark:bookMarkList){
                System.out.println("BookMark : "+bookMark.toString());
            }
        }
    }

    private static void printUserData() {
        for(User user:users){
            System.out.println("user : "+user.toString() );
        }
    }

    private static void start() throws SQLException {
        System.out.println("2. Book Marking");

        for(User user:users){
            View.browse(user, bookMarks);
        }
    }

    private static void runDownloaderJob() {
        WebPageDownloaderTask task = new WebPageDownloaderTask(true);
        (new Thread(task)).start();
    }

    public static void main(String[] args) throws SQLException {
loadData();
       start();
//        printUserData();
//        printBookMarkData()
//
        runDownloaderJob();
    }


}
