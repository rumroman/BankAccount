/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccount;

import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author Rum
 */
public class ChangeAccountDialog extends javax.swing.JDialog {

    private Bank bank;
    private BankGUI bankGUI;
    private ListSelectionModel listSelectionModelAccount;
    private int indexAccountBank;

    /**
     * Creates new form PutMoneyDialog
     *
     * @param bank
     * @param bankGUI
     */
    public ChangeAccountDialog(Bank bank, BankGUI bankGUI) {
        this.bankGUI = bankGUI;
        this.bank = bank;
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonOkChangeAccount = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListBankAccounts = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Положить деньги");

        jButtonOkChangeAccount.setText("Ok");
        jButtonOkChangeAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkChangeAccountActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jListBankAccounts);
        jListBankAccounts.setModel(new DefaultComboBoxModel<>(bank.getNamesBankAccounts()));
        listSelectionModelAccount = jListBankAccounts.getSelectionModel();
        listSelectionModelAccount.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModelAccount.addListSelectionListener((ListSelectionEvent e) -> {
            if (e.getValueIsAdjusting() == false) {
                indexAccountBank = jListBankAccounts.getSelectedIndex();

            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonOkChangeAccount)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonOkChangeAccount)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOkChangeAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkChangeAccountActionPerformed
        if (bank.isOpenBankAccount()) {
            bank.setCurrentBankAccount(indexAccountBank);
            bankGUI.updateLabelSum();
            bankGUI.updateLabelWelcome();
            bankGUI.updateLabelPercent();
        }
        dispose();
    }//GEN-LAST:event_jButtonOkChangeAccountActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonOkChangeAccount;
    private javax.swing.JList<String> jListBankAccounts;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}