package vn.edu.iuh.fit.wwwlab02.backend.enums;

public enum ProductStatus {
    ACTIVE(1), DEACTIVE(0), DELETE(-1);
    private final int code;

    public int getCode() {
        return code;
    }

    ProductStatus(int code) {
        this.code = code;
    }

    public static ProductStatus fromcode(int code) {
        for (ProductStatus status : ProductStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid EmployeeStatus code: " + code);
    }
}
