package com.lx.criminallent;

import androidx.fragment.app.Fragment;

public class CrimeListActivity extends SingleFragmentActivity{
    //想要启动的是CrimeList这个 这个为启动显示
    @Override
    protected Fragment createFragment() {

        return new CrimeListFragment();
    }
}
