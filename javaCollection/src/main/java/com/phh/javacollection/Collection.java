package com.phh.javacollection;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Date;
/**
 * Created by my-tran on 6/5/17.
 */
public class Collection {

    public int[] myCollectionArray = {1, 2, 4, 5, 3, 2, 8};
    public List<Integer> myCollectionList = new ArrayList<Integer>();
    public Set<String> myCollectionSet = new HashSet<String>();
    public Map<String, Integer> myCollectionMap = new HashMap<String, Integer>();
    public String myLocalDateText = null;
    public String myLocalDateTimeText = null;

    public static void main(String args[]) {
        Collection c = new Collection();
        System.out.println("Object array values before: " + Arrays.toString(c.myCollectionArray));
        c.filterOutDuplicateInArray();
        System.out.println("Object array values: " + Arrays.toString(c.myCollectionArray));
        c.myCollectionList.add(7);
        c.myCollectionList.add(4);
        c.myCollectionList.add(9);
        c.myCollectionList.add(7);
        c.myCollectionList.add(9);
        c.myCollectionList.add(1);
        c.myCollectionList.add(15);
        c.myCollectionList.add(1);
        System.out.println("\nObject List values before: " + c.myCollectionList);
        c.filterOutDuplicateInList();
        System.out.println("Object List values after: " + c.myCollectionList);
        c.myLocalDateText = c.getNowDate();
        System.out.print(c.myLocalDateText);
        c.myLocalDateTimeText = c.getNowDateTime();
        System.out.print("\n" + c.myLocalDateTimeText);
        c.initSetFromArrayAndListValues();
        System.out.println(c.myCollectionSet);
        c.myCollectionMap = c.initMapFromSetAndList();
        System.out.print("\n Map" + c.myCollectionMap);

    }

    public void filterOutDuplicateInList() {

        List<Integer> Arr = new ArrayList<Integer>();
        for (int i = 0; i < this.myCollectionList.size(); i++) {
            if (!Arr.contains(myCollectionList.get(i))) {
                Arr.add(myCollectionList.get(i));
            }
        }
        myCollectionList = Arr;
    }

    public void filterOutDuplicateInArray() {
        int n = myCollectionArray.length;
        for (int i = 0; i < n; i++) {
            for (int k = i + 1; k < n; k++)
                if (myCollectionArray[k] == myCollectionArray[i]) {
                    int j;
                    for (j = k; j < n - 1; j++) {
                        myCollectionArray[j] = myCollectionArray[j + 1];
                    }
                    n--;
                    k--;
                }
        }
        myCollectionArray = this.reSize(myCollectionArray, n);
    }

    public boolean initSetFromArrayAndListValues() {
        boolean hasDuplicate = false;
        if (myCollectionList != null)
            for (Integer intNum : myCollectionList) {
                myCollectionSet.add(intNum.toString());
            }
        if (myCollectionArray != null)
            for (int i = 0; i < myCollectionArray.length; i++) {
                if (!myCollectionSet.contains(String.valueOf(myCollectionArray[i])))
                    myCollectionSet.add(String.valueOf(myCollectionArray[i]));
                else {
                    //System.out.print("\nTrue");
                    hasDuplicate = true;
                    //return true;
                }
            }
        return false;
    }

    public Map initMapFromSetAndList() {
        //TreeSet sortedSet = new TreeSet<String>(myCollectionSet);
        List<String> a = new ArrayList<String>(myCollectionSet);
        if(a.size()>0) {
            if (a.size() > myCollectionList.size()) {
                for (int i = 0; i < myCollectionList.size(); i++) {
                    myCollectionMap.put(a.get(i), myCollectionList.get(i));
                }
            } else {
                for (int i = 0; i < a.size(); i++) {
                    myCollectionMap.put(a.get(i), myCollectionList.get(i));
                }
            }
//        for (Map.Entry<String, Integer> entry : myCollectionMap.entrySet()) {
//            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//        }
        }
            return myCollectionMap;
    }

    public String getNowDate() {
        Date date = new Date();
        SimpleDateFormat ft =
            new SimpleDateFormat("yyyy-MM-dd");
        //System.out.println("Date hien tai: " + ft.format(date));
        return ft.format(date).toString();
    }

    public String getNowDateTime() {
        Date date = new Date();
        SimpleDateFormat ft =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //System.out.println("Date hien tai: " + ft.format(date));
        return ft.format(date).toString();
    }

    public int[] reSize(int[] a, int size) {
        int[] temp = new int[size];
        System.arraycopy(a, 0, temp, 0, (a.length < size) ? a.length : size);
        return temp;
    }
}

