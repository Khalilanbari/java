import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {

        String[] options = { "Calculatrice", "Durée de voyage" };

        int choix = JOptionPane.showOptionDialog(
            null,
            "Que voulez-vous lancer ?",
            "TP6 - Menu de lancement",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );

        if (choix == 0) {
            Calculatrice.main(args);
        } else if (choix == 1) {
            DureeVoyage.main(args);
        } else {
            System.exit(0);
        }
    }
}
