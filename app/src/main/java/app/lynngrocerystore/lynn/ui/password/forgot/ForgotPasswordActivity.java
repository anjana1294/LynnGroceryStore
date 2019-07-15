package app.lynngrocerystore.lynn.ui.password.forgot;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.example.lynngrocerystore.R;

import app.lynngrocerystore.lynn.ui.Login.LoginActivity;
import app.lynngrocerystore.lynn.ui.password.otp.OtpActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPasswordActivity extends AppCompatActivity {
    @BindView(R.id.app_toolbar)
    Toolbar toolbar;



    @OnClick(R.id.btn_send)
    void onForgot() {
        startActivity(new Intent(this,OtpActivity .class));
        finish();
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Forgot Password");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
}
}
