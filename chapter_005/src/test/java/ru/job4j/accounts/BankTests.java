package ru.job4j.accounts;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for bank application.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 01.07.17
 */
public class BankTests {
    /**
     * Bank accounts variable.
     */
    private Bank bank;

    /**
     * Sets up variables before each test.
     */
    @Before
    public void setUp() {
        bank = new Bank();
    }

    /**
     * Tests addUser method adds a new user with blank list of accounts.
     *
     * @throws NoSuchUser user does not exist
     */
    @Test
    public void whenAddUserGetAccountReturnsZeroSizedList() throws NoSuchUser {
        final User user = new User("John", "123456");

        bank.addUser(user);

        assertThat(bank.getUserAccounts(user).size(), is(0));
    }

    /**
     * Tests add account to existing user.
     *
     * @throws NoSuchUser user does not exist
     */
    @Test
    public void whenAddAccountGetAccountsReturnsOneAccount() throws NoSuchUser {
        final User user = new User("John", "123456");
        final Account account = new Account(BigDecimal.ZERO, 1000L);

        bank.addUser(user);
        bank.addAccountToUser(user, account);

        assertThat(bank.getUserAccounts(user).size(), is(1));
    }

    /**
     * Tests the account deletion.
     *
     * @throws NoSuchUser user does not exist
     */
    @Test
    public void whenAddOneAccountAndDeleteOneAccountGetAccountReturnsZeroSizedList() throws NoSuchUser {
        final User user = new User("John", "123456");
        final Account account = new Account(BigDecimal.ZERO, 1000L);

        bank.addUser(user);
        bank.addAccountToUser(user, account);
        bank.deleteAccountFromUser(user, account);

        assertThat(bank.getUserAccounts(user).size(), is(0));
    }

    /**
     * Tests the user deletion.
     *
     * @throws NoSuchUser user does not exist
     */
    @Test(expected = NoSuchUser.class)
    public void whenDeleteUserGetUserAccountsRaiseException() throws NoSuchUser {
        final User user = new User("John", "123456");

        bank.addUser(user);
        bank.deleteUser(user);
        bank.getUserAccounts(user);
    }

    /**
     * Tests transfer between two users accounts.
     *
     * @throws NoSuchUser user does not exist
     */
    @Test
    public void whenTransferMoneyToBlankAccountTargetAccountReturnsTransferAmount() throws NoSuchUser {
        final User user1 = new User("User1", "123456");
        final User user2 = new User("User2", "654321");
        final Account accountSrc = new Account(BigDecimal.valueOf(100), 1000L);
        final Account accountDst = new Account(BigDecimal.ZERO, 1001L);
        final BigDecimal transferAmount = BigDecimal.valueOf(100);

        bank.addUser(user1);
        bank.addUser(user2);
        bank.addAccountToUser(user1, accountSrc);
        bank.addAccountToUser(user2, accountDst);
        bank.transferMoney(user1, accountSrc, user2, accountDst, transferAmount);

        assertThat(bank.findAccount(accountDst, bank.getUserAccounts(user2)).getMoneyAmount(), is(transferAmount));
    }

    /**
     * Tests that transfer impossible between two blank accounts.
     */

    @Test
    public void whenTryTransferMoneyFromBlankAccountTransferMoneyReturnsFalse() {
        final User user = new User("John", "123456");
        final Account accountSrc = new Account(BigDecimal.ZERO, 1000L);
        final Account accountDst = new Account(BigDecimal.ZERO, 1001L);
        final BigDecimal transferAmount = BigDecimal.valueOf(100);

        bank.addUser(user);
        bank.addAccountToUser(user, accountSrc);
        bank.addAccountToUser(user, accountDst);
        final boolean transferResult = bank.transferMoney(user, accountSrc, user, accountDst, transferAmount);
        bank.deleteAccountFromUser(user, accountSrc);

        assertThat(transferResult, is(false));
    }
}
