package vn.edu.iuh.fit.wwwlab02.pks;

import lombok.Getter;
import lombok.Setter;
import vn.edu.iuh.fit.wwwlab02.entities.Order;
import vn.edu.iuh.fit.wwwlab02.entities.Product;

import java.io.Serializable;

@Setter
@Getter
public class OrderDetailPK implements Serializable {
    private Long order;
    private Long product;
}