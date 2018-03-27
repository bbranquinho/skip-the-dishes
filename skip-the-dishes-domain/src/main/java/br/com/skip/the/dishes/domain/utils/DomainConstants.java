package br.com.skip.the.dishes.domain.utils;

public final class DomainConstants {

    private DomainConstants() { }

    ///////////////////////////////////////
    // Customer
    ///////////////////////////////////////

    public static final int CUSTOMER_ID_SIZE = 255;

    ///////////////////////////////////////
    // Product
    ///////////////////////////////////////

    public static final int PRODUCT_ID_SIZE = 36;

    public static final int PRODUCT_NAME_SIZE = 45;

    public static final int PRODUCT_DESCRIPTION_SIZE = 128;

    public static final int PRODUCT_STORE_ID_SIZE = 36;

    public static final int PRODUCT_PRICE_MIN = 0;

    ///////////////////////////////////////
    // Order
    ///////////////////////////////////////

    public static final int ORDER_CONTACT_SIZE = 128;

    public static final int ORDER_DELIVERY_ADDRESS_SIZE = 128;

    public static final int ORDER_ID_SIZE = 36;

}
