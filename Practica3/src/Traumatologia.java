public class Traumatologia extends Pacient {
    private String zonaCos;

    public Traumatologia(String nom, int edat, boolean teAlergia, String zonaCos) {
        super(nom, edat, teAlergia);
        this.zonaCos = zonaCos;
        calcularPuntsAddicionals();
    }

    @Override
    public void calcularPuntsAddicionals() {
        // Traumatologia no afegeix punts extres segons l'enunciat
    }

    @Override
    public String toString() {
        return "[Traumatologia] " + super.toString() + ", Zona del cos: " + zonaCos;
    }
}