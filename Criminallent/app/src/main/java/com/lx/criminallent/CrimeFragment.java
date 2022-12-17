package com.lx.criminallent;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
//CrimeFragment类是与模型及视图对象交互的控制器，
//用于显示特定crime的明细信息，并在用户修改这些信息后立即进行更新
public class CrimeFragment extends Fragment {
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    //实例化fragement
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建一个crime 对象
        mCrime=new Crime();
    }
    //实例化fragement的视图布局
    //然后将实例化的 View 返回给托管 activity

    @Override
    //LayoutInflater及ViewGroup是实例化布局的必要参数。Bundle用来存储恢复数据，可供该方法从保存状态下重建视图。
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //fragment的视图是直接通过调用LayoutInflater.inflate(...)方法并传入布局的资源ID生成的。
        //第二个参数是视图的父视图，我们通常需要父视图来正确配置组件。
        //第三个参数告诉布局生成器是否将生成的视图添加给父视图。这里，传入了false参数，
        //因为我们将以代码的方式添加视图

        View v = inflater.inflate(R.layout.fragment_crime, container, false);
        //实例化组件
        mTitleField=(EditText)v.findViewById(R.id.crime_title);
        //对文字输入监视
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mDateButton=(Button) v.findViewById(R.id.crime_date);
        //设置日期
        mDateButton.setText(mCrime.getDate().toString());
        //点不动
        mDateButton.setEnabled(false);

        //监听CheckBox
        mSolvedCheckBox=(CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        return v;
    }
}
