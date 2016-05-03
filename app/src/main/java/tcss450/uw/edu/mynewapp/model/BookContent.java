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
    private String mItem_id;
    private String mItem_title;
    private String mItem_price;
    private String mItem_condition;
    private String mItem_description;
    private String mSeller_location;
    private String mSeller_contact;

    public static final String Item_id = "Item_id", Item_title = "Item_Title", Item_price = "Item_price",
            Item_Condition = "Item_condition", Item_description = "Item_descriptions",
            seller_location = "Seller_location", seller_contact = "Seller_contact";


    public BookContent(String itemId, String ItemTitle, String ItemPrice, String ItemCond,
                       String ItemDesc, String SellerLocation, String SellerContact) {
        setItemId(itemId);
        setItemTitle(ItemTitle);
        setItemPrice(ItemPrice);
        setItemCondition(ItemCond);
        setItemDescription(ItemDesc);
        setSellerLocation(SellerLocation);
        setSellerContact(SellerContact);

    }

    public void setItemId(String ItemId) {
        if(ItemId == null)
            throw new IllegalArgumentException("Course Id must be supplies");

        mItem_id = ItemId;

    }

    public void setItemTitle(String ItemTilte) {
        if(ItemTilte == null)
            throw new IllegalArgumentException("Course Id must be supplies");
        if(ItemTilte.length() < 5)
            throw new IllegalArgumentException("Course Id must be at least five character");
        mItem_title = ItemTilte;

    }
    public void setItemPrice(String ItemPrice) {
        if(ItemPrice == null)
            throw new IllegalArgumentException("Course Id must be supplies");

        mItem_price = ItemPrice;

    }



    public void setItemCondition(String ItemCond) {
        if(ItemCond == null)
            throw new IllegalArgumentException("Course Id must be supplies");
//        if(ItemCond.length() < 5)
//            throw new IllegalArgumentException("Course Id must be at least five character");
        mItem_condition = ItemCond;

    }
    public void setItemDescription(String Item_Desc) {
        if(Item_Desc == null)
            throw new IllegalArgumentException("Course Id must be supplies");
        if(Item_Desc.length() < 5)
            throw new IllegalArgumentException("Course Id must be at least five character");
        mItem_description = Item_Desc;

    }


    public void setSellerLocation(String salerLoca) {
        if(salerLoca == null)
            throw new IllegalArgumentException("Course Id must be supplies");
        if(salerLoca.length() < 5)
            throw new IllegalArgumentException("Course Id must be at least five character");
        mSeller_location = salerLoca;

    }
    public void setSellerContact(String sellerContact) {
        if(sellerContact == null)
            throw new IllegalArgumentException("Course Id must be supplies");
        if(sellerContact.length() < 5)
            throw new IllegalArgumentException("Course Id must be at least five character");
        mSeller_contact = sellerContact;

    }


    public String getItemTitle() {
        return mItem_title;
    }

    public String getItemID() { return mItem_id; }

    public String getItemPrice() {
        return mItem_price;
    }

    public String getmItemCondtion() {
        return mItem_condition;
    }

    public String getItemDescription() {
        return mItem_description;
    }

    public String getSellerLocation() {
        return mSeller_location;
    }

    public String getSellerContact() {
        return mSeller_contact;
    }

    @Override
    public String toString() {
        return "edu.UW.sdendaa.HuskyList.SubCategory.SubCategoryContent{" +
                ", mItemId = " + mItem_id + '\'' +
                ", mItemTitle = " + mItem_title + '\'' +
                ", mItemPrice = " + mItem_price + '\'' +
                ", mItemCondition = " + mItem_condition + '\'' +
                ", mItemSubcategory = " + mItem_description + '\'' +
                ", mSalerLocation = " + mSeller_location +  '\'' +
                ", mItemDescription = " + mSeller_contact + '}';
    }

    public static String parseCourseJSON(String courseJSON, List<BookContent> bookList) {
        String reason = null;
        if (courseJSON != null) {
            try {
                JSONArray arr = new JSONArray(courseJSON);

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = arr.getJSONObject(i);

                    BookContent book = new BookContent(obj.getString(BookContent.Item_id), obj.getString(BookContent.Item_title),
                            obj.getString(BookContent.Item_price), obj.getString(BookContent.Item_Condition), obj.getString(BookContent.Item_description),
                            obj.getString(BookContent.seller_location), obj.getString(BookContent.seller_contact));

                    bookList.add(book);
                }

            } catch (JSONException e) {
                reason = "Unable to parse data, Reason: " + e.getMessage();
            }
        }
        return reason;
    }

}