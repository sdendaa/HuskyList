package tcss450.uw.edu.mynewapp.model;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.String;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class BookContent implements Serializable {
    private int mItem_id;
    private String mItem_title;
    private String mItem_Description;
    private String mItem_condition;
    private double mItem_price;
    private String mSaler_Location;
    private String mItem_category;

    public static final String Item_title = "ItemTitle", Item_Description = "ItemDecs", Item_Condition = "ItemCond",
            saler_Location = "SalerLocat", Item_category = "ItemCategory";

    public BookContent(int itemId, String ItemTitle, String ItemDecs, String ItemCond,
                              double ItemPrice, String Itemcategory, String salerLocation) {
        setItemId(itemId);
        SetItemTitle(ItemTitle);
        setItemDescription(ItemDecs);
        setItemCondition(ItemCond);
        setItemCategoty(Itemcategory);
        setSalerLocation(salerLocation);
        setItemPrice(ItemPrice);

    }

    public void setItemId(Integer ItemId) {
        if(ItemId == null)
            throw new IllegalArgumentException("Course Id must be supplies");

        mItem_id = ItemId;

    }

    public void SetItemTitle(String ItemTilte) {
        if(ItemTilte == null)
            throw new IllegalArgumentException("Course Id must be supplies");
        if(ItemTilte.length() < 5)
            throw new IllegalArgumentException("Course Id must be at least five character");
        mItem_title = ItemTilte;

    }

    public void setItemDescription(String ItemDesc) {
        if(ItemDesc == null)
            throw new IllegalArgumentException("Course Id must be supplies");
        if(ItemDesc.length() < 5)
            throw new IllegalArgumentException("Course Id must be at least five character");
        mItem_Description = ItemDesc;

    }

    public void setItemCondition(String ItemCond) {
        if(ItemCond == null)
            throw new IllegalArgumentException("Course Id must be supplies");
        if(ItemCond.length() < 5)
            throw new IllegalArgumentException("Course Id must be at least five character");
        mItem_condition = ItemCond;

    }

    public void setItemCategoty(String ItemCateg) {
        if(ItemCateg == null)
            throw new IllegalArgumentException("Course Id must be supplies");
        if(ItemCateg.length() < 5)
            throw new IllegalArgumentException("Course Id must be at least five character");
        mItem_category = ItemCateg;

    }

    public void setSalerLocation(String salerLoca) {
        if(salerLoca == null)
            throw new IllegalArgumentException("Course Id must be supplies");
        if(salerLoca.length() < 5)
            throw new IllegalArgumentException("Course Id must be at least five character");
        mSaler_Location = salerLoca;

    }

    public void setItemPrice(Double ItemPrice) {
        if(ItemPrice == null)
            throw new IllegalArgumentException("Course Id must be supplies");

        mItem_price = ItemPrice;

    }

    public String getItemTitle() {
        return mItem_title;
    }

    public int getItemID() { return mItem_id; }

    public double getItemPrice() {
        return mItem_price;
    }

    public String getItemDescription() {
        return mItem_Description;
    }

    public String getmItemCondtion() {
        return mItem_condition;
    }

    public String getItemCategory() {
        return mItem_condition;
    }

    public String getSalerLocation() {
        return mSaler_Location;
    }

    @Override
    public String toString() {
        return "edu.UW.sdendaa.HuskyList.SubCategory.SubCategoryContent{" +
                ", mItemId = " + mItem_id + '\'' +
                ", mItemTitle = " + mItem_title + '\'' +
                ", mItemPrice = " + mItem_price + '\'' +
                ", mItemDescription = " + mItem_Description + '\'' +
                ", mItemCondition = " + mItem_condition + '\'' +
                ", mItemSubcategory = " + mItem_category + '\'' +
                ", mSalerLocation = " + mSaler_Location + '}';
    }

    public static String parseCourseJSON(String courseJSON, List<BookContent> courseList) {
        String reason = null;
        if (courseJSON != null) {
            try {
                JSONArray arr = new JSONArray(courseJSON);
                for (int i = 0; i < arr.length(); i++) {
//                    JSONObject obj = arr.getJSONObject(i);
//                    SubCategoryContent mySubCategoryCont = new SubCategoryContent(obj.getString(SubCategoryContent.Item_title), obj.getString(SubCategoryContent.Item_Description)
//                            , obj.getString(SubCategoryContent.Item_Condition), obj.getString(SubCategoryContent.Item_category),
//                            obj.getString(SubCategoryContent.),obj.getString(SubCategoryContent.saler_Location));
//                    courseList.add(mySubCategoryCont);
                }

            } catch (JSONException e) {
                reason = "Unable to parse data, Reason: " + e.getMessage();
            }
        }
        return reason;
    }

}