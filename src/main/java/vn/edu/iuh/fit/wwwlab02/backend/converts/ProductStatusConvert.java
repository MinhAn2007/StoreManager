package vn.edu.iuh.fit.wwwlab02.backend.converts;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import vn.edu.iuh.fit.wwwlab02.backend.enums.ProductStatus;
@Converter(autoApply = true)

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