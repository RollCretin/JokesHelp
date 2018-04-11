package com.cretin.www.jokeshelp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cretin.www.jokeshelp.R;
import com.cretin.www.jokeshelp.app.URLConstant;
import com.cretin.www.jokeshelp.db.JokeModel;
import com.cretin.www.jokeshelp.db.JokeModel_Table;
import com.cretin.www.jokeshelp.db.JokesHelperDataBase;
import com.cretin.www.jokeshelp.db.UserModel;
import com.cretin.www.jokeshelp.db.UserModel_Table;
import com.cretin.www.jokeshelp.model.BaseResultModel;
import com.cretin.www.jokeshelp.model.JokeTextModel;
import com.cretin.www.jokeshelp.model.event.NotifyUpdate;
import com.cretin.www.jokeshelp.utils.MyAlertDialog;
import com.cretin.www.jokeshelp.utils.UUIDUtils;
import com.cretin.www.jokeshelp.view.ItemButtomDecoration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.hawk.Hawk;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;
import com.squareup.picasso.Picasso;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import cn.qqtheme.framework.picker.DateTimePicker;

public class AddTextJokeActivity extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "AddTextJokeActivity";
    private EditText mEt_page;
    private TextView mTv_03;
    private TextView mTv_01;
    private TextView mTv_04;
    private TextView mTv_02;
    private TextView mTv_05;
    private android.support.v7.widget.RecyclerView recyclerview;
    private TextView mTv_tips;
    private TextView mTv_tips_time;

    private List<JokeModel> list;
    private List<Boolean> listFlag;
    private ListAdapter adapter;

    //开始时间
    private String startTime;
    //结束时间
    private String endTime;

    private SimpleDateFormat simpleDateFormat;

    private Random random;

    private Gson gson;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_text_joke;
    }

    @Override
    public void initView() {
        setMainTitle("添加图片段子");
        bindViews();
    }

    @Override
    public void initData() {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        random = new Random();
        recyclerview.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.addItemDecoration(new ItemButtomDecoration(this, 5));
        list = new ArrayList();
        listFlag = new ArrayList();
        adapter = new ListAdapter(list);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.setNotDoAnimationCount(2);
        adapter.bindToRecyclerView(recyclerview);
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                MyAlertDialog myAlertDialog = new MyAlertDialog(AddTextJokeActivity.this, "提示", "是否删除改条记录？");
                myAlertDialog.setOnClickListener(new MyAlertDialog.OnPositiveClickListener() {
                    @Override
                    public void onPositiveClickListener(View v) {
                        list.remove(position);
                        listFlag.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
                myAlertDialog.show();
                return false;
            }
        });
        adapter.setEmptyView(R.layout.empty_view);
        recyclerview.setAdapter(adapter);
    }

    //获取数据
    private void getData() {
        String page = mEt_page.getText().toString().trim();
        if ( TextUtils.isEmpty(page) ) {
            showToast("请先输入页号");
            return;
        }
        showDialog("正在加载...");
        String url = "http://route.showapi.com/341-1?showapi_appid=6641&page=" + page + "&maxResult=10&showapi_sign=02e32d0ef52345288485d4ae63600eaa";
        myOkHttp.get()
                .url(url)
                .tag(this)
                .enqueue(new GsonResponseHandler<JokeTextModel>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        stopDialog();
                    }

                    @Override
                    public void onProgress(long currentBytes, long totalBytes) {
                    }

                    @Override
                    public void onSuccess(int statusCode, JokeTextModel response) {
                        //本地校验数据
                        analyseData(response);
                    }
                });
    }

    //分析数据
    private void analyseData(JokeTextModel response) {
        new AsyncTask<JokeTextModel, Void, Integer>() {

            @Override
            protected Integer doInBackground(JokeTextModel... jokeImgModels) {
                if ( jokeImgModels[0].getShowapi_res_body() != null
                        && jokeImgModels[0].getShowapi_res_body().getContentlist() != null ) {
                    //获取本地数据库中类型为2的所有数据
                    List<JokeModel> jokeModels = new Select()
                            .from(JokeModel.class)
                            .where(JokeModel_Table.type.eq(2))
                            .queryList();
                    //获取本地存储的可用的用户
                    List<UserModel> userList = new Select()
                            .from(UserModel.class)
                            .queryList();
                    if ( userList == null || userList.isEmpty() ) {
                        //没有可以选择的用户 请先添加用户再操作
                        return -1;
                    }
                    //如果本地的数据不为空 也要添加进来 否则也会重复
                    if ( !list.isEmpty() ) {
                        jokeModels.addAll(list);
                    }
                    for ( JokeTextModel.ShowapiResBodyBean.ContentlistBean joke :
                            jokeImgModels[0].getShowapi_res_body().getContentlist() ) {
                        String userId = userList.get(random.nextInt(userList.size())).getUserId();
                        JokeModel jokeModel = new JokeModel();
                        jokeModel.setImageUrl("");
                        String content = joke.getText().replaceAll("\\r\\n", "");
                        content = content.replaceAll("<br />", "");
                        content = content.replaceAll("<br>", "");
                        content = content.replaceAll("\\n", "");
                        content = content.replaceAll("\\r", "");
                        content = content.replaceAll("<p>", "");
                        content = content.replaceAll("</p>", "");
                        jokeModel.setContent(content);
                        jokeModel.setJokeId(UUIDUtils.getUuid());
                        jokeModel.setUserId(userId);
                        jokeModel.setType(1);
                        //日期的处理
                        try {
                            Date start = simpleDateFormat.parse(startTime);
                            Date end = simpleDateFormat.parse(endTime);
                            int left = ( int ) ((end.getTime() - start.getTime()) / 1000);
                            if ( left == 0 ) {
                                return -2;
                            }

                            if ( end.after(new Date()) ) {
                                return -3;
                            }
                            jokeModel.setUpdateTime(new Date(random.nextInt(left) * 1000 + start.getTime()));
                        } catch ( ParseException e ) {
                            e.printStackTrace();
                        }

                        if ( jokeModels.contains(jokeModel) ) {
                            //有这条数据 抛弃
                            list.add(jokeModel);
                            listFlag.add(false);
                        } else {
                            //没有这条数据 添加
                            list.add(jokeModel);
                            listFlag.add(true);
                        }
                        //将数据添加进来 否则会重复
                        jokeModels.add(jokeModel);
                    }
                }
                return 1;
            }

            @Override
            protected void onPostExecute(Integer aVoid) {
                super.onPostExecute(aVoid);
                if ( aVoid == -1 ) {
                    showToast("没有可以选择的用户，请先添加用户再操作");
                } else if ( aVoid == 1 ) {
                    //拉取数据条数 15条 匹配失败条数 10条 可上传条数 5条
                    int errNum = 0;
                    for ( Boolean b :
                            listFlag ) {
                        if ( !b ) {
                            errNum++;
                        }
                    }
                    mTv_tips.setText("拉取数据条数 " + list.size() +
                            "条 匹配失败条数 " + errNum + "条 可上传条数 " + (list.size() - errNum) + "条");

                    mEt_page.setText((Integer.parseInt(mEt_page.getText().toString()) + 1) + "");

                    adapter.notifyDataSetChanged();
                } else if ( aVoid == -2 ) {
                    showToast("开始时间和结束时间不能一样");
                } else if ( aVoid == -3 ) {
                    showToast("截止日期不能比当前时间大");
                }
                stopDialog();
            }
        }.execute(response);
    }

    private void bindViews() {
        mEt_page = ( EditText ) findViewById(R.id.et_page);
        mTv_03 = ( TextView ) findViewById(R.id.tv_03);
        mTv_01 = ( TextView ) findViewById(R.id.tv_01);
        mTv_04 = ( TextView ) findViewById(R.id.tv_04);
        mTv_02 = ( TextView ) findViewById(R.id.tv_02);
        mTv_05 = ( TextView ) findViewById(R.id.tv_05);
        recyclerview = ( RecyclerView ) findViewById(R.id.recyclerview);
        mTv_tips = ( TextView ) findViewById(R.id.tv_tips);
        mTv_tips_time = ( TextView ) findViewById(R.id.tv_tips_time);

        mTv_01.setOnClickListener(this);
        mTv_03.setOnClickListener(this);
        mTv_04.setOnClickListener(this);
        mTv_05.setOnClickListener(this);
        mTv_02.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId() ) {
            case R.id.tv_01:
                //时间
                mTv_tips_time.setVisibility(View.GONE);
                startTime = "";
                endTime = "";
                showToast("请设置开始时间");
                onYearMonthDayTimePicker();
                break;
            case R.id.tv_02:
                //用户设置
                startActivity(new Intent(this, AddUserActivity.class));
                break;
            case R.id.tv_03:
                //清空数据
                list.clear();
                listFlag.clear();
                mTv_tips.setText("拉取数据条数 " + 0 +
                        "条 匹配失败条数 " + 0 + "条 可上传条数 " + 0 + "条");
                adapter.notifyDataSetChanged();
                break;
            case R.id.tv_04:
                if ( TextUtils.isEmpty(startTime) || TextUtils.isEmpty(endTime) ) {
                    showToast("请先进行时间的设置");
                    return;
                }
                getData();
                break;
            case R.id.tv_05:
                //提交数据
                commit();
                break;
        }
    }

    //提交数据
    private void commit() {
        List<JokeModel> empList = new ArrayList<>();
        for ( int i = 0; i < list.size(); i++ ) {
            //获取可以提交的数据
            if ( listFlag.get(i) ) {
                empList.add(list.get(i));
            }
        }
        //如果数据不为空
        if ( !empList.isEmpty() ) {
            showDialog("正在提交...");
            String url = URLConstant.BASE_URL + "/admin/betchInsert";
            if ( Hawk.get("AUTO_LIKE", false) )
                url = URLConstant.BASE_URL + "/admin/extra/betchInsert";
            if ( gson == null )
                gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd HH:mm:ss")
                        .create();
            String json = gson.toJson(empList);
            myOkHttp.post()
                    .url(url)
                    .jsonParams(json)//与params不共存 以jsonParams优先
                    .tag(this)
                    .enqueue(new GsonResponseHandler<BaseResultModel>() {
                        @Override
                        public void onFailure(int statusCode, String error_msg) {
                            showToast("服务器异常");
                            stopDialog();
                        }

                        @Override
                        public void onSuccess(int statusCode, BaseResultModel response) {
                            if ( response.getCode() == 1 ) {
                                //成功
                                FlowManager.getDatabase(JokesHelperDataBase.class)
                                        .beginTransactionAsync(new ProcessModelTransaction.Builder<JokeModel>(
                                                BaseModel::insert
                                        ).addAll(empList).build())
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
                            } else {
                                showToast(response.getMessage());
                                stopDialog();
                            }
                        }
                    });
        } else {
            showToast("没有可提交的数据");
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            stopDialog();
            if ( msg.what == 0 ) {
                //失败
                showToast("提交成功，但是插入失败");
                EventBus.getDefault().post(new NotifyUpdate());
                finish();
            } else {
                //成功
                showToast("提交成功");
                EventBus.getDefault().post(new NotifyUpdate());
                finish();
            }
        }
    };

    //选择时间
    public void onYearMonthDayTimePicker() {
        DateTimePicker picker = new DateTimePicker(this, DateTimePicker.HOUR_24);
        Calendar now = new GregorianCalendar();
        now.setTime(new Date());
        int year = now.get(Calendar.YEAR);
        int month = (now.get(Calendar.MONTH) + 1);
        int day = now.get(Calendar.DAY_OF_MONTH);
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        picker.setTopPadding(15);
        picker.setDateRangeStart(year - 1, 1, 1);
        picker.setDateRangeEnd(year, month, day);
        picker.setTimeRangeStart(0, 0);
        picker.setTimeRangeEnd(23, 59);
        picker.setSelectedItem(year, month, day, hour, minute);
        picker.setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
            @Override
            public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                if ( TextUtils.isEmpty(startTime) ) {
                    //设置开始时间
                    startTime = year + "-" + month + "-" + day + " " + hour + ":" + minute;
                    showToast("请设置结束时间");
                    onYearMonthDayTimePicker();
                } else {
                    //设置结束时间
                    endTime = year + "-" + month + "-" + day + " " + hour + ":" + minute;
                    mTv_tips_time.setVisibility(View.VISIBLE);
                    mTv_tips_time.setText(startTime + " ~ " + endTime);
                }
            }
        });
        picker.show();
    }

    class ListAdapter extends BaseQuickAdapter<JokeModel, BaseViewHolder> {

        public ListAdapter(List list) {
            super(R.layout.item_recycler_add_joke, list);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final JokeModel item) {
            new AsyncTask<String, Void, UserModel>() {
                @Override
                protected UserModel doInBackground(String... strings) {
                    //查询用户信息
                    return new Select()
                            .from(UserModel.class)
                            .where(UserModel_Table.userId.eq(strings[0]))
                            .querySingle();
                }

                @Override
                protected void onPostExecute(UserModel userModel) {
                    super.onPostExecute(userModel);
                    if ( userModel != null ) {
                        if ( TextUtils.isEmpty(userModel.getAvatar()) ) {
                            helper.setImageResource(R.id.iv_uservavtar, R.mipmap.default_avatar_oval);
                        } else {
                            Picasso.with(AddTextJokeActivity.this).
                                    load(URLConstant.BASE_URL + "/" + userModel.getAvatar())
                                    .into(( ImageView ) helper.getView(R.id.iv_uservavtar));
                        }

                        String nick = userModel.getNickname();
                        if ( TextUtils.isEmpty(nick) ) {
                            nick = userModel.getUsername();
                        }
                        helper.setText(R.id.tv_nickname, nick);
                        helper.setText(R.id.tv_asign, userModel.getSignature());
                    }
                }
            }.execute(item.getUserId());

            helper.setVisible(R.id.tv_type, false);

            if ( listFlag.get(helper.getPosition()) ) {
                //可用
                helper.setText(R.id.tv_status, "此数据可用");
                (( TextView ) helper.getView(R.id.tv_status)).setTextColor(Color.parseColor("#6BAE36"));
            } else {
                //不可用
                helper.setText(R.id.tv_status, "此数据重复 不可用");
                (( TextView ) helper.getView(R.id.tv_status)).setTextColor(Color.parseColor("#f44336"));
            }

            helper.setText(R.id.richText, item.getContent());

            helper.setVisible(R.id.ll_img, false);
        }
    }
}

