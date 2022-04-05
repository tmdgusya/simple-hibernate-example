package testEntity;

import entity.Entity;
import entity.Id;

@Entity
public class Entity01 {

    @Id
    private Long id = 1L;

    @Override
    public String toString() {
        return "Entity01{" +
                "id=" + id +
                '}';
    }
}
