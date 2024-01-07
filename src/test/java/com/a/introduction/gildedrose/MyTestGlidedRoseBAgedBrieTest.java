package com.a.introduction.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyTestGlidedRoseBAgedBrieTest {


    public static final int AGED_BRIE_SELL_IN = 4;
    public static final int AGED_BRIE_QUALITY = 3;
    private final String AGED_BRIE_ITEM = "Aged Brie";

    @Test
    public void testUpdateQuality_WhenAgedBrie_thenSellInDecreasedByOneAndQualityIncreasedByOne() {
//        Arrange
        Item item = createItem(AGED_BRIE_ITEM, AGED_BRIE_SELL_IN, AGED_BRIE_QUALITY);
        Item[] items = new Item[]{item};
        GildedRose app = createGildedRose(items);
//        Act
        app.updateQuality();
//        Assert
        Item expected = createItem(AGED_BRIE_ITEM, AGED_BRIE_SELL_IN - 1, AGED_BRIE_QUALITY + 1);
        assertItem(expected, app.items[0]);
    }

    private static void assertItem(Item expected, Item actual) {
        assertEquals(expected.name, actual.name);
        assertEquals(expected.sellIn, actual.sellIn);
        assertEquals(expected.quality, actual.quality);
    }

    private static Item createItem(String agedBrieItem, int agedBrieSellIn, int agedBrieQuality) {
        return new Item(agedBrieItem, agedBrieSellIn, agedBrieQuality);
    }

    private static GildedRose createGildedRose(Item[] items) {
        return new GildedRose(items);
    }
}
