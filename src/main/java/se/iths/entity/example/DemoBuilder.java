package se.iths.entity.example;

public class DemoBuilder {
    private String name;
    private int value;

    public DemoBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public DemoBuilder setValue(int value) {
        this.value = value;
        return this;
    }

    public DemoBuilder createDefaultPerson() {
        this.name = "Martin";
        this.value = 7;
        return this;
    }

    public Demo build() {
        return new Demo(name, value);
    }

    public static void main(String[] args) {
        Demo person = new DemoBuilder().createDefaultPerson().build();
        Demo person2 = new DemoBuilder().setName("Kalle").setValue(2).build();
    }
}