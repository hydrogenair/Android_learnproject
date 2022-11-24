package com.lx.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

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
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private TextView mQuestionTextView;
    private ImageButton mPreviousButton;
    private Question[] mQuestionBank = new Question[]{//R.string.question---找位置 在string资源里面
            new Question(R.string.question_australia, true),//r文件是资源标识的统一管理文件 在r文件中找到String
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)

    };
    private int mCurrentIndex = 0;


    public MainActivity() {
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {//初始化Activity对象
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            mCurrentIndex=savedInstanceState.getInt(KEY_INDEX,0);//没有值是第一次的时候 返回默认值0
            //如果没有KEY_INDEX 更新为0
        }
        //关联一下
        mTrueButton = (Button) findViewById(R.id.true_button);
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

            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast toast=Toast.makeText(MainActivity.this,R.string.incorrect_toast,Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.TOP, 0, 0);
//                toast.show();
                checkAnswer(false);
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//点击之后会发生什么
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mPreviousButton=(ImageButton)findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex=(mCurrentIndex+4)%mQuestionBank.length;
                updateQuestion();
            }
        });
        //打开网页重新更新问题
        updateQuestion();


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

        //savedInstanceState是最开始调的
        //以KEY_INDEX作为键，mCurrentIndex为变量值保存到bundle中
        // Bundle是存储字符串键与限定类型值之间映射关系（键——值对）的一种结构
        boolean[] answers=new boolean[mQuestionBank.length];
        for (int i = 0; i < mQuestionBank.length; i++) {
            answers[i]=mQuestionBank[i].isAnswered();
        }
        savedInstanceState.putBooleanArray(KEY_ANSWERED,answers);
    }



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


//public class QuizActivity extends AppCompatActivity {
//    private Button mTrueButton;
//    private Button mFalseButton;
//    private TextView mQuestionTextView;
//    private Button mNextButton;
//
//
//    private Question[] mQuestionBank = new Question[]{
//            new Question(R.string.question_australia, true),//r文件是资源标识的统一管理文件 在r文件中找到String
//            new Question(R.string.question_oceans, true),
//            new Question(R.string.question_mideast, false),
//            new Question(R.string.question_africa, false),
//            new Question(R.string.question_americas, true),
//            new Question(R.string.question_asia, true)
//
//    };
//    private int mCurrentIndex = 0;
//
//
//    private void updateQuestion() {
//        int question = mQuestionBank[mCurrentIndex].getTextResId();
//        mQuestionTextView.setText(question);
//    }
//
//    private void checkAnswer(boolean userPressedTrue) {
//        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
//        int messageResId = 0;
//        if (userPressedTrue == answerIsTrue) {
//            messageResId = R.string.correct_toast;
//        } else {
//            messageResId = R.string.incorrect_toast;
//        }
//        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
//    }
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
//        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
//                updateQuestion();
//            }
//        });
//
//        mTrueButton = (Button) findViewById(R.id.true_button);
//        mTrueButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                checkAnswer(true);
//            }
//        });
//        mFalseButton = (Button) findViewById(R.id.false_button);
//        mFalseButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                checkAnswer(false);
//            }
//        });
//
//        mNextButton = (Button) findViewById(R.id.next_button);
//        mNextButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
//                updateQuestion();
//            }
//        });
//        updateQuestion();

//        mPreviousButton = (ImageButton)findViewById(R.id.previous_button);
//        mPreviousButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                mCurrentIndex = (mCurrentIndex + 4) % mQuestionBank.length;
//                updateQuestion();
//            }
//        });
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null)
//                        .show();
//            }
//        });
//    }
//
//    @Override public boolean onCreateOptionsMenu(Menu menu) {
//// Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_quiz, menu);
//        return true;
//    }
//
//    @Override public boolean onOptionsItemSelected(MenuItem item) {
//// Handle action bar item clicks here. The action bar will
//// automatically handle clicks on the Home/Up button, so long
//// as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
////noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//    }
//}
//
//
//}