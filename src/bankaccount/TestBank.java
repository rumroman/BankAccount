/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccount;

/**
 *
 * @author Rum
 */
public class TestBank {
    
    public static void main(String args[]){
        
        Bank bank = new Bank();
        BankGUI bankGUI = new BankGUI(bank);
        bankGUI.setVisible(true);
        bankGUI.setLocationRelativeTo(null);         
    }
    
}
