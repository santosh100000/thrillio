package com.thrillio.services;

import com.thrillio.constants.BookGenre;
import com.thrillio.constants.KidFriendlyStatus;
import com.thrillio.constants.MovieGenre;
import com.thrillio.dao.BookMarkDao;
import com.thrillio.entities.*;
import com.thrillio.entities.Movie;
import com.thrillio.util.HttpConnect;
import com.thrillio.util.IOUtil;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

public class BookMarkServices {
    private static BookMarkServices instance = new BookMarkServices();
    private static BookMarkDao bookMarkDao = new BookMarkDao();

    private BookMarkServices(){}

    public static BookMarkServices getInstance(){
        return instance;
    }

    public Movie createMovie(long id, String title, String profileUrl, int releaseYear, String[] cast, String[] directors, MovieGenre genre, double imdbRating){
        com.thrillio.entities.Movie movie = new Movie();

        movie.setId(id);
        movie.setTitle(title);
        movie.setProfileUrl(profileUrl);
        movie.setReleaseYear(releaseYear);
        movie.setCast(cast);
        movie.setDirectors(directors);
        movie.setGenre(genre);
        movie.setImdbRating(imdbRating);

        return movie;
    }

    public Book createBook(long id, String title, String bookUrl, String imageUrl, int publicationYear, String publisher, String[] authors, BookGenre genre,
                           double amazonRating) {
        Book book = new Book();

        book.setId(id);
        book.setTitle(title);
        book.setBookUrl(bookUrl);
        book.setImageUrl(imageUrl);
        book.setPublicationYear(publicationYear);
        book.setPublisher(publisher);
        book.setAuthors(authors);
        book.setGenre(genre);
        book.setAmazonRating(amazonRating);

        return book;
    }


    public WebLink createWebLink(long id, String title, String imageUrl, String url, String host) {
        WebLink weblink = new WebLink();

        weblink.setId(id);
        weblink.setTitle(title);
        weblink.setImageUrl(imageUrl);
        weblink.setUrl(url);
        weblink.setHost(host);

        return weblink;
    }

    public List<List<BookMark>> getBookMarks(){
        return bookMarkDao.getBookMarks();
    }

    public void saveUserBookmark(User user, BookMark bookMark) {
        UserBookMark userBookMark = new UserBookMark();
        userBookMark.setUser(user);
        userBookMark.setBookMark(bookMark);

        bookMarkDao.saveUserBookmark(userBookMark);


    }

    public void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyStatus, BookMark bookMark) throws SQLException {
        bookMark.setKidFriendlyStatus(kidFriendlyStatus);
        bookMark.setKidFriendlyMarkedBy(user);
        bookMarkDao.updateKidFriendlyStatus(bookMark);
        System.out.println("Kid Friendly Status : "+ kidFriendlyStatus + ", Marked By: "+user.getEmail() + bookMark);
    }

    public void share(User user, BookMark bookMark) throws SQLException {

            bookMark.setSharedBy(user);
        System.out.println("Data to be Shared");
        if(bookMark instanceof Book){
            //we are down casting book from bookMark
            System.out.println(( (Book)bookMark).getItemData());

        }else if(bookMark instanceof WebLink){
            //we are down casting book from bookMark
            System.out.println(( (WebLink)bookMark).getItemData());

        }
        bookMarkDao.shareByInfo(bookMark);
    }
}
