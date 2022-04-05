package validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import scanner.EntityScanner;
import scanner.GuavaEntityScanner;
import validatortest.Test01;
import validatortest.Test02;

class EntityValidatorTest {
    EntityScanner entityScanner = new GuavaEntityScanner();

    @Test
    void testIsContainsId() {
        List<Class> entityClassList = entityScanner.scanEntity("validatortest").stream().toList();
        Optional<Class> entity = entityClassList.stream().filter(aClass -> aClass.getName().equals(Test01.class.getName())).findAny();
        Assertions.assertTrue(entity.isPresent());

        EntityValidator entityValidator = new EntityValidator(entity.get());

        boolean result = entityValidator.isContainsId();
        Assertions.assertTrue(result);
    }

    @Test
    void testIsNotContainsId() {
        List<Class> entityClassList = entityScanner.scanEntity("validatortest").stream().toList();
        Optional<Class> entity = entityClassList.stream().filter(aClass -> aClass.getName().equals(Test02.class.getName())).findAny();
        Assertions.assertTrue(entity.isPresent());

        EntityValidator entityValidator = new EntityValidator(entity.get());

        boolean result = entityValidator.isContainsId();
        Assertions.assertFalse(result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme