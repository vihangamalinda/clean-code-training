package com.a.introduction.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyTestGildedRoseCBackstagePassesTest {

    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final int SELL_IN_WITH_LESS_DEMAND = 15;
    public static final int QUALITY_ON_LESS_DEMAND = 3;
    public static final int SELL_IN_WITH_MID_DEMAND = 7;
    public static final int QUALITY_ON_MID_DEMAND = 3;
    public static final int SELL_IN_WITH_HIGH_DEMAND = 4;
    public static final int QUALITY_ON_HIGH_DEMAND = 3;
    public static final int QUALITY_AFTER_CONCERT = QUALITY_ON_HIGH_DEMAND;
    public static final int SELL_IN_AFTER_CONCERT = 0;

    @Test
    public void testUpdateQuality_WhenSellInMoreThanTenWithQualityThree_thenIncreaseQualityByOneAndDecreaseSellInByOne() {
//        Arrange
        Item[] items = new Item[]{createItem(BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT, SELL_IN_WITH_LESS_DEMAND, QUALITY_ON_LESS_DEMAND)};
        GildedRose gildedRose = createGildedRose(items);
//        Act
        gildedRose.updateQuality();
        Item expected = createItem(BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT, SELL_IN_WITH_LESS_DEMAND - 1, QUALITY_ON_LESS_DEMAND + 1);
//        Assert
        assertItem(expected, gildedRose.items[0]);
    }

    @Test
    public void testUpdateQuality_WhenSellInLessThanOrEqualToTenWithQualityThree_thenIncreaseQualityByTwoAndDecreaseSellInByOne() {
//        Arrange
        Item[] items = new Item[]{new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT, SELL_IN_WITH_MID_DEMAND, QUALITY_ON_MID_DEMAND)};
        GildedRose gildedRose = createGildedRose(items);
//        Act
        gildedRose.updateQuality();
//        Assert
        Item expected = createItem(BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT, SELL_IN_WITH_MID_DEMAND - 1, QUALITY_ON_MID_DEMAND + 2);
        assertItem(expected, gildedRose.items[0]);
    }

    @Test
    public void testUpdateQuality_WhenSellInLessThanOrEqualToFiveWithQualityThree_thenIncreaseQualityByThreeAndDecreaseSellInByOne() {
//        Arrange
        Item[] items = new Item[]{createItem(BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT, SELL_IN_WITH_HIGH_DEMAND, QUALITY_ON_HIGH_DEMAND)};
        GildedRose gildedRose = createGildedRose(items);
//        Act
        gildedRose.updateQuality();
//        Assert
        Item expected = createItem(BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT, SELL_IN_WITH_HIGH_DEMAND - 1, QUALITY_ON_HIGH_DEMAND + 3);
        assertItem(expected, gildedRose.items[0]);
    }

    @Test
    public void testUpdateQuality_WhenSellLessThanOrEqualZeroWithQualityThree_thenDropsQualityToZeroAndDecreaseSellInByOne() {
        //        Arrange
        Item[] items = new Item[]{createItem(BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT, SELL_IN_AFTER_CONCERT, QUALITY_AFTER_CONCERT)};
        GildedRose gildedRose = createGildedRose(items);
//        Act
        gildedRose.updateQuality();
//        Assert
        Item expected = createItem(BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT, SELL_IN_AFTER_CONCERT - 1, 0);
        assertItem(expected, gildedRose.items[0]);
    }

    private static GildedRose createGildedRose(Item[] items) {
        return new GildedRose(items);
    }

    private static void assertItem(Item expected, Item actual) {
        assertEquals(expected.name, actual.name);
        assertEquals(expected.sellIn, actual.sellIn);
        assertEquals(expected.quality, actual.quality);
    }

    private static Item createItem(String name, int sellIn, int quality) {
        return new Item(name, sellIn, quality);
    }

}
