package ru.job4j.accounts;

import java.math.BigDecimal;

/**
 * Account representation.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 01.07.17
 */
public class Account {

    /**
     * Amount of money.
     */
    private BigDecimal moneyAmount;

    /**
     * Account number.
     */
    private final long accountNumber;

    /**
     * Accounts constructor.
     *
     * @param moneyAmount   some amount of money
     * @param accountNumber some account number
     */
    Account(BigDecimal moneyAmount, long accountNumber) {
        this.moneyAmount = moneyAmount;
        this.accountNumber = accountNumber;
    }

    /**
     * Money amount getter.
     *
     * @return amount of money
     */
    BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    /**
     * Money amount setter.
     *
     * @param moneyAmount to set
     */
    void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    /**
     * Account number getter.
     *
     * @return account number as a long
     */
    long getAccountNumber() {
        return accountNumber;
    }

    /**
     * Compare by account numbers.
     *
     * @param o some object
     * @return true if accounts numbers are the same
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account account = (Account) o;

        return accountNumber == account.accountNumber;
    }

    /**
     * Generates hashcode using account number.
     *
     * @return int as hash code
     */
    @Override
    public int hashCode() {
        return (int) (accountNumber ^ (accountNumber >>> 32));
    }
}
