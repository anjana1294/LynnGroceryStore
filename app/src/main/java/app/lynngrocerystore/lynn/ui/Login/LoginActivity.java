package app.lynngrocerystore.lynn.ui.Login;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import com.example.lynngrocerystore.R;
import app.lynngrocerystore.lynn.ui.base.BaseDialog;
import app.lynngrocerystore.lynn.ui.custom.SpannyText;
import app.lynngrocerystore.lynn.ui.home.HomeActivity;
import app.lynngrocerystore.lynn.ui.password.forgot.ForgotPasswordActivity;
import app.lynngrocerystore.lynn.ui.signup.SignupActivity;
import app.lynngrocerystore.util.TextviewReg;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.tv_signup)
    TextviewReg tv_sign_up;

    @OnClick(R.id.forgot_password)
    void onForgot()
    {
        startActivity(new Intent(this, ForgotPasswordActivity.class));
    }


    @OnClick(R.id.tv_signup)
    void onSignUp() {
        startActivity(new Intent(this, SignupActivity.class));
    }

    @OnClick(R.id.btn_login)
    void onLogin() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        SpannyText spannyText = new SpannyText(getResources().getString(R.string.donot_have_any_account), new ForegroundColorSpan(
                ContextCompat.getColor(this, R.color.colorDarkGrey))).append(" Sign Up",
                new ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorTextColor)), new StyleSpan(Typeface.BOLD));


        tv_sign_up.setText(spannyText);
    }


}
