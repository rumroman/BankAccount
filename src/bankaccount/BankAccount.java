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
public class BankAccount {
    
    private String name;                // Имя
    private String accountNumber;       // Номер счета
    private double percent;        // Процент
    private double balance;         // Сумма
    
    
    public BankAccount(String name, String accountNumber,double percentMonth,double sumInRubles){       // Конструктор с параметрами.
        this.name = name;
        this.accountNumber = accountNumber;
        this.percent = percentMonth;
        this.balance = sumInRubles;
    }
    
    public void setName(String name){       // Задать имя владельца
        this.name = name;
    }
    
    public String getName(){                // Получить имя владельца
        return name;
    }
    
    public String getAccountNumber(){       // Получить номер счета
        return accountNumber;
    }
    
    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }
    
    public void setPercent(double percentMonth){   // Задать процент
        this.percent = percentMonth;
    }
    
    public double getPercent(){            // Получить процент
        return percent;
    }
    
    public double getBalance(){         // Получить сумму 
        return balance;
    }
    
    public void setBalance(double sumInRubles){    // Операция c деньгами, 
                                                         // если значение параметра положительное число , то операция зачисления,
                                                         // если отрицательное , то операция снятия.
        this.balance += sumInRubles;
    }
    
    public Object[] getValuesGood(){        // Получение массива всех полей класса, для сохранения в файле.
        Object[] arrValues = new Object[] {name,accountNumber,percent,balance};
        return arrValues;
    }
    
    @Override
    public String toString(){
        return name;
    }
    
    
    
    
}
