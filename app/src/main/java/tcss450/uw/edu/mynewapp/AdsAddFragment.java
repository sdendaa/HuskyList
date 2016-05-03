package tcss450.uw.edu.mynewapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdsAddFragment extends Fragment {
    private EditText mItemIdEditText;
    private TextView mItemTitleEditText;
    private TextView mItemPriceEditText;
    private TextView mItemConditionEditText;
    private TextView mItemDescriptionEditText;
    private TextView mItemCategoryEditText;
    private TextView mItemSalerLocationEditText;

    private final static String Ads_ADD_URL
            = "http://cssgate.insttech.washington.edu/~sdendaa/addCourse.php?";


    public AdsAddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ads_add, container, false);
    }

}
