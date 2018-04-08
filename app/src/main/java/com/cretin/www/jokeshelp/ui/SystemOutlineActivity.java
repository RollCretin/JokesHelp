package com.cretin.www.jokeshelp.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cretin.www.jokeshelp.R;
import com.cretin.www.jokeshelp.app.URLConstant;
import com.cretin.www.jokeshelp.model.TableInfo;
import com.cretin.www.jokeshelp.view.ItemButtomDecoration;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemOutlineActivity extends BaseActivity {
    public static final String TAG = "SystemOutlineActivity";
    private List<TableInfo.DataBean.JokesListBean> list;

    private ListAdapter adapter;

    private RecyclerView recyclerview;

    private SwipeRefreshLayout swipeRefresh;

    @Override
    public int getLayoutId() {
        return R.layout.activity_system_outline;
    }

    @Override
    public void initView() {
        recyclerview = ( RecyclerView ) findViewById(R.id.recyclerview);
        swipeRefresh = ( SwipeRefreshLayout ) findViewById(R.id.swipe_refresh);

        setMainTitle("系统概览");

        swipeRefresh.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
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
        adapter.setEmptyView(R.layout.empty_view);
        recyclerview.setAdapter(adapter);

        showDialog("加载中...");
        getData();
    }

    //获取数据
    private void getData() {
        String url = URLConstant.BASE_URL + "/admin/maininfo";
        Map<String, String> params = new HashMap<>();
        myOkHttp.post()
                .url(url)
                .params(params)
                .tag(this)
                .enqueue(new GsonResponseHandler<TableInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        stopDialog();
                        swipeRefresh.setRefreshing(false);
                    }

                    @Override
                    public void onProgress(long currentBytes, long totalBytes) {
                    }

                    @Override
                    public void onSuccess(int statusCode, TableInfo response) {
                        stopDialog();
                        swipeRefresh.setRefreshing(false);
                        if ( response.getCode() == 1 ) {
                            list.clear();
                            TableInfo.DataBean data = response.getData();
                            if ( data != null ) {
                                List<TableInfo.DataBean.JokesListBean> jokesList = data.getJokesList();
                                if ( jokesList != null ) {
                                    list.addAll(jokesList);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(SystemOutlineActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    class ListAdapter extends BaseQuickAdapter<TableInfo.DataBean.JokesListBean, BaseViewHolder> {

        public ListAdapter(List list) {
            super(R.layout.item_recycler_system_outline, list);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final TableInfo.DataBean.JokesListBean item) {
            helper.setVisible(R.id.tv_num, false);
            helper.setText(R.id.tv_name, "表名：" + item.getTableName());
            helper.setText(R.id.tv_type, "数据：" + item.getTableRows() + " 条");
        }
    }
}
