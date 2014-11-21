/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

/**
 *
 * @author jayp 
 */

public interface IMap
{
    /**
     * returns the no of elements present in the map
     * @return the capacity
     */
    int size();
    /**
     * checks whether the map is empty
     * @return true is map empty false otherwise
     */
    boolean isEmpty();
    /**
     * takes a key and returns the String value with the given key
     * @param key
     * @return value with the given key 
     */
    String get(int key);
    /**
     * takes key and a value and stores the value in the map according to the 
     * key in case of duplication it changes the value at the key to new value
     * @param key
     * @param value 
     */
    void put(int key, String value);
    /**
     * takes a key and deletes the element with the key
     * @param key
     * @return whether the key exists in the map
     */
    boolean remove(int key);
    /**
     * returns the list of keys in the map
     * @return 
     */
    IList keySet();
    /**
     * returns the list of values in the map
     * @return 
     */
    String[] valueSet();
}
