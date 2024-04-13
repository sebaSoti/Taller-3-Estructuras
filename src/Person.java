import java.text.DecimalFormat;

class Person implements Comparable<Person> {

    private final String nombres;
    private final String apellidos;

    public Person(String n, String a) {
        nombres = n;
        apellidos = a;
    }

    public String toString() {
        return nombres + " " + apellidos;
    }

    @Override
    public int compareTo(Person o) {
        return 0;
    }

    private static final DecimalFormat df = new DecimalFormat();
    {
        df.setMaximumFractionDigits(2);
    }

}
