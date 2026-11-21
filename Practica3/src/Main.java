import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public interface Main {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SalaEspera sala = new SalaEspera();
        int opcio = 0;

        do {
            System.out.println("  GESTIO SALA D'ESPERA");
            System.out.println("1. Afegir un nou pacient");
            System.out.println("2. Incrementar el grau d'urgencia a tothom");
            System.out.println("3. Atendre el pacient mes urgent");
            System.out.println("4. Mostrar tots els pacients");
            System.out.println("5. Sortir");
            System.out.print("Tria una opcio: ");

            try {
                opcio = scanner.nextInt();
                scanner.nextLine();

                switch (opcio) {
                    case 1:
                        System.out.print("Nom del pacient: ");
                        String nom = scanner.nextLine();
                        System.out.print("Edat: ");
                        int edat = scanner.nextInt();
                        System.out.print("Té al·lèrgia a medicaments? (true/false): ");
                        boolean alergia = scanner.nextBoolean();
                        scanner.nextLine();

                        System.out.println("Tria la categoria:");
                        System.out.println("1. Ginecologia");
                        System.out.println("2. Traumatologia");
                        System.out.println("3. Cardiologia");
                        System.out.println("4. Altres");
                        System.out.print("Opció de categoria: ");
                        int cat = scanner.nextInt();
                        scanner.nextLine();

                        Pacient p = null;
                        if (cat == 1) {
                            System.out.print("És el primer fill? (true/false): ");
                            boolean pFill = scanner.nextBoolean();
                            System.out.print("Té diabetis gestacional? (true/false): ");
                            boolean dGestacional = scanner.nextBoolean();
                            p = new Ginecologia(nom, edat, alergia, pFill, dGestacional);
                        } else if (cat == 2) {
                            System.out.print("Zona del cos lesionada: ");
                            String zona = scanner.nextLine();
                            p = new Traumatologia(nom, edat, alergia, zona);
                        } else if (cat == 3) {
                            System.out.print("Minuts des de l'inici dels símptomes: ");
                            int minuts = scanner.nextInt();
                            p = new Cardiologia(nom, edat, alergia, minuts);
                        } else if (cat == 4) {
                            System.out.print("Breu explicació del cas: ");
                            String exp = scanner.nextLine();
                            p = new Altres(nom, edat, alergia, exp);
                        } else {
                            System.out.println("Categoria no vàlida.");
                            break;
                        }

                        if (p != null) {
                            sala.afegirPacient(p);
                            System.out.println("Pacient afegit correctament a la sala d'espera.");
                        }
                        break;

                    case 2:
                        System.out.print("Introdueix els punts a sumar a tothom: ");
                        double punts = scanner.nextDouble();
                        sala.incrementarUrgencia(punts);
                        System.out.println("S'han actualitzat els graus d'urgència.");
                        break;

                    case 3:
                        Pacient ates = sala.treurePacientMesUrgent();
                        if (ates != null) {
                            System.out.println("--> Pacient derivat a tractament: " + ates);
                            try (PrintWriter pw = new PrintWriter(new FileWriter("pacients_atesos.txt", true))) {
                                pw.println(ates.toString());
                            }
                        } else {
                            System.out.println("No hi ha pacients esperant a la sala.");
                        }
                        break;

                    case 4:
                        sala.mostrarTots();
                        break;

                    case 5:
                        System.out.println("Sortint de l'aplicació...");
                        break;

                    default:
                        System.out.println("Opció no vàlida. Torna-ho a provar.");
                }
            } catch (SalaPlenaException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Error en introduir les dades: " + e.getMessage());
                scanner.nextLine(); // Neteja buffer en cas d'error de format
            }
        } while (opcio != 5);

        scanner.close();
    }
}