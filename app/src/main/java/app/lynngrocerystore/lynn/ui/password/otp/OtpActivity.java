package app.lynngrocerystore.lynn.ui.password.otp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.lynngrocerystore.R;

import app.lynngrocerystore.lynn.ui.password.forgot.ChangePassword;
import app.lynngrocerystore.lynn.ui.password.forgot.ForgotPasswordActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class OtpActivity extends AppCompatActivity {

    @BindView(R.id.app_toolbar)
    Toolbar toolbar;
    @OnClick(R.id.btn_resend)
    void onResendClick(){
        startActivity(new Intent(this, ChangePassword.class));
    }
    @BindView(R.id.ed_one)
    EditText edOne;
    @BindView(R.id.ed_two)
    EditText edTwo;
    @BindView(R.id.ed_three)
    EditText edThree;
    @BindView(R.id.ed_four)
    EditText edFour;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitle("ENTER OTP");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        edOne.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                if (s.length() ==1) {
                    edTwo.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }
        });

        edTwo.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    edThree.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }
        });
        edThree.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    edFour.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }
        });
    }

}
