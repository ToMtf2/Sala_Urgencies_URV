public class Altres extends Pacient {
    private String explicacio;

    public Altres(String nom, int edat, boolean teAlergia, String explicacio) {
        super(nom, edat, teAlergia);
        this.explicacio = explicacio;
        calcularPuntsAddicionals();
    }

    @Override
    public void calcularPuntsAddicionals() {
        // Sense punts addicionals
    }

    @Override
    public String toString() {
        return "[Altres] " + super.toString() + ", Explicació: " + explicacio;
    }
}