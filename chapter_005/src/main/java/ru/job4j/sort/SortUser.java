package ru.job4j.sort;

import java.util.Collections;
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

    /**
     * Sorts given list of users by name length.
     *
     * @param userList list of users
     * @return sortes list of users
     */
    public List<User> sortByNameLength(List<User> userList) {
        Collections.sort(userList, (o1, o2) ->
                Integer.valueOf(o1.getName().length()).compareTo(o2.getName().length())
        );
        return userList;
    }

    /**
     * Sorts given list of users by name length and age.
     *
     * @param userList list of users
     * @return sortes list of users
     */
    public List<User> sortByAllFields(List<User> userList) {
        Collections.sort(userList, (o1, o2) -> {
                    int nameCompareResult = o1.getName().compareTo(o2.getName());
                    if (nameCompareResult == 0) {
                        return o1.getAge().compareTo(o2.getAge());
                    } else {
                        return nameCompareResult;
                    }
                }
        );
        return userList;
    }
}