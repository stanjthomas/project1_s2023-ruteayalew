/*
    A generic Node class.  Instances contain a data field and a count field.
    In Project 1 each Node will contain a String (shingle) and a count of the
    number of times the shingle has been encountered.
 */

class Node<E extends Comparable<E>> implements Comparable<Node<E>>{
    E data;  // a shingle in the current application
    int    count;  // number of appearances of the shingle

    public Node(E data){
        this.data = data;
        this.count = 1;
    }

    public E getData(){
        return this.data;
    }

    public int getCount(){
        return this.count;
    }

    public void incrementCount(){
        this.count++;
    }

    // to compare two nodes we compare their data field
    // NOTE:  returns 0, negative value, or positive value
    public int compareTo(Node<E> other){
        return this.data.compareTo(other.data);
    }

    public String toString(){
        return String.format("%s : %d", this.data, this.count);
    }
}