package scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import scannertestpackage.Test01;
import scannertestpackage.Test02;

class GuavaEntityScannerTest {
    GuavaEntityScanner guavaEntityScanner = new GuavaEntityScanner();

    @Test
    void testScanEntity() throws IOException {
        // given
        final String testPackageName = "scannertestpackage";
        final String testFile = Test01.class.getName();

        // when
        Set<Class> result = guavaEntityScanner.scanEntity(testPackageName);

        // then
        Assertions.assertTrue(result.contains(Test01.class));
        Assertions.assertFalse(result.contains(Test02.class));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme