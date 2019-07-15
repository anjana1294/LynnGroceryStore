package app.lynngrocerystore.lynn.ui.signup;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lynngrocerystore.R;

import app.lynngrocerystore.lynn.ui.Login.LoginActivity;
import app.lynngrocerystore.lynn.ui.custom.SpannyText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.tv_login)
    TextView tv_login;

    @BindView(R.id.cb_tnc)
    CheckBox tnc;

    @OnClick(R.id.tv_login)
    void tv_login() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        tnc.setSaveEnabled(false);
        CharSequence sequence = Html.fromHtml(getString(R.string.cb_tnc_msg));
        SpannableStringBuilder strBuilder = new SpannableStringBuilder(sequence);
        URLSpan[] urls = strBuilder.getSpans(0, sequence.length(), URLSpan.class);
        for (URLSpan span : urls) {
            makeLinkClickable(strBuilder, span);
        }
        tnc.setText(strBuilder);
        tnc.setMovementMethod(LinkMovementMethod.getInstance());

        SpannyText spannyText = new SpannyText(getResources().getString(R.string.already_have_an_account), new ForegroundColorSpan(
                ContextCompat.getColor(this, R.color.colorDarkGrey))).append(" Login",
                new ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorTextColor)), new StyleSpan(Typeface.BOLD));
        tv_login.setText(spannyText);
    }
    protected void makeLinkClickable(SpannableStringBuilder strBuilder, final URLSpan span) {
        int start = strBuilder.getSpanStart(span);
        int end = strBuilder.getSpanEnd(span);
        int flags = strBuilder.getSpanFlags(span);
        ClickableSpan clickable = new ClickableSpan() {
            public void onClick(View view) {
                Toast.makeText(SignupActivity.this, "Terms and condition", Toast.LENGTH_SHORT).show();
            }
        };
        strBuilder.setSpan(clickable, start, end, flags);
        strBuilder.removeSpan(span);
    }
}

