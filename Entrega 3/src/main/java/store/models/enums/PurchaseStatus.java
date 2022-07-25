package store.models.enums;

public enum PurchaseStatus {
    PENDING(0, "Pendente"),
    APPROVED(1, "Aprovado"),
    REJECTED(-1, "Rejeitado");

    public final int VALUE;
    public final String NAME;

    private PurchaseStatus(int VALUE, String NAME) {
        this.VALUE = VALUE;
        this.NAME = NAME;
    }
}
