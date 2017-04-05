package org.unreal.widget.textview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * <b>类名称：</b> DrawableLeftCenterTextView <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 16-11-15 上午11:19<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

public class DrawableLeftCenterTextView extends AppCompatTextView {

    public DrawableLeftCenterTextView(Context context) {
        super(context);
    }

    public DrawableLeftCenterTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawableLeftCenterTextView(Context context, AttributeSet attrs,
                                      int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        if(drawables != null){
            Drawable drawableLeft = drawables[0];
            if(drawableLeft != null){
                float textWidth = getPaint().measureText(getText().toString());
                int drawablePadding = getCompoundDrawablePadding();
                int drawableWidth = drawableLeft.getIntrinsicWidth();
                float bodyWidth = textWidth + drawableWidth + drawablePadding;
                canvas.translate((getWidth() - bodyWidth) / 2, 0);
            }
        }
        super.onDraw(canvas);
    }


}