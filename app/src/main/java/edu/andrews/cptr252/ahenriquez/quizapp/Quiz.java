package edu.andrews.cptr252.ahenriquez.quizapp;

import java.util.UUID;

public class Quiz {
/* UUID class is used to automatically create new unique ids for a specific quiz*/
    private UUID mId;

    /* Title of Quiz*/
    private String mTitle;

    /* Create and initialize new Quiz */
    public Quiz() {
        //Generate unique identifier for new Quiz
        mId = UUID.randomUUID();
    }
    /* Return unique id for quiz
    @return Quiz id
     */
    public String getTitle() {
        return mTitle;
    }
    /* provide new title for Quiz
    @param title New title
     */
    public void setTitle(String title) {
        mTitle = title;
    }
}


