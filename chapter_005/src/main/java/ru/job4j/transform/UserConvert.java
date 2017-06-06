package ru.job4j.transform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains logic for transformation to Map.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 06.06.17
 */
public class UserConvert {

    /**
     * Converts list to map.
     *
     * @param users list of users
     * @return map with userid as a key< and user as a value
     */
    public Map<Integer, User> process(List<User> users) {
        Map<Integer, User> resultMap = new HashMap<>();
        for (User user : users) {
            resultMap.put(user.getId(), user);
        }
        return resultMap;
    }
}
