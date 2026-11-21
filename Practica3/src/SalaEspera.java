import java.io.Serializable;

public class SalaEspera implements Serializable {
    private static final int CAPACITAT_MAXIMA = 30;
    private Pacient[] pacients;
    private int quantitat;

    public SalaEspera() {
        pacients = new Pacient[CAPACITAT_MAXIMA];
        quantitat = 0;
    }


    public void afegirPacient(Pacient p) throws SalaPlenaException {
        if (quantitat >= CAPACITAT_MAXIMA) {
            throw new SalaPlenaException("Error: La sala d'espera està plena (màxim 30 persones)[cite: 1, 2].");
        }
        pacients[quantitat] = p;
        quantitat++;
    }

    public void incrementarUrgencia(double punts) {
        for (int i = 0; i < quantitat; i++) {
            pacients[i].setGrauUrgencia(pacients[i].getGrauUrgencia() + punts);
        }
    }


    public Pacient treurePacientMesUrgent() {
        if (quantitat == 0) return null;

        int indexMax = 0;
        for (int i = 1; i < quantitat; i++) {
            if (pacients[i].getGrauUrgencia() > pacients[indexMax].getGrauUrgencia()) {
                indexMax = i;
            }
        }

        Pacient urgent = pacients[indexMax];

        for (int i = indexMax; i < quantitat - 1; i++) {
            pacients[i] = pacients[i + 1];
        }
        pacients[quantitat - 1] = null;
        quantitat--;

        return urgent;
    }

    public void mostrarTots() {
        if (quantitat == 0) {
            System.out.println("La sala d'espera està buida.");
            return;
        }
        for (int i = 0; i < quantitat; i++) {
            System.out.println((i + 1) + ". " + pacients[i]);
        }
    }

    public int getQuantitat() { return quantitat; }
    public Pacient getPacient(int index) { return pacients[index]; }
}
