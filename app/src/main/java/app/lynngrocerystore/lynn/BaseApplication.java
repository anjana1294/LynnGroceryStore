package app.lynngrocerystore.lynn;

import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.multidex.BuildConfig;
import android.support.multidex.MultiDexApplication;

import com.crashlytics.android.Crashlytics;
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import org.greenrobot.eventbus.EventBus;

import app.lynngrocerystore.lynn.ui.data.NetworkEventBusMessage;
import app.lynngrocerystore.util.CrashlyticsTree;
import io.fabric.sdk.android.Fabric;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

import static app.lynngrocerystore.util.Constants.EVENT_CONNECTIVITY_CONNECTED;
import static app.lynngrocerystore.util.Constants.EVENT_CONNECTIVITY_LOST;

public class BaseApplication  extends MultiDexApplication {
    private Disposable disposable;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                @Override
                protected void log(int priority, String tag, @NonNull String msg, Throwable t) {
                    super.log(priority, "lynn_grocerystore_logs_" + tag, msg, t);
                }
            });
        } else {
            Fabric.with(this, new Crashlytics());

            Timber.plant(new CrashlyticsTree());
        }

        if(disposable == null) {
            disposable = ReactiveNetwork.observeNetworkConnectivity(this)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
                    .subscribe(new Consumer<Connectivity>() {
                        @Override
                        public void accept(Connectivity connectivity) throws Exception {
                            if (connectivity.getState() != NetworkInfo.State.CONNECTED) {
                                EventBus.getDefault().post(new NetworkEventBusMessage(EVENT_CONNECTIVITY_LOST));
                            } else {
                                EventBus.getDefault().post(new NetworkEventBusMessage(EVENT_CONNECTIVITY_CONNECTED));
                            }
                        }
                    });
        }

      //  JodaTimeAndroid.init(this);
    }
}
