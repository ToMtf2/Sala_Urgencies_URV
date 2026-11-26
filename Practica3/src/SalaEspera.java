import java.io.Serializable;

/**
 * Sala d'espera d'urgències. Guarda els pacients en una llista implementada
 * amb una taula de mida fixa (30 places) i un comptador d'elements ocupats.
 * Les posicions de 0 a quantitat-1 contenen pacients; la resta són buides (null).
 */
public class SalaEspera implements Serializable {
    /** Nombre màxim de pacients que caben a la sala. */
    private static final int CAPACITAT_MAXIMA = 30;
    /** Taula on es guarden els pacients. */
    private Pacient[] pacients;
    /** Nombre de pacients que hi ha ara mateix a la sala. */
    private int quantitat;

    /**
     * Constructor: crea una sala d'espera buida amb capacitat per a 30 pacients.
     */
    public SalaEspera() {
        pacients = new Pacient[CAPACITAT_MAXIMA];
        quantitat = 0;
    }

    /**
     * Afegeix un pacient al final de la llista.
     * @param p pacient que arriba a la sala d'espera
     * @throws SalaPlenaException si la sala ja té el màxim de pacients
     */
    public void afegirPacient(Pacient p) throws SalaPlenaException {
        if (quantitat >= CAPACITAT_MAXIMA) {
            throw new SalaPlenaException("Error: La sala d'espera està plena (màxim 30 persones).");
        }
        pacients[quantitat] = p;
        quantitat++;
    }

    /**
     * Suma els punts indicats al grau d'urgència de tots els pacients de la sala.
     * @param punts punts que se sumen a cada pacient
     */
    public void incrementarUrgencia(double punts) {
        for (int i = 0; i < quantitat; i++) {
            pacients[i].setGrauUrgencia(pacients[i].getGrauUrgencia() + punts);
        }
    }

    /**
     * Treu de la llista el pacient amb el grau d'urgència més alt. Les posicions
     * següents es desplacen cap a l'esquerra perquè no quedin buits a la taula.
     * En cas d'empat, surt el primer que ha arribat.
     * @return el pacient més urgent, o null si la sala és buida
     */
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

    /**
     * Compta quants pacients de la sala són d'una especialitat. L'especialitat
     * d'un pacient és el nom de la seva classe (Ginecologia, Traumatologia,
     * Cardiologia o Altres).
     * @param especialitat nom de l'especialitat (no distingeix majúscules)
     * @return nombre de pacients d'aquesta especialitat (0 si no n'hi ha cap)
     */
    public int comptarEspecialitat(String especialitat) {
        int quants = 0;
        for (int i = 0; i < quantitat; i++) {
            if (pacients[i].getClass().getSimpleName().equalsIgnoreCase(especialitat)) {
                quants++;
            }
        }
        return quants;
    }

    /**
     * Busca el grau d'urgència més alt entre els pacients d'una especialitat.
     * @param especialitat nom de l'especialitat: Ginecologia, Traumatologia,
     *                     Cardiologia o Altres (no distingeix majúscules)
     * @return el grau d'urgència més alt, o -1 si no hi ha cap pacient d'aquesta especialitat
     */
    public double getGrauMaximEspecialitat(String especialitat) {
        double max = -1;
        for (int i = 0; i < quantitat; i++) {
            if (pacients[i].getClass().getSimpleName().equalsIgnoreCase(especialitat)
                    && pacients[i].getGrauUrgencia() > max) {
                max = pacients[i].getGrauUrgencia();
            }
        }
        return max;
    }

    /**
     * Compta quants pacients de la sala tenen al·lèrgia a algun medicament.
     * @return nombre de pacients amb al·lèrgia
     */
    public int comptarAlergics() {
        int quants = 0;
        for (int i = 0; i < quantitat; i++) {
            if (pacients[i].isTeAlergia()) {
                quants++;
            }
        }
        return quants;
    }

    /**
     * Compta quantes dones de la sala tenen diabetis gestacional. Només els
     * pacients de Ginecologia tenen aquesta dada, per això es comprova el tipus
     * abans de consultar-la.
     * @return nombre de pacients de Ginecologia amb diabetis gestacional
     */
    public int comptarDiabetisGestacional() {
        int quants = 0;
        for (int i = 0; i < quantitat; i++) {
            if (pacients[i].getClass().getSimpleName().equals("Ginecologia")
                    && ((Ginecologia) pacients[i]).isDiabetisGestacional()) {
                quants++;
            }
        }
        return quants;
    }

    /**
     * Compta quants pacients de Cardiologia han arribat amb més de x minuts
     * des de l'inici dels símptomes.
     * @param x minuts que s'han de superar (estrictament)
     * @return nombre de pacients de Cardiologia amb més de x minuts de símptomes
     */
    public int comptarCardiologiaMesDeMinuts(int x) {
        int quants = 0;
        for (int i = 0; i < quantitat; i++) {
            if (pacients[i].getClass().getSimpleName().equals("Cardiologia")
                    && ((Cardiologia) pacients[i]).getMinutsSimptomes() > x) {
                quants++;
            }
        }
        return quants;
    }

    /**
     * Mostra per pantalla les dades de tots els pacients de la sala, numerats.
     */
    public void mostrarTots() {
        if (quantitat == 0) {
            System.out.println("La sala d'espera està buida.");
            return;
        }
        for (int i = 0; i < quantitat; i++) {
            System.out.println((i + 1) + ". " + pacients[i]);
        }
    }

    /**
     * Retorna el nombre de pacients que hi ha a la sala.
     * @return nombre de pacients de la llista
     */
    public int getQuantitat() { return quantitat; }

    /**
     * Retorna el pacient d'una posició de la llista.
     * @param index posició (de 0 a quantitat-1)
     * @return el pacient d'aquesta posició
     */
    public Pacient getPacient(int index) { return pacients[index]; }
}