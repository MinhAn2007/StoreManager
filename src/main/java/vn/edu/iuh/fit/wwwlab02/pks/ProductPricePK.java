package vn.edu.iuh.fit.wwwlab02.pks;

import lombok.Getter;
import lombok.Setter;
import vn.edu.iuh.fit.wwwlab02.entities.Product;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
public class ProductPricePK implements Serializable {
    private Long product;
    private LocalDateTime price_date_time;
}
