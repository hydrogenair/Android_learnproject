package com.lx.criminallent;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public abstract class SingleFragmentActivity extends AppCompatActivity {
    protected abstract Fragment createFragment();
//创造单实例fragment 重写方法为创建什么类的fragment
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm=getSupportFragmentManager();
        Fragment fragment=fm.findFragmentById(R.id.fragment_container);

        if(fragment==null){
            //创建提交了一个fragment事务
//            //事务用来添加、移除、附加、分离或替换fragment队列中的fragment
//            //创建一个新的fragment事务，执行一个
//            //fragment添加操作，然后提交该事务。
            fragment=createFragment();
            fm.beginTransaction().add(
                    R.id.fragment_container,
                    fragment).commit();
        }
    }
}
