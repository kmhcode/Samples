package app.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "counters")
public class CounterEntity {
    
    @Id
    @Column(name = "ctr_name")
    private String id;

    @Column(name = "cur_val")
    private int currentValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public final int nextValue() {
        return ++currentValue + 1000;
    }
}
