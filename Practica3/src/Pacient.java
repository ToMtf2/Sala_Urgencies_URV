import java.io.Serializable;


public abstract class Pacient implements Serializable {
    private String nom;
    private int edat;
    private boolean teAlergia;
    private double grauUrgencia;


    public Pacient(String nom, int edat, boolean teAlergia) {
        this.nom = nom;
        this.edat = edat;
        this.teAlergia = teAlergia;
        calcularUrgenciaBase();
    }


    public void calcularUrgenciaBase() {
        this.grauUrgencia = (double) this.edat / 20.0;
    }

    public abstract void calcularPuntsAddicionals();

    public String getNom() { return nom; }
    public int getEdat() { return edat; }
    public boolean isTeAlergia() { return teAlergia; }
    public double getGrauUrgencia() { return grauUrgencia; }
    
    public void setGrauUrgencia(double grauUrgencia) {
        this.grauUrgencia = grauUrgencia;
    }

    @Override
    public String toString() {
        return "Nom: " + nom + ", Edat: " + edat + ", Al·lèrgia: " + (teAlergia ? "Sí" : "No") + ", Grau Urgència: " + String.format("%.2f", grauUrgencia);
    }
}