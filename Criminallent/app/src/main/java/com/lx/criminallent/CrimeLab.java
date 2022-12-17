package com.lx.criminallent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab sCrimeLab;
    //s开头的为静态变量
    //创建容纳crime的list
    private List<Crime> mCrimes;

    public CrimeLab(Context context) {
        mCrimes =new ArrayList<>();
    }

    public Crime getCrime(UUID id){
        for (Crime crime:mCrimes){
            if(crime.getId().equals(id)){
                return crime;
            }
        }
        return null;
    }


    public static CrimeLab get(Context context){
        if(sCrimeLab==null){
            sCrimeLab= new CrimeLab(context);
        }
        return sCrimeLab;
    }
}
