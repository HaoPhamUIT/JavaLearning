package phh;

import com.phh.javacollection.Collection;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Date;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


/**
 * Created by my-tran on 6/6/17.
 */
public class JavaCollectionTest {

    @Test
    public void test_array() {
        Collection c = new Collection();
        c.myCollectionArray = new int[]{1, 2, 4, 5, 3, 2, 8};
        c.filterOutDuplicateInArray();
        int[] myCollectionArrayTest = {1, 2, 4, 5, 3, 8};
        //System.out.print(Arrays.toString(myCollectionArrayTest)+Arrays.toString(c.myCollectionArray));
        assertArrayEquals(myCollectionArrayTest, c.myCollectionArray);
        assertEquals(Arrays.toString(myCollectionArrayTest), Arrays.toString(c.myCollectionArray));
    }

    @Test
    public void test_array_is_null() {
        Collection c = new Collection();
        c.myCollectionArray = new int[]{};
        c.filterOutDuplicateInArray();
        int[] myCollectionArrayTest = {};
        //System.out.print(Arrays.toString(myCollectionArrayTest)+Arrays.toString(c.myCollectionArray));
        assertArrayEquals(myCollectionArrayTest, c.myCollectionArray);
        assertEquals(Arrays.toString(myCollectionArrayTest), Arrays.toString(c.myCollectionArray));
    }

    @Test
    public void test_list() {
        Collection c = new Collection();
        c.myCollectionList.add(1);
        c.myCollectionList.add(2);
        c.myCollectionList.add(5);
        c.myCollectionList.add(2);
        c.myCollectionList.add(3);
        c.myCollectionList.add(5);
        c.myCollectionList.add(4);
        c.myCollectionList.add(6);
        c.filterOutDuplicateInList();
        List<Integer> myCollectionListTest = new ArrayList<Integer>();
        myCollectionListTest.add(1);
        myCollectionListTest.add(2);
        myCollectionListTest.add(5);
        myCollectionListTest.add(3);
        myCollectionListTest.add(4);
        myCollectionListTest.add(6);
        assertEquals(myCollectionListTest, c.myCollectionList);
    }

    @Test
    public void test_list_ís_null() {
        Collection c = new Collection();
        c.filterOutDuplicateInList();
        List<Integer> myCollectionListTest = new ArrayList<Integer>();
        assertEquals(myCollectionListTest, c.myCollectionList);
    }

    @Test
    public void test_set() {
        Collection c = new Collection();
        c.myCollectionArray = new int[]{1, 2, 4, 5, 3, 2, 8, 7};
        c.myCollectionList.add(1);
        c.myCollectionList.add(2);
        c.myCollectionList.add(5);
        c.myCollectionList.add(2);
        c.myCollectionList.add(3);
        c.myCollectionList.add(5);
        c.myCollectionList.add(4);
        c.myCollectionList.add(6);
        c.myCollectionList.add(7);
        c.initSetFromArrayAndListValues();
        Set<String> setTest = new HashSet<String>();
        int[] myCollectionArrayTest = {1, 2, 4, 5, 3, 8, 7};
        List<Integer> myCollectionListTest = new ArrayList<Integer>();
        myCollectionListTest.add(1);
        myCollectionListTest.add(2);
        myCollectionListTest.add(5);
        myCollectionListTest.add(3);
        myCollectionListTest.add(4);
        myCollectionListTest.add(6);
        myCollectionListTest.add(7);
        for (Integer intNum : myCollectionListTest) {
            setTest.add(intNum.toString());
        }
        for (int i = 0; i < myCollectionArrayTest.length; i++) {
            if (!setTest.contains(String.valueOf(myCollectionArrayTest[i])))
                setTest.add(String.valueOf(myCollectionArrayTest[i]));
        }
        assertEquals(setTest, c.myCollectionSet);
    }

    @Test
    public void test_set_is_null() {
        Collection c = new Collection();
        c.myCollectionArray = new int[]{};
        c.myCollectionList = null;
        c.initSetFromArrayAndListValues();
        Set<String> setTest = new HashSet<String>();
        assertEquals(setTest, c.myCollectionSet);
    }

    @Test
    public void test_map_key_bigger_value() {
        Collection c = new Collection();
        c.myCollectionArray = new int[]{1, 2, 4, 5, 3, 2, 8, 7, 10, 14, 11, 10};
        c.myCollectionList.add(1);
        c.myCollectionList.add(2);
        c.myCollectionList.add(5);
        c.myCollectionList.add(2);
        c.myCollectionList.add(3);
        c.myCollectionList.add(5);
        c.myCollectionList.add(4);
        c.myCollectionList.add(6);
        c.myCollectionList.add(7);
        c.filterOutDuplicateInList();
        c.initSetFromArrayAndListValues();
        c.initMapFromSetAndList();
        Map<String, Integer> mapTest = new HashMap<String, Integer>();
        Set<String> setTest = new HashSet<String>();
        int[] myCollectionArrayTest = {1, 2, 4, 5, 3, 8, 7, 10, 14, 11};
        List<Integer> myCollectionListTest = new ArrayList<Integer>();
        myCollectionListTest.add(1);
        myCollectionListTest.add(2);
        myCollectionListTest.add(5);
        myCollectionListTest.add(3);
        myCollectionListTest.add(4);
        myCollectionListTest.add(6);
        myCollectionListTest.add(7);
        for (Integer intNum : myCollectionListTest) {
            setTest.add(intNum.toString());
        }
        for (int i = 0; i < myCollectionArrayTest.length; i++) {
            if (!setTest.contains(String.valueOf(myCollectionArrayTest[i])))
                setTest.add(String.valueOf(myCollectionArrayTest[i]));
        }
        List<String> a = new ArrayList<String>(setTest);
        if (a.size() > myCollectionListTest.size()) {
            for (int i = 0; i < myCollectionListTest.size(); i++) {
                mapTest.put(a.get(i), myCollectionListTest.get(i));
            }
        } else {
            for (int i = 0; i < a.size(); i++) {
                mapTest.put(a.get(i), myCollectionListTest.get(i));
            }
        }
        assertEquals(mapTest, c.myCollectionMap);
    }

    @Test
    public void test_map_value_bigger_key() {
        Collection c = new Collection();
        c.myCollectionArray = new int[]{1, 2, 4, 5, 3, 2, 8};
        c.myCollectionList.add(1);
        c.myCollectionList.add(2);
        c.myCollectionList.add(5);
        c.myCollectionList.add(2);
        c.myCollectionList.add(3);
        c.myCollectionList.add(5);
        c.myCollectionList.add(4);
        c.myCollectionList.add(6);
        c.myCollectionList.add(12);
        c.myCollectionList.add(23);
        c.myCollectionList.add(15);
        c.myCollectionList.add(11);
        c.myCollectionList.add(10);
        c.filterOutDuplicateInList();
        c.initSetFromArrayAndListValues();
        c.myCollectionMap = c.initMapFromSetAndList();
        Map<String, Integer> mapTest = new HashMap<String, Integer>();
        Set<String> setTest = new HashSet<String>();
        int[] myCollectionArrayTest = {1, 2, 4, 5, 3, 8};
        List<Integer> myCollectionListTest = new ArrayList<Integer>();
        myCollectionListTest.add(1);
        myCollectionListTest.add(2);
        myCollectionListTest.add(5);
        myCollectionListTest.add(3);
        myCollectionListTest.add(4);
        myCollectionListTest.add(6);
        myCollectionListTest.add(12);
        myCollectionListTest.add(23);
        myCollectionListTest.add(15);
        myCollectionListTest.add(11);
        myCollectionListTest.add(10);
        for (Integer intNum : myCollectionListTest) {
            setTest.add(intNum.toString());
        }
        for (int i = 0; i < myCollectionArrayTest.length; i++) {
            if (!setTest.contains(String.valueOf(myCollectionArrayTest[i])))
                setTest.add(String.valueOf(myCollectionArrayTest[i]));
        }
        List<String> a = new ArrayList<String>(setTest);
        if (a.size() > myCollectionListTest.size()) {
            for (int i = 0; i < myCollectionListTest.size(); i++) {
                mapTest.put(a.get(i), myCollectionListTest.get(i));
            }
        } else {
            for (int i = 0; i < a.size(); i++) {
                mapTest.put(a.get(i), myCollectionListTest.get(i));
            }
        }
        assertEquals(mapTest, c.myCollectionMap);
    }

    @Test
    public void test_map_key_value_null() {
        Collection c = new Collection();
        c.myCollectionArray = new int[]{};
        c.myCollectionList = null;
        c.initSetFromArrayAndListValues();
        c.initMapFromSetAndList();
        Map<String,Integer> myCollectionMapTest = new HashMap<String, Integer>(){};
        assertEquals(myCollectionMapTest, c.myCollectionMap);
    }

    @Test
    public void test_get_now_date() {
        Collection c = new Collection();
        c.myLocalDateText = c.getNowDate();
        String myLocalDateTextTest = null;
        Date date = new Date();
        SimpleDateFormat ft =
            new SimpleDateFormat("yyyy-MM-dd");
        myLocalDateTextTest = ft.format(date).toString();
        assertEquals(myLocalDateTextTest, c.myLocalDateText);
    }

    @Test
    public void test_get_now_date_time() {
        Collection c = new Collection();
        c.myLocalDateTimeText = c.getNowDateTime();
        String myLocalDateTextTimeTest = null;
        Date date = new Date();
        SimpleDateFormat ft =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        myLocalDateTextTimeTest = ft.format(date).toString();
        assertEquals(myLocalDateTextTimeTest, c.myLocalDateTimeText);
    }
}
