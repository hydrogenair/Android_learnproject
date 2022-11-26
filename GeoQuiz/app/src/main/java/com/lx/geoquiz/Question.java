package com.lx.geoquiz;

public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean Answered;

    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        Answered=false;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public boolean Answered() {return Answered;}

    public void setAnswered(boolean Answered) {this.Answered = Answered;
    }
}
