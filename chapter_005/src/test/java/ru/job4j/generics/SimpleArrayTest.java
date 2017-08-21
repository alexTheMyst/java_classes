package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for SimpleArray.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 21.08.17
 */
public class SimpleArrayTest {
    /**
     * Tests entities.
     */
    private TestEntity testEntityOne, testEntityTwo;

    /**
     * SimpleArray instance.
     */
    private SimpleArray<TestEntity> simpleArray = new SimpleArray<>();

    /**
     * Sets up test before each test.
     */
    @Before
    public void setup() {
        this.testEntityOne = new TestEntity(1L, "test1");
        this.testEntityTwo = new TestEntity(2L, "test2");
        this.simpleArray = new SimpleArray<>();
    }

    /**
     * Adds one entity and checks that array returns the same instance.
     */
    @Test
    public void whenAddOneThenGetByIdReturnsTheSame() {
        this.simpleArray.add(testEntityOne);

        assertThat(this.simpleArray.get(0), is(this.testEntityOne));
    }

    /**
     * Adds two entities then deletes first and checks that right entity returns.
     */
    @Test
    public void whenAddOneAndDeleteOneSizeEqualsZero() {
        this.simpleArray.add(this.testEntityOne);
        this.simpleArray.add(this.testEntityTwo);
        this.simpleArray.delete(this.testEntityOne);

        assertThat(this.simpleArray.get(0), is(this.testEntityTwo));
    }

    /**
     * Adds entity and updates it, then checks that returns updated record.
     */
    @Test
    public void whenAddAndUpdateReturnsUpdatedEntity() {
        TestEntity testEntityForUpdate = new TestEntity(1L, "test123");

        this.simpleArray.add(this.testEntityOne);
        this.simpleArray.update(testEntityForUpdate);

        assertThat(this.simpleArray.get(0).name, is("test123"));
    }

    /**
     * Simple entity class for tests.
     */
    class TestEntity {
        /**
         * Id field.
         */
        private Long id;

        /**
         * Name field.
         */
        private String name;

        /**
         * Constructor.
         *
         * @param id   id.
         * @param name name.
         */
        TestEntity(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * Overridden equals.
         *
         * @param o some object to compare.
         * @return true if objects have the same id.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            TestEntity that = (TestEntity) o;

            return id.equals(that.id);
        }

        /**
         * Overridden hashCode.
         *
         * @return hashcode of id field.
         */
        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }
}
