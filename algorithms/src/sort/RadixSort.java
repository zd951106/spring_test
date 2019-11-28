package sort;

import org.junit.Test;
import sort.sortutil.AbstractSort;
import sort.sortutil.Sort;
import sort.sortutil.TestSortUtil;

import java.util.Arrays;

/**
 * Created by zd on 2019/11/21.
 */
public class RadixSort extends AbstractSort {


    public RadixSort() {
        super(null);
    }

    public RadixSort(String name) {
        super(name);
    }

    class Node{
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            String r=""+data;
            if(this.next!=null) r=r+"|"+this.next.toString();
            return r;
        }
    }
    public int[] radixSort1(int[] data,boolean flag) {
        Node[] nodes=new Node[10];
        Node[] bucketTail=new Node[10];
        for (int i = 0; i < data.length; i++) {
            int bucket;
            if(flag) bucket=data[i]%10;
            else bucket=data[i]/10;
            if(bucketTail[bucket]==null) {
                Node node=new Node(data[i]);
                nodes[bucket]=node;
                bucketTail[bucket]=node;
            }else {
                Node node=new Node(data[i]);
                bucketTail[bucket].next=node;
                bucketTail[bucket]=node;
            }
        }


        int index=0;
        for(Node node:nodes) {
            while(node!=null) {
                data[index]=node.data;
                index++;
                node=node.next;
            }
        }


        return data;

    }

    @Override
    public int[] sort(int[] data) {
        data = radixSort1(data, true);
        data=radixSort1(data, false);
        return data;
    }
    @Test
    public void test() {
        TestSortUtil.setLength(20);
        TestSortUtil.setNoneFlag(false);
        TestSortUtil.setOnlyFlag(false);
        TestSortUtil.setRandom(0, 99);
        TestSortUtil.test(this::sort);
    }

}
