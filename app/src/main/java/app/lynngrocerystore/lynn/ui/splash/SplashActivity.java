package app.lynngrocerystore.lynn.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.lynngrocerystore.R;

import java.util.concurrent.TimeUnit;

import app.lynngrocerystore.lynn.ui.Login.LoginActivity;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static app.lynngrocerystore.util.Constants.SPLASH_TIMEOUT_IN_SECONDS;


public class SplashActivity extends AppCompatActivity {
    private Handler handler;
    private Disposable splashTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().post(new Runnable() {
            @Override
            public void run() {

            }
        });

        splashTimer = Single.timer(SPLASH_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long success) throws Exception {
                        Intent intent;

                        intent = new Intent(SplashActivity.this, LoginActivity.class);

                        SplashActivity.this.startActivity(intent);
                        SplashActivity.this.finish();
                    }
                });
    }
}
