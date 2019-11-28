package sort;

import org.junit.Test;
import sort.sortutil.AbstractSort;
import sort.sortutil.Sort;
import sort.sortutil.TestSortUtil;

/**
 * Created by zd on 2019/11/20.
 * 插入排序
 */
public class InsertSort extends AbstractSort {


    public InsertSort() {
        super(null);
    }

    public InsertSort(String name) {
        super(name);
    }

    /*
        直接插入排序:
        把待排序的记录按其关键码值的大小逐个插入到一个已经排好序的有序序列中，
        直到所有的记录插入完为止，得到一个新的有序序列。
     */
    public void directInsertSort(int[] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = i; j >0 ; j--) {
                if(data[j]<data[j-1]) {
                    swap(data, j, j-1);
                }else break;
            }
        }
    }

    /*
        优化一：二分插排  将直接插入排序中寻找A[i]的插入位置的方法改为采用折半比较，即可得到折半插入排序算法。
        BinaryInsertionSort
     */

    public void binaryInsertionSort(int[] data) {
        for (int i = 0; i < data.length; i++) {
            int head=0;
            int tail=i;
            int mid;
            for(mid=(head+tail)/2;head-tail>0;mid=(head+tail)/2) {
                if(data[i]<data[mid]) {
                    tail=mid;
                }else if(data[i]>data[mid]) {
                    head=mid;
                }else break;
            }
            int j;
            if (data[i]>data[mid]) j=mid+1;
                else j=mid;
            for(;j<i;j++) {
                swap(data, j, j+1);
            }
        }
    }

    @Override
    public int[] sort(int[] data) {
        directInsertSort(data);
        return data;
    }

    @Test
    public void test() {
        TestSortUtil.test(this::sort);
    }

    private void swap(int[] data,int i,int j) {
        int temp=data[i];
        data[i]=data[j];
        data[j]=temp;
    }
}
