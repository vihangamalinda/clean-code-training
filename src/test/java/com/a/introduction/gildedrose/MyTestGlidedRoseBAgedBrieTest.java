package com.a.introduction.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyTestGlidedRoseBAgedBrieTest {


    public static final int AGED_BRIE_SELL_IN = 4;
    public static final int AGED_BRIE_QUALITY = 3;
    public static final int CURRENT_SELL_IN = -1;
    public static final int CURRENT_AGED_BRIE_QUALITY = 3;
    public static final int ULTIMATE_QUALITY_AGED_BRIE_SELL_IN = 4;
    public static final int ULTIMATE_AGED_BRIE_QUALITY = 50;
    private final String AGED_BRIE_ITEM = "Aged Brie";

    @Test
    public void testUpdateQuality_WhenAgedBrie_thenSellInDecreasedByOneAndQualityIncreasedByOne() {
//        Arrange
        Item[] items = new Item[]{createItem(AGED_BRIE_ITEM, AGED_BRIE_SELL_IN, AGED_BRIE_QUALITY)};
        GildedRose app = createGildedRose(items);
//        Act
        app.updateQuality();
//        Assert
        Item expected = createItem(AGED_BRIE_ITEM, AGED_BRIE_SELL_IN - 1, AGED_BRIE_QUALITY + 1);
        assertItem(expected, app.items[0]);
    }

    @Test
    public void testUpdateQuality_WhenAgedBrieWithNegativeOneSellIn_thenSellInDecreasedByOneAndQualityIncreasedByTwo() {
//        Arrange
        Item[] items = new Item[]{createItem(AGED_BRIE_ITEM, CURRENT_SELL_IN, CURRENT_AGED_BRIE_QUALITY)};
        GildedRose app = new GildedRose(items);
//        Act
        app.updateQuality();
//        Assert
        Item expected = createItem(AGED_BRIE_ITEM, CURRENT_SELL_IN - 1, CURRENT_AGED_BRIE_QUALITY + 2);
        assertItem(expected, app.items[0]);
    }

    @Test
    public void testUpdateQuality_WhenAgedBrieWithMaximumQuality_thenSellInDecreasedByOneAndQualityStaySame() {
//        Arrange
        Item[] items = new Item[]{createItem(AGED_BRIE_ITEM, ULTIMATE_QUALITY_AGED_BRIE_SELL_IN, ULTIMATE_AGED_BRIE_QUALITY)};
        GildedRose app = createGildedRose(items);
//        Act
        app.updateQuality();
//        Assert
        Item expected = createItem(AGED_BRIE_ITEM, ULTIMATE_QUALITY_AGED_BRIE_SELL_IN - 1, ULTIMATE_AGED_BRIE_QUALITY);
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
