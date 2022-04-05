package scanner;

import java.util.Set;

public interface EntityScanner {
    Set<Class> scanEntity(String packageName);
}
