package com.cretin.www.jokeshelp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cretin.www.jokeshelp.R;
import com.cretin.www.jokeshelp.app.URLConstant;
import com.cretin.www.jokeshelp.db.UserModel;
import com.cretin.www.jokeshelp.db.UserModel_Table;
import com.cretin.www.jokeshelp.model.UserResultModel;
import com.cretin.www.jokeshelp.model.event.NotifyUpdateUser;
import com.cretin.www.jokeshelp.utils.MyAlertDialog;
import com.cretin.www.jokeshelp.view.ItemButtomDecoration;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.squareup.picasso.Picasso;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddUserActivity extends BaseActivity {
    public static final String TAG = "AddUserActivity";
    private EditText mEt_phone;
    private TextView mTv_add;
    private TextView mTv_right;
    private android.support.v7.widget.RecyclerView recyclerview;

    private List list;

    private ListAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_user;
    }

    @Override
    public void initView() {
        setMainTitle("用户管理");
        bindViews();
    }

    @Override
    public void initData() {
        recyclerview.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.addItemDecoration(new ItemButtomDecoration(this, 5));
        list = new ArrayList();
        adapter = new ListAdapter(list);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.setNotDoAnimationCount(2);
        adapter.bindToRecyclerView(recyclerview);
        adapter.setEmptyView(R.layout.empty_view);
        recyclerview.setAdapter(adapter);

        getData();
    }

    //获取数据
    private void getData() {
        //获取本地存储的可用的用户
        try {
            List<UserModel> userList = new Select()
                    .from(UserModel.class)
                    .queryList();
            if ( userList != null && !userList.isEmpty() ) {
                list.clear();
                list.addAll(userList);
                mTv_right.setText("用户：" + list.size() + "个");
                adapter.notifyDataSetChanged();
            }
        } catch ( Exception e ) {

        }
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
    public void notifyUpdateUser(NotifyUpdateUser event){
        getData();
    }

    private void bindViews() {
        mEt_phone = ( EditText ) findViewById(R.id.et_phone);
        mTv_add = ( TextView ) findViewById(R.id.tv_add);
        recyclerview = ( android.support.v7.widget.RecyclerView ) findViewById(R.id.recyclerview);
        mTv_right = ( TextView ) findViewById(R.id.tv_right);
        mTv_right.setVisibility(View.VISIBLE);
        mTv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加用户
                addData();
            }
        });

        mTv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入下面的
                startActivity(new Intent(AddUserActivity.this, AutoAddUserActicity.class));
            }
        });
    }

    class ListAdapter extends BaseQuickAdapter<UserModel, BaseViewHolder> {

        public ListAdapter(List list) {
            super(R.layout.item_recycler_user, list);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final UserModel item) {
            if ( !TextUtils.isEmpty(item.getAvatar()) ) {
                Picasso.with(AddUserActivity.this).load(URLConstant.BASE_URL + "/" + item.getAvatar())
                        .into(( ImageView ) helper.getView(R.id.iv_avatar));
            } else {
                (( ImageView ) helper.getView(R.id.iv_avatar)).setImageResource(R.mipmap.default_avatar_oval);
            }
            helper.setText(R.id.tv_nickname, item.getUsername());
            helper.setText(R.id.tv_signature, item.getNickname());

            //移除
            helper.getView(R.id.tv_remove).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyAlertDialog myAlertDialog =
                            new MyAlertDialog(AddUserActivity.this, "温馨提示", "是否要移除该用户？");
                    myAlertDialog.setOnClickListener(new MyAlertDialog.OnPositiveClickListener() {
                        @Override
                        public void onPositiveClickListener(View v) {
                            //移除
                            if ( item.delete() ) {
                                list.remove(item);
                            }
                            mTv_right.setText("用户：" + list.size() + "个");
                            adapter.notifyDataSetChanged();
                        }
                    });
                    myAlertDialog.show();
                }
            });
        }
    }

    //添加一个用户到数据库
    private void addData() {
        String phone = mEt_phone.getText().toString().trim();
        if ( TextUtils.isEmpty(phone) ) {
            showToast("请先输入手机号");
            return;
        }

        try {
            UserModel userModels = new Select()
                    .from(UserModel.class)
                    .where(UserModel_Table.username.eq(phone))
                    .querySingle();
            if ( userModels != null ) {
                showToast("该手机号已添加，请不要重复添加");
                return;
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        showDialog("正在添加...");

        String url = URLConstant.BASE_URL + "/admin/finduser";
        Map<String, String> params = new HashMap<>();
        params.put("username", phone);
        myOkHttp.post()
                .url(url)
                .params(params)
                .tag(this)
                .enqueue(new GsonResponseHandler<UserResultModel>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        stopDialog();
                    }

                    @Override
                    public void onProgress(long currentBytes, long totalBytes) {

                    }

                    @Override
                    public void onSuccess(int statusCode, UserResultModel response) {
                        if ( response.getCode() == 1 ) {
                            UserResultModel.DataBean data = response.getData();
                            if ( data != null ) {
                                UserModel userModel = new UserModel();
                                userModel.setAvatar(data.getAvatar());
                                userModel.setNickname(data.getNickname());
                                userModel.setSex(data.getSex());
                                userModel.setUserId(data.getUserId());
                                userModel.setSignature(data.getSignature());
                                userModel.setUsername(data.getUsername());
                                userModel.save();
                                showToast("添加成功");
                                list.add(userModel);
                            }
                            mTv_right.setText("用户：" + list.size() + "个");
                            adapter.notifyDataSetChanged();
                            mEt_phone.setText("");
                        } else {
                            showToast(response.getMessage());
                        }
                        stopDialog();
                    }
                });
    }
}
