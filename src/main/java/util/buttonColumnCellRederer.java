/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author kauan
 */
public class buttonColumnCellRederer extends DefaultTableCellRenderer {
    
    private String buttonType;
    
    public buttonColumnCellRederer(String ButtonType){
        this.buttonType = buttonType;
    }

    public String getButtonType() {
        return buttonType;
    }

    public void setButtonType(String buttonType) {
        this.buttonType = buttonType;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        //Cells are by default rendered as a JLabel.
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, 
                value, isSelected, hasFocus, row, col);
        
        label.setHorizontalAlignment(JLabel.CENTER);
        
         if (col == 4) { // Coluna 4 - Ícone de "edit"
            label.setIcon(new ImageIcon(getClass().getResource("/imagens/edit.png")));
        } else if (col == 5) { // Coluna 5 - Ícone de "delete"
            label.setIcon(new ImageIcon(getClass().getResource("/imagens/delete.png")));
        } else {
            label.setIcon(null); // Remover ícone se não for a coluna 4 ou 5
        }
      
        /*label.setIcon(new javax.swing.ImageIcon(getClass().
              getResource("/imagens/" + buttonType + "delete.png")));*/
      
       
       /*label.setIcon(new javax.swing.ImageIcon(String.valueOf(getClass().
              getResourceAsStream("/imagens" + buttonType + "delete.png"))));*/
       
        /*label.setIcon(new javax.swing.ImageIcon(getClass().
              getResource("/imagens/delete.png")));*/
        
       /* InputStream input = getClass().getResourceAsStream("/imagens/edit.png");
        BufferedImage image = ImageIO.read(input);
        JTableTasks.getColumnModel().getColumn(4).setIcon(new ImageIcon(image));*/
       

        //Return the JLabel which renders the cell.
        return label;
    }
    
}
