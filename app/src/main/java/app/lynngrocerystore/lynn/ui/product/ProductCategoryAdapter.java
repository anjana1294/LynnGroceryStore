package app.lynngrocerystore.lynn.ui.product;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lynngrocerystore.R;

import java.util.ArrayList;

import app.lynngrocerystore.lynn.ui.product.model.ProductCategory;
import butterknife.BindView;

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.MyViewHolder> {
    Context context;

    ArrayList<ProductCategory> productCategories;

    public ProductCategoryAdapter(Context context, ArrayList<ProductCategory> productCategories) {
        this.context = context;
        this.productCategories = productCategories;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_product_category, viewGroup, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
       /* try {
            picasso.load(productCategories.get(i).getCategoryName())
                    .placeholder(R.drawable.fruits_img)
                    .error(R.drawable.fruits_img)
                    .into(myViewHolder.iv_category_image);
        } catch (Exception e) {
            Timber.e(e);
            picasso.load(R.drawable.fruits_img).fit().into(myViewHolder.iv_category_image);
        }*/
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_category_image)
        ImageView iv_category_image;
        @BindView(R.id.tv_category_name)
        TextView tv_category_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
