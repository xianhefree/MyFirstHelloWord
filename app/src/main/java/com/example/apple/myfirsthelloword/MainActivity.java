package com.example.apple.myfirsthelloword;


import android.net.Uri;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.apple.myfirsthelloword.com.example.apple.fragment.CommunityFragment;
import com.example.apple.myfirsthelloword.com.example.apple.fragment.ZhuangxiuFragment;
import com.example.apple.myfirsthelloword.com.example.apple.fragment.meFragment;


public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener{


    private RadioGroup radioGroup;
    private   RadioButton zhuangxiurbtn ;
    private  FragmentManager fragmentManager;

    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       initData();

        radioGroup.setOnCheckedChangeListener(this);

        zhuangxiurbtn.setChecked(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
      public  void  initData()
      {


          fragmentManager =getSupportFragmentManager();

          radioGroup = (RadioGroup) findViewById(R.id.rg_main_rg);

          zhuangxiurbtn= (RadioButton) radioGroup.getChildAt(0);


      }
    //逻辑代码快

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
          switch (checkedId)
        {//跳转到装修界面
            case  R.id.rb_main_zhuangxu:
                //暂时未设置参数
              fragmentManager.beginTransaction().replace(R.id.fl_main_show, ZhuangxiuFragment.newInstance(" "," ")).commit();
                Toast.makeText(MainActivity.this,"an",Toast.LENGTH_LONG).show();
                break;
            //跳转到社区界面
            case  R.id.rb_main_community:
                 fragmentManager.beginTransaction().replace(R.id.fl_main_show, CommunityFragment.newInstance("","")).commit();
                break;
            //跳到我的设置界面
            case  R.id.rb_main_me:
             fragmentManager.beginTransaction().replace(R.id.fl_main_show, meFragment.newInstance("","")).commit();
                break;


        }



    }
//从fragment传过来的uri;

}
