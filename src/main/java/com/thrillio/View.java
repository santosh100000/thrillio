package com.thrillio;

import com.thrillio.constants.KidFriendlyStatus;
import com.thrillio.constants.UserType;
import com.thrillio.controllers.BookMarkController;
import com.thrillio.entities.BookMark;
import com.thrillio.entities.User;
import com.thrillio.partner.Sharable;

import java.sql.SQLException;
import java.util.List;

public class View {


    public static void browse(User user, List<List<BookMark>>bookMarks) throws SQLException {
        System.out.println("\n"+user.getEmail()+ "is bookmarking");

        int bookmarkCount = 0;

        for (List<BookMark> bookMarkList:bookMarks){
            for (BookMark bookMark: bookMarkList){
                //Book marking

                  boolean isBookMarked = getBookMarkDecision(bookMark);
                  if(isBookMarked){
                      bookmarkCount ++;
//                      BookMarkController.getInstance().saveUserBookmark(user, bookMark);

                      System.out.println("New item Added by user " +bookMark);
                  }


                //Mark as kid Friendly
                if(user.getUserType().equals(UserType.EDITOR)
                        ||user.getUserType().equals(UserType.CHIEF_EDITOR))
                {
                    //Mark as kid-friendly
                    if(bookMark.isKidFriendlyEligible() && bookMark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN))
                    {
                        KidFriendlyStatus kidFriendlyStatus = getKidFriendlyStatusDecision(bookMark);

                        if(!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN))
                        {
                            //BookmarkController.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);

                        }
                    }


                    //Sharing
                    if(bookMark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED)
                    && bookMark instanceof Sharable){
                        boolean isShared = getShareDecision();
                        if(isShared){
                            BookMarkController.getInstance().share(user, bookMark);
                        }


                    }



                }
            }
        }



    }
//Below Method simulate user input after IO, we take input via console
    private static KidFriendlyStatus getKidFriendlyStatusDecision(BookMark bookMark) {
        double decision = Math.random();

        return decision < 0.4 ? KidFriendlyStatus.APPROVED
                : (decision >= 0.4 && decision < 0.8) ? KidFriendlyStatus.REJECTED
                : KidFriendlyStatus.UNKNOWN;
    }

    private static boolean getBookMarkDecision(BookMark bookMark) {

        return Math.random() < 0.5 ? true : false;
    }

    private static boolean getShareDecision( ) {

        return Math.random() < 0.5 ? true : false;
    }
}

