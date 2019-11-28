package sort.sortutil;

import java.util.Arrays;

/**
 * Created by zd on 2019/11/17.
 *
 * 在进行数据交互，不使用 （不用临时变量发），当交换的是同一个下标的数据是会产生0的错误
 *
 * 冒泡排序 O(n2) BubbleSort    稳定排序
 * 选择排序 O(n2) SelectionSort 不稳定排序
 * 插入排序 O(n2) InsertSort    稳定排序
 * 希尔排序 O(n1.5) ShellSort   不稳定排序
 * 快速排序 O(N*logN) QuickSort 不稳定排序
 * 归并排序 O(N*logN) MergeSort 稳定排序
 * 堆排序 O(N*logN) Heapsort   实现原理:二叉树    不稳定排序
 * 基数排序 O(d(n+r))   RadixSort   稳定排序
 */
public class TestSortUtil {
    private static int haveData[];
    private static int rightData[];
    private static int noneData[];
    private static int onlyData[];
    private static int length=10;
    private static boolean noneFlag=true;
    private static boolean onlyFlag=true;
    private static int randomLimitStart = 0;
    private static int randomLimitEnd = 20;
    private static boolean hasInit;

    public static void setRandom(int start,int end) {
        randomLimitStart=start;
        randomLimitEnd=end;
    }
    public static void setRandomLimitStart(int randomLimitStart) {
        TestSortUtil.randomLimitStart = randomLimitStart;
    }

    public static void setRandomLimitEnd(int randomLimitEnd) {
        TestSortUtil.randomLimitEnd = randomLimitEnd;
    }

    public static void setNoneFlag(boolean noneFlag) {
        TestSortUtil.noneFlag = noneFlag;
    }

    public static void setOnlyFlag(boolean onlyFlag) {
        TestSortUtil.onlyFlag = onlyFlag;
    }

    public static void setLength(int length) {
        TestSortUtil.length = length;
    }

    static boolean init(int[] data){
        if (data !=null) {
            haveData=data;
        }else {
            haveData = new int[length];
            for (int i = 0; i < haveData.length; i++) {
                haveData[i] = (int) (Math.random() * (randomLimitEnd-randomLimitStart)+randomLimitStart);
            }
        }
        noneData=new int[0];

        onlyData=new int[]{1};

        rightData = Arrays.copyOf(haveData, haveData.length);
        Arrays.sort(rightData);
        return true;
    }
    public static long test(Sort sortDemo) {
        init(null);
        return run(sortDemo);
    }

    public static long test(Sort sortDemo,int[] data) {
        init(data);
        return run(sortDemo);
    }
    public static long testGroup(Sort sortDemo) {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println(sortDemo+":");
        if(hasInit) hasInit=init(null);
        return run(sortDemo);
    }

    public static long testGroup(Sort sortDemo,int[] data) {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println(sortDemo+":");
        if(hasInit) hasInit=init(null);
        return run(sortDemo);
    }
    private static long run(Sort sortDemo) {
        long start=0;
        long end=0;
        int[] res;
        boolean sucessFlag=true;
        try {
            int[] newData = Arrays.copyOf(haveData, haveData.length);
            start = System.nanoTime();
            res = sortDemo.sort(newData);
            end = System.nanoTime();
            for (int i = 0; i < res.length; i++) {
                if (res[i] != rightData[i]) {
                    System.out.println("different: ("+i+") "+res[i]+":"+rightData[i]);
                    sucessFlag=false;
                }
            }
            if(!sucessFlag) {
                System.out.println("fail:raw data:" + Arrays.toString(haveData));
                System.out.println("      error result -" + Arrays.toString(res));
                System.out.println("      right result -" + Arrays.toString(rightData));
            }

        }catch (Exception e) {
            System.out.println("fail:"+e.toString().substring(e.toString().lastIndexOf(".")+1));
            e.printStackTrace();
            return -1;
        }finally {

            System.out.println("process: havedata");
        }

       testNone(sortDemo, noneData, noneFlag);
        testOnly(sortDemo, onlyData, onlyFlag);
        if (sucessFlag) {
            System.out.println("sucess: use (" + (end - start) / 1000.0 + ") microsecond");
            System.out.println("raw data:" + Arrays.toString(haveData));
            System.out.println("res data:" + Arrays.toString(res));
            return end-start;
        }

        return -1;

    }

    private static void testNone(Sort sortDemo,int[] noneData,boolean noneFlag) {
        if(!noneFlag) return;
        try{
            sortDemo.sort(noneData);
        }catch (Exception e) {
            System.out.println("fail:"+e.toString().substring(e.toString().lastIndexOf(".")+1)+" where data length is" +
                    " " +
                    "0;" );
            e.printStackTrace();
            return;
        }finally {

            System.out.println("         nonedata");
        }
    }

    private static void testOnly(Sort sortDemo,int[] onlyData,boolean onlyFlag) {
        if(!onlyFlag) return;
        try{
            sortDemo.sort(onlyData);
        }catch (Exception e) {
            System.out.println("fail:"+e.toString().substring(e.toString().lastIndexOf(".")+1)+" where data length is" +
                    " " +
                    "1;" );
            e.printStackTrace();
            return;
        }finally {

            System.out.println("         onlydata");
        }
    }
}
