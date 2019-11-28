package sort;

import org.junit.Test;
import sort.sortutil.AbstractSort;
import sort.sortutil.Sort;
import sort.sortutil.TestSortUtil;

/**
 * Created by zd on 2019/11/21.
 * 归并排序
 */
public class MergeSort extends AbstractSort {


    public MergeSort() {
        super(null);
    }

    public MergeSort(String name) {
        super(name);
    }


    public int[] mergeSort(int[] data,int i,int j) {
        if(j-i>0) {
            int[] leftData = mergeSort(data, i, (i + j) / 2);
            int[] rightData=mergeSort(data, (i+j)/2+1, j);
            return merge(leftData,rightData);
        }else return new int[] {data[i]};

    }

    public int[] merge(int[] leftData,int[] rightData) {
        int[] newData=new int[leftData.length+rightData.length];
        int i=0;
        int j=0;
        while (i<leftData.length||j<rightData.length) {
            int min=leftData[i];
            if(i<leftData.length&&leftData[i]<min) {
                min=leftData[i];
                i++;
            }
            if(j<rightData.length&&rightData[j]<min) {
                min=rightData[j];
                j++;
            }
            newData[i+j-1]=min;
        }

        return newData;
    }
    @Override
    public int[] sort(int[] data) {
        return mergeSort(data, 0, data.length-1);
    }
    @Test
    public void test() {
        TestSortUtil.test(this::sort);
    }
}
