package AnimalIerarhy;

public abstract class Animal {
    private static Animal head = null;

    protected Animal next;

    protected String name;

    public Animal(String name) {
        this.name = name;
        this.next = head;
        head = this;
    }

    public abstract void show();

    public static void showList() {
        Animal current = head;
        while (current != null) {
            current.show();
            current = current.next;
        }
    }
}
