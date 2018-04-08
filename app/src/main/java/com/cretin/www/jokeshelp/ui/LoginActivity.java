package com.cretin.www.jokeshelp.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.cretin.www.jokeshelp.R;
import com.cretin.www.jokeshelp.app.URLConstant;
import com.cretin.www.jokeshelp.model.LoginModel;
import com.orhanobut.hawk.Hawk;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity {
    public static final String TAG = "LoginActivity";
    private com.cretin.www.clearedittext.view.ClearEditText mEd_username;
    private com.cretin.www.clearedittext.view.ClearEditText mEd_password;
    private TextView mTv_login;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mEd_password.requestFocus();
            String password = mEd_password.getText().toString().trim();
            mEd_password.setSelection(password.length());//将光标移至文字末尾
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        bindViews();
    }

    @Override
    public void initData() {
        String info = Hawk.get("LOGIN_INFO");
        if ( !TextUtils.isEmpty(info) ) {
            if ( info.contains(" ") ) {
                mEd_username.setText(info.split(" ")[0]);
                mEd_password.setText(info.split(" ")[1]);

                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Thread.sleep(300);
                            handler.sendEmptyMessage(0);
                        } catch ( InterruptedException e ) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        }
    }

    private void bindViews() {
        mEd_username = ( com.cretin.www.clearedittext.view.ClearEditText ) findViewById(R.id.ed_username);
        mEd_password = ( com.cretin.www.clearedittext.view.ClearEditText ) findViewById(R.id.ed_password);
        mTv_login = ( TextView ) findViewById(R.id.tv_login);

        mTv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String username = mEd_username.getText().toString().trim();
        String password = mEd_password.getText().toString().trim();
        if ( TextUtils.isEmpty(username) ||
                TextUtils.isEmpty(password) ) {
            showToast("账号和密码不能为空");
            return;
        }
        showDialog("正在登录...");
        String url = URLConstant.BASE_URL + "/admin/login";
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        myOkHttp.post()
                .url(url)
                .params(params)
                .tag(this)
                .enqueue(new GsonResponseHandler<LoginModel>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        showToast("服务器异常");
                        stopDialog();
                    }

                    @Override
                    public void onSuccess(int statusCode, LoginModel response) {
                        stopDialog();
                        if ( response.getCode() == 1 ) {
                            //成功
                            Hawk.put("LOGIN_INFO", username + " " + password);
                            Hawk.put("LOGIN_USER", response.getData());
                            showToast("登录成功");
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            showToast(response.getMessage());
                        }
                    }
                });
    }
}
