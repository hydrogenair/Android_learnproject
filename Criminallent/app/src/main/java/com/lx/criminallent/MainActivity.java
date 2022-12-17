package com.lx.criminallent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FragmentManager fm=getFragmentManager();
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment=fm.findFragmentById(R.id.fragment_container);


        if(fragment==null){
            fragment =new CrimeFragment();
            //创建提交了一个fragment事务
            //事务用来添加、移除、附加、分离或替换fragment队列中的fragment
            //创建一个新的fragment事务，执行一个
            //fragment添加操作，然后提交该事务。
            fm.beginTransaction().add(
                    R.id.fragment_container,fragment)
                    .commit();

        }
    }
}