package com.thrillio.entities;

import com.thrillio.constants.BookGenre;
import com.thrillio.constants.MovieGenre;
import com.thrillio.services.BookMarkServices;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void isKidFriendlyEligible() {
        //Test1 - if book genre is philosophy then not kid friendly

        Book book = BookMarkServices.getInstance().createBook(4000,	"Walden",	"",	"", 2000, "Walden",	new String[]{"Henry", "David", "Thoreau"},  BookGenre.PHILOSOPHY,	4.3);
        boolean isisKidFriendlyEligible = book.isKidFriendlyEligible();
        assertFalse(isisKidFriendlyEligible, "For philopshy as book genre is not kid friendly");

        //Test 2 - if book genre is self - help then not kid friendly
        Book book1 = BookMarkServices.getInstance().createBook(4000,	"Walden",	"",	"", 2000, "Walden",	new String[]{"Henry", "David", "Thoreau"},  BookGenre.SELF_HELP,	4.3);
         isisKidFriendlyEligible = book.isKidFriendlyEligible();
        assertFalse(isisKidFriendlyEligible, "For Self_help as book genre is not kid friendly");

        //Test 3 - if book genre is other than Self_help & philosophy then it is kid Friendly
        Book book2 = BookMarkServices.getInstance().createBook(4000,	"Walden",	"",	"", 2000, "Walden",	new String[]{"Henry", "David", "Thoreau"},  BookGenre.CHILDREN,	4.3);
        isisKidFriendlyEligible = book.isKidFriendlyEligible();
        assertTrue(isisKidFriendlyEligible, "For Other genre than Philosophy & Self Helps are kids friendly");

    }
}