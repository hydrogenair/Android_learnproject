package com.lx.criminallent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.UUID;

public class MainActivity extends SingleFragmentActivity {
    //传递附加到Intent extra上的crime ID
    // CrimeFragment就能知道该显示哪个Crime。
    //用intent.extra
    private static final String EXTRA_CRIME_ID="com.lx.android.criminalintent.crime_id.";
    public  static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent=new Intent(packageContext,MainActivity.class);
        //赋予键值
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }
//创建的fragment实例中包含有UUID这个EXTRA
    @Override
    protected Fragment createFragment() {
        UUID crimeId=(UUID) getIntent().
                getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }
}