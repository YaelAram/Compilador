package ui;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Estilo implements FocusListener, MouseListener {
  private final Color greyLight = new Color(230, 230, 230);
  private final Color grey = new Color(212, 212, 212);
  private final Color greyDark = new Color(55, 58, 64);
  private final Color black = new Color(0, 0, 0);
  private final Font primaryFont = new Font("Roboto", Font.PLAIN, 16);
  private final Font primaryFontBold = new Font("Roboto", Font.BOLD, 16);
  private final JFrame jFrame;

  public Estilo(JFrame jFrame){
    this.jFrame = jFrame;
  }

  // Frame
  public void frameEstilo(String title, int exitMode, int[] bound){
    this.jFrame.setBounds(bound[0], bound[1], bound[2], bound[3]);
    this.jFrame.setTitle(title);
    this.jFrame.getContentPane().setBackground(this.greyLight);
    this.jFrame.setLayout(null);
    this.jFrame.setDefaultCloseOperation(exitMode);
    this.jFrame.setLocationRelativeTo(null);
  }

  //TextField
  public void activoTextComponentEstilo(JTextComponent jTextField){
      jTextField.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, this.greyDark));
      jTextField.setBackground(this.greyLight);
      jTextField.setForeground(this.black);
  }

  public void inactivoTextComponentEstilo(JTextComponent jTextField){
    jTextField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, this.greyDark));
    jTextField.setBackground(this.grey);
    jTextField.setForeground(this.greyDark);
  }

  public void textComponentEstilo(JTextComponent jTextField, int[] bound){
    jTextField.setBounds(bound[0], bound[1], bound[2], bound[3]);
    jTextField.setFont(this.primaryFont);
    jTextField.setForeground(this.black);
    inactivoTextComponentEstilo(jTextField);
    jTextField.setText(jTextField.getName());
    jTextField.addFocusListener(this);
    jFrame.add(jTextField);
  }

  // Button
  public void activoButtonEstilo(JButton jButton){
    jButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, this.greyDark));
    jButton.setBackground(this.greyDark);
    jButton.setForeground(this.greyLight);
  }

  public void inactivoButtonEstilo(JButton jButton){
    jButton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, this.greyDark));
    jButton.setBackground(this.greyLight);
    jButton.setForeground(this.greyDark);
  }

  public void buttonEstilo(JButton jButton, int[] bound, String text){
    jButton.setBounds(bound[0], bound[1], bound[2], bound[3]);
    jButton.setText(text);
    jButton.setFocusable(false);
    jButton.setFont(this.primaryFontBold);
    inactivoButtonEstilo(jButton);
    jButton.addMouseListener(this);
    jFrame.add(jButton);
  }

  //Focus Events
  @Override
  public void focusGained(FocusEvent focusEvent) {
    try{
      activoTextComponentEstilo((JTextComponent) focusEvent.getSource());
    }
    catch (Exception error){
      System.out.println(error.getMessage());
      error.printStackTrace();
    }
  }

  @Override
  public void focusLost(FocusEvent focusEvent) {
    try{
      inactivoTextComponentEstilo((JTextComponent) focusEvent.getSource());
    }
    catch (Exception error){
      System.out.println(error.getMessage());
      error.printStackTrace();
    }
  }

  //Mouse Events
  @Override
  public void mouseClicked(MouseEvent e) {
  }

  @Override
  public void mousePressed(MouseEvent e) {
  }

  @Override
  public void mouseReleased(MouseEvent e) {
  }

  @Override
  public void mouseEntered(MouseEvent mouseEvent) {
    try{
      activoButtonEstilo((JButton) mouseEvent.getSource());
    }
    catch (Exception error){
      System.out.println(error.getMessage());
      error.printStackTrace();
    }
  }

  @Override
  public void mouseExited(MouseEvent mouseEvent) {
    try{
      inactivoButtonEstilo((JButton) mouseEvent.getSource());
    }
    catch (Exception error){
      System.out.println(error.getMessage());
      error.printStackTrace();
    }
  }
}
