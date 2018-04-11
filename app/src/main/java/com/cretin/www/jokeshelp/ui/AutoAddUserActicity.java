package com.cretin.www.jokeshelp.ui;

import android.graphics.Color;
import android.support.annotation.NonNull;
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
import com.cretin.www.jokeshelp.db.JokesHelperDataBase;
import com.cretin.www.jokeshelp.db.UserModel;
import com.cretin.www.jokeshelp.db.UserModel_Table;
import com.cretin.www.jokeshelp.model.AutoAdduserModel;
import com.cretin.www.jokeshelp.model.event.NotifyUpdateUser;
import com.cretin.www.jokeshelp.utils.MyAlertDialog;
import com.cretin.www.jokeshelp.view.ItemButtomDecoration;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;
import com.squareup.picasso.Picasso;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutoAddUserActicity extends BaseActivity {
    public static final String TAG = "AutoAddUserActicity";
    private EditText mEt_phone;
    private TextView mTv_add;
    private TextView mTv_right;
    private android.support.v7.widget.RecyclerView recyclerview;

    private List<UserModel> list;

    private ListAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_auto_add_user_acticity;
    }

    @Override
    public void initView() {
        setMainTitle("批量用户管理");
        bindViews();
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
                List<UserModel> listInset = new ArrayList<>();
                for ( UserModel user :
                        list ) {
                    UserModel userModel = new Select().from(UserModel.class).where(UserModel_Table.userId
                            .eq(user.getUserId())).querySingle();
                    if ( userModel == null ) {
                        //可添加
                        listInset.add(user);
                    }
                }
                //批量插入
                MyAlertDialog myAlertDialog =
                        new MyAlertDialog(AutoAddUserActicity.this,
                                "温馨提示", "是否要批量插入数据(" + listInset.size() + "条)？ ");
                myAlertDialog.setOnClickListener(new MyAlertDialog.OnPositiveClickListener() {
                    @Override
                    public void onPositiveClickListener(View v) {
                        insert(listInset);
                    }
                });
                myAlertDialog.show();
            }
        });
    }

    //批量插入数据
    private void insert(List<UserModel> listInset) {
        showDialog("批量插入...");
        FlowManager.getDatabase(JokesHelperDataBase.class)
                .beginTransactionAsync(new ProcessModelTransaction.Builder<UserModel>(
                        BaseModel::insert
                ).addAll(listInset).build())
                .error(new Transaction.Error() {
                    @Override
                    public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                        showToast("批量插入失败");
                        stopDialog();
                    }
                })
                .success(new Transaction.Success() {
                    @Override
                    public void onSuccess(@NonNull Transaction transaction) {
                        showToast("批量插入成功" + listInset.size() + "条");
                        stopDialog();
                        EventBus.getDefault().post(new NotifyUpdateUser());
                        finish();
                    }
                })
                .build()
                .execute();
    }

    class ListAdapter extends BaseQuickAdapter<UserModel, BaseViewHolder> {

        public ListAdapter(List list) {
            super(R.layout.item_recycler_user, list);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final UserModel item) {
            if ( !TextUtils.isEmpty(item.getAvatar()) ) {
                Picasso.with(AutoAddUserActicity.this).load(URLConstant.BASE_URL + "/" + item.getAvatar())
                        .into(( ImageView ) helper.getView(R.id.iv_avatar));
            } else {
                (( ImageView ) helper.getView(R.id.iv_avatar)).setImageResource(R.mipmap.default_avatar_oval);
            }
            helper.setText(R.id.tv_nickname, item.getUsername());
            helper.setText(R.id.tv_signature, item.getNickname());
            helper.setBackgroundColor(R.id.tv_remove, Color.parseColor("#ffffff"));
            (( TextView ) helper.getView(R.id.tv_remove)).setTextSize(14);

            UserModel userModel = new Select().from(UserModel.class).where(UserModel_Table.userId
                    .eq(item.getUserId())).querySingle();
            if ( userModel == null ) {
                //没有 可以添加
                helper.setText(R.id.tv_remove, "可添加");
                helper.setTextColor(R.id.tv_remove, Color.parseColor("#89c732"));
            } else {
                //有 不可以添加
                helper.setText(R.id.tv_remove, "已存在");
                helper.setTextColor(R.id.tv_remove, Color.parseColor("#f46950"));
            }
        }
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
    }


    //添加一个用户到数据库
    private void addData() {
        String phone = mEt_phone.getText().toString().trim();
        if ( TextUtils.isEmpty(phone) ) {
            showToast("请先输入手机号");
            return;
        }

        showDialog("正在查询...");

        String url = URLConstant.BASE_URL + "/admin/finduserlike";
        Map<String, String> params = new HashMap<>();
        params.put("username", phone);
        myOkHttp.post()
                .url(url)
                .params(params)
                .tag(this)
                .enqueue(new GsonResponseHandler<AutoAdduserModel>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        stopDialog();
                    }

                    @Override
                    public void onProgress(long currentBytes, long totalBytes) {

                    }

                    @Override
                    public void onSuccess(int statusCode, AutoAdduserModel response) {
                        if ( response.getCode() == 1 ) {
                            AutoAdduserModel.DataBean data = response.getData();
                            if ( data != null ) {
                                list.clear();
                                list.addAll(data.getList());
                            }
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
