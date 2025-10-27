package mvc_jdbc_test.view;

public abstract class ObjectView<T> {

    public abstract void printItem(T item);

    public abstract void printHead();

    public void printFoot() {
        System.out.println("\n========================");
        System.out.println("====== Print Done ======");
        System.out.println("========================\n");
    }
}
