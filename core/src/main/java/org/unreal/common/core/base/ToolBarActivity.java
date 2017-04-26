package org.unreal.common.core.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.unreal.common.core.R;


/**
 * <b>类名称：</b> ToolBarActivity <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月05日 11:15<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public abstract class ToolBarActivity<P extends BasePresenter>
        extends BaseActivity<P>
        implements BaseView {

    public static final int TOOL_BAR_LAYOUT = R.layout.activity_tool_bar;

    /**
     * The Toolbar.
     */
    Toolbar toolbar;
    /**
     * The Content.
     */
    LinearLayout content;

    /**
     * The Tool bar title.
     */
    TextView toolBarTitle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(TOOL_BAR_LAYOUT);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        content = (LinearLayout) findViewById(R.id.content);
        toolBarTitle = (TextView) findViewById(R.id.toolbar_title);
        getLayoutInflater().inflate(bindLayout(), content, true);
        initToolbar();
        super.onCreate(savedInstanceState);
    }

    /**
     * Init toolbar.
     */
    protected void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolBarTitle.setText(setTitle());
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    /**
     * Hide navigation.
     */
    public void hideNavigation() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    /**
     * Gets tool bar.
     *
     * @return the tool bar
     */
    public Toolbar getToolbar() {
        return toolbar;
    }

    /**
     * Hide toolbar.
     */
    public void hideToolbar() {
        toolbar.setVisibility(View.GONE);
    }

    /**
     * Show toolbar.
     */
    public void showToolbar() {
        toolbar.setVisibility(View.VISIBLE);
    }


    /**
     * Sets title.
     *
     * @return the title
     */
    public abstract String setTitle();


    public void setTitle(String title) {
        toolBarTitle.setText(title);
    }

    @Override
    public boolean isCustomerView() {
        return false;
    }
}
