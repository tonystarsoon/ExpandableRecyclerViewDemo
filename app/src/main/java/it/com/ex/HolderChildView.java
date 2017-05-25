package it.com.ex;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import it.com.ex.exView.ChildViewHolder;

/**
 * 子目录下的viewholder
 */
public class HolderChildView extends ChildViewHolder {
    private TextView mChildTextView;
    public HolderChildView(@NonNull View itemView) {
        super(itemView);
        mChildTextView = (TextView) itemView.findViewById(R.id.ingredient_textview);
    }

    public void bind(@NonNull ItemChild itemChild) {
        mChildTextView.setText(itemChild.getName());
    }
}
