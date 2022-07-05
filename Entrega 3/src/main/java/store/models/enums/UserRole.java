package store.models.enums;

public enum UserRole {
    USER(0, "Usuário"),
    SELLER(1, "Vendedor"),
    MANAGER(2, "Gerente"),
    ADMIN(3, "Admin");

    public final int VALUE;
    public final String NAME;

    private UserRole(int VALUE, String NAME) {
        this.VALUE = VALUE;
        this.NAME = NAME;
    }
}
