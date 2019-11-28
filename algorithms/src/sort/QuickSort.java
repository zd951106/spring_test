package sort;

import org.junit.Test;
import sort.sortutil.AbstractSort;
import sort.sortutil.Sort;
import sort.sortutil.TestSortUtil;

/**
 * Created by zd on 2019/11/18.
 * 快速排序
 * 快速排序（Quicksort）是对冒泡排序的一种改进。
 */
public class QuickSort extends AbstractSort {


    public QuickSort() {
        super(null);
    }

    public QuickSort(String name) {
        super(name);
    }

    /*
        基础快速排序
     */
    public void qsort(int[] data,int i,int j) {
        int key=i;
        int preIndex=i;
        int sufIndex=j;
        while(i<j) {
            if(key==i) {
                if(data[j]<data[key]) {
                    swap(data, j, key);
                    key=j;
                    i++;
                }else j--;
            }else {
                if(data[i]>data[key]) {
                    swap(data, i, key);
                    key=i;
                    j--;
                }else i++;
            }
        }
        if(preIndex<key-1)qsort(data, preIndex, key-1);

        if (key+1<sufIndex)qsort(data, key+1, sufIndex);
    }

    /*
        优化一：三数取中值。数组的第一各下标和最后一个下标的中间下标的中间值
        优化二：随机基准 引入的原因：在待排序列是部分有序时，固定选取枢轴使快排效率底下，要缓解这种情况，就引入了随机选取枢轴
     */

    public void qsort1(int[] data,int i,int j) {
        if (j-i>0){
            int mid =(i+j)/2;
            if(i>j) swap(data, i,j);
            if(mid>j) swap(data, mid, j);
            if(mid>i) swap(data, i, mid);
        }
        qsort(data, i, j);
    }

    /*
        优化三：将于key相同的值放在一起

        优化4：
        优化小数组的交换，就是为了解决大才小用问题原因：对于很小和部分有序的数组，快排不如插排好。
        当待排序序列的长度分割到一定大小后，继续分割的效率比插入排序要差，
        此时可以使用插排而不是快排截止范围：待排序序列长度N = 10，虽然在5~20之间任一截止范围都有可能产生类似的结果，
        这种做法也避免了一些有害的退化情形。
     */

    public void qsort2(int[] data,int i,int j) {
        int key=i;
        int preIndex=i;
        int sufIndex=j;
        while(i<j) {
            if(key==i) {
                if(data[j]<data[key]) {
                    swap(data, j, key);
                    key=j;
                    i++;
                }else j--;
            }else {
                if(data[i]>data[key]) {
                    swap(data, i, key);
                    key=i;
                    j--;
                }else i++;
            }
        }
        int index1=key;
        for (int k = 0; k < key; k++) {
            if(data[k]==data[key]) {
                while((index1-1>=0)&&data[--index1]==data[key]) {

                }
                swap(data, k, index1);
            }
        }
        int index2=key;
        for (int k = index2+1; k < data.length; k++) {
            if((index2+1<data.length-1)&&data[k]==data[key]) {
                while (data[++index2]==data[key]);
                swap(data, k, index2);
            }
        }

        if(preIndex<index1-1)qsort2(data, preIndex, index1-1);

        if (index2+1<sufIndex)qsort2(data, index2+1, sufIndex);
    }


    @Override
    public int[] sort(int[] data) {
        qsort2(data, 0, data.length-1);
        return data;
    }
    @Test
    public void test() {
//        int[] data=new int[]{1,2,3,4,5,6,7,8,9,10};
        TestSortUtil.test(this::sort);
    }

    private void swap(int[] data,int i,int j) {
        int temp=data[i];
        data[i]=data[j];
        data[j]=temp;
    }
}
