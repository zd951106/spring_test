package sort.sortutil;

import sort.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zd on 2019/11/21.
 */
public class GroupTest {
    public static void main(String[] args) {
        TestSortUtil.setLength(20);
        TestSortUtil.setNoneFlag(false);
        TestSortUtil.setOnlyFlag(false);
        TestSortUtil.setRandom(0, 99);
        List<Long> list = new ArrayList<>();
        list.add(TestSortUtil.testGroup(new BubbleSort("BubbleSort")));
        list.add(TestSortUtil.testGroup(new InsertSort("InsertSort")));
        list.add(TestSortUtil.testGroup(new MergeSort("MergeSort")));
        list.add(TestSortUtil.testGroup(new QuickSort("QuickSort")));
        list.add(TestSortUtil.testGroup(new RadixSort("RadixSort")));
        list.add(TestSortUtil.testGroup(new SelectionSort("SelectionSort")));
        list.add(TestSortUtil.testGroup(new ShellSort("ShellSort")));

        list.forEach(System.out::println);
    }
}
