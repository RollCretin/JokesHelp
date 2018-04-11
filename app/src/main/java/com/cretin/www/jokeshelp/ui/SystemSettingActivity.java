package com.cretin.www.jokeshelp.ui;

import android.view.View;
import android.widget.TextView;

import com.cretin.www.jokeshelp.R;
import com.cretin.www.jokeshelp.db.JokeModel;
import com.cretin.www.jokeshelp.db.UserModel;
import com.cretin.www.jokeshelp.model.event.NotifyUpdate;
import com.cretin.www.jokeshelp.utils.MyAlertDialog;
import com.orhanobut.hawk.Hawk;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.greenrobot.eventbus.EventBus;

public class SystemSettingActivity extends BaseActivity {
    public static final String TAG = "SystemSettingActivity";
    private TextView tv_01;
    private TextView tv_02;
    private TextView tv_03;
    private TextView tv_04;

    @Override
    public int getLayoutId() {
        return R.layout.activity_system_setting;
    }

    @Override
    public void initView() {
        setMainTitle("系统设置");

        tv_01 = ( TextView ) findViewById(R.id.tv_01);
        tv_02 = ( TextView ) findViewById(R.id.tv_02);
        tv_03 = ( TextView ) findViewById(R.id.tv_03);
        tv_04 = ( TextView ) findViewById(R.id.tv_04);

        if ( Hawk.get("AUTO_LIKE", false) ) {
            tv_04.setText("关闭添加段子自动点赞");
        } else {
            tv_04.setText("开启添加段子自动点赞");
        }

        tv_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清除本地段子数据
                MyAlertDialog myAlertDialog =
                        new MyAlertDialog(SystemSettingActivity.this, "温馨提示", "是否要清除本地段子？");
                myAlertDialog.setOnClickListener(new MyAlertDialog.OnPositiveClickListener() {
                    @Override
                    public void onPositiveClickListener(View v) {
                        showToast("本地段子数据已清除");
                        clearAllJokes();
                        EventBus.getDefault().post(new NotifyUpdate());
                    }
                });
                myAlertDialog.show();
            }
        });

        tv_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清除本地用户数据
                MyAlertDialog myAlertDialog =
                        new MyAlertDialog(SystemSettingActivity.this, "温馨提示", "是否要清除本地用户？");
                myAlertDialog.setOnClickListener(new MyAlertDialog.OnPositiveClickListener() {
                    @Override
                    public void onPositiveClickListener(View v) {
                        showToast("本地用户数据已清除");
                        clearAllUsers();
                        EventBus.getDefault().post(new NotifyUpdate());
                    }
                });
                myAlertDialog.show();
            }
        });

        tv_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAlertDialog myAlertDialog =
                        new MyAlertDialog(SystemSettingActivity.this, "温馨提示", "是否要清除本地所有数据？");
                myAlertDialog.setOnClickListener(new MyAlertDialog.OnPositiveClickListener() {
                    @Override
                    public void onPositiveClickListener(View v) {
                        showToast("本地所有数据已清除");
                        //清除本地所有数据
                        clearAllData();
                        EventBus.getDefault().post(new NotifyUpdate());
                    }
                });
                myAlertDialog.show();
            }
        });

        tv_04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = Hawk.get("AUTO_LIKE", false);
                Hawk.put("AUTO_LIKE", !flag);
                if ( !flag ) {
                    tv_04.setText("关闭添加段子自动点赞");
                    showToast("添加段子自动点赞已打开");
                } else {
                    tv_04.setText("开启添加段子自动点赞");
                    showToast("添加段子自动点赞已关闭");
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    public synchronized void clearAllJokes() {
        SQLite.delete().from(JokeModel.class).execute();
    }

    public synchronized void clearAllUsers() {
        SQLite.delete().from(UserModel.class).execute();
    }

    public synchronized void clearAllData() {
        clearAllJokes();
        clearAllUsers();
    }
}
