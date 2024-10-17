class Mammal extends Animal {
    public Mammal(String name) {
        super(name);
    }

    @Override
    public void show() {
        System.out.println("Млекопитающее: " + name);
    }
}