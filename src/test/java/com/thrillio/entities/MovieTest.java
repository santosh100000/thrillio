package com.thrillio.entities;

import com.thrillio.constants.MovieGenre;
import com.thrillio.services.BookMarkServices;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void isKidFriendlyEligible() {


        //Test 1
        Movie movie = BookMarkServices.getInstance().createMovie(3000,	"Citizen Kane","",	1941,	new String[]{"Orson Welles","Joseph Cotten"},	new String[]{"Orson Welles"},   MovieGenre.COMEDIES, 	8.5);
        boolean isKidFriendlyEligible = movie.isKidFriendlyEligible();
        assertTrue(isKidFriendlyEligible, "For horror in genre - isKidFriendlyEligible() must return false");

        //Test2 if movie genre thrillers return false
         movie = BookMarkServices.getInstance().createMovie(3000,	"Citizen Kane","",	1941,	new String[]{"Orson Welles","Joseph Cotten"},	new String[]{"Orson Welles"},   MovieGenre.THRILLERS, 	8.5);
         isKidFriendlyEligible = movie.isKidFriendlyEligible();
        assertFalse(isKidFriendlyEligible, "For horror in genre - isKidFriendlyEligible() must return false");

    }
}