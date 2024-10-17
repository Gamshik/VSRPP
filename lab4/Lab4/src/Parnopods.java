class Parnopods extends Mammal {
    public Parnopods(String name) {
        super(name);
    }

    @Override
    public void show() {
        System.out.println("Парнокопытное: " + name);
    }
}