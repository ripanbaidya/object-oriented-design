package state;

public class HasMoneyState implements VendingState {

    @Override
    public void insertMoney(VendingMachine machine) {
        System.out.println("Money already inserted.");
    }

    @Override
    public void selectProduct(VendingMachine machine) {
        System.out.println("Select product.");
        machine.setState(new DispensingState());
    }
}
