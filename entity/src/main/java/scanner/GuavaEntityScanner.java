package scanner;

import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import entity.Entity;
import entity.Id;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GuavaEntityScanner implements EntityScanner, EntityPropertyScanner {

    @Override
    public Set<Class> scanEntity(String packageName) {
        try {
            log.debug("Entity Scanning In Package : {}...", packageName);
            return ClassPath
                    .from(ClassLoader.getSystemClassLoader())
                    .getAllClasses()
                    .stream()
                    .filter(classInfo -> classInfo.getPackageName().equalsIgnoreCase(packageName))
                    .map(ClassPath.ClassInfo::load)
                    .filter(aClass -> aClass.getAnnotation(Entity.class) != null)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            log.error("Error Occur During Entity Scanning...", e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object getId(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        return Arrays.stream(fields)
                .filter(field -> field.getAnnotation(Id.class) != null)
                .findAny()
                .orElseThrow(NotExistUniqueKeyException::new);
    }
}
