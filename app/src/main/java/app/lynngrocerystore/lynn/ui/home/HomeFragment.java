package app.lynngrocerystore.lynn.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lynngrocerystore.R;

import app.lynngrocerystore.lynn.ui.base.BaseFragment;
import app.lynngrocerystore.lynn.ui.product.ProductCategoryAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragment {

    private View rootview;
    private Unbinder unbinder;
    Callback callback;

    @BindView(R.id.recycler_view)
    RecyclerView rvCategories;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, rootview);
        ProductCategoryAdapter productCategoryAdapter = new ProductCategoryAdapter(context(), null);
        rvCategories.setLayoutManager(new GridLayoutManager(context(), 3));
        rvCategories.setHasFixedSize(true);
        rvCategories.setAdapter(productCategoryAdapter);
        return rootview;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof Callback)
//            callback = (Callback) context;
    }

    @Override
    public void onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu();
        unbinder.unbind();
    }


    interface Callback {

    }
}
