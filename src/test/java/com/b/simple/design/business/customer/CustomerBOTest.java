package com.b.simple.design.business.customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.Amount;
import com.b.simple.design.model.customer.AmountImpl;
import com.b.simple.design.model.customer.Currency;
import com.b.simple.design.model.customer.Product;
import com.b.simple.design.model.customer.ProductImpl;
import com.b.simple.design.model.customer.ProductType;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerBOTest {
    private static final Currency CURRENCY_EURO = Currency.EURO;
    private static final ProductType TYPE_BANK_GUARANTEE = ProductType.BANK_GUARANTEE;
    private static final Currency CURRENCY_INDIAN_RUPEE = Currency.INDIAN_RUPEE;
    private CustomerBO customerBO = new CustomerBOImpl();

    @Test
    public void testCustomerProductSum_whenTwoProductsSameCurrencies_returnProductSum() throws DifferentCurrenciesException {
        // Arrange
        List<Product> products = getProductsWithSameCurrencies();
        // Act
        Amount temp = customerBO.getCustomerProductsSum(products);
        // Assert
        assertAmount(getAmountInEuro("11.0"), temp);
    }

    @Test
    public void testCustomerProductSum_whenTwoProductsDifferentCurrencies_throwsDifferentCurrenciesException() {
        // Arrange
        List<Product> products = getProductsWithDifferentCurrencies();

        // Act & Assert
        assertThrows(DifferentCurrenciesException.class, () -> customerBO.getCustomerProductsSum(products));
    }

    @Test
    public void testCustomerProductSum_WhenEmptyProductListGiven_returnsAmountAsZeroAndCurrencyAsEuro() throws DifferentCurrenciesException {
        // Arrange
        List<Product> products = new ArrayList<Product>();
        // Act
        Amount temp = customerBO.getCustomerProductsSum(products);
        Amount expected = getAmountInEuro("0");
        // Assert
        assertAmount(expected, temp);
    }

    private static List<Product> getProductsWithSameCurrencies() {
        return Arrays.asList(getProductWithBanKGrantee(100, "Product 15", getAmountInEuro("5.0")), getProductWithBanKGrantee(120, "Product 20", getAmountInEuro("6.0")));
    }

    private static List<Product> getProductsWithDifferentCurrencies() {
        return Arrays.asList(getProductWithBanKGrantee(100, "Product 15", getAmountInIndianRupee("5.0")), getProductWithBanKGrantee(120, "Product 20", getAmountInEuro("6.0")));
    }

    private static ProductImpl getProductWithBanKGrantee(int id, String name, AmountImpl amount) {
        return new ProductImpl(id, name, TYPE_BANK_GUARANTEE, amount);
    }

    private static AmountImpl getAmountInEuro(String value) {
        return new AmountImpl(new BigDecimal(value), CURRENCY_EURO);
    }

    private static AmountImpl getAmountInIndianRupee(String value) {
        return new AmountImpl(new BigDecimal(value), CURRENCY_INDIAN_RUPEE);
    }

    private static void assertAmount(Amount expected, Amount actual) {
        assertEquals(Currency.EURO, actual.getCurrency());
        assertEquals(expected.getValue(), actual.getValue());
    }
}