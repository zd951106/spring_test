package sort;

import org.junit.Test;
import sort.sortutil.AbstractSort;
import sort.sortutil.Sort;
import sort.sortutil.TestSortUtil;

import java.util.Arrays;

/**
 * Created by zd on 2019/11/21.
 * 希尔排序
 * 希尔排序(Shell's Sort)是插入排序的一种又称“缩小增量排序”（Diminishing Increment Sort），
 * 是直接插入排序算法的一种更高效的改进版本。希尔排序是非稳定排序算法。该方法因D.L.Shell于1959年提出而得名。
 */
public class ShellSort extends AbstractSort {
    /*
        希尔排序
     */

    public ShellSort() {
        super(null);
    }

    public ShellSort(String name) {
        super(name);
    }

    public void shellSort(int[] data,int incre) {   //incre：增量
        for(int i=incre;i<data.length;i++) {
            for(int j=i;j>=0;j-=incre) {
                if(j-incre>=0) {
                    if(data[j]<data[j-incre]) {
                        swap(data, j, j-incre);
                    }
                }else break;
            }
        }
        if(incre>1) shellSort(data, incre/2);
    }
    //swap 交换数据
    private void swap(int[] data,int i,int j) {
        int temp=data[i];
        data[i]=data[j];
        data[j]=temp;
    }
    @Override
    public int[] sort(int[] data) {
        shellSort(data, data.length/2);
        return data;
    }
    @Test
    public void test() {
//        TestSortUtil.test(this::sort);
        int[] data=new int[]{5};
        shellSort(data, data.length/2);
    }

 /*   public static void main(String[] args) {
        ShellSort shellSort = new ShellSort();
        int[] data=new int[]{9,6,4,2,5,3,8,7,1,0};
        shellSort.shellSort(data, data.length/2);

        System.out.println(Arrays.toString(data));
    }*/
}
