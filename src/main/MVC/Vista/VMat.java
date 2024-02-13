package Vista;

import Controlador.CMat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VMat extends JFrame implements IMat, ActionListener {
    private JTextField tNumA,tNumB,tResp;
    private JButton btnCalcular, btnLimpiar, btnSalir;
    private JRadioButton rSuma, rResta, rMul, rDiv;

    public VMat(){
        super("MVC");
        this.setSize(370,350);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //norte
        JLabel lTitulo = new JLabel("Operaciones Matemáticas", JLabel.CENTER);
        lTitulo.setFont(new Font("Arial", Font.BOLD+Font.ITALIC, 16));
        this.add(lTitulo, BorderLayout.NORTH);

        //sur
        JPanel pBot = new JPanel();
        btnCalcular = new JButton("Calcular");
        btnCalcular.setActionCommand(CALCULAR);
        pBot.add(btnCalcular);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(this);
        pBot.add(btnLimpiar);

        btnSalir = new JButton("Salir");
        btnSalir.addActionListener(this);
        pBot.add(btnSalir);

        this.add(pBot, BorderLayout.SOUTH);

        //centro
        JPanel pForm = new JPanel(new GridLayout(4,1,5,5));

        JPanel pFila1 = new JPanel();
        pFila1.add(new JLabel("Número A: "));
        tNumA = new JTextField(20);
        pFila1.add(tNumA);
        pForm.add(pFila1);

        JPanel pFila2 = new JPanel();
        pFila2.add(new JLabel("Número B: "));
        tNumB = new JTextField(20);
        pFila2.add(tNumB);
        pForm.add(pFila2);

        JPanel pFila3 = new JPanel();
        pFila3.setBorder(BorderFactory.createTitledBorder("Operaciónes"));
        ButtonGroup bg = new ButtonGroup();
        rSuma = new JRadioButton("Suma");
        rResta = new JRadioButton("Resta");
        rMul = new JRadioButton("Multiplicación");
        rDiv = new JRadioButton("División");
        bg.add(rSuma);
        bg.add(rResta);
        bg.add(rMul);
        bg.add(rDiv);
        pFila3.add(rSuma);
        pFila3.add(rResta);
        pFila3.add(rMul);
        pFila3.add(rDiv);
        pForm.add(pFila3);

        JPanel pFila4 = new JPanel();
        pFila4.add(new JLabel("Respuesta: "));
        tResp = new JTextField(20);
        pFila4.add(tResp);
        pForm.add(pFila4);
        this.add(pForm, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object bt= e.getSource();
        if(bt == btnSalir)
            System.exit(0);

        if(bt == btnLimpiar){
            tNumA.setText("");
            tNumB.setText("");
            tResp.setText("");
            rSuma.setSelected(true);
        }
    }

    @Override
    public double getA() {
        double a = 0.0;
        try {
            a = Double.parseDouble(tNumA.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "DEVE ESCRIBIR UN NUMERO.");
        }
        return a;
    }

    @Override
    public double getB() {
        double b = 0.0;
        try {
            b = Double.parseDouble(tNumB.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "DEVE ESCRIBIR UN NUMERO.");
        }
        return b;
    }

    @Override
    public char getOperacion() {
        char op = ' ';
        if(rSuma.isSelected())
            op = '+';
        else if(rResta.isSelected())
            op = '-';
        else if(rMul.isSelected())
            op = '*';
        else if(rDiv.isSelected())
            op = '/';
        return op;
    }

    @Override
    public void setRespuesta(double resp) {
        tResp.setText(resp+"");
    }

    @Override
    public void arrancar() {
        this.setVisible(true);
    }

    @Override
    public void setControlador(CMat c) {
        btnCalcular.addActionListener(c);
    }
}
