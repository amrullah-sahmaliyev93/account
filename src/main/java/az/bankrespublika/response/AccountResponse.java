package az.bankrespublika.response;

public class AccountResponse {
    private String name;
    private String surname;
    private String accountNumber;
    private Double balance;

    public AccountResponse() {
    }

    public AccountResponse(String name, String surname, String accountNumber, Double balance) {
        this.name = name;
        this.surname = surname;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}