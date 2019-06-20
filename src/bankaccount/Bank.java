/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccount;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author Rum
 */
public class Bank {

    private double dollarRate;                     // Курс доллара
    private double euroRate;                       // Курс евро
    private BankAccount[] arrayBankAccount;        // Массив счетов в банке
    private int sizeBankAccountOpen;               // Сколько открыто счетов
    private int maxSizeBankAccount;                // Максимальный размер массива
    private int currentBankAccount;                // Текущий счет
    private int numberInc;                         // Генератор нового номера счета    

    public Bank() {         // Конструктор по умолчанию, дли инициализации данных
        numberInc = 1;
        maxSizeBankAccount = 10;
        arrayBankAccount = new BankAccount[maxSizeBankAccount];
        sizeBankAccountOpen = 0;
        currentBankAccount = -1;
        dollarRate = 58.82;
        euroRate = 69.14;
    }
    
    public void createBankAccount(String name, double percentMonth, double sumInRubles) {   // Открываем новый счет        

        while (searchByAccountNumber(Integer.toString(numberInc)) != null) {
            numberInc++;
        }
        BankAccount bankAccount = new BankAccount(name, Integer.toString(numberInc), percentMonth, sumInRubles);
        arrayBankAccount[sizeBankAccountOpen] = bankAccount;    // Заносим в массив счетов
        setCurrentBankAccount(sizeBankAccountOpen);               // Определяем в каком мы счете находимся
        sizeBankAccountOpen++;                                  // Увеличиваем количество открытых счетов на 1  
        numberInc++;                                        // Увеличиваем генератор на 1
        if (maxSizeBankAccount == sizeBankAccountOpen) {          // Если массив заполнен,то создаем массив в 3 раза больше и переопределяем старый
            maxSizeBankAccount = maxSizeBankAccount * 3;
            arrayBankAccount = Arrays.copyOf(arrayBankAccount, maxSizeBankAccount);
        }
    }

    public void createBankAccount(String name, String accountNumber, double percentMonth, double sumInRubles) {   // Открываем сохраненный счет
        BankAccount bankAccount = new BankAccount(name, accountNumber, percentMonth, sumInRubles);
        arrayBankAccount[sizeBankAccountOpen] = bankAccount;    // Заносим в массив счетов
        setCurrentBankAccount(sizeBankAccountOpen);               // Определяем в каком мы счете находимся
        numberInc = Integer.parseInt(accountNumber);
        sizeBankAccountOpen++;                                  // Увеличиваем количество открытых счетов на 1  
        numberInc++;                                        // Увеличиваем генератор на 1
        if (maxSizeBankAccount == sizeBankAccountOpen) {          // Если массив заполнен,то создаем массив в 3 раза больше и переопределяем старый
            maxSizeBankAccount = maxSizeBankAccount * 3;
            arrayBankAccount = Arrays.copyOf(arrayBankAccount, maxSizeBankAccount);
        }
    }

    public boolean changeBankAccount(String name) {            // Сменить владельца
        boolean stateChangeAccount = false;
        for (int i = 0; i < sizeBankAccountOpen; i++) {
            if (arrayBankAccount[i].getName().equals(name)) {
                stateChangeAccount = true;
                setCurrentBankAccount(i);
                break;
            }
        }
        return stateChangeAccount;
    }

    public double getDollarRate() {                 //Вернуть курс доллара
        return dollarRate;
    }

    public void setDollarRate(double dollarRate) {  // Установить курс доллара
        this.dollarRate = dollarRate;
    }

    public double getEuroRate() {                   //Вернуть курс евро
        return euroRate;
    }

    public void setEuroRate(double euroRate) {      // Установить курс евро
        this.euroRate = euroRate;
    }    

    public boolean putMoney(double money) {            // Положить деньги на счет
        boolean stateWithdrawMoney = false;
        if (getCurrentBankAccount() > -1) {
            arrayBankAccount[getCurrentBankAccount()].setBalance(money);
            stateWithdrawMoney = true;
        }
        return stateWithdrawMoney;
    }

    public boolean withdrawMoney(double money) {            // Снять деньги
        boolean stateWithdrawMoney = false;
        if (getCurrentBankAccount() > -1) {
            if (arrayBankAccount[getCurrentBankAccount()].getBalance() >= money) {
                money = -money;
                arrayBankAccount[getCurrentBankAccount()].setBalance(money);
                stateWithdrawMoney = true;
            }
        }
        return stateWithdrawMoney;
    }

    public boolean calculatePercent() {             // Вычислить проценты
        boolean stateCalculatePercent = false;
        if (arrayBankAccount[getCurrentBankAccount()].getBalance() > 0) {
            double currentPercent = arrayBankAccount[getCurrentBankAccount()].getPercent();
            double sumInRubles = arrayBankAccount[getCurrentBankAccount()].getBalance();
            double sumPercent = sumInRubles / 100 * currentPercent;
            arrayBankAccount[getCurrentBankAccount()].setBalance(sumPercent);
            stateCalculatePercent = true;
        }
        return stateCalculatePercent;
    }

    public String getOwnerName() {
        return arrayBankAccount[getCurrentBankAccount()].getName();
    }

    public String transferSumIntoDollars() {       // Перевести сумму в доллары
        String sumInDollar = String.format("%.2f", arrayBankAccount[getCurrentBankAccount()].getBalance() / dollarRate);
        sumInDollar += " $";
        return sumInDollar;
    }

    public String transferSumIntoEuro() {          // Перевести сумму в евро
        String sumInEuro = String.format("%.2f", arrayBankAccount[getCurrentBankAccount()].getBalance() / euroRate);
        sumInEuro += " €";
        return sumInEuro;
    }

    public double getSum() {                     // Получить сумму текущего счета
        return arrayBankAccount[getCurrentBankAccount()].getBalance();
    }

    public int getSizeOpenBankAccount() {       // Получить количесто открытых счетов
        return sizeBankAccountOpen;
    }

    public String getPercent() {                // Получить процент текущего счета
        return arrayBankAccount[getCurrentBankAccount()].getPercent() + "";
    }

    private BankAccount searchByAccountNumber(String accountNumber) {   // Найти банковский счет по номеру счета
        
        BankAccount foundBankAccount = null;
        if (sizeBankAccountOpen > 0) {
            for (int i = 0; i > sizeBankAccountOpen; i++) {                
                if (arrayBankAccount[i].getAccountNumber().equals(accountNumber)) {
                    foundBankAccount = arrayBankAccount[i];
                    break;
                }                
            }
        }
        return foundBankAccount;
    }
   
    public String[] getNamesBankAccounts(){         // Получить массив имен владельцев счетов
        String arNames[] = new String[sizeBankAccountOpen];
        for(int p = 0; p < sizeBankAccountOpen; p ++){
            arNames[p] = arrayBankAccount[p].getName();            
        }
        return arNames;
    }
    
    public void setCurrentBankAccount(int currentBankAccount){      // Установить текущий счет
        this.currentBankAccount = currentBankAccount;
    }
    
    public int getCurrentBankAccount(){
        return currentBankAccount;
    }
    
    public boolean isOpenBankAccount(){             // Проверка , открыт хотя бы один счет? 
        return (sizeBankAccountOpen > 0);
    }

    public void saveInFileTxt(File file) {          // Сохранение счетов в файл. Сохраняет все открытые счета,в отдельные файлы.

        for (int i = 0; i < sizeBankAccountOpen; i++) {
            String path = file.getAbsolutePath() + "\\" + arrayBankAccount[i].getName();
            if (!path.endsWith(".txt")) {
                path = path + ".txt";
            }

            try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(path))) {                
                Object[] bankAccounts = arrayBankAccount[i].getValuesGood();
                fileWriter.write(new Date().toString() + "\r\n");
                for (int jj = 0; jj < bankAccounts.length; jj++) {
                    fileWriter.write(bankAccounts[jj].toString() + "\r\n");
                }                
                fileWriter.flush();

            } catch (InvalidPathException e) {
                System.out.println("Неверный путь файла");
            } catch (IOException e) {
                System.out.println("Ошибка ввода-вывода");
            }
        }
    }

    public String openFileTxt(File file) {      // Загрузить счет из файла.
        String stateOpen;
        String name;
        String accountNumber;
        double percentMonth;
        double sumInRubles;

        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
            fileReader.readLine();
            name = fileReader.readLine();
            accountNumber = fileReader.readLine();
            percentMonth = Double.parseDouble(fileReader.readLine());
            sumInRubles = Double.parseDouble(fileReader.readLine());

        } catch (InvalidPathException e) {
            System.out.println("Неверный путь файла");
            return stateOpen = "Неверный путь файла.";
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода");
            return stateOpen = "Ошибка ввода-вывода.";
        }

        if (searchByAccountNumber(accountNumber) != null) {
            return stateOpen = "Файл с таким счетом уже существует.";
        }

        createBankAccount(name, accountNumber, percentMonth, sumInRubles);

        return stateOpen = "Файл успешно загружен.";
    }
    
     public static boolean isNumeric(String s) throws NumberFormatException {   // Статический метод - Проверка , является ли строка числом.,
                                                                                        // если да, то возвращает true.
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
}
