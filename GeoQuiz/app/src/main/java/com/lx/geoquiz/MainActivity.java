package com.lx.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity";
    private static final String KEY_INDEX="index";
    private static final String KEY_ANSWERED="answered";
    private static final String KEY_NUM="answer_num";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private ImageButton mNextButton;
    private TextView mQuestionTextView;
    private ImageButton mPreviousButton;
    private int answer_true_num = 0;
    private int mCurrentIndex = 0;
    private Question[] mQuestionBank = new Question[]{//R.string.question---找位置 在string资源里面
            new Question(R.string.question_australia, true),//r文件是资源标识的统一管理文件 在r文件中找到String
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)

    };



    public MainActivity() {
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {//初始化Activity对象
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){//每次初始化实例（点击）的时候都需要鉴别一下
            mCurrentIndex=savedInstanceState.getInt(KEY_INDEX,0);
            answer_true_num=savedInstanceState.getInt(KEY_NUM,0);
            //没有值是第一次的时候 返回默认值0
            //如果没有KEY_INDEX 更新为0
            //获取答题情况
            boolean[] answers=savedInstanceState.getBooleanArray(KEY_ANSWERED);
            //把答题情况放到QUESTION里
            for (int i = 0; i < answers.length; i++) {
                mQuestionBank[i].setAnswered(answers[i]);
            }

        }
        //关联一下
        mTrueButton = (Button) findViewById(R.id.true_button);
        //mTrueButton = (Button) findViewById(R.id.question_text_view);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mQuestionTextView=(TextView)findViewById(R.id.question_text_view);
        //设置监听器

        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });//点击文本换题

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override//匿名内部类（直接创建抽象对象，重写抽象方法）：集中实现监听方法 省去繁琐的命名
            public void onClick(View view) {
//                Toast toast=Toast.makeText(MainActivity.this,R.string.correct_toast,Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.TOP, 0, 0);
//                toast.show();
                checkAnswer(true);
                results();

            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast toast=Toast.makeText(MainActivity.this,R.string.incorrect_toast,Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.TOP, 0, 0);
//                toast.show();
                checkAnswer(false);
                results();
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//点击之后会发生什么
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
                checkIsAnswered();
                results();
            }
        });

        mPreviousButton=(ImageButton)findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex=(mCurrentIndex+4)%mQuestionBank.length;
                updateQuestion();
                checkIsAnswered();
                results();
            }
        });
        //打开网页重新更新问题
        updateQuestion();

        mCheatButton=(Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                updateQuestion();
            }
        });


    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
//        Log.d(TAG,"Updating question text",new Exception());
        mQuestionTextView.setText(question);
    }

    private void checkIsAnswered(){
        boolean answered=mQuestionBank[mCurrentIndex].Answered();
        if(answered==true){
            mFalseButton.setEnabled(false);
            mTrueButton.setEnabled(false);
        }else{
            mFalseButton.setEnabled(true);
            mTrueButton.setEnabled(true);
        }
    }


    private void checkAnswer(boolean userPressedTrue) {
        mQuestionBank[mCurrentIndex].setAnswered(true);//已经点击了一次
        checkIsAnswered();
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
            answer_true_num++;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
        mQuestionBank[mCurrentIndex].setAnswered(true);
    }

    private void results(){
        int flag=0;
        for (int i = 0; i < mQuestionBank.length; i++) {
            if(mQuestionBank[i].Answered()==false){
                flag=1;
                break;
            }
        }
        if(flag==0){
            double result =((double)answer_true_num/mQuestionBank.length)*100;
            Toast.makeText(this, "正确率为"+result+"%", Toast.LENGTH_SHORT).show();

        }

    }
    //把这些方法重写方便在日志中找到
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart() called");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume() called");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"onPause() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        //存储bundle
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        savedInstanceState.putInt(KEY_NUM,answer_true_num);

        //savedInstanceState是最开始调的
        //以KEY_INDEX作为键，mCurrentIndex为变量值保存到bundle中
        // Bundle是存储字符串键与限定类型值之间映射关系（键——值对）的一种结构
        boolean[] answers=new boolean[mQuestionBank.length];
        for (int i = 0; i < mQuestionBank.length; i++) {
            answers[i]=mQuestionBank[i].Answered();
        }
        savedInstanceState.putBooleanArray(KEY_ANSWERED,answers);
    }
    //禁止一题多答：
    // 什么时候更改内容？ 第一次check之后
    // 什么时候判断内容？ 再次点击答案之前 允许next，previous 不允许作答 ！进入next需重置按钮



    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"onStop() called");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }


}


