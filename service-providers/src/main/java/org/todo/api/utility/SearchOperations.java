package org.todo.api.utility;

import org.springframework.stereotype.Service;
import org.todo.api.entity.TodoItem;
import org.todo.api.entity.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class SearchOperations {

    public List<Object> searchSubstrings(List<Object> objects, Object object, String value) {
        List<Object> foundObjects = null;
        if(value.equalsIgnoreCase("all")) {
            return objects;
        }
        if(object == User.class) {
            foundObjects =  objects.stream()
                    .filter(obj -> {
                        if(obj instanceof User) {
                            return ((User) obj).getUserName().contains(value);
                        }
                        return false;
                    }).toList();
        }
        if(object == TodoItem.class) {
            foundObjects =  objects.stream()
                    .filter(obj -> {
                        if(obj instanceof TodoItem) {
                            return ((TodoItem) obj).getTitle().contains(value);
                        }
                        return false;
                    }).toList();
        }
        assert foundObjects != null;
        if(!foundObjects.isEmpty()) {
            return foundObjects;
        }
        return null;
    }
}
