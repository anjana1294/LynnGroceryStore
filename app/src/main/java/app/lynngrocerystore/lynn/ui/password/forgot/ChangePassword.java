package app.lynngrocerystore.lynn.ui.password.forgot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.lynngrocerystore.R;

import app.lynngrocerystore.lynn.ui.Login.LoginActivity;
import app.lynngrocerystore.lynn.ui.password.otp.OtpActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangePassword extends AppCompatActivity {
    @BindView(R.id.app_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Change Password");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
