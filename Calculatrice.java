import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * Exercice 1 – Calculatrice Swing basique.
 * <p>Permet d’effectuer +, -, ×, ÷ sur deux nombres réels et
 * d’afficher le nom de l’opération et le résultat.</p>
 * <p>Menus : Application→Initialiser (Ctrl + I), Fermer (Ctrl + F) ;
 * Info→À-propos.</p>
 * 
 * @author Khalil Anbari - groupe tp 1
 */


public class Calculatrice extends JFrame implements ActionListener {

    private final JTextField champA    = new JTextField();
    private final JTextField champB    = new JTextField();
    private final JTextField champOp   = new JTextField();
    private final JTextField champRes  = new JTextField();
    private final JButton    btnAdd    = new JButton("a + b");
    private final JButton    btnSub    = new JButton("a - b");
    private final JButton    btnMul    = new JButton("a × b");
    private final JButton    btnDiv    = new JButton("a ÷ b");

    public Calculatrice() {
        super("Calculatrice");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5));

        champOp.setEditable(false);
        champRes.setEditable(false);

        btnAdd.addActionListener(this);
        btnSub.addActionListener(this);
        btnMul.addActionListener(this);
        btnDiv.addActionListener(this);

        JMenuBar menuBar = new JMenuBar();
        JMenu appMenu = new JMenu("Application");
        JMenuItem initItem = new JMenuItem("Initialiser");
        initItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
        initItem.addActionListener(e -> initialiser());
        JMenuItem closeItem = new JMenuItem("Fermer");
        closeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
        closeItem.addActionListener(e -> System.exit(0));
        appMenu.add(initItem);
        appMenu.addSeparator();
        appMenu.add(closeItem);

        JMenu infoMenu = new JMenu("Info");
        JMenuItem aboutItem = new JMenuItem("À-propos");
        aboutItem.addActionListener(e ->
            JOptionPane.showMessageDialog(this,
                "mini Calculatrice\nAuteur : Khalil Anbari\nGroupe TP : 1 ",
                "À propos",
                JOptionPane.INFORMATION_MESSAGE)
        );
        infoMenu.add(aboutItem);

        menuBar.add(appMenu);
        menuBar.add(infoMenu);
        setJMenuBar(menuBar);

        // Ajout composants
        add(new JLabel("A ="));    add(champA);
        add(new JLabel("B ="));    add(champB);
        add(btnAdd);               add(btnSub);
        add(btnMul);               add(btnDiv);
        add(new JLabel("Opération :")); add(champOp);
        add(new JLabel("Résultat :"));  add(champRes);

        setVisible(true);
    }

    private void initialiser() {
        champA.setText("");
        champB.setText("");
        champOp.setText("");
        champRes.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double a = Double.parseDouble(champA.getText());
            double b = Double.parseDouble(champB.getText());
            String op;
            double res;

            if (e.getSource() == btnAdd) {
                op  = "Addition";
                res = a + b;
            } else if (e.getSource() == btnSub) {
                op  = "Soustraction";
                res = a - b;
            } else if (e.getSource() == btnMul) {
                op  = "Multiplication";
                res = a * b;
            } else {
                if (b == 0) {
                    JOptionPane.showMessageDialog(this,
                        "Division par zéro impossible !",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                op  = "Division";
                res = a / b;
            }

            champOp.setText(op);
            champRes.setText(String.valueOf(res));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Veuillez entrer des nombres valides !",
                "Erreur de saisie",
                JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculatrice::new);
    }
}
