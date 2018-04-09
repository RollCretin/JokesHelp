package com.cretin.www.jokeshelp.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cretin.www.jokeshelp.R;
import com.cretin.www.jokeshelp.app.URLConstant;
import com.cretin.www.jokeshelp.model.VerificationCodeModel;
import com.cretin.www.jokeshelp.view.ItemButtomDecoration;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VerificationCodeActivity extends BaseActivity {
    public static final String TAG = "VerificationCodeActivity";
    private List<VerificationCodeModel.DataBean.ListBean> list;

    private ListAdapter adapter;

    private RecyclerView recyclerview;

    private SwipeRefreshLayout swipeRefresh;

    private ImageView ivRight;
    private LinearLayout ll_find;
    private ImageView ivAdd;
    private EditText edPhone;

    @Override
    public int getLayoutId() {
        return R.layout.activity_verification_code;
    }

    private int currPage = 1;

    @Override
    public void initView() {
        recyclerview = ( RecyclerView ) findViewById(R.id.recyclerview);
        swipeRefresh = ( SwipeRefreshLayout ) findViewById(R.id.swipe_refresh);
        ivRight = ( ImageView ) findViewById(R.id.iv_right);
        ivAdd = ( ImageView ) findViewById(R.id.iv_add);
        edPhone = ( EditText ) findViewById(R.id.et_phone);
        ll_find = ( LinearLayout ) findViewById(R.id.ll_find);

        setMainTitle("验证码查询");

        swipeRefresh.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(1, edPhone.getText().toString().trim());
            }
        });

        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(R.mipmap.find);
        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( ll_find.getVisibility() == View.GONE ) {
                    ll_find.setVisibility(View.VISIBLE);
                } else {
                    ll_find.setVisibility(View.GONE);
                }
            }
        });

        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(1, edPhone.getText().toString().trim());
            }
        });
    }

    @Override
    public void initData() {
        recyclerview.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.addItemDecoration(new ItemButtomDecoration(this, 1));
        list = new ArrayList();
        adapter = new ListAdapter(list);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.setNotDoAnimationCount(2);
        adapter.bindToRecyclerView(recyclerview);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getData(currPage, edPhone.getText().toString().trim());
            }
        });
        adapter.setEmptyView(R.layout.empty_view);
        recyclerview.setAdapter(adapter);

        showDialog("加载中...");
        getData(1, edPhone.getText().toString().trim());
    }

    //获取数据
    private void getData(int page, String phone) {
        currPage = page + 1;
        String url = URLConstant.BASE_URL + "/admin/verificationcode";
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("phone", phone + "");
        myOkHttp.post()
                .url(url)
                .params(params)
                .tag(this)
                .enqueue(new GsonResponseHandler<VerificationCodeModel>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        stopDialog();
                        swipeRefresh.setRefreshing(false);
                    }

                    @Override
                    public void onProgress(long currentBytes, long totalBytes) {
                    }

                    @Override
                    public void onSuccess(int statusCode, VerificationCodeModel response) {
                        stopDialog();
                        swipeRefresh.setRefreshing(false);
                        if ( response.getCode() == 1 ) {
                            if ( page == 1 ) {
                                list.clear();
                            }
                            VerificationCodeModel.DataBean data = response.getData();
                            if ( data != null ) {
                                List<VerificationCodeModel.DataBean.ListBean> jokesList = data.getList();
                                if ( jokesList != null ) {
                                    list.addAll(jokesList);
                                    if ( jokesList.size() < 10 ) {
                                        adapter.loadMoreEnd();
                                    } else {
                                        adapter.loadMoreComplete();
                                    }
                                }
                            } else {
                                adapter.loadMoreFail();
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(VerificationCodeActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    class ListAdapter extends BaseQuickAdapter<VerificationCodeModel.DataBean.ListBean, BaseViewHolder> {
        private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        public ListAdapter(List list) {
            super(R.layout.item_recycler_verification_code, list);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final VerificationCodeModel.DataBean.ListBean item) {
            helper.setText(R.id.tv_type, item.getSmstype().equals("regist") ? "注册" : "重置登录密码");
            helper.setText(R.id.tv_code, "验证码：" + item.getSmscode());
            helper.setText(R.id.tv_time, simpleDateFormat.format(new Date(item.getUpdateTime())));
            helper.setText(R.id.tv_user, item.getSmstel());
        }
    }
}
