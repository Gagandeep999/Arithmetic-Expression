/*
 Author: Gagandeep Singh
 Date: 21 February, 2018
 Purpose: The class MyStack is a generic class that creates a stack based on the data type provided.
 */
import java.util.Stack;

public class MyStack<E>  {

    private E[] e;
    int s = -1;

    public MyStack(){
        e = (E[])new Object[10];
    }
    public  MyStack(int size){
        e = (E[])new Object[size];
    }
    public int size(){
        return (s+1);
    }
    boolean isEmpty(){
        return (s == -1);
    }
    //if the size of the stack is full it calls another method called "changeSize()" that takes an array of
    //E datatype and also returns the same array but with the size than the previous one.
    void push(E e1){
        if(e.length == size()){
            this.e = changeSize(e);
        }
        e[++s] = e1;
    }
    //returns the passed array with double size.
    private E[] changeSize(E[] e){
        E[] temp = (E[])new Object[2*(e.length)];
//        for(int i=0;i<e.length;i++){
//            temp[i] = e[i];
//        }
        System.arraycopy(e,0,temp,0,e.length);
        return temp;
    }
    public E top(){
        if(isEmpty())
            return null;
        else
            return e[s];
    }
    public E pop(){
        if(isEmpty())
            return null;
        else{
            E value = e[s];
            e[s] = null;
            s--;
            return value;
        }
    }
}
