/*
* HuskyList App
* Authors: Vladimir Smirnov and Shelema Bekele
*/
package tcss450.uw.edu.mynewapp.model;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import java.util.List;
import java.lang.String;

/**
 * The BookContent is the model class that represents a "Book".
 *
 * @author Shelema Bekele
 * @author Vladimir Smirnov
 * @version 1.0
 */
public class ItemContent implements Serializable {
//    /** This variable holds the item ID. */
    private String mItem_id;
    /** This variable holds the item title. */
    private String mItem_title;
    /** This variable holds the item price. */
    private String mItem_price;
    /** This variable holds the item condition. */
    private String mItem_condition;
    /** This variable holds the item description. */
    private String mItem_description;
    /** This variable holds the seller location. */
    private String mSeller_location;
    /** This variable holds the seller contact information. */
    private String mSeller_contact;
    private String mSeller_userName;

    /** This variable holds the database variable names. */
    public static final String seller_userName= "Seller_userName", Item_id = "Item_id",
            Item_title = "Item_title", Item_price = "Item_price",
            Item_Condition = "Item_condition", Item_description = "Item_descriptions",
            seller_location = "Seller_location", seller_contact = "Seller_contact";


    /**
     * This is the BookContent constructor.
     *
    // * @param itemId is the given item id.
     * @param ItemTitle is the given item title.
     * @param ItemPrice is the given item price.
     * @param ItemCond is the given item condition.
     * @param ItemDesc is the given item description.
     * @param SellerLocation is the given seller location.
     * @param SellerContact is the given seller contact information.
     */
    public ItemContent(String SellerUserName, String ItemTitle, String ItemPrice, String ItemCond,
                       String ItemDesc, String SellerLocation, String SellerContact) {
        setSellerUserName(SellerUserName);
       // setItemId(ItemId);
        setItemTitle(ItemTitle);
        setItemPrice(ItemPrice);
        setItemCondition(ItemCond);
        setItemDescription(ItemDesc);
        setSellerLocation(SellerLocation);
        setSellerContact(SellerContact);

    }

    /**
     * This method sets the item ID.
     *
     * @param ItemId is the given item ID.
     */
    public void setItemId(String ItemId) {
        if(ItemId == null)
            throw new IllegalArgumentException("Item Id must be supplies");
        mItem_id = ItemId;
    }

    /**
     * This method sets the item title.
     *
     * @param ItemTitle is the given item title.
     */
    public void setItemTitle(String ItemTitle) {
        if(ItemTitle == null)
            throw new IllegalArgumentException("Course Id must be supplies");
        if(ItemTitle.length() < 5)
            throw new IllegalArgumentException("Course Id must be at least five character");
        mItem_title = ItemTitle;

    }

    /**
     * This method sets the item price.
     *
     * @param ItemPrice is the given item price.
     */
    public void setItemPrice(String ItemPrice) {
        if(ItemPrice == null)
            throw new IllegalArgumentException("Course Id must be supplies");

        mItem_price = ItemPrice;

    }

    /**
     * This method sets the item condition.
     *
     * @param ItemCond is the given item condition.
     */
    public void setItemCondition(String ItemCond) {
        if(ItemCond == null)
            throw new IllegalArgumentException("Course Id must be supplies");
//        if(ItemCond.length() < 5)
//            throw new IllegalArgumentException("Course Id must be at least five character");
        mItem_condition = ItemCond;

    }

    /**
     * This method sets the item description.
     *
     * @param Item_Desc is the given item description.
     */
    public void setItemDescription(String Item_Desc) {
        if(Item_Desc == null)
            throw new IllegalArgumentException("Course Id must be supplies");
        if(Item_Desc.length() < 3)
            throw new IllegalArgumentException("Course Id must be at least three character");
        mItem_description = Item_Desc;

    }

    /**
     * This method sets the seller location.
     *
     * @param salerLoca is the given seller location.
     */
    public void setSellerLocation(String salerLoca) {
        if(salerLoca == null)
            throw new IllegalArgumentException("Course Id must be supplies");
        if(salerLoca.length() < 5)
            throw new IllegalArgumentException("Course Id must be at least five character");
        mSeller_location = salerLoca;

    }

    /**
     * This method sets the sellers contact information.
     *
     * @param sellerContact is the given seller contact information.
     */
    public void setSellerContact(String sellerContact) {
        if(sellerContact == null)
            throw new IllegalArgumentException("Course Id must be supplies");
//        if(sellerContact.length() < 3)
//            throw new IllegalArgumentException("Course Id must be at least five character");
        mSeller_contact = sellerContact;

    }
    /**
     * This method sets the sellers contact information.
     *
     * @param sellerUserName is the given seller contact information.
     */
    public void setSellerUserName(String sellerUserName) {
        if(sellerUserName == null)
            throw new IllegalArgumentException("Course Id must be supplies");
//        if(sellerContact.length() < 3)
//            throw new IllegalArgumentException("Course Id must be at least five character");
        mSeller_userName = sellerUserName;

    }

    /**
     * This method gets the item title.
     *
     * @return is the given title.
     */
    public String getItemTitle() {
        return mItem_title;
    }

    /**
     * This method gets the item ID.
     *
     * @return is the given ID.
     */
    public String getItemID() {
        return mItem_id;

    }
    /**
     * This method gets the item price.
     *
     * @return is the given price.
     */
    public String getItemPrice() {
        return mItem_price;
    }

    /**
     * This method gets the item condition.
     *
     * @return is the items condition.
     */
    public String getmItemCondtion() {
        return mItem_condition;
    }

    /**
     * This method gets the item description.
     *
     * @return is the items description.
     */
    public String getItemDescription() {
        return mItem_description;
    }

    /**
     * This method gets the sellers location.
     *
     * @return is the sellers location.
     */
    public String getSellerLocation() {
        return mSeller_location;
    }

    /**
     * This method gets the sellers contact information.
     *
     * @return is the given contact information.
     */
    public String getSellerContact() {
        return mSeller_contact;
    }
    /**
     * This method gets the sellers contact information.
     *
     * @return is the given contact information.
     */
    public String getSellerUserName() {
        return mSeller_userName;
    }


    /**
     * This method is used to represent the item in a String.
     *
     * @return is the given String.
     */
    @Override
    public String toString() {
        return "edu.UW.sdendaa.HuskyList.SubCategory.SubCategoryContent{" +
                ", mItemId = " + mSeller_userName + '\'' +
                ", mItemTitle = " + mItem_title + '\'' +
                ", mItemPrice = " + mItem_price + '\'' +
                ", mItemCondition = " + mItem_condition + '\'' +
                ", mItemSubcategory = " + mItem_description + '\'' +
                ", mSellerLocation = " + mSeller_location +  '\'' +
                ", mItemDescription = " + mSeller_contact + '\''+
                ", mItemDescription = " + mSeller_userName + '}';
    }

    /**
     * This method is used to parse the book JSON.
     *
     * @param bookJSON is the given JSON String.
     * @param bookList is a list to hold all the books.
     *
     * @return is a String representing the reason.
     */
    public static String parseBookJSON(String bookJSON, List<ItemContent> bookList) {
        String reason = null;
        if (bookJSON != null) {
            try {
                JSONArray arr = new JSONArray(bookJSON);

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = arr.getJSONObject(i);

                    ItemContent book = new ItemContent(obj.getString(ItemContent.seller_userName),
                          obj.getString(ItemContent.Item_title), obj.getString(ItemContent.Item_price),
                            obj.getString(ItemContent.Item_Condition), obj.getString(ItemContent.Item_description),
                            obj.getString(ItemContent.seller_location), obj.getString(ItemContent.seller_contact));

                    bookList.add(book);
                }

            } catch (JSONException e) {
                reason = "Unable to parse data, Reason: " + e.getMessage();
            }
        }
        return reason;
    }
}