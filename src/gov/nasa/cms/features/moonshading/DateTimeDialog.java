/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.nasa.cms.features.moonshading;

/**
 *
 * @author Kaitlyn Dickinson
 */
public class DateTimeDialog extends javax.swing.JDialog
{

     /**
      * Creates new form DateTimeDialog
      */
     public DateTimeDialog(java.awt.Frame parent, boolean modal)
     {
          super(parent, modal);
          initComponents();
     }

     /**
      * This method is called from within the constructor to initialize the
      * form. WARNING: Do NOT modify this code. The content of this method is
      * always regenerated by the Form Editor.
      */
     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents()
     {

          jLabel2 = new javax.swing.JLabel();
          startDateTime = new javax.swing.JSpinner();
          jLabel1 = new javax.swing.JLabel();
          endDateTime = new javax.swing.JSpinner();
          jLabel3 = new javax.swing.JLabel();
          animationSpeedSlider = new javax.swing.JSlider();
          applyChangesButton = new javax.swing.JButton();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
          setLocation(new java.awt.Point(633, 180));

          jLabel2.setText("Start date/time: ");

          startDateTime.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.MINUTE));
          startDateTime.setToolTipText("Select a start date/time");

          jLabel1.setText("End date/time:");

          endDateTime.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.MINUTE));
          endDateTime.setToolTipText("Select a start date/time");

          jLabel3.setText("Animation Speed: ");

          animationSpeedSlider.addChangeListener(new javax.swing.event.ChangeListener()
          {
               public void stateChanged(javax.swing.event.ChangeEvent evt)
               {
                    animationSpeedSliderStateChanged(evt);
               }
          });

          applyChangesButton.setText("OK");
          applyChangesButton.addActionListener(new java.awt.event.ActionListener()
          {
               public void actionPerformed(java.awt.event.ActionEvent evt)
               {
                    applyChangesButtonActionPerformed(evt);
               }
          });

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(layout.createSequentialGroup()
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                   .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                              .addGap(18, 18, 18)
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                   .addComponent(startDateTime, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                   .addComponent(endDateTime)))
                         .addGroup(layout.createSequentialGroup()
                              .addComponent(jLabel3)
                              .addGap(18, 18, 18)
                              .addComponent(animationSpeedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addComponent(applyChangesButton))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(startDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(endDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(animationSpeedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(jLabel3))
                    .addGap(35, 35, 35)
                    .addComponent(applyChangesButton)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          );

          pack();
     }// </editor-fold>//GEN-END:initComponents

     private void animationSpeedSliderStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_animationSpeedSliderStateChanged
     {//GEN-HEADEREND:event_animationSpeedSliderStateChanged
          // TODO add your handling code here:
     }//GEN-LAST:event_animationSpeedSliderStateChanged

     private void applyChangesButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_applyChangesButtonActionPerformed
     {//GEN-HEADEREND:event_applyChangesButtonActionPerformed
          
     }//GEN-LAST:event_applyChangesButtonActionPerformed

     /**
      * @param args the command line arguments
      */
     public static void main(String args[])
     {
          /* Set the Nimbus look and feel */
          //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
          /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
           */
          try
          {
               for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
               {
                    if ("Nimbus".equals(info.getName()))
                    {
                         javax.swing.UIManager.setLookAndFeel(info.getClassName());
                         break;
                    }
               }
          } catch (ClassNotFoundException ex)
          {
               java.util.logging.Logger.getLogger(DateTimeDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex)
          {
               java.util.logging.Logger.getLogger(DateTimeDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex)
          {
               java.util.logging.Logger.getLogger(DateTimeDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex)
          {
               java.util.logging.Logger.getLogger(DateTimeDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable()
          {
               public void run()
               {
                    DateTimeDialog dialog = new DateTimeDialog(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter()
                    {
                         @Override
                         public void windowClosing(java.awt.event.WindowEvent e)
                         {
                              System.exit(0);
                         }
                    });
                    dialog.setVisible(true);
               }
          });
     }

     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JSlider animationSpeedSlider;
     private javax.swing.JButton applyChangesButton;
     private javax.swing.JSpinner endDateTime;
     private javax.swing.JLabel jLabel1;
     private javax.swing.JLabel jLabel2;
     private javax.swing.JLabel jLabel3;
     private javax.swing.JSpinner startDateTime;
     // End of variables declaration//GEN-END:variables
}
