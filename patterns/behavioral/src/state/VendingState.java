package state;

public interface VendingState {

    void insertMoney(VendingMachine machine);

    void selectProduct(VendingMachine machine);
}
