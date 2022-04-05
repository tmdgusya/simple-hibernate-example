package validator;

import java.lang.reflect.Field;
import java.util.Arrays;

import entity.Id;

public class EntityValidator implements EntityPropertyValidator {

    private final Class clazz;

    public EntityValidator(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean isContainsId() {
        Field[] fields = clazz.getDeclaredFields();
        return Arrays.stream(fields).anyMatch(field -> field.getAnnotation(Id.class) != null);
    }

    @Override
    public boolean isDetachedEntity() {
        return false;
    }
}
