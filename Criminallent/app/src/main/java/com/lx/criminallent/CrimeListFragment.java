package com.lx.criminallent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class CrimeListFragment extends Fragment {
    //RecyclerView的任务仅限于回收和定位屏幕上的View
    //每一个列表项都是作为一个View子对象显示的
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    //保存刷新位置
    private int UpdatePosition = 0;

    @Nullable
    @Override
    //连接fragmentlist的视图
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_crime_list,container,false);

        mCrimeRecyclerView=view.findViewById(R.id.crime_recycler_view);
        //RecyclerView类不会亲自摆放屏幕上的列表项。
        //实际上，摆放的任务被委托给了LayoutManager。
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //关联recycleview 和adapter
        updateUI();
        return view;
    }
   //模型层保存的数据若有变化（或可能有变） 应通知RecyclerView的Adapter
    //用户点击后退键返回到列表项界面，CrimeActivity随即弹出栈外并被销毁。
   // 此时，CrimeListActivity立即重新启动并恢复运行
   //操作系统会发出调用onResume()生命周期方法的指令。
    @Override
    public void onResume(){
        super.onResume();
        //刷新列表 更新更改内容
        updateUI();
    }

    private void updateUI(){
        //初始化列表
        CrimeLab crimeLab=CrimeLab.get(getActivity());
        List<Crime> crimes=crimeLab.getCrimes();
        //System.out.println(crimes.size());

        if(mAdapter==null){
            //关联recycleview 和adapter
            mAdapter =new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        }else{
            //调用notifyDataSetChanged()方法来修改updateUI()方法
            //notifyDataSetChanged方法通过一个外部的方法控制如果适配器的内容改变时
            //需要强制调用getView来刷新每个Item的内容
            //notifyItemChanged局部刷新
            mAdapter.notifyItemChanged(UpdatePosition);
        }

    }

//实例化list_item_crime布局，然后传给super(...)方法，也就是ViewHolder的构造方法。
//基类ViewHolder因而实际引用这个视图。
    //ViewHolder只做一件事：容纳View视图
    private class CrimeHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private  Crime mCrime;
        private ImageView msolvedImageView;

        public CrimeHolder(LayoutInflater inflater,ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_crime,parent,false));
            //重写接口Onclick这里再调用就是完整的
            itemView.setOnClickListener(this);
            //关联一下视图组件
            mTitleTextView=itemView.findViewById(R.id.crime_title);
            mDateTextView=itemView.findViewById(R.id.crime_date);
            msolvedImageView= itemView.findViewById(R.id.handcuffs);
        }

        @Override
        public void onClick(View view) {
//            Toast.makeText(getActivity(), mCrime.getTitle()+"checked!",Toast.LENGTH_SHORT)
//                    .show();
            //从fragment中启动Activity类似Activity中启动activity 用intent
            Intent intent= MainActivity.newIntent(getActivity(),mCrime.getId());
            //notifyDataSetChanged方法会通知RecyclerView刷新全部的可见列表项。
            //notifyItemChanged则只是刷新局部
            UpdatePosition=getAdapterPosition();
            startActivity(intent);
        }

    //CrimeHolder还需要一个bind(Crime)方法。
        //每次有新的Crime要在CrimeHolder中显示时，都要调用它一次。
        public void bind(Crime crime){
            mCrime=crime;
            //设置文本
            mTitleTextView.setText(mCrime.getTitle());

            //String formatdata = "yyyy,MM,dd 'at' HH:mm:ss";
            //String date = DateFormat.format(formatdata, mCrime.getDate()).toString();
            DateFormat df= new SimpleDateFormat("EEE,MMM dd,yyyy");
            String time=df.format(mCrime.getDate());
            mDateTextView.setText(time);
            //根据crime记录的解决状态，控制图片的显示
            msolvedImageView.setVisibility(crime.isSolved()?View.VISIBLE:View.GONE);
        }

    }
    private class PoliceCrimeHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private  Crime mPoliceCrime;
        private Button mCallPolice;

        public PoliceCrimeHolder(LayoutInflater inflater,ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_police_crime,parent,false));
            itemView.setOnClickListener(this);
            mTitleTextView=itemView.findViewById(R.id.crime_title);
            mDateTextView=itemView.findViewById(R.id.crime_date);
            mCallPolice=itemView.findViewById(R.id.call_police);

            mCallPolice.setOnClickListener(view -> Toast.makeText
            (getActivity(),"call police now",
            Toast.LENGTH_SHORT).show());

        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), mPoliceCrime.getTitle()+"checked!",Toast.LENGTH_SHORT)
                    .show();
        }
        public void bind(Crime crime){
            mPoliceCrime=crime;
            mTitleTextView.setText(mPoliceCrime.getTitle());
            DateFormat df= new SimpleDateFormat("EEE,MMM dd,yyyy");
            String time=df.format(mPoliceCrime.getDate());
            mDateTextView.setText(time);
        }
    }
//Adapter负责：
// 创建必要的ViewHolder；
// 绑定ViewHolder至模型层数据
    private class CrimeAdapter extends RecyclerView.Adapter{
        private List<Crime> mCrimes;

        @Override
        //判断视图的方法
        public int getItemViewType(int position) {
            //position表示列表中需要判断的crime的位置
            if (mCrimes.get(position).isRequiresPolice() == true){
                return 1;
            }
            else{
                return 0;
            }
        }

        public CrimeAdapter(List<Crime> crimes){
            mCrimes=crimes;
        }


    @NonNull
    @Override
    //RecyclerView需要新的ViewHolder来显示列表项时，会调用onCreateViewHolder方法。
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //创建一个LayoutInflater，然后用它创建CrimeHolder。
        LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
        //据getViewType函数里面的设置的viewType的值，调用不同的holder
        if(viewType==0){
            return new PoliceCrimeHolder(layoutInflater, parent);
        }else {
            return new CrimeHolder(layoutInflater,parent);
        }


    }

//每次RecyclerView要求CrimeHolder绑定对应的Crime时，
// 都会调用bind(Crime)方法
    //更换绑定对象
    @Override
    //不能指定crimeholder的类型
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Crime crime=mCrimes.get(position);
        //指定类型
        if(this.getItemViewType(position)==0){
            ((PoliceCrimeHolder)holder).bind(crime);
        }else {
            ((CrimeHolder)holder).bind(crime);
        }
    }

    @Override
    public int getItemCount() {
        return mCrimes.size();
    }

}
}
