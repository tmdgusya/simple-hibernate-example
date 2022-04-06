package scanner;

import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;

import util.Assert;

public class DefaultEntityDirtyScanner implements EntityDirtyScanner {

    @Override
    public boolean isDirty(Object older, Object newer) {
        Assert.notNull(older, "older Entity");
        Assert.notNull(newer, "newer Entity");

        if (!isSameEntityClass(older, newer)) {
            throw new IllegalArgumentException("Dirty Change Entity Must Be Same Entity Class");
        }

        Diff compare = JaversBuilder
                .javers()
                .build()
                .compare(newer, older);

        return compare.hasChanges();
    }

    private boolean isSameEntityClass(Object older, Object newer) {
        String olderClassName = older.getClass().getName();
        String newerClassName = newer.getClass().getName();

        return olderClassName.equals(newerClassName);
    }
}
