package ru.job4j.maps;

import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Tests functionality.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 02.10.17
 */
public class UserTest {

    /**
     * Adds two users to map and prints it.
     */
    @Test
    public void whenAddTwoUsersMapShowsTwoEntities() {
        final Calendar calendar = Calendar.getInstance();
        final User userOne = new User("test", 1, calendar);
        final User userTwo = new User("test", 1, calendar);
        final Map<User, Object> users = new HashMap<>();

        Object o1 = new Object();
        Object o2 = new Object();

        System.out.printf("first object = %s, second object = %s\n", o1, o2);

        users.put(userOne, o1);
        users.put(userTwo, o2);

        System.out.printf("HashCode equals: %s \n", userOne.hashCode() == userTwo.hashCode());
        System.out.printf("Object equals: %s \n", userOne.equals(userTwo));
        System.out.println(users);
    }
}