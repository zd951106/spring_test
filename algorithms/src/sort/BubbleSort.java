package sort;

import org.junit.Test;
import sort.sortutil.AbstractSort;
import sort.sortutil.Sort;
import sort.sortutil.TestSortUtil;

/**
 * Created by zd on 2019/11/17.
 * 冒泡排序算法
 *初试状态是正序最好的时间复杂度O(n)
 *初试状态是反序最坏的时间复杂度O(n²)
 *
 */

public class BubbleSort extends AbstractSort {


    public BubbleSort() {
        super(null);
    }

    public BubbleSort(String name) {
        super(name);
    }


    /*
                基础冒泡排序
             */
    public int[] sort1(int[] data) {
        for(int i=0;i<data.length-1;i++) {  //最外层 i<data.length-1 即可
            for(int j=0;j<data.length-i-1;j++) {
                if(data[j]>data[j+1]) {
                    data[j]=data[j]+data[j+1];
                    data[j+1]=data[j]-data[j+1];
                    data[j]=data[j]-data[j+1];
                }
            }
        }
        return data;
    }


    /*
        冒泡排序优化一      设置flag，

        假设我们现在排序ar[]={1,2,3,4,5,6,7,8,10,9}这组数据，按照上面的排序方式，
        第一趟排序后将10和9交换已经有序，接下来的8趟排序就是多余的，什么也没做。
        所以我们可以在交换的地方加一个标记，如果那一趟排序没有交换元素，说明这组数据已经有序，不用再继续下去。
     */
    public int[] sort2(int[] data) {
        boolean flag;
        for(int i=0;i<data.length-1;i++) {  //最外层 i<data.length-1 即可
            flag=false;
            for(int j=0;j<data.length-i-1;j++) {
                if(data[j]>data[j+1]) {
                    data[j]=data[j]+data[j+1];
                    data[j+1]=data[j]-data[j+1];
                    data[j]=data[j]-data[j+1];
                    flag=true;
                }
            }
            if (!flag) break;
        }
        return data;
    }
    /*
        优化二

        优化一仅仅适用于连片有序而整体无序的数据(例如：1， 2，3 ，4 ，7，6，5)。
        但是对于前面大部分是无序而后边小半部分有序的数据(1，2，5，7，4，3，6，8，9，10)排序效率也不可观，
        对于种类型数据，我们可以继续优化。既我们可以记下最后一次交换的位置，后边没有交换，必然是有序的，
        然后下一次排序从第一个比较到上次记录的位置结束即可。
     */

    public int[] sort3(int[] data) {
        boolean flag;
        int head=data.length-1;
        for(int i=0;i<data.length-1;i++) {  //最外层 i<data.length-1 即可
            flag=false;
            int headTemp=0;
            for(int j=0;j<head;j++) {
                if(data[j]>data[j+1]) {
                    data[j]=data[j]+data[j+1];
                    data[j+1]=data[j]-data[j+1];
                    data[j]=data[j]-data[j+1];
                    flag=true;
                    headTemp=j;
                }
            }
            if (!flag) break;
            head =headTemp;
        }
        return data;
    }

    /*
        优化三
        优化二的效率有很大的提升，还有一种优化方法可以继续提高效率。大致思想就是一次排序可以确定两个值，
        正向扫描找到最大值交换到最后，反向扫描找到最小值交换到最前面。例如：排序数据1，2，3，4，5，6，0
     */
    public int[] sort4(int[] data) {
        boolean flag;
        int head=data.length-1;
        int tail=0;
        for(int i=0;i<data.length-1;i++) {  //最外层 i<data.length-1 即可
            flag=false;
            int headTemp=0;
            int tailTemp=0;
            int m=0,n=0;
            for(int j=tail;j<head;j++) {
                if(data[j]>data[j+1]) {
                    data[j]=data[j]+data[j+1];
                    data[j+1]=data[j]-data[j+1];
                    data[j]=data[j]-data[j+1];
                    flag=true;
                    headTemp=j;
                }
            }

            for(int j=head;j>tail;j--) {
                if(data[j]<data[j-1]) {
                    data[j]=data[j]+data[j-1];
                    data[j-1]=data[j]-data[j-1];
                    data[j]=data[j]-data[j-1];
                    tailTemp=j;
                }
            }
            if (!flag) break;
            head =headTemp;
            tail=tailTemp;
        }
        return data;
    }


    @Override
    public int[] sort(int[] data) {
        return sort4(data);
    }

    @Test
    public void test() {
        int[] data=new int[]{6, 11, 0, 9, 7, 15, 6, 4, 13, 9, 3, 16, 0, 2, 8, 13, 9, 12, 0, 19, 4, 4, 6, 15, 8, 16, 15, 13, 2, 2, 17, 11, 6, 14, 0, 9, 17, 11, 14, 2, 6, 0, 18, 11, 18, 12, 3, 3, 17, 5, 16, 8, 15, 13, 17, 3, 10, 8, 19, 10, 1, 0, 0, 13, 0, 17, 9, 8, 17, 10, 12, 5, 8, 7, 4, 19, 0, 18, 16, 19, 17, 13, 5, 14, 15, 11, 16, 0, 16, 10, 13, 6, 3, 3, 2, 5, 11, 3, 13, 15};
//        int[] data=new int[]{9, 9, 5, 17, 13, 8, 19, 16, 15, 3};
        TestSortUtil.test(this::sort,data);
//       TestSortUtil.test(this::sort);
    }
}
