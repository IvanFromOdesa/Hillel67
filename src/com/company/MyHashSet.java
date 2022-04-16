package com.company;

public class MyHashSet implements MySet<String> {

    public static class Entry {
        String string;
        int hashCode;

        public Entry(String string, int hashCode) {
            this.string = string;
            this.hashCode = hashCode;
        }
    }

    private int realSize = 0;
    private int size = 0;/* for correct work of the size() method, because working with realSize when
    removing an object results with an inappropriate return in this method (removed objects are
     still in the set, but are equal to null)*/
    private int capacity = 1;
    private Entry[] arr = new Entry[capacity];

    @Override
    public boolean add(String e) {
        if (contains(e)) {
            return false;
        }
        if(checkNull(e)) return false;
        if (realSize == capacity - 1) {
            int newCapacity = capacity*2; /* ok for the sets with a little or average amount of elements, so
            we do not call the if body every time*/
            Entry[] newArr = new Entry[newCapacity];
            System.arraycopy(arr, 0, newArr, 0, realSize + 1);
            capacity = newCapacity;
            arr = newArr;
        }
        arr[realSize] = new Entry(e, e.hashCode());
        size++;
        realSize++;

        return true;
    }

    @Override
    public void clear() {
        for (int index = 0; index < realSize; index++) {
            if(arr[index]!=null)
            remove(arr[index].string);
        }
    }


    @Override
    public boolean contains(String o) {
        if (realSize > 0 && o!=null) {
            for (int index=0; index<realSize; index++) {
                if (arr[index]!= null) {
                    if (arr[index].string.equals(o)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        boolean flag=true;
        for(int index=0; index<realSize; index++){
            if(arr[index]!=null){
                flag=false;
                return flag;
            } else{
                flag=true;
            }
        }
        return flag;
    }

    @Override
    public boolean remove(String o) {
        if(checkNull(o)){
            return false;
        };
        if (contains(o)) {
           for(int index=0; index<realSize; index++){
               if(arr[index]!=null){
               if(arr[index].string==o){
                   arr[index]=null;
                   size--;// for the correct return value in size()
               }
           }}
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String[] toArray() {
        String[] newArr = new String[realSize];
        for (int i = 0; i < realSize; i++) {
            if(arr[i]!=null){
            newArr[i] = arr[i].string;
        }}
        return newArr;
    }

    /*@Override
    public StringBuffer toArray() {
        StringBuffer str = new StringBuffer();
            for(int index=0; index<size; index++){
                    str.ensureCapacity(str.length()+arr[index].string.length());
                    str.append(arr[index].string+"\n");
            }
        return str;
    }
    Без понятия, что Вы имели в виду под предлогом изменить String[] на StringBuffer, нам же в конечном
    итоге нужен массив, а не строка, чтобы работать с массивом через индексы и т.д. А по-другому как
    реализовать тут StringBuffer не понимаю (С занятия №10, где Вы обсуждали другую работу).
     */

    private boolean checkNull(String e) {
        if(e==null) return true;
        return false;
        }
    }