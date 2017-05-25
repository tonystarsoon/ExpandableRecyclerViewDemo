package it.com.ex;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import it.com.ex.exView.ExpandableRecyclerAdapter;


/**
 * recyclerview的适配器
 */
public class RecyclerAdapter extends ExpandableRecyclerAdapter<ItemParent, ItemChild, HolderParent, HolderChildView> {
    private static final int PARENT_VEGETARIAN = 0;
    private static final int PARENT_NORMAL = 1;
    private static final int CHILD_VEGETARIAN = 2;
    private static final int CHILD_NORMAL = 3;

    private LayoutInflater mInflater;
    private List<ItemParent> mItemParentList;

    public RecyclerAdapter(Context context, @NonNull List<ItemParent> itemParentList) {
        super(itemParentList);
        mItemParentList = itemParentList;
        mInflater = LayoutInflater.from(context);
    }

    @UiThread
    @NonNull
    @Override
    public HolderParent onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View recipeView;
        switch (viewType) {
            default:
            case PARENT_NORMAL:
                recipeView = mInflater.inflate(R.layout.recipe_view, parentViewGroup, false);
                break;
            case PARENT_VEGETARIAN:
                recipeView = mInflater.inflate(R.layout.vegetarian_recipe_view, parentViewGroup, false);
                break;
        }
        return new HolderParent(recipeView);
    }

    @UiThread
    @NonNull
    @Override
    public HolderChildView onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View ingredientView;
        switch (viewType) {
            default:
            case CHILD_NORMAL:
                ingredientView = mInflater.inflate(R.layout.ingredient_view, childViewGroup, false);
                break;
            case CHILD_VEGETARIAN:
                ingredientView = mInflater.inflate(R.layout.vegetarian_ingredient_view, childViewGroup, false);
                break;
        }
        return new HolderChildView(ingredientView);
    }

    /**
     * parent的ViewHolder
     * @param holderParent
     * @param parentPosition The position of the parent to bind
     * @param itemParent
     */
    @UiThread
    @Override
    public void onBindParentViewHolder(@NonNull HolderParent holderParent,
                                       int parentPosition,
                                       @NonNull ItemParent itemParent) {
        holderParent.bind(itemParent);
    }

    /**
     * child的ViewHolder
     * @param holderChildView
     * @param parentPosition The position of the parent that contains the child to bind
     * @param childPosition The position of the child to bind
     * @param itemChild
     */
    @UiThread
    @Override
    public void onBindChildViewHolder(@NonNull HolderChildView holderChildView,
                                      int parentPosition, int childPosition,
                                      @NonNull ItemChild itemChild) {
        holderChildView.bind(itemChild);
    }

    /**
     * 获取父条目的类型
     * @param parentPosition The index of the parent to query
     * @return
     */
    @Override
    public int getParentViewType(int parentPosition) {
        if (mItemParentList.get(parentPosition).isVegetarian()) {
            return PARENT_VEGETARIAN;
        } else {
            return PARENT_NORMAL;
        }
    }

    /**
     * 获取子条目的类型
     * @param parentPosition The index of the parent continaing the child to query
     * @param childPosition The index of the child within the parent to query
     * @return
     */
    @Override
    public int getChildViewType(int parentPosition, int childPosition) {
        ItemChild itemChild = mItemParentList.get(parentPosition).getIngredient(childPosition);
        if (itemChild.ifShowIcon()) {
            return CHILD_VEGETARIAN;
        } else {
            return CHILD_NORMAL;
        }
    }

    @Override
    public boolean isParentViewType(int viewType) {
        return viewType == PARENT_VEGETARIAN || viewType == PARENT_NORMAL;
    }
}
