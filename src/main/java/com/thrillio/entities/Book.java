package com.thrillio.entities;

import com.thrillio.constants.BookGenre;
import com.thrillio.partner.Sharable;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

public class Book extends BookMark implements Sharable {
    private int publicationYear;
    private String publisher;
    private String[] authors;
    private BookGenre genre;
    private double amazonRating;
    private String bookUrl;
    private String imageUrl;

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public double getAmazonRating() {
        return amazonRating;
    }

    public void setAmazonRating(double amazonRating) {
        this.amazonRating = amazonRating;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Book{" +
                "publicationYear=" + publicationYear +
                ", publisher='" + publisher + '\'' +
                ", authors=" + Arrays.toString(authors) +
                ", genre='" + genre + '\'' +
                ", amazonRating=" + amazonRating +
                '}';
    }

    @Override
    public boolean isKidFriendlyEligible() {
        if(genre.equals(BookGenre.SELF_HELP) || genre.equals(BookGenre.PHILOSOPHY)){
            return false;
        }return true;
    }

    @Override
    public String getItemData() {

        //Xtensible Markup Languages

        StringBuilder builder = new StringBuilder();
        builder.append("<item>");
         builder.append("<type>Book</type>");
         builder.append("<title>").append(getTitle()).append("</title>");
         builder.append("<authors>").append(StringUtils.join(authors, ",")).append("</authors>");
         builder.append("<publisher>").append(publisher).append("</publisher>");
         builder.append("<publicationYear>").append(publicationYear).append("</publicationYear>");
         builder.append("<genre>").append(genre).append("</genre>");
         builder.append("<amazonRating>").append(amazonRating).append("</amazonRating>");

        builder.append("</item>");

        return builder.toString();
    }
}
