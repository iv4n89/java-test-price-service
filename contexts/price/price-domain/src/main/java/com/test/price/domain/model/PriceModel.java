package com.test.price.domain.model;

import com.test.price.domain.valueobject.Currency;
import com.test.price.domain.valueobject.PriceList;
import com.test.price.domain.valueobject.Priority;
import com.test.shared.domain.AggregateRoot;
import com.test.shared.domain.valueobject.BrandId;
import com.test.shared.domain.valueobject.Money;
import com.test.shared.domain.valueobject.ProductId;
import java.time.LocalDateTime;
import java.util.Objects;

public final class PriceModel implements AggregateRoot {
  private final BrandId brandId;
  private final LocalDateTime startDate;
  private final LocalDateTime endDate;
  private final PriceList priceList;
  private final ProductId productId;
  private final Priority priority;
  private final Money price;
  private final Currency currency;

  private PriceModel(Builder builder) {
    brandId = builder.brandId;
    startDate = builder.startDate;
    endDate = builder.endDate;
    priceList = builder.priceList;
    productId = builder.productId;
    priority = builder.priority;
    price = builder.price;
    currency = builder.currency;
  }

  public static Builder builder() {
    return new Builder();
  }

  public BrandId getBrandId() {
    return brandId;
  }

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public LocalDateTime getEndDate() {
    return endDate;
  }

  public PriceList getPriceList() {
    return priceList;
  }

  public ProductId getProductId() {
    return productId;
  }

  public Priority getPriority() {
    return priority;
  }

  public Money getPrice() {
    return price;
  }

  public Currency getCurrency() {
    return currency;
  }

  public static final class Builder {
    private BrandId brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private PriceList priceList;
    private ProductId productId;
    private Priority priority;
    private Money price;
    private Currency currency;

    private Builder() {}

    public Builder brandId(BrandId val) {
      brandId = val;
      return this;
    }

    public Builder startDate(LocalDateTime val) {
      startDate = val;
      return this;
    }

    public Builder endDate(LocalDateTime val) {
      endDate = val;
      return this;
    }

    public Builder priceList(PriceList val) {
      priceList = val;
      return this;
    }

    public Builder productId(ProductId val) {
      productId = val;
      return this;
    }

    public Builder priority(Priority val) {
      priority = val;
      return this;
    }

    public Builder price(Money val) {
      price = val;
      return this;
    }

    public Builder currency(Currency val) {
      currency = val;
      return this;
    }

    public PriceModel build() {
      return new PriceModel(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PriceModel that = (PriceModel) o;
    return Objects.equals(startDate, that.startDate)
        && Objects.equals(endDate, that.endDate)
        && Objects.equals(priceList, that.priceList)
        && Objects.equals(productId, that.productId)
        && Objects.equals(priority, that.priority);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startDate, endDate, priceList, productId, priority);
  }
}
