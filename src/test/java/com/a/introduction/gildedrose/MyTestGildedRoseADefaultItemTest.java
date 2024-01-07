package com.a.introduction.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyTestGildedRoseADefaultItemTest {


    public static final String DEFAULT_ITEM_TYPE = "DEFAULT_ITEM";
    public static final int NOT_EXPIRED_SELLIN = 15;
    public static final int DEFAULT_QUALITY = 3;
    private final int EXPIRED_SELLIN = -1;

    @Test
    public void testUpdateQuality_whenDefaultItem_thenQualityAndSellDecreasedByOne() {
//        Arrange
        Item[] items = new Item[]{createItem(DEFAULT_ITEM_TYPE, NOT_EXPIRED_SELLIN, DEFAULT_QUALITY)};
        GildedRose app = createGildedRose(items);
        Item expected = createItem(DEFAULT_ITEM_TYPE, NOT_EXPIRED_SELLIN - 1, DEFAULT_QUALITY - 1);
//        Act
        app.updateQuality();
//        Assert
        assertDefaultItem(expected, app.items[0]);
    }
    
    @Test
    public void testUpdateQuality_whenItemSellLessThanZero_thenDecreaseSellInByOneAndQualityByTwo() {
//        Arrange
        Item[] items = new Item[]{createItem(DEFAULT_ITEM_TYPE, EXPIRED_SELLIN, DEFAULT_QUALITY)};
        Item expected = createItem(DEFAULT_ITEM_TYPE, EXPIRED_SELLIN - 1, DEFAULT_QUALITY - 2);

        GildedRose app = createGildedRose(items);
//        Act
        app.updateQuality();
//        Asset
        assertDefaultItem(expected, app.items[0]);
    }

    private static Item createItem(String defaultItemType, int notExpiredSellingCount, int quality) {
        return new Item(defaultItemType, notExpiredSellingCount, quality);
    }

    private static GildedRose createGildedRose(Item[] items) {
        return new GildedRose(items);
    }

    private static void assertDefaultItem(Item expected, Item actual) {
        assertEquals(expected.name, actual.name);
        assertEquals(expected.sellIn, actual.sellIn);
        assertEquals(expected.quality, actual.quality);
    }

}
