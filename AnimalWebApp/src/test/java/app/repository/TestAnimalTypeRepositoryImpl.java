package app.repository;

import com.animals.app.domain.AnimalType;
import com.animals.app.repository.Impl.AnimalTypeRepositoryImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by Rostyslav.Viner on 23.07.2015.
 */
public class TestAnimalTypeRepositoryImpl {
    private static AnimalTypeRepositoryImpl animalTypeRepositoryImpl;

    @Before
    public void runBeforeClass() {
        animalTypeRepositoryImpl = new AnimalTypeRepositoryImpl();
    }

    @After
    public void runAfterClass() {
        animalTypeRepositoryImpl = null;
    }

    @Test
    public void testGetAll() {
        List<AnimalType> list = animalTypeRepositoryImpl.getAll();

        assertNotNull(list);
    }

    @Test
    public void testGetById() {
        AnimalType expected = animalTypeRepositoryImpl.getById(1);

        assertNotNull(expected);
    }
}
