package ru.job4j.mt.jcip;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple user storage implementation.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 23.01.2018
 */
@ThreadSafe
public class UserStorage {

    /**
     * Users storage.
     */
    @GuardedBy("this")
    private final List<User> users;

    /**
     * Constructor.
     */
    public UserStorage() {
        this.users = new ArrayList<>();
    }

    /**
     * Adds given user.
     *
     * @param user some user
     * @return true if successful
     */
    public boolean add(User user) {
        boolean canAddUser = true;
        synchronized (this) {

            for (User userLocal : this.users) {
                if (userLocal.equals(user)) {
                    canAddUser = false;
                }
            }
            if (canAddUser) {
                this.users.add(user);
            }
        }
        return canAddUser;
    }

    /**
     * Updates given user.
     *
     * @param user some user
     * @return true if successful
     */
    public boolean update(User user) {
        boolean result = false;
        synchronized (this) {
            for (User userLocal : this.users) {
                if (userLocal.equals(user)) {
                    userLocal.setAmount(user.getAmount());
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * Deletes given user.
     *
     * @param user some user
     * @return true if successful
     */
    public boolean delete(User user) {
        boolean result;
        synchronized (this) {
            result = this.users.remove(user);
        }
        return result;
    }

    /**
     * Transfers amount from user with fromId to user with toId.
     *
     * @param fromId id user whose amount will be decreased
     * @param toId id user whose amount will be increased
     * @param amount amount
     * @return true if successful
     */
    public boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        User userFrom = findUserById(fromId);
        User userTo = findUserById(toId);
        if (userFrom.getId() != User.DEFAULT_ID && userTo.getId() != User.DEFAULT_ID && userFrom.getAmount() >= amount) {
            synchronized (userFrom) {
                synchronized (userTo) {
                    int userFromNewAmount = userFrom.getAmount() - amount;
                    userFrom.setAmount(userFromNewAmount);

                    int userToNewAmount = userTo.getAmount() + amount;
                    userTo.setAmount(userToNewAmount);
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * Searches for user with given id.
     *
     * @param id some id
     * @return user with defaukt id and amount if user was not fount or user with id
     */
    private User findUserById(int id) {
        User result = new User();
        synchronized (this) {
            for (User user : this.users) {
                if (id == user.getId()) {
                    result = user;
                }
            }
        }
        return result;
    }
}