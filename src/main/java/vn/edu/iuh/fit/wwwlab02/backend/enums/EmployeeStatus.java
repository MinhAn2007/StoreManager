package vn.edu.iuh.fit.wwwlab02.backend.enums;

public enum EmployeeStatus {
    ACTIVE(1), DEACTIVE(0), DELETE(-1);
    private final int code;
    EmployeeStatus(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }
    public static EmployeeStatus fromcode(int code) {
        for (EmployeeStatus status : EmployeeStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid EmployeeStatus code: " + code);
    }
}
