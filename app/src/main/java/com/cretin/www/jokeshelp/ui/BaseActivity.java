package com.cretin.www.jokeshelp.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cretin.www.jokeshelp.BaseApp;
import com.cretin.www.jokeshelp.R;
import com.cretin.www.jokeshelp.utils.ViewUtils;
import com.cretin.www.jokeshelp.view.CustomProgressDialog;
import com.tsy.sdk.myokhttp.MyOkHttp;

/**
 * Created by cretin on 2018/4/6.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public MyOkHttp myOkHttp;
    private CustomProgressDialog dialog;

    private TextView tvLeft;
    private TextView tvTitle;
    private View line;
    private LinearLayout llMainTitle;

    private boolean isKitkat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            isKitkat = true;
        }

        myOkHttp = BaseApp.getInstance().getMyOkHttp();

        tvLeft = ( TextView ) findViewById(R.id.tv_back);
        tvTitle = ( TextView ) findViewById(R.id.tv_title_info);
        line = findViewById(R.id.line_divider);
        llMainTitle = ( LinearLayout ) findViewById(R.id.ll_main_title);

        if ( tvLeft != null )
            tvLeft.setOnClickListener(v -> finish());

        if ( isKitkat && llMainTitle != null ) {
            llMainTitle.setPadding(0, ViewUtils.getStatusBarHeights(this), 0, 0);
        }

        initView();
        initData();
    }

    //设置标题
    public void setMainTitle(String title) {
        if ( tvTitle != null )
            tvTitle.setText(title);
    }

    //隐藏返回按钮
    public void hidBack() {
        if ( tvLeft != null )
            tvLeft.setVisibility(View.GONE);
    }

    //隐藏line
    public void hidLine() {
        if ( line != null )
            line.setVisibility(View.GONE);
    }

    //显示Toast
    public void showToast(String msg) {
        if ( !TextUtils.isEmpty(msg) ) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myOkHttp.cancel(this);
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();

    public void showDialog(String msg) {
        if ( dialog == null ) {
            dialog = CustomProgressDialog.createDialog(this);
        }
        if ( dialog.isShowing() ) {
            dialog.dismiss();
        }
        if ( !TextUtils.isEmpty(msg) ) {
            dialog.setMessage(msg);
        }
        dialog.show();
    }

    public void stopDialog() {
        if ( dialog != null )
            dialog.dismiss();
    }
}
