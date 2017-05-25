package it.com.ex;

public class ItemChild {
    private String mName;
    private boolean mIfShowIcon;

    public ItemChild(String name, boolean ifShowIcon) {
        mName = name;
        mIfShowIcon = ifShowIcon;
    }

    public String getName() {
        return mName;
    }

    public boolean ifShowIcon() {
        return mIfShowIcon;
    }
}
