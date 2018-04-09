package com.cretin.www.jokeshelp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.cretin.www.jokeshelp.R;
import com.cretin.www.jokeshelp.app.URLConstant;
import com.cretin.www.jokeshelp.db.JokeModel;
import com.cretin.www.jokeshelp.db.JokesHelperDataBase;
import com.cretin.www.jokeshelp.model.JokesResultModel;
import com.cretin.www.jokeshelp.model.LoginModel;
import com.cretin.www.jokeshelp.model.TableInfo;
import com.cretin.www.jokeshelp.model.event.NotifyUpdate;
import com.cretin.www.jokeshelp.utils.MyAlertDialog;
import com.orhanobut.hawk.Hawk;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "MainActivity";

    //控件
    private TextView mTv_01;
    private TextView mTv_02;
    private TextView mTv_03;
    private TextView mTv_04;
    private TextView mTv_05;
    private TextView mTv_06;
    private TextView mTv_07;
    private TextView mTv_08;
    private TextView mTv_09;
    private TextView tv_compare_tips;
    private TextView tv_tongbu_tips;
    private TextView tv_curr_user;

    //记录对比结果 0 未对比 1 对比失败 2 对比成功
    private int mCompareFlag = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            stopDialog();
            if ( msg.what == 0 ) {
                //同步失败
                tv_tongbu_tips.setVisibility(View.VISIBLE);
                tv_tongbu_tips.setText("同步失败");
                tv_tongbu_tips.setTextColor(Color.parseColor("#D32F2F"));
                mCompareFlag = 1;
            } else {
                //同步成功
                tv_tongbu_tips.setVisibility(View.VISIBLE);
                tv_tongbu_tips.setText("同步成功");
                mTv_02.performClick();
                tv_tongbu_tips.setTextColor(Color.parseColor("#89c732"));
                mCompareFlag = 2;
            }
        }
    };

    //清空表数据
    public synchronized void clearAll() {
        SQLite.delete().from(JokeModel.class).execute();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        bindViews();

        hidBack();

        setMainTitle("段子乐管理系统");
    }

    @Override
    public void initData() {
        LoginModel.DataBean user = Hawk.get("LOGIN_USER");
        if ( user != null ) {
            tv_curr_user.setText("当前登录：" + user.getUsername());
        }

    }

    private void bindViews() {
        mTv_01 = ( TextView ) findViewById(R.id.tv_01);
        mTv_02 = ( TextView ) findViewById(R.id.tv_02);
        mTv_03 = ( TextView ) findViewById(R.id.tv_03);
        mTv_04 = ( TextView ) findViewById(R.id.tv_04);
        mTv_05 = ( TextView ) findViewById(R.id.tv_05);
        mTv_06 = ( TextView ) findViewById(R.id.tv_06);
        mTv_07 = ( TextView ) findViewById(R.id.tv_07);
        mTv_08 = ( TextView ) findViewById(R.id.tv_08);
        mTv_09 = ( TextView ) findViewById(R.id.tv_09);
        tv_compare_tips = ( TextView ) findViewById(R.id.tv_compare_tips);
        tv_tongbu_tips = ( TextView ) findViewById(R.id.tv_tongbu_tips);
        tv_curr_user = ( TextView ) findViewById(R.id.tv_curr_user);

        mTv_01.setOnClickListener(this);
        mTv_02.setOnClickListener(this);
        mTv_03.setOnClickListener(this);
        mTv_04.setOnClickListener(this);
        mTv_05.setOnClickListener(this);
        mTv_06.setOnClickListener(this);
        mTv_07.setOnClickListener(this);
        mTv_08.setOnClickListener(this);
        mTv_09.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId() ) {
            case R.id.tv_01:
                //系统概览
                startActivity(new Intent(this, SystemOutlineActivity.class));
                break;
            case R.id.tv_02:
                //数据对比
                dataCompare();
                break;
            case R.id.tv_03:
                if ( mCompareFlag == 0 ) {
                    showToast("请先进行数据对比");
                    return;
                } else if ( mCompareFlag == 1 ) {
                    //对比失败
                    //同步数据
                    dataAsync();
                } else {
                    //对比成功
                    showToast("数据已是最新，不用同步");
                    return;
                }
                break;
            case R.id.tv_04:
                if ( mCompareFlag != 2 ) {
                    showToast("数据对比成功后才能操作");
                    return;
                }
                startActivity(new Intent(this, AddPicJokesActivity.class));
                break;
            case R.id.tv_05:
                if ( mCompareFlag != 2 ) {
                    showToast("数据对比成功后才能操作");
                    return;
                }
                //添加文字段子
                startActivity(new Intent(this, AddTextJokeActivity.class));
                break;
            case R.id.tv_06:
                //用户管理
                startActivity(new Intent(this, AddUserActivity.class));
                break;
            case R.id.tv_09:
                //验证码查询
                startActivity(new Intent(this, VerificationCodeActivity.class));
                break;
            case R.id.tv_07:
                //设置
                startActivity(new Intent(this, SystemSettingActivity.class));
                break;
            case R.id.tv_08:
                //退出登录
                MyAlertDialog myAlertDialog = new MyAlertDialog(this, "提示", "确定退出登录？");
                myAlertDialog.setOnClickListener(new MyAlertDialog.OnPositiveClickListener() {
                    @Override
                    public void onPositiveClickListener(View v) {
                        Hawk.remove("LOGIN_USER");
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        MainActivity.this.finish();
                    }
                });
                myAlertDialog.show();
                break;
        }
    }

    //数据同步
    private void dataAsync() {
        String url = URLConstant.BASE_URL + "/admin/jokesalllist";
        showDialog("正在同步...");
        myOkHttp.post()
                .url(url)
                .tag(this)
                .enqueue(new GsonResponseHandler<JokesResultModel>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        showToast("服务器错误");
                        stopDialog();
                    }

                    @Override
                    public void onProgress(long currentBytes, long totalBytes) {
                    }

                    @Override
                    public void onSuccess(int statusCode, JokesResultModel response) {
                        if ( response.getCode() == 1 ) {
                            new DataAsyncTask().execute(response);
                        } else {
                            stopDialog();
                            showToast(response.getMessage());
                        }
                    }
                });
    }

    private class DataAsyncTask extends AsyncTask<JokesResultModel, Void, Boolean> {

        @Override
        protected Boolean doInBackground(JokesResultModel... jokesResultModels) {
            //创建一个新的集合保存数据
            JokesResultModel response = jokesResultModels[0];
            if ( response != null ) {
                List<JokesResultModel.DataBean.JokesListBean> jokesList =
                        response.getData().getJokesList();
                if ( jokesList != null ) {
                    List<JokeModel> jokeModels = new ArrayList<>();
                    //获取到数据 后解析
                    JokeModel jokeModel;
                    for ( JokesResultModel.DataBean.JokesListBean joke :
                            jokesList ) {
                        jokeModel = new JokeModel();
                        jokeModel.setIsHot(joke.getIsHot());
                        jokeModel.setImageUrl(joke.getImageUrl());
                        jokeModel.setType(joke.getType());
                        jokeModel.setUserId(joke.getUserId());
                        jokeModel.setUpdateTime(new Date(joke.getUpdateTime()));
                        jokeModel.setContent(joke.getContent());
                        jokeModel.setJokeId(joke.getJokeId());
                        jokeModel.setAddress(joke.getAddress());
                        jokeModel.setLatitudeLongitude(joke.getLatitudeLongitude());
                        jokeModel.setShowAddress(joke.getShowAddress());
                        jokeModels.add(jokeModel);
                    }
                    if ( !jokeModels.isEmpty() ) {
                        //清除本地的数据
                        clearAll();

                        FlowManager.getDatabase(JokesHelperDataBase.class)
                                .beginTransactionAsync(new ProcessModelTransaction.Builder<JokeModel>(
                                        BaseModel::insert
                                ).addAll(jokeModels).build())
                                .error(new Transaction.Error() {
                                    @Override
                                    public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                                        handler.sendEmptyMessage(0);
                                    }
                                })
                                .success(new Transaction.Success() {
                                    @Override
                                    public void onSuccess(@NonNull Transaction transaction) {
                                        handler.sendEmptyMessage(1);
                                    }
                                })
                                .build()
                                .execute();
                    }
                }
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            tv_tongbu_tips.setTextColor(Color.parseColor("#D32F2F"));
            tv_tongbu_tips.setText("数据同步失败");
        }
    }

    //数据对比
    private void dataCompare() {
        showDialog("正在对比...");
        String url = URLConstant.BASE_URL + "/admin/getjokesinfo";
        Map<String, String> params = new HashMap<>();
        myOkHttp.post()
                .url(url)
                .params(params)
                .tag(this)
                .enqueue(new GsonResponseHandler<TableInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        showToast("服务器错误");
                        stopDialog();
                    }

                    @Override
                    public void onProgress(long currentBytes, long totalBytes) {
                    }

                    @Override
                    public void onSuccess(int statusCode, TableInfo response) {
                        if ( response.getCode() == 1 ) {
                            //请求成功
                            TableInfo.DataBean data = response.getData();
                            if ( data != null ) {
                                List<TableInfo.DataBean.JokesListBean> jokesList = data.getJokesList();
                                if ( jokesList != null && !jokesList.isEmpty() ) {
                                    TableInfo.DataBean.JokesListBean jokesListBean = jokesList.get(0);
                                    int tableRows = jokesListBean.getTableRows();
                                    //获取本地存储的数据总和
                                    long count = SQLite.selectCountOf().from(JokeModel.class).count();
                                    if ( tableRows == count ) {
                                        //对比成功
                                        tv_compare_tips.setVisibility(View.VISIBLE);
                                        tv_compare_tips.setTextColor(Color.parseColor("#89c732"));
                                        tv_compare_tips.setText("对比成功  数据条数：" + tableRows + "条");
                                        mCompareFlag = 2;
                                    } else {
                                        //对比失败
                                        tv_compare_tips.setVisibility(View.VISIBLE);
                                        tv_compare_tips.setTextColor(Color.parseColor("#03A9F4"));
                                        tv_compare_tips.setText("对比失败  线上：" + tableRows + "条  本地：" + count + "条");
                                        mCompareFlag = 1;
                                    }
                                }
                            }
                        } else {
                            showToast(response.getMessage());
                        }
                        stopDialog();
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void notifyUpdate(NotifyUpdate event) {
        mCompareFlag = 0;
        tv_compare_tips.setVisibility(View.GONE);
        tv_tongbu_tips.setVisibility(View.GONE);
        mTv_02.performClick();
    }
}
