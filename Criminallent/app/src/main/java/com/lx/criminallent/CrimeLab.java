package com.lx.criminallent;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab sCrimeLab;
    //s开头的为静态变量
    //创建容纳crime的list
    private List<Crime> mCrimes;
    //符号告诉编译器，List中的元素类型可
    //以基于变量声明传入的抽象参数来确定。

    public List<Crime> getCrimes(){
        return mCrimes;
    }


    public CrimeLab(Context context) {
        for (int i = 0; i < 100; i++) {
            //满载100个crime数据的模型层诞生了
            Crime crime=new Crime();
            crime.setTitle("Crime #"+i);
            crime.setSolved(i %2==0);
            //初始化crime 偶数为police版本
            if(i%2==0){
                crime.setRequiresPolice(true);
            }else{
                crime.setRequiresPolice(false);
            }
          mCrimes.add(crime);
        }
    }

    public Crime getCrime(UUID id){
        for (Crime crime:mCrimes){
            if(crime.getId().equals(id)){
                return crime;
            }
        }
        return null;
    }

//单例
    public static CrimeLab get(Context context){

        if(sCrimeLab==null){
            sCrimeLab= new CrimeLab(context);
        }
        return sCrimeLab;
    }
}
