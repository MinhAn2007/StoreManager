package vn.edu.iuh.fit.wwwlab02.converts;

import jakarta.persistence.AttributeConverter;
import vn.edu.iuh.fit.wwwlab02.enums.ProductStatus;

public class ProductStatusConvert implements AttributeConverter<ProductStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ProductStatus attribute) {
        if (attribute == null)
            return null;
        return attribute.getCode();
    }

    @Override
    public ProductStatus convertToEntityAttribute(Integer dbData) {
        if (dbData == null)
            return null;
        return ProductStatus.fromcode(dbData);
    }
}