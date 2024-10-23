package com.example.productManagement.entity;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class Discount {

    private BigDecimal discountPercent;

    protected Discount() {
    }

    public Discount(BigDecimal percentage) {
        if (percentage != null && (percentage.compareTo(BigDecimal.ZERO) < 0 || percentage.compareTo(new BigDecimal("100")) > 0)) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100");
        }
        this.discountPercent = percentage;
    }

    public BigDecimal getDiscountedPrice(BigDecimal price) {
        BigDecimal percentage = discountPercent != null? discountPercent : new BigDecimal("0");
        return price.subtract(price.multiply(percentage).divide(new BigDecimal("100")));
    }

    public BigDecimal getPercentage() {
        return discountPercent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return Objects.equals(discountPercent, discount.discountPercent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountPercent);
    }
}
