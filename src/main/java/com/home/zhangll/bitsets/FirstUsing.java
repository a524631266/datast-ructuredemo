package com.home.zhangll.bitsets;

import com.carrotsearch.sizeof.RamUsageEstimator;

import java.util.BitSet;



public class FirstUsing {
    // 实现自己的位图myBitSet.set(2);
    // char 在java中为无符号数字myBitSet.set(2);
    static class MyBitSet{
        private final char[] aChar;
        private final int size;
        public MyBitSet(int size){
            aChar = new char[ size % 16 == 0? size / 16  : size /16 +1];
            this.size = size;
        }

        public void set(int i) {
//            int index = i / (1 << 16);
//            int splitindex = i - (1<< 16) *  index + 1;
            int index = i % 16 == 0? i / 16 - 1 : i /16;
            int splitindex = 1 << (i - index * 16 - 1);
//            System.out.println(index + ":" + splitindex);
            aChar[index] = (char) (aChar[index] | splitindex);
        }
        public boolean get(int i) {
//            int index = i / (1 << 16);
//            int splitindex = i - (1<< 16) *  index + 1;
            int index = i % 16 == 0? i / 16 -1 : i /16;
            int splitindex = 1 << (i - index * 16 - 1);
            int i1 = aChar[index] & splitindex;
//            System.out.println(Integer.toBinaryString(aChar[index]));
//            System.out.println(Integer.toBinaryString(splitindex));
            return i1 == splitindex;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
//            StringBuffer
            for (int i = 0; i < aChar.length; i++) {
                sb.append(aChar[i]);
                System.out.println(Integer.toBinaryString(aChar[i]));
            }
            return sb.toString();
        }
    }


    private static BitSet bitSet = new BitSet(10000);
    public static void main(String[] args) {
//        bitSet.set(10);
//        boolean b = bitSet.get(9);
//        System.out.println(b);
//        char a = 65535;
////        Integer a = 1;
////        String a = "1";
//
//        System.out.println(Integer.toBinaryString(a));

        int size = 1234567890;
        MyBitSet myBitSet = new MyBitSet(size);
        myBitSet.set(1);
        myBitSet.set(2);
        myBitSet.set(3);
        myBitSet.set(31);
        myBitSet.set(32);
        myBitSet.set(16);
//        myBitSet.set(123324);
//        myBitSet.set(23423);
//        myBitSet.set(34234234);
//        System.out.println(myBitSet);
        for (int i = 1; i <= size; i++) {
            boolean b = myBitSet.get(i);
            if(b) System.out.println(i);
        }
//        boolean b = myBitSet.get(3);
//        System.out.println("if:"+ b);
//        System.out.println(myBitSet);
        long l = RamUsageEstimator.sizeOf(myBitSet);
        System.out.println("size: "+l);
    }
}
