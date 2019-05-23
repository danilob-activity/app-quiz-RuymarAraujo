package com.londonappbrewery.quizzler.models;

/**
 * Created by aluno on 23/05/19.
 */

public class TrueFalse {
    private int mQuestionID;
    private boolean mAnswer;

    public TrueFalse(int questionID, boolean answer) {
        mQuestionID = questionID;
        mAnswer = answer;
    }

    public int getQuestionID() {
        return mQuestionID;
    }

    public boolean isAnswerID () {
        return mAnswer;
    }
}
