public class Ginecologia extends Pacient {
    private boolean primerFill;
    private boolean diabetisGestacional;

    public Ginecologia(String nom, int edat, boolean teAlergia, boolean primerFill, boolean diabetisGestacional) {
        super(nom, edat, teAlergia);
        this.primerFill = primerFill;
        this.diabetisGestacional = diabetisGestacional;
        calcularPuntsAddicionals();
    }

    @Override
    public void calcularPuntsAddicionals() {
        double extra = 0;
        if (primerFill) extra += 0.3;
        if (diabetisGestacional) extra += 0.5;
        setGrauUrgencia(getGrauUrgencia() + extra);
    }

    public boolean isDiabetisGestacional() { return diabetisGestacional; }

    @Override
    public String toString() {
        return "[Ginecologia] " + super.toString() + ", Primer fill: " + primerFill + ", Diabetis gestacional: " + diabetisGestacional;
    }
}
