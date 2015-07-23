package com.example.apple.myfirsthelloword.com.example.apple.fragment;


import android.app.DownloadManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apple.myfirsthelloword.R;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.LENGTH_SHORT;

/*
*  装修主界面的程序
*
 */
public class ZhuangxiuFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btn_city;
    private Button btn_find_zhuangxiu;
    private Button btn_xuetang;
    private Button btn_tuangou;
    private Button btn_tuku;
    private ViewPager vp_top;
    private LinearLayout ll_point;
    private PullToRefreshScrollView scrollView;
    private ImageButton ibtn_message;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ZhuangxiuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ZhuangxiuFragment newInstance(String param1, String param2) {
        ZhuangxiuFragment fragment = new ZhuangxiuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ZhuangxiuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_zhuangxiu, container, false);

        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();

    }

    public void initView(View view) {
        //城市
        btn_city = (Button) view.findViewById(R.id.btn_main_city);
        //上方的信息
        btn_city.setOnClickListener(this);
        ibtn_message = (ImageButton) view.findViewById(R.id.btn_main_message);
        //找装修
        btn_find_zhuangxiu = (Button) view.findViewById(R.id.btn_fm_zhuangxiu_zhaozhuangxiu);
        //团购
        btn_tuangou = (Button) view.findViewById(R.id.btn_fm_zhuangxiu_tuangou);
        //图库
        btn_tuku = (Button) view.findViewById(R.id.btn_fm_zhuangxiu_tuku);
        //学堂
        btn_xuetang = (Button) view.findViewById(R.id.btn_fm_zhuangxiu_xuetang);
        scrollView = (PullToRefreshScrollView) view.findViewById(R.id.p2rfscollView_zhuangxiu_show);
        //点
        ll_point = (LinearLayout) view.findViewById(R.id.ll_zhuangxiu_dot);
        //上部的广播轮播图
        vp_top = (ViewPager) view.findViewById(R.id.zhuangxiu_top_vp);


    }

    public void initData() {    //scollView 自定义刷新时的文字
        scrollView.getLoadingLayoutProxy(true, false).setLastUpdatedLabel("下拉刷新.......");
        scrollView.getLoadingLayoutProxy(true, false).setRefreshingLabel("正在刷新.......");
        scrollView.getLoadingLayoutProxy(true, false).setPullLabel("");
        scrollView.getLoadingLayoutProxy(true, false).setReleaseLabel("放开以刷新");
    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case  R.id.btn_main_city:
              long string=  new Date().getTime();
              long time=  System.currentTimeMillis();
                Log.e("TAG","最近时间"+time);
                downData();
            Toast.makeText(getActivity().getApplicationContext(),"sssss"+time,Toast.LENGTH_LONG).show();
                break;
        }
    }
    public void downData()
    {
        long time =System.currentTimeMillis();
        String  preMd5String ="bdd81bf94015c47fa6e4c6bd5602d527"+time;
        String  basePath="http://xiaoguotu.17house.com/flush?";
        final String  token=get32MD5Str(preMd5String);
        Log.e("TAG","MD5"+token);
        final String  timeString =time+"";
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
       //广播轮廓图的下载
        StringRequest stringRequest =new StringRequest(Request.Method.POST, basePath, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
               Log.e("TAG","返回信息"+s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
               Log.e("TAG","错误信息"+ volleyError.getMessage());
            }
        }
      )
        {
            @Override
           protected Map<String,String> getParams ()
           {
                Map<String,String> map =new HashMap<String,String>();
                map.put("token",token);
                map.put("time",timeString);

                return map;

            }
        };

       requestQueue.add(stringRequest);

    }

    //Md532位加密算法；
    public final static String get32MD5Str(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString();
    }


}
