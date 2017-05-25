package it.com.ex;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import it.com.ex.exView.ParentViewHolder;

/**
 * parent的ViewHolder
 */
public class HolderParent extends ParentViewHolder {
    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;

    @NonNull
    private final ImageView mArrowImageView;
    private TextView mParentTitle;

    /**
     * @param itemView
     */
    public HolderParent(@NonNull View itemView) {
        super(itemView);
        mParentTitle = (TextView) itemView.findViewById(R.id.recipe_textview);
        mArrowImageView = (ImageView) itemView.findViewById(R.id.arrow_expand_imageview);
    }

    /**
     * 设置title文本
     * @param itemParent
     */
    public void bind(@NonNull ItemParent itemParent) {
        mParentTitle.setText(itemParent.getName());
    }

    @SuppressLint("NewApi")
    @Override
    public void setExpanded(boolean expanded) {
        super.setExpanded(expanded);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (expanded) {
                mArrowImageView.setRotation(ROTATED_POSITION);
            } else {
                mArrowImageView.setRotation(INITIAL_POSITION);
            }
        }
    }

    @Override
    public void onExpansionToggled(boolean expanded) {
        super.onExpansionToggled(expanded);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            RotateAnimation rotateAnimation;
            if (expanded) { // rotate clockwise
                 rotateAnimation = new RotateAnimation(ROTATED_POSITION,
                        INITIAL_POSITION,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            } else { // rotate counterclockwise
                rotateAnimation = new RotateAnimation(-1 * ROTATED_POSITION,
                        INITIAL_POSITION,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            }
            rotateAnimation.setDuration(200);
            rotateAnimation.setFillAfter(true);
            mArrowImageView.startAnimation(rotateAnimation);
        }
    }
}



