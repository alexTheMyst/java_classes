package ru.job4j.sort;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests methods for collections sort.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 13.06.17
 */
public class SortTests {

    /**
     * SortUser instance.
     */
    private SortUser sortUser;

    /**
     * User variable.
     */
    private final User user1 = new User("test1t", 15);

    /**
     * User variable.
     */
    private final User user2 = new User("test2te", 20);

    /**
     * User variable.
     */
    private final User user3 = new User("test3tes", 25);

    /**
     * User variable.
     */
    private final User user4 = new User("test3tes", 20);

    /**
     * Creates new SortUser before each test.
     */
    @Before
    public void setup() {
        this.sortUser = new SortUser();
    }

    /**
     * Tests that sorted set first entity is user1.
     */
    @Test
    public void testSortMethod() {
        final List<User> users = new LinkedList<>();
        users.add(this.user2);
        users.add(this.user1);
        users.add(this.user3);
        for (User u : this.sortUser.sort(users)) {
            System.out.println(u.toString());
        }
        assertThat(this.sortUser.sort(users).toArray()[0], is(user1));
    }

    /**
     * Tests that list sorted by name length.
     */
    @Test
    public void testSortByNameLength() {
        final List<User> unsortedList = new LinkedList<>();
        final List<User> sortedList = new LinkedList<>();
        sortedList.add(user1);
        sortedList.add(user2);
        sortedList.add(user3);
        unsortedList.add(user2);
        unsortedList.add(user3);
        unsortedList.add(user1);
        assertThat(this.sortUser.sortByNameLength(unsortedList), is(sortedList));
    }

    /**
     * Tests that list sorted by name and by age.
     */
    @Test
    public void testSortByAllFields() {
        final List<User> unsortedList = new LinkedList<>();
        final List<User> sortedList = new LinkedList<>();
        sortedList.add(user1);
        sortedList.add(user2);
        sortedList.add(user4);
        sortedList.add(user3);
        unsortedList.add(user4);
        unsortedList.add(user2);
        unsortedList.add(user3);
        unsortedList.add(user1);
        assertThat(this.sortUser.sortByAllFields(unsortedList), is(sortedList));
    }
}