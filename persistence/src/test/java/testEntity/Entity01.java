package testEntity;

import entity.Entity;
import entity.Id;

@Entity
public class Entity01 {

    @Id
    private Long id;

    public Entity01(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Entity01{" +
                "id=" + id +
                '}';
    }
}
