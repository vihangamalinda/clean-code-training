package com.b.simple.design.business.customer;

import java.math.BigDecimal;
import java.util.List;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.Amount;
import com.b.simple.design.model.customer.AmountImpl;
import com.b.simple.design.model.customer.Currency;
import com.b.simple.design.model.customer.Product;

public class CustomerBOImpl implements CustomerBO {

	@Override
	public Amount getCustomerProductsSum(List<Product> products)
			throws DifferentCurrenciesException {

		if (isProductsEmpty(products)) return getAmount(BigDecimal.ZERO, Currency.EURO);

		productsCurrencyValidation(products);

		return getProductsSum(products);
	}

	private static AmountImpl getProductsSum(List<Product> products) {
		BigDecimal productsSum = products.stream().map(CustomerBOImpl::getAmountValue).reduce(BigDecimal.ZERO, BigDecimal::add);
		Currency firstProductCurrency = getProductCurrency(products.get(0));
		return getAmount(productsSum, firstProductCurrency);
	}

	private static BigDecimal getAmountValue(Product product) {
		return product.getAmount().getValue();
	}

	private static AmountImpl getAmount(BigDecimal temp, Currency euro) {
		return new AmountImpl(temp, euro);
	}

	private static void productsCurrencyValidation(List<Product> products) throws DifferentCurrenciesException {
		Currency firstProductCurrency = getProductCurrency(products.get(0));
		for (Product product : products) getProductValidated(firstProductCurrency, product);
	}

	private static void getProductValidated(Currency firstProductCurrency, Product product) throws DifferentCurrenciesException {
		if (!isCurrencySameAsFirstProduct(product, firstProductCurrency)) throw new DifferentCurrenciesException();
	}

	private static boolean isCurrencySameAsFirstProduct(Product product, Currency firstProductCurrency) {
		return getProductCurrency(product).equals(firstProductCurrency);
	}

	private static Currency getProductCurrency(Product products) {
		return products.getAmount()
				.getCurrency();
	}

	private static boolean isProductsEmpty(List<Product> products) {
		return products.isEmpty();
	}
}