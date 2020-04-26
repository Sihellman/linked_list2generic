package edu.ti.collections.list.linked;

public class LinkedList<T> {
    public class Node {
        T payload;
        Node next = null;

        public Node(T payload) {
            this.payload = payload;
        }

        public T getPayload() {
            return payload;
        }

        public void setPayload(T payload) {
            this.payload = payload;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    Node headNode = null;
    Node endNode = null;


    public LinkedList() {
        // nothing
    }

    public LinkedList(T payload) {
        headNode = new Node(payload);
        endNode = headNode;
    }


    public boolean isEmpty() {
        return (headNode == null);
    }
    Node end() {
        return lastNode(headNode);
        /*Node endNode = headNode;
        while (endNode.getNext() != null) {
            endNode = endNode.getNext();
        }
        return endNode;*/
    }
    private Node lastNode(Node node){
        Node lastNode = (node == null)? null: (node.next == null)? node: lastNode(node.next);
        return lastNode;
    }

    public int size() {
        return sizeHelper(headNode);
        /*int size = 0;
        for (Node start = headNode; start != null; start = start.next) {
            size += 1;
        }
        return size;*/
    }
    int sizeHelper(Node node){
        int size = (node == null)? 0: 1 + sizeHelper(node.next);
        return size;
    }

    public void insert(T object) {
        Node newNode = new Node(object);
        newNode.setNext(headNode);
        if (headNode == null){
            endNode = newNode;
        }
        headNode = newNode;
    }

    public void append(T object) {
        Node newNode = new Node(object);
        if (headNode == null) {
            headNode = newNode;
            endNode = newNode;
        } else {
            endNode.setNext(newNode);
            endNode = newNode;
        }
    }

    public T get(int n) {
        T requestedObject = null;
        if (n < size()) {
            requestedObject = getRequestObject(n, headNode).getPayload();
            /*Node requestedNode = headNode;
            while (n-- > 0) {
                requestedNode = requestedNode.getNext();
            }
            requestedObject = requestedNode.getPayload();*/
        }
        return requestedObject;
    }
    private Node getRequestObject(int n, Node node){
        Node requestedNode = (n == 0)? node: getRequestObject(n-1, node.getNext());
        return requestedNode;
    }

    public T remove(int n) {
        T requestedObject = null;
        if (n < size()) {
            Node beforeRequestedNode = null;
            Node requestedNode = headNode;
            while (n-- > 0) {
                beforeRequestedNode = requestedNode;
                requestedNode = requestedNode.getNext();
            }
            if (beforeRequestedNode != null) {
                beforeRequestedNode.setNext(requestedNode.getNext());
                if (beforeRequestedNode.getNext() == null){
                    endNode = beforeRequestedNode;
                }
            } else {
                headNode = requestedNode.getNext();
                endNode = headNode;
            }
            requestedObject = requestedNode.getPayload();
        }
        return requestedObject;
    }
    /*public T remove(int n){
        T requestedObject = null;
        if (n < size()){
            Node beforeRequestedNode = null;
            Node requestedNode = headNode;
            if (n > 0){
                remove(n-1);
                beforeRequestedNode = requestedNode;
                requestedNode = requestedNode.getNext();
                System.out.println("beforeRequestedNode" + beforeRequestedNode);
                System.out.println("requestedNode " + requestedNode);
            }
            if (beforeRequestedNode != null) {
                beforeRequestedNode.setNext(requestedNode.getNext());
                if (beforeRequestedNode.getNext() == null){
                    endNode = beforeRequestedNode;
                }
            } else {
                headNode = requestedNode.getNext();
                endNode = headNode;
            }
            requestedObject = requestedNode.getPayload();
        }
        return requestedObject;

    }*/

    public static void main(String[] args) {
        Integer i12 = new Integer(12);
        Integer j37 = new Integer(37);
        Integer k34 = new Integer(34);
        LinkedList linkedList = new LinkedList<>(i12);
        linkedList.insert(j37);
        linkedList.insert(k34);
        linkedList.remove(2);

    }

    public T remove(T object) {
        T requestedObject = null;
        if (headNode != null) {
            Node beforeRequestedNode = null;
            Node requestedNode = headNode;
            boolean foundNode = false;
            do {
                if (requestedNode.getPayload().equals(object)) {
                    foundNode = true;
                } else {
                    beforeRequestedNode = requestedNode;
                    requestedNode = requestedNode.getNext();
                }
            } while (!foundNode && requestedNode.getNext() != null);

            if (beforeRequestedNode != null) {
                beforeRequestedNode.setNext(requestedNode.getNext());
                if (beforeRequestedNode.getNext() == null){
                    endNode = beforeRequestedNode;
                }
            } else {
                headNode = requestedNode.getNext();
                endNode = headNode;
            }
            requestedObject = requestedNode.getPayload();
        }
        return requestedObject;
    }
}
