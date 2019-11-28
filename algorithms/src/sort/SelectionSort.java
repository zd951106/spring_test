package sort;

import org.junit.Test;
import sort.sortutil.AbstractSort;
import sort.sortutil.Sort;
import sort.sortutil.TestSortUtil;

import java.util.Arrays;

/**
 * Created by zd on 2019/11/21.
 * 选择排序
 */
public class SelectionSort extends AbstractSort {

    public SelectionSort() {
        super(null);
    }

    public SelectionSort(String name) {
        super(name);
    }

    public void selectionSort(int[] data) {
        for(int i=0;i<data.length-1;i++) {
            int min=i;
            for(int j=i;j<data.length;j++) {
                if(data[j]<data[min]) {
                    min=j;
                }
            }
            {
                int temp=data[i];
                data[i]=data[min];
                data[min]=temp;
            }
        }
    }

    /*
        优化：双向
     */
    public void selectionSort2(int[] data) {
        for(int head=0,tail=data.length-1;head<tail;head++,tail--) {
            int max=data[head];
            int maxIndex=head;
            int min=data[tail];
            int minIndex=tail;
            for (int i = head; i <=tail; i++) {
                if(data[i]<min) {
                    min=data[i];
                    minIndex=i;
                }else if(data[i]>max) {
                    max=data[i];
                    maxIndex=i;
                }
            }
            swap(data, minIndex, maxIndex, head, tail);
        }
    }

    private void swap(int[] data,int minIndex,int maxIndex,int head,int tail) {
        int minTemp=data[minIndex];
        int maxTemp=data[maxIndex];
        data[minIndex]=data[head];
        data[maxIndex]=data[tail];
        data[head]=minTemp;
        data[tail]=maxTemp;
    }
    @Override
    public int[] sort(int[] data) {
        selectionSort2(data);
        return data;
    }
    @Test
    public void test() {
        int[] data=new int[]{4, 1, 7, 15, 18, 7, 1, 8, 10, 16};
        TestSortUtil.test(this::sort,data);
    }
}
