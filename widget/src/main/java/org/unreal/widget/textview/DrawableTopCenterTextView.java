package org.unreal.widget.textview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * <b>类名称：</b> DrawableTopCenterTextView <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 16-11-15 上午11:19<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

public class DrawableTopCenterTextView extends AppCompatTextView {

    public DrawableTopCenterTextView(Context context) {
        super(context);
    }

    public DrawableTopCenterTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawableTopCenterTextView(Context context, AttributeSet attrs,
                                     int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        if(drawables != null){
            Drawable drawableTop = drawables[1];
            if(drawableTop != null){
                Paint.FontMetrics fm = getPaint().getFontMetrics();
                int textHeight = (int) Math.ceil(fm.descent - fm.ascent);
                int drawablePadding = getCompoundDrawablePadding();
                int drawableHeight = drawableTop.getIntrinsicHeight();
                float bodyHeight = textHeight + drawableHeight + drawablePadding;
                canvas.translate(0, (getHeight() - bodyHeight) / 2);
            }
        }
        super.onDraw(canvas);
    }


}