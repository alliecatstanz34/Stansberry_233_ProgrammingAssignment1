public class Node {
    int data;
    Node next;

    //creates a Node to point to another node with no data, will be used as temp
    public Node (Node initNode){
        data = 0;
        next = initNode;
    }

    public Node (int value){
        data = value;
        next = null;
    }
}
