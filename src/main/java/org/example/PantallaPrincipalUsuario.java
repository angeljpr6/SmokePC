package org.example;

import javax.swing.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class PantallaPrincipalUsuario {
    private JTextField textField1;
    private JButton verMasButton;
    private JScrollBar scrollBar1;

    public PantallaPrincipalUsuario() {
        scrollBar1.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {

            }
        });
    }
}
