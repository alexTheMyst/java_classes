package ru.job4j.accounts;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Simple bank representation.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 01.07.17
 */
public class Bank {

    /**
     * Users with their accounts.
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Adds the user with a blank account list.
     *
     * @param user some user
     */
    void addUser(User user) {
        users.put(user, new LinkedList<>());
    }

    /**
     * Gets users accounts.
     *
     * @param user some user
     * @return list of users accounts
     * @throws NoSuchUser user does not exist
     */
    List<Account> getUserAccounts(User user) throws NoSuchUser {
        List<Account> usersAccounts = users.get(user);
        if (usersAccounts != null) {
            return usersAccounts;
        } else {
            throw new NoSuchUser();
        }

    }

    /**
     * Adds account to the user.
     *
     * @param user    some user
     * @param account some account
     */
    void addAccountToUser(User user, Account account) {
        users.get(user).add(account);
    }

    /**
     * Deletes account from the user.
     *
     * @param user    some user
     * @param account some account
     */
    void deleteAccountFromUser(User user, Account account) {
        users.get(user).remove(account);
    }

    /**
     * Deletes the user from users map.
     *
     * @param user some user
     */
    void deleteUser(User user) {
        users.remove(user);
    }

    /**
     * Transfers money between accounts.
     *
     * @param srcUser        user from whom account will be transfer money
     * @param accountSrc     users account from which money will be transferred
     * @param dstUser        user to whom account will be transfer money
     * @param accountDst     users account to which money will be transferred
     * @param transferAmount amount of money
     * @return true if transfer completed, false if something wrong
     */
    boolean transferMoney(User srcUser,
                          Account accountSrc,
                          User dstUser,
                          Account accountDst,
                          BigDecimal transferAmount) {
        boolean result = false;
        final Account srcAccount = this.findAccount(accountSrc, users.get(srcUser));
        final Account dstAccount = this.findAccount(accountDst, users.get(dstUser));
        if (srcAccount.getAccountNumber() != 0L
                && dstAccount.getAccountNumber() != 0L
                && srcAccount.getMoneyAmount().compareTo(transferAmount) >= 0) {
            srcAccount.setMoneyAmount(srcAccount.getMoneyAmount().subtract(transferAmount));
            dstAccount.setMoneyAmount(dstAccount.getMoneyAmount().add(transferAmount));
            result = true;
        }
        return result;
    }

    /**
     * Searches for account in the list of accounts.
     *
     * @param accountToFind some accounts
     * @param accounts      list of accounts
     * @return account from list
     */
    Account findAccount(final Account accountToFind, final List<Account> accounts) {
        Account accountToReturn = new Account(BigDecimal.ZERO, 0L);
        for (Account account : accounts) {
            if (account == accountToFind) {
                accountToReturn = account;
            }
        }
        return accountToReturn;
    }
}