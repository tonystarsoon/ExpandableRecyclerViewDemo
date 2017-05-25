package it.com.ex;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import it.com.ex.exView.ExpandableRecyclerAdapter;

/**
 * 垂直方向的实例
 */
public class ActivityVertical extends Activity {
    private RecyclerAdapter mAdapter;

    @NonNull
    public static Intent newIntent(Context context) {
        return new Intent(context, ActivityVertical.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_sample);

        //具体的子条目
        ItemChild beef = new ItemChild("路人甲", true);
        ItemChild cheese = new ItemChild("路人乙", true);
        ItemChild salsa = new ItemChild("路人丙", true);
        ItemChild tortilla = new ItemChild("路人丁", true);
        ItemChild ketchup = new ItemChild("路人戊", true);
        ItemChild bun = new ItemChild("路人己", true);

        //最外层的3组
        ItemParent taco = new ItemParent("初中", Arrays.asList(beef, cheese, salsa, tortilla));
        ItemParent quesadilla = new ItemParent("高中", Arrays.asList(cheese, tortilla));
        ItemParent burger = new ItemParent("大学", Arrays.asList(beef, cheese, ketchup, bun));

        //Adapter中接收到的
        final List<ItemParent> itemParents = Arrays.asList(taco, quesadilla, burger);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mAdapter = new RecyclerAdapter(this, itemParents);

        mAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @UiThread
            @Override
            public void onParentExpanded(int parentPosition) {
                ItemParent expandedItemParent = itemParents.get(parentPosition);
                String toastMsg = getResources().getString(R.string.expanded, expandedItemParent.getName());
            }

            @UiThread
            @Override
            public void onParentCollapsed(int parentPosition) {
                ItemParent collapsedItemParent = itemParents.get(parentPosition);
                String toastMsg = getResources().getString(R.string.collapsed, collapsedItemParent.getName());
            }
        });

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mAdapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mAdapter.onRestoreInstanceState(savedInstanceState);
    }
}
