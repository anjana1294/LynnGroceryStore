package app.lynngrocerystore.lynn.ui.custom.country;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.lynngrocerystore.R;

import java.util.ArrayList;
import java.util.List;

class CountryCodeAdapter extends RecyclerView.Adapter<CountryCodeAdapter.CountryCodeViewHolder> {

    private List<Country> filteredCountries = null, masterCountries = null;
    private TextView textView_noResult;
    private CountryCodePicker codePicker;
    private LayoutInflater inflater;
    private EditText editText_search;
    private Dialog dialog;
    private Context context;

    CountryCodeAdapter(Context context, List<Country> countries, CountryCodePicker codePicker, final EditText editText_search, TextView textView_noResult, Dialog dialog) {
        this.context = context;
        this.masterCountries = countries;
        this.codePicker = codePicker;
        this.dialog = dialog;
        this.textView_noResult = textView_noResult;
        this.editText_search = editText_search;
        this.inflater = LayoutInflater.from(context);
        this.filteredCountries = getFilteredCountries("");
        setSearchBar();
    }

    private void setSearchBar() {
        if (codePicker.isSelectionDialogShowSearch()) {
            setTextWatcher();
        } else {
            editText_search.setVisibility(View.GONE);
        }
    }

    /**
     * add textChangeListener, to apply new query each time editText get text changed.
     */
    private void setTextWatcher() {
        if (this.editText_search != null) {
            this.editText_search.addTextChangedListener(new TextWatcher() {

                @Override
                public void afterTextChanged(Editable s) {
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    applyQuery(s.toString());
                }
            });

            if (codePicker.isKeyboardAutoPopOnSearch()) {
                InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager != null) {
                    inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                }
            }
        }
    }

    /**
     * Filter country list for given keyWord / query.
     * Lists all countries that contains @param query in country's name, name code or phone code.
     *
     * @param query : text to match against country name, name code or phone code
     */
    private void applyQuery(String query) {


        textView_noResult.setVisibility(View.GONE);
        query = query.toLowerCase();

        //if query started from "+" ignore it
        if (query.length() > 0 && query.charAt(0) == '+') {
            query = query.substring(1);
        }

        filteredCountries = getFilteredCountries(query);

        if (filteredCountries.size() == 0) {
            textView_noResult.setVisibility(View.VISIBLE);
        }
        notifyDataSetChanged();
    }

    private List<Country> getFilteredCountries(String query) {
        List<Country> tempCountryList = new ArrayList<Country>();
        if (codePicker.preferredCountries != null && codePicker.preferredCountries.size() > 0) {
            for (Country country : codePicker.preferredCountries) {
                if (country.isEligibleForQuery(query)) {
                    tempCountryList.add(country);
                }
            }

            if (tempCountryList.size() > 0) { //means at least one preferred country is added.
                Country divider = null;
                tempCountryList.add(divider);
            }
        }

        for (Country country : masterCountries) {
            if (country.isEligibleForQuery(query)) {
                tempCountryList.add(country);
            }
        }
        return tempCountryList;
    }

    @Override
    public CountryCodeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View rootView = inflater.inflate(R.layout.item_layout_country_picker_list, viewGroup, false);
        CountryCodeViewHolder viewHolder = new CountryCodeViewHolder(rootView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CountryCodeViewHolder countryCodeViewHolder, final int i) {
        countryCodeViewHolder.setCountry(filteredCountries.get(i));
        countryCodeViewHolder.getMainView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codePicker.setSelectedCountry(filteredCountries.get(i));
                if (view != null && filteredCountries.get(i) != null) {
                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    dialog.dismiss();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredCountries.size();
    }

    class CountryCodeViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout_main;
        TextView textView_name, textView_code;
        ImageView imageViewFlag;
        LinearLayout linearFlagHolder;
        View divider;

        CountryCodeViewHolder(View itemView) {
            super(itemView);
            relativeLayout_main = (RelativeLayout) itemView;
            textView_name = (TextView) relativeLayout_main.findViewById(R.id.tv_country_name);
            textView_code = (TextView) relativeLayout_main.findViewById(R.id.tv_country_code);
            imageViewFlag = (ImageView) relativeLayout_main.findViewById(R.id.iv_country_flag);
            linearFlagHolder = (LinearLayout) relativeLayout_main.findViewById(R.id.layout_flag_holder);
            divider = relativeLayout_main.findViewById(R.id.divider);
        }

        public void setCountry(Country country) {
            if (country != null) {
                divider.setVisibility(View.GONE);
                textView_name.setVisibility(View.VISIBLE);
                textView_code.setVisibility(View.VISIBLE);
                linearFlagHolder.setVisibility(View.VISIBLE);
                textView_name.setText(country.getName() + " (" + country.getNameCode().toUpperCase() + ")");
                textView_code.setText("+" + country.getPhoneCode());
                imageViewFlag.setImageResource(country.getFlagID());
            } else {
                divider.setVisibility(View.VISIBLE);
                textView_name.setVisibility(View.GONE);
                textView_code.setVisibility(View.GONE);
                linearFlagHolder.setVisibility(View.GONE);
            }
        }

        private RelativeLayout getMainView() {
            return relativeLayout_main;
        }
    }
}