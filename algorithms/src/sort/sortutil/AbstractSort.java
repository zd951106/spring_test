package sort.sortutil;

/**
 * Created by zd on 2019/11/21.
 */
public abstract class AbstractSort implements Sort {
    private String name;

    public String getName() {
        return name;
    }

    public AbstractSort() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbstractSort(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
