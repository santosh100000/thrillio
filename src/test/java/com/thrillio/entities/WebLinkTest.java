package com.thrillio.entities;

import com.thrillio.services.BookMarkServices;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WebLinkTest {

    @Test
    void isKidFriendlyEligible() {
        //Test 1 : Porn in url -- false
        WebLink webLink = BookMarkServices.getInstance().createWebLink
                (2000,	"Taming Tiger, Part 2",	"http://www.javaworld.com/article/2072759/core-java/taming-porn--part-2.html",	"http://www.javaworld.com", "");

        boolean isKidFriendlyEligible = webLink.isKidFriendlyEligible();

        assertFalse(isKidFriendlyEligible, "For porn in url - isKidFriendlyEligible() must return false");

        //Test 2 : porn in title -- false
         webLink = BookMarkServices.getInstance().createWebLink
                (2000,	"Taming porn, Part 2",	"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",	"http://www.javaworld.com", "");

         isKidFriendlyEligible = webLink.isKidFriendlyEligible();
         assertFalse(isKidFriendlyEligible, "For porn in title -isKidFriendlyEligible() must return false");

        //Test 3 : adult in host -- false
        webLink = BookMarkServices.getInstance().createWebLink
                (2000,	"Taming Tiger, Part 2",	"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",	"http://www.adult.com", "http://www.adult.com");

        isKidFriendlyEligible = webLink.isKidFriendlyEligible();
        assertFalse(isKidFriendlyEligible, "For adult in host -isKidFriendlyEligible() must return false");

        //test4: adult in url, but not in host part -- true
        webLink = BookMarkServices.getInstance().createWebLink
                (2000,	"Taming Tiger, Part 2",	"http://www.javaworld.com/article/2072759/core-java/taming-adult--part-2.html",	"http://www.adult.com", "");

        isKidFriendlyEligible = webLink.isKidFriendlyEligible();
        assertTrue(isKidFriendlyEligible, "For adult in url but not in host -isKidFriendlyEligible() must return false");

        //test5: adult in title only --true
        webLink = BookMarkServices.getInstance().createWebLink
                (2000,	"Taming adult, Part 2",	"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",	"http://www.javaworld.com", "");

        isKidFriendlyEligible = webLink.isKidFriendlyEligible();
        assertTrue(isKidFriendlyEligible, "For adult in url but not in host -isKidFriendlyEligible() must return false");

    }
}