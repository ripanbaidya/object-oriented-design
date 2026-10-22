package state;

public class IdleState implements VendingState {

    @Override
    public void insertMoney(VendingMachine machine) {
        System.out.println("Money inserted.");
        machine.setState(new HasMoneyState());
    }

    @Override
    public void selectProduct(VendingMachine machine) {
        System.out.println("Please insert money first.");
    }
}
