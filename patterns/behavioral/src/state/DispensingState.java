package state;

public class DispensingState implements VendingState {

    @Override
    public void insertMoney(VendingMachine machine) {
        System.out.println("Please wait, dispensing.");
    }

    @Override
    public void selectProduct(VendingMachine machine) {
        System.out.println("Already dispensing.");
    }
}
