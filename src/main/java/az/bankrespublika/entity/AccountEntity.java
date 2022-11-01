package az.bankrespublika.entity;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "balance")
    private Double balance;

    @JoinColumn(name = "user_id")
    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity userEntity;

    public AccountEntity() {
    }

    public AccountEntity(Long id, String accountNumber, Double balance,UserEntity userEntity) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.userEntity=userEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}