package com.lx.criminallent;

import java.util.Date;
import java.util.UUID;

public class Crime {
    //UUID 一种可电脑自动生成识别的通用唯一识别码
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    //是否需要创建police
    private boolean mRequiresPolice;

    public Crime( ) {
        //随机产生一个ID
        mId = UUID.randomUUID();
        mDate=new Date();
    }
    public boolean isRequiresPolice() {
        return mRequiresPolice;
    }

    public void setRequiresPolice(boolean requiresPolice) {
        mRequiresPolice = requiresPolice;
    }
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public UUID getId() {
        return mId;
    }
}
