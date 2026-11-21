public class Cardiologia extends Pacient {
    private int minutsSimptomes;

    public Cardiologia(String nom, int edat, boolean teAlergia, int minutsSimptomes) {
        super(nom, edat, teAlergia);
        this.minutsSimptomes = minutsSimptomes;
        calcularPuntsAddicionals();
    }

    @Override
    public void calcularPuntsAddicionals() {
        double extra = (minutsSimptomes / 5) * 0.1;
        setGrauUrgencia(getGrauUrgencia() + extra);
    }

    public int getMinutsSimptomes() { return minutsSimptomes; }

    @Override
    public String toString() {
        return "[Cardiologia] " + super.toString() + ", Minuts símptomes: " + minutsSimptomes;
    }
}