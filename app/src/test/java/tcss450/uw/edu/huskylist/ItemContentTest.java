package tcss450.uw.edu.huskylist;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import tcss450.uw.edu.huskylist.model.ItemContent;


/**
 * this class is to test JUnit test to ItemContent class in our model
 */
public class ItemContentTest extends TestCase {
    /**
     * This variable is ItemContent that hold the itemcontent
     */
    private ItemContent mItemContent;
    /**
     * this method is to test the constructor
     */
    @Test
    public void testConstructor(){
        ItemContent itemContent = new ItemContent("sdendaa@uw.edu","Mobile Applications", "98.89",
                "used","this book is like new","UW Tacoma", "sdendaa@yahoo.com");
        assertNotNull(itemContent);
    }
    /**
     * this method is to test the JarseJSON
     */
    @Test
    public void testParseItemContentJSON() {
        String ItemContentJSON = "[{\"Seller_userName\":\"sdendaa@uw.edu\",\"Item_title\":\"Mobile Applications\",\"Item_price\":\"98.89\"," +
                "\"Item_condition\":\"used\",\"Item_descriptions\":\"this book is like new\",\"Seller_location\":\"UW Tacoma\"," +
                "\"Seller_contact\":\"sdendaa@yahoo.com\"},{\"Seller_userName\":\"sdendaa@uw.edu\",\"Item_title\":\"Data structure\"," +
                "\"Item_price\":\"988.97\", \"Item_condition\":\"new\",\"Item_descriptions\":\"never open the package\"," +
                "\"Seller_location\":\"UW Seattle\",\"Seller_contact\":\"sdendaa@yahoo.com\"}]";
        String message =  ItemContent.parseItemContentJSON(ItemContentJSON
                , new ArrayList<ItemContent>());
        assertTrue("JSON With Valid String", message == null);
    }
    /**
     * this method is to setup the instrumental unit test before build unit test
     */
    @Before
    public void setUp() {
        mItemContent = new ItemContent("sdendaa@uw.edu", "Mobile Applications", "98.89", "used",
                "this book is like new", "UW Tacoma","sdendaa@yahoo.com");
    }
    /**
     * this method is to test item title null
     */
    @Test
    public void testSetNullItemTitle() {
        try {
            mItemContent.setItemTitle(null);
            fail("Item Title can't be set to null");
        }
        catch (IllegalArgumentException e) {

        }
    }
    /**
     * this method is to test item price null
     */
    @Test
    public void testSetNullItemPrice() {
        try {
            mItemContent.setItemPrice(null);
            fail("Item price can't be set to null");
        }
        catch (IllegalArgumentException e) {

        }
    }
    /**
     * this method is to test item condition null
     */
    @Test
    public void testSetNullItemCondition() {
        try {
            mItemContent.setItemCondition(null);
            fail("seller condition can't be set to null");
        }
        catch (IllegalArgumentException e) {

        }
    }
    /**
     * this method is to test the seller location null
     */
    @Test
    public void testSetNullSellerLoaction() {
        try {
            mItemContent.setSellerLocation(null);
            fail("Sellere location can't be set to null");
        }
        catch (IllegalArgumentException e) {

        }
    }
    /**
     * this method is to test seller contact null
     */
    @Test
    public void testSetNullSellerContact() {
        try {
            mItemContent.setSellerContact(null);
            fail("seller contact can't be set to null");
        }
        catch (IllegalArgumentException e) {

        }
    }
    /**
     * this method is to test user name null
     */
    @Test
    public void testSetNullSellerUserName() {
        try {
            mItemContent.setSellerUserName(null);
            fail("seller username can't be set to null");
        }
        catch (IllegalArgumentException e) {

        }
    }
    /**
     * this method to test the item title length
     */
    @Test
    public void testSetLengthItemTitle() {
        try {
            mItemContent.setItemTitle("3");
            fail("Item Title must be set to greater than  or equal to 5 characters long");
        }
        catch (IllegalArgumentException e) {

        }
    }
    /**
     * this method is to test the get item title method
     */
    @Test
    public void testGetItemTitle() {
        assertEquals("Mobile Applications", mItemContent.getItemTitle());
    }
    /**
     * this method is to test get item price method.
     */
    @Test
    public void testGetItemPrice() {
        assertEquals("98.89", mItemContent.getItemPrice());
    }
    /**
     * this method is to test get item condition method
     */
    @Test
    public void testGetItemCondition() {
        assertEquals("used", mItemContent.getmItemCondtion());
    }
    /**
     * this method is to test get item description method
     */
    @Test
    public void testGetItemDesciption() {
        assertEquals("this book is like new", mItemContent.getItemDescription());
    }
    /**
     * this method is to test get sellet loaction method
     */
    @Test
    public void testGetSellereLoaction() {
        assertEquals("UW Tacoma", mItemContent.getSellerLocation());
    }
    /**
     * this method is to test seller contact method
     */
    @Test
    public void testGetSellereContact() {
        assertEquals("sdendaa@yahoo.com", mItemContent.getSellerContact());
    }

}