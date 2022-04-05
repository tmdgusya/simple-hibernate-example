package scanner;

import java.io.IOException;
import java.util.Set;

public interface EntityScanner {
    public Set<Class> scanEntity(String packageName) throws IOException;
}
