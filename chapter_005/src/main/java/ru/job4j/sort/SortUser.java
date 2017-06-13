package ru.job4j.sort;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Contains sort methods implementation for a User class.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 13.06.17
 */
public class SortUser {

    /**
     * Converts given list of users to tree set of users.
     *
     * @param users list of users
     * @return tree set of users
     */
    public Set<User> sort(List<User> users) {
        final Set<User> result = new TreeSet<>();
        result.addAll(users);
        return result;
    }

}
