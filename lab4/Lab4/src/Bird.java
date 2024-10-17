class Bird extends Animal {
    public Bird(String name) {
        super(name);
    }

    @Override
    public void show() {
        System.out.println("Птица: " + name);
    }
}