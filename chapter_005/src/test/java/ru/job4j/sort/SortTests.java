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
}
