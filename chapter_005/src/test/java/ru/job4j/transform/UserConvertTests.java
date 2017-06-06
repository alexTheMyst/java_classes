package ru.job4j.transform;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Contains tests  for UserConvert.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 06.06.17
 */
public class UserConvertTests {
    /**
     * UserConvert instance.
     */
    private UserConvert userConvert;

    /**
     * Creates new UserConvert instance before each test.
     */
    @Before
    public void setup() {
        this.userConvert = new UserConvert();
    }

    /**
     * Tests process method.
     */
    @Test
    public void processMethodTest() {
        final List<User> users = new ArrayList<>();
        final User user1 = new User(1, "test1", "test1");
        final User user2 = new User(2, "test2", "test2");
        final User user3 = new User(3, "test3", "test3");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        final Map<Integer, User> mapUsers = new HashMap<>();
        mapUsers.put(user1.getId(), user1);
        mapUsers.put(user2.getId(), user2);
        mapUsers.put(user3.getId(), user3);
        assertThat(this.userConvert.process(users), is(mapUsers));
    }

}
