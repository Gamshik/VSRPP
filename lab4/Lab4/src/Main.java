public class Main {
    public static void main(String[] args) {
        new Mammal("Лев");
        new Parnopods("Олень");
        new Mammal("Кенгуру");
        new Bird("Воробей");
        new Parnopods("Лошадь");
        new Mammal("Медведь");
        new Bird("Снигирь");
        new Parnopods("Зебра");

        Animal.showList();
    }
}