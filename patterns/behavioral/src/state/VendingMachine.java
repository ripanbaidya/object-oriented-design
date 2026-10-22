package state;

public class VendingMachine {

    private VendingState state = new IdleState();

    public void setState(VendingState state) {
        this.state = state;
    }

    public void insertMoney() {
        state.insertMoney(this);
    }

    public void selectProduct() {
        state.selectProduct(this);
    }
}
