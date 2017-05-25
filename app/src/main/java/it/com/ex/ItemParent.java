package it.com.ex;


import java.util.List;

import it.com.ex.exView.Parent;

public class ItemParent implements Parent<ItemChild> {

    private String mName;
    private List<ItemChild> mItemChildren;

    public ItemParent(String name, List<ItemChild> itemChildren) {
        mName = name;
        mItemChildren = itemChildren;
    }

    public String getName() {
        return mName;
    }

    @Override
    public List<ItemChild> getChildList() {
        return mItemChildren;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public ItemChild getIngredient(int position) {
        return mItemChildren.get(position);
    }

    public boolean isVegetarian() {
        for (ItemChild itemChild : mItemChildren) {
            if (!itemChild.ifShowIcon()) {
                return false;
            }
        }
        return true;
    }
}
