package ru.job4j.mt.jcip;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Simple user storage tests.
 *
 * @author Alexey Aleshin
 * @version $id\$
 * @since 23.01.2018
 */
public class UserStorageTest {

    /**
     * User storage.
     */
    private UserStorage userStorage;

    /**
     * Setup method.
     */
    @Before
    public void setUp() {
        this.userStorage = new UserStorage();
    }

    /**
     * Adds user and checks result.
     */
    @Test
    public void whenAddUserThenAddTrue() {
        User user = new User(1, 10);

        assertThat(this.userStorage.add(user), is(true));
    }

    /**
     * Checks that we can update existing user.
     */
    @Test
    public void whenAddUserAndUpdateUserWithSameIdThenTrue() {
        User user = new User(1, 10);
        User otherUser = new User(1, 20);
        this.userStorage.add(user);

        assertThat(this.userStorage.update(otherUser), is(true));
    }

    /**
     * Checks that we can delete existing user.
     */
    @Test
    public void whenAddUserAndDeleteThenTrue() {
        User user = new User(1, 10);
        this.userStorage.add(user);

        assertThat(this.userStorage.delete(user), is(true));
    }

    /**
     * Checks that valid update completes successfully.
     */
    @Test
    public void whenUpdateSuccessfulThenTrue() {
        User userOne = new User(1, 10);
        User userTwo = new User(2, 10);

        this.userStorage.add(userOne);
        this.userStorage.add(userTwo);
        this.userStorage.transfer(1, 2, 5);

        assertThat(userTwo.getAmount(), is(15));
    }

    /**
     * Checks that not valid update returns false.
     */
    @Test
    public void whenNotEnoughAmountThenFalse() {
        User userOne = new User(1, 10);
        User userTwo = new User(2, 10);

        this.userStorage.add(userOne);
        this.userStorage.add(userTwo);

        assertThat(this.userStorage.transfer(1, 2, 15), is(false));
    }
}