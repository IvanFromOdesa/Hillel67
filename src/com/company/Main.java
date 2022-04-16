package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
       MySet<String> set=new MyHashSet();
       set.add("Apple");
       set.add("Banana");
       set.remove("Apple");
       set.remove("Banana");
       System.out.println(set.contains("Apple"));
       System.out.println(set.isEmpty());
       System.out.println(set.size());
       set.add("Orange");
       set.add("Cherry");
       set.clear();
       System.out.println(Arrays.toString(set.toArray()));
    }
}
