package app.lynngrocerystore.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.EditText;

@SuppressLint("AppCompatCustomView")
public class EdittextViewReg extends EditText {

    public EdittextViewReg(Context context) {
        super(context);
        init();
    }

    public EdittextViewReg(Context context, AttributeSet attributeset) {
        super(context, attributeset);
        init();
    }

    public EdittextViewReg(Context context, AttributeSet attributeset, int i) {
        super(context, attributeset, i);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public EdittextViewReg(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            //setTypeface(Typeface.createFromAsset(getContext().getAssets(), "roboto_regular.ttf"));
            setTypeface(Typeface.createFromAsset(getContext().getAssets(), "opensans_regular.ttf"));
        }
    }
}
