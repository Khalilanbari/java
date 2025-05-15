import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Exercice 2 – Calcul de la durée d’un voyage.
 * <p>L’utilisateur entre un temps de départ et d’arrivée (hh:mm:ss).
 * Le programme affiche la durée.</p>
 * <p>Menus : Application→Initialiser (Ctrl + I), Fermer (Ctrl + F) ;
 * Info→À-propos.</p>
 * 
 * @author khalil Anbari - groupe tp 1
 */
public class DureeVoyage extends JFrame implements ActionListener {

    private final JTextField dh = new JTextField(2);
    private final JTextField dm = new JTextField(2);
    private final JTextField ds = new JTextField(2);
    private final JTextField ah = new JTextField(2);
    private final JTextField am = new JTextField(2);
    private final JTextField as = new JTextField(2);
    private final JTextField rh = new JTextField(2);
    private final JTextField rm = new JTextField(2);
    private final JTextField rs = new JTextField(2);
    private final JButton btnCalc = new JButton("Calculer la durée");

    public DureeVoyage() {
        super("Durée du voyage");
        setSize(450, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));


        JPanel pDep = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pDep.setBorder(BorderFactory.createTitledBorder("Départ (hh:mm:ss)"));
        pDep.add(new JLabel("H :")); pDep.add(dh);
        pDep.add(new JLabel("M :")); pDep.add(dm);
        pDep.add(new JLabel("S :")); pDep.add(ds);

     
        JPanel pArr = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pArr.setBorder(BorderFactory.createTitledBorder("Arrivée (hh:mm:ss)"));
        pArr.add(new JLabel("H :")); pArr.add(ah);
        pArr.add(new JLabel("M :")); pArr.add(am);
        pArr.add(new JLabel("S :")); pArr.add(as);

 
        JPanel pDur = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pDur.setBorder(BorderFactory.createTitledBorder("Durée"));
        pDur.add(new JLabel("H :")); pDur.add(rh);
        pDur.add(new JLabel("M :")); pDur.add(rm);
        pDur.add(new JLabel("S :")); pDur.add(rs);
        rh.setEditable(false);
        rm.setEditable(false);
        rs.setEditable(false);

    
        JMenuBar mb = new JMenuBar();
        JMenu menuApp = new JMenu("Application");
        JMenuItem init = new JMenuItem("Initialiser");
        init.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
        init.addActionListener(e -> initialiser());
        JMenuItem close = new JMenuItem("Fermer");
        close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
        close.addActionListener(e -> System.exit(0));
        menuApp.add(init);
        menuApp.addSeparator();
        menuApp.add(close);

        JMenu menuInfo = new JMenu("Info");
        JMenuItem about = new JMenuItem("À-propos");
        about.addActionListener(e ->
            JOptionPane.showMessageDialog(this,
                "DureeVoyage\nAuteur : Khalil Anbari\nGroupe TP : 1 ",
                "À-propos",
                JOptionPane.INFORMATION_MESSAGE)
        );
        menuInfo.add(about);

        mb.add(menuApp);
        mb.add(menuInfo);
        setJMenuBar(mb);

        // Assemblage
        add(pDep);
        add(pArr);
        add(btnCalc);
        add(pDur);

        btnCalc.addActionListener(this);
        setVisible(true);
    }

    private void initialiser() {
        dh.setText(""); dm.setText(""); ds.setText("");
        ah.setText(""); am.setText(""); as.setText("");
        rh.setText(""); rm.setText(""); rs.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int h1 = Integer.parseInt(dh.getText());
            int m1 = Integer.parseInt(dm.getText());
            int s1 = Integer.parseInt(ds.getText());
            int h2 = Integer.parseInt(ah.getText());
            int m2 = Integer.parseInt(am.getText());
            int s2 = Integer.parseInt(as.getText());

            if (!valid(h1, m1, s1) || !valid(h2, m2, s2)) {
                JOptionPane.showMessageDialog(this,
                    "Temps invalide (h:0-23, m/s:0-59) !",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            int t1 = h1*3600 + m1*60 + s1;
            int t2 = h2*3600 + m2*60 + s2;
            if (t2 < t1) t2 += 24*3600;
            int diff = t2 - t1;

            rh.setText(String.valueOf(diff/3600));
            rm.setText(String.valueOf((diff%3600)/60));
            rs.setText(String.valueOf(diff%60));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Veuillez saisir des entiers",
                "Erreur",
                JOptionPane.WARNING_MESSAGE);
        }
    }

    private boolean valid(int h, int m, int s) {
        return h >= 0 && h < 24 && m >= 0 && m < 60 && s >= 0 && s < 60;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DureeVoyage::new);
    }
}
