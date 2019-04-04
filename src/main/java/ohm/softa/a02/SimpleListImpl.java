package ohm.softa.a02;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable {

	// TODO: Implement the required methods.

    public Element head;

    private int size = 0;

    public SimpleListImpl(){

    }

    public void add(Object item){

        size++;

        if(head == null){
            head = new Element(item);
            return;
        }

        Element current = head;
        while(current.next != null){
            current = current.next;
        }

        current.next = new Element(item);
    }

    public int size(){
        return size;
    }

    public SimpleList filter(SimpleFilter filter){
        SimpleList filteredList = new SimpleListImpl();
        for(Object a : this){
            if(filter.include(a)){
                filteredList.add(a);
            }
        }
        return filteredList;
    }

    @Override
    public Iterator iterator() {
        //Möglich als anonyme Klasse
        return new Iterator(){

            Element current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Object next() {
                Object toReturn = current.item;
                current = current.next;
                return toReturn;
            }
        };
        //Auch möglich
        //return new SimpleIteratorImpl();
    }


    private static class Element{

        Object item;
        Element next;

        Element(Object item){
            this.item = item;
            next = null;
        }
    }

    public class SimpleIteratorImpl implements Iterator {

        Element current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            Object toReturn = current.item;
            current = current.next;
            return toReturn;
        }
    }
}
