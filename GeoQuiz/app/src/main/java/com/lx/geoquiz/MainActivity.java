package com.lx.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private Question[] mQuestionBank=new Question[]{//R.string.question---找位置 在string资源里面
            new Question(R.string.question_australia,true),//r文件是资源标识的统一管理文件 在r文件中找到String
            new Question(R.string.question_oceans,true),
            new Question(R.string.question_mideast,false),
            new Question(R.string.question_africa,false),
            new Question(R.string.question_americas,true),
            new Question(R.string.question_asia, true)

    };
    private int mCurrentIndex=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //关联一下
        mTrueButton=(Button) findViewById(R.id.true_button);
        mFalseButton=(Button)findViewById(R.id.false_button);

        //设置监听器
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override//匿名内部类（直接创建抽象对象，重写抽象方法）：集中实现监听方法 省去繁琐的命名
            public void onClick(View view) {
                Toast toast=Toast.makeText(MainActivity.this,R.string.correct_toast,Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();

            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast=Toast.makeText(MainActivity.this,R.string.incorrect_toast,Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            }
        });

        mNextButton=(Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex=(mCurrentIndex+1)% mQuestionBank.length;
                updateQuestion();
            }
        });


    }

    private void updateQuestion(){
        int question =mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }


}