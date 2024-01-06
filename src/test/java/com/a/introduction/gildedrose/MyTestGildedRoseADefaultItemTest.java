package com.a.introduction.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyTestGildedRoseADefaultItemTest {


    public static final String DEFAULT_ITEM_TYPE = "DEFAULT_ITEM";
    public static final int NOT_EXPIRED_SELLING_COUNT = 15;
    public static final int DEFAULT_QUALITY = 3;

    @Test
    public void testUpdateQuality_whenDefaultItem_thenQualityAndSellDecreasedByOne() {
//        Arrange
        Item item = createItem(DEFAULT_ITEM_TYPE, NOT_EXPIRED_SELLING_COUNT, DEFAULT_QUALITY);
        GildedRose app = getGildedRose(new Item[]{item});
//        Act
        app.updateQuality();
//        Assert
        Item expected = createItem(DEFAULT_ITEM_TYPE, NOT_EXPIRED_SELLING_COUNT - 1, DEFAULT_QUALITY - 1);
        assertDefaultItem(expected, app.items[0]);
    }

    private static Item createItem(String defaultItemType, int notExpiredSellingCount, int defaultQuality) {
        return new Item(defaultItemType, notExpiredSellingCount, defaultQuality);
    }

    private static GildedRose getGildedRose(Item[] items) {
        return new GildedRose(items);
    }

    private static void assertDefaultItem(Item expected, Item actual) {
        assertEquals(expected.name, actual.name);
        assertEquals(expected.sellIn, actual.sellIn);
        assertEquals(expected.quality, actual.quality);
    }

}
