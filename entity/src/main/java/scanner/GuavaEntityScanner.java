package scanner;

import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import entity.Entity;

public class GuavaEntityScanner implements EntityScanner {
    @Override
    public Set<Class> scanEntity(String packageName) throws IOException {
        return ClassPath
                .from(ClassLoader.getSystemClassLoader())
                .getAllClasses()
                .stream()
                .filter(classInfo -> classInfo.getPackageName().equalsIgnoreCase(packageName))
                .map(ClassPath.ClassInfo::load)
                .filter(aClass -> aClass.getAnnotation(Entity.class) != null)
                .collect(Collectors.toSet());
    }
}
