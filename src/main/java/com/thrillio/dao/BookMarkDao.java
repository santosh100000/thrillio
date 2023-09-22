package com.thrillio.dao;

import com.thrillio.dataStore.DataStore;
import com.thrillio.entities.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookMarkDao {
    public List<List<BookMark>> getBookMarks(){
        return DataStore.getBookmarks();
    }

    public void saveUserBookmark(UserBookMark userBookMark) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false",
                "root", "root12345"); Statement stmt = conn.createStatement();) {

            if (userBookMark.getBookMark() instanceof Book) {
                saveUserBook(userBookMark, stmt);
            }

            else if (userBookMark.getBookMark()  instanceof Movie) {
                saveUserMovie(userBookMark, stmt);
            }

            else {
                 saveUserWebLink(userBookMark, stmt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
            * Adds Book bookmark and the corresponding user to the User_Book table
	 */
    public void saveUserBook(UserBookMark userBookMark, Statement stmt) throws SQLException {
        String query = "Insert into User_Book (user_id, book_id) values (" + userBookMark.getUser().getId() + ", "
                + userBookMark.getBookMark().getId() + ")";
        stmt.executeUpdate(query);
    }
    /*
     * Adds Movie bookmark and the corresponding user to the User_Movie table
     */
    private void saveUserMovie(UserBookMark userBookmark, Statement stmt) throws SQLException {
        String query = "Insert into User_Movie (user_id, movie_id) values (" + userBookmark.getUser().getId() + ", "
                + userBookmark.getBookMark().getId() + ")";

        stmt.executeUpdate(query);
    }

    /*
     * Adds WebLink bookmark and the corresponding user to the User_WebLink table
     */


      private void saveUserWebLink(UserBookMark userBookmark, Statement stmt)
      throws SQLException { String query =
     "Insert into User_WebLink (user_id, weblink_id) values (" +
      userBookmark.getUser().getId() + ", " + userBookmark.getBookMark() .getId() +
     ")";

     stmt.executeUpdate(query); }


    /*
     * Updates the appropriate database table based on the kid-friendly status of
     * bookmarks that were marked as "approved" or "rejected"
     */
    public void updateKidFriendlyStatus(BookMark bookmark) throws SQLException {
        int kidFriendlyStatus = bookmark.getKidFriendlyStatus().ordinal();
        long userId = bookmark.getKidFriendlyMarkedBy().getId();

        String tableToUpdate = "Book";

        if(bookmark instanceof Movie){
            tableToUpdate = "Movie";
        }
        else if (bookmark instanceof WebLink) {
            tableToUpdate = "WebLink";
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false",
                "root", "root12345"); Statement stmt = conn.createStatement();) {

            String query = "Update " + tableToUpdate + " set kid_friendly_status = " + kidFriendlyStatus
                    + ", kid_friendly_marked_by = " + userId + " where id = " + bookmark.getId();

            System.out.println("query (updateKidFriendlyStatus) : " + query);
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * Updates the appropriate database table based on bookmarks that have been
     * shared
     *
     * Movie bookmarks cannot be shared
     */
    public void shareByInfo(BookMark bookMark) throws SQLException{
        long userId = bookMark.getSharedBy().getId();

        String tableToUpdate = "Book";

        if (bookMark instanceof WebLink) {
            tableToUpdate = "WebLink";
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false",
                "root", "root12345"); Statement stmt = conn.createStatement();) {
            String query = "Update " + tableToUpdate + " set shared_by = " + userId + " where id = " + bookMark.getId();
            System.out.println("query (sharedByInfo): " + query);
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }

    /*
     * Downloads all web links
     *
     * Used when application is initially launched
     */
    public List<WebLink> getAllWebLinks() {
        List<WebLink> result = new ArrayList<>();

        List<List<BookMark>> bookmarks = DataStore.getBookmarks();
        List<BookMark> allWebLinks = bookmarks.get(0);

        // Return a list of web links
        for (BookMark bookmark : allWebLinks) {
            result.add((WebLink) bookmark);
        }

        return result;
    }

    /*
     * Retrieves new web links
     *
     * New web links --> Web links whose download status is "not attempted"
     *
     * In other words, web links that were added since the previous set of web-pages
     * were downloaded
     */
    public List<WebLink> getWebLinks(WebLink.DownloadStatus downloadStatus) {
        List<WebLink> result = new ArrayList<>();

        List<WebLink> allWebLinks = getAllWebLinks();

        for (WebLink webLink : allWebLinks) {
            if (webLink.getDownloadStatus().equals(downloadStatus)) {
                result.add(webLink);
            }
        }
        return result;
    }

    }


