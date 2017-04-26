package org.unreal.widget.tablehost;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.unreal.widget.R;

/**
 * <b>类名称：</b> IconTitleTableIndicator <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年03月31日 17:22<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class IconTitleTableIndicator extends LinearLayout {

    private ImageView iconIv;
    private TextView titleTv;
    private int normalIcon;
    private int pressedIcon;
    private int normalColor;
    private int pressedColor;
    private String title;

    public IconTitleTableIndicator(Context context) {
        this(context, null);
    }

    public IconTitleTableIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconTitleTableIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.widget_icon_title_tab, this, true);
        initView();
    }

    private void initView() {
        iconIv = (ImageView) findViewById(R.id.iconIv);
        titleTv = (TextView) findViewById(R.id.titleTv);
        checkParam();
    }

    public IconTitleTableIndicator setIcon(@DrawableRes int normalIcon ,
                        @DrawableRes int pressedIcon){
        this.normalIcon = normalIcon;
        this.pressedIcon = pressedIcon;
        iconIv.setImageResource(normalIcon);
        return this;
    }

    public IconTitleTableIndicator setTitle(@ColorInt int normalColor,
                         @ColorInt int pressedColor,
                         String title){
        this.normalColor = normalColor;
        this.pressedColor = pressedColor;
        this.title = title;
        titleTv.setText(title);
        titleTv.setTextColor(normalColor);
        return this;
    }

    public void clickIndicator(){
        checkParam();
        iconIv.setImageResource(pressedIcon);
        titleTv.setTextColor(pressedColor);
    }

    private void checkParam() {
        if(normalIcon == 0){
            normalIcon = R.mipmap.tab_default_normal;
            iconIv.setImageResource(normalIcon);
        }
        if(pressedIcon == 0){
            pressedIcon = R.mipmap.tab_default_pressed;
        }
        if(normalColor == 0){
            normalColor = Color.BLACK;
            titleTv.setTextColor(normalColor);
        }
        if(pressedColor == 0){
            pressedColor = Color.GREEN;
        }
        if(title == null || "".equals(title)){
            title = "标题";
            titleTv.setText(title);
        }

    }

    public void resetView(){
        checkParam();
        iconIv.setImageResource(normalIcon);
        titleTv.setTextColor(normalColor);
    }
}
