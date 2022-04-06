package scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import entity.Entity;
import entity.Id;
import scannertestpackage.Test01;
import scannertestpackage.Test02;

class DefaultEntityDirtyScannerTest {
    DefaultEntityDirtyScanner defaultEntityDirtyScanner = new DefaultEntityDirtyScanner();

    @Test
    @DisplayName("다른 Entity 일 경우 IllegalArgumentException 을 방출한다.")
    void testIsDirty() {
        // given
        Test01 test01 = new Test01();
        Test02 test02 = new Test02();

        // when && then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            defaultEntityDirtyScanner.isDirty(test01, test02);
        });
    }

    @Test
    @DisplayName("같은 Entity 에서 다를 경우 True 를 리턴합니다.")
    void changeVersion() {

        // given
        DirtyClass older = new DirtyClass(1L);
        DirtyClass newer = new DirtyClass(1L);

        newer.setValue("zzzz");

        // when
        boolean dirty = defaultEntityDirtyScanner.isDirty(older, newer);

        // then
        Assertions.assertTrue(dirty);
    }

    @Entity
    class DirtyClass {

        @Id
        private final Long id;
        private String value;

        public DirtyClass(Long id) {
            this.id = id;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme