package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests classes that implements Store interface.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 26.08.17
 */
public class StoresTests {

    /**
     * UserStore for tests.
     */
    private UserStore userStore;

    /**
     * RolesStore for tests.
     */
    private RolesStore rolesStore;

    /**
     * User for tests.
     */
    private User user;

    /**
     * Role for tests.
     */
    private Role role;


    /**
     * Creates classes before each tests.
     */
    @Before
    public void setup() {
        this.userStore = new UserStore();
        this.rolesStore = new RolesStore();
        this.user = new User();
        this.user.setId("1");
        this.role = new Role();
        this.role.setId("1");
    }

    /**
     * Adds one user and updates it.
     */
    @Test
    public void whenAddOneUserUpdateReturnsTrue() {
        this.userStore.add(this.user);

        assertThat(this.userStore.update(this.user), is(true));
    }

    /**
     * Adds one user and deletes it.
     */
    @Test
    public void whenAddOneUserDeleteReturnsTrue() {
        this.userStore.add(this.user);

        assertThat(this.userStore.delete(this.user), is(true));
    }

    /**
     * Adds one role and updates it.
     */
    @Test
    public void whenAddOneRoleUpdateReturnsTrue() {
        this.rolesStore.add(this.role);

        assertThat(this.rolesStore.update(this.role), is(true));
    }

    /**
     * Adds one role and deletes it.
     */
    @Test
    public void whenAddOneRoleDeleteReturnsTrue() {
        this.rolesStore.add(this.role);

        assertThat(this.rolesStore.delete(this.role), is(true));
    }
}
