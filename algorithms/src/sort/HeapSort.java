package sort;

import org.junit.Test;
import sort.sortutil.AbstractSort;
import sort.sortutil.TestSortUtil;

/**
 * Created by zd on 2019/11/21.
 * 堆排序
 */
public class HeapSort extends AbstractSort {
    private void build(int[] data, int i, int length) {
        int max = data[i];
        int maxIndex = i;
        if (i * 2 + 1 < length) {
            if (data[i * 2 + 1] > max) {
                max = data[i * 2 + 1];
                maxIndex = i * 2 + 1;
            }
        }
        if (i * 2 + 2 < length) {
            if (data[i * 2 + 2] > max) {
                max = data[i * 2 + 2];
                maxIndex = i * 2 + 2;
            }
        }
        if (maxIndex != i) {
            swap(data, i, maxIndex);
            build(data, maxIndex, length);
        }
    }

    private void removeMax(int[] data, int tail) {
        swap(data, 0, tail);
        if (tail > 0) build(data, 0, tail);
    }

    public void heapSort(int[] data) {
        for (int i = data.length / 2 - 1; i >= 0; i--) {
            build(data, i, data.length);
        }
        for (int i = data.length - 1; i >= 0; i--) {
            removeMax(data, i);
        }
    }

    private void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    @Override
    public int[] sort(int[] data) {
        heapSort(data);
        return data;
    }

    @Test
    public void test() {
        TestSortUtil.test(this::sort);
    }
}
