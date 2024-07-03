public class TwoWayLinkedList {

    private Node head;
    private Node tail;
    private int size;
    private Iterator iterator;

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    public void clearAll() {
        head = null;
        tail = null;
        size = 0;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public void add(String val) {
        if (isEmpty()) {
            head = new Node(val);
            tail = head;
            size++;
            return;
        }

        Node newNode = new Node(tail, val, null);
        tail.setNext(newNode);
        tail = newNode;
        size++;
    }

    public void add(int index, String value) {
        if (index > size - 1 || index < 0) {
            throw new IndexExceedsSizeLengthException(String.format("Индекс %d не существует при размерности списка %d", index, size));
        }

        Node currentNode = null;
        if (index == 0) {
            currentNode = head;
            head = new Node(null, value, currentNode);
            currentNode.setPrev(head);
        } else if (index == size) {
            add(value);
        } else {
            currentNode = head;
            int count = 0;
            while (count != index) {
                currentNode = currentNode.getNext();
                count++;
            }
            Node newNode = new Node(currentNode.getPrev(), value, currentNode);
            currentNode.getPrev().setNext(newNode);
            currentNode.setPrev(newNode);
        }
        size++;
    }

    public void replace(int index, String val) {
        if (index > size - 1 || index < 0) {
            throw new IndexExceedsSizeLengthException(String.format("Индекс %d не существует при размерности списка %d", index, size));
        }
        if (index == 0) {
            if (head == null) {
                add(val);
            } else {
                head.setValue(val);
            }
        } else {
            Node currentNode = head;
            int count = 0;
            while (count != index) {
                currentNode = currentNode.getNext();
                count++;
            }
            currentNode.setValue(val);
        }
    }

    public void delete(int index) {
        if (index > size - 1 || index < 0) {
            throw new IndexExceedsSizeLengthException(String.format("Индекс %d не существует при размерности списка %d", index, size));
        }
        if (size > 1) {
            if (index == 0) {
                head = head.getNext();
            } else if (index == size - 1) {
                tail = tail.getPrev();
                tail.setNext(null);
            } else {
                Node currentNode = head;
                int count = 0;
                while (count != index) {
                    currentNode = currentNode.getNext();
                    count++;
                }
                currentNode.getPrev().setNext(currentNode.getNext());
                currentNode.getNext().setPrev(currentNode.getPrev());
            }
        } else if (size == 1) {
            head = null;
            tail = null;
        }
        size--;
    }

    public void delete(String val) {
        Node currentNode = head;
        int count = 0;
        while (count != size) {
            if (currentNode.getValue().equals(val)) {
                delete(count);
                return;
            }
            currentNode = currentNode.getNext();
            count++;
        }
    }

    public Iterator iteratorHead() {
        return new Iterator(head);
    }

    public Iterator iteratorTail() {
        return new Iterator(tail);
    }

    @Override
    public String toString() {
        String list = "";
        if (head != null) {
            Node current = head;
            list = "TwoWayLinkedList{" + head + ", ";
            while (current.getNext() != null) {
                list = list.concat(current.getNext().toString());
                current = current.getNext();
            }
            list = list + "}";
        }
        return list;
    }

    protected static class Iterator {
        private Node current;

        public Iterator(Node current) {
            this.current = current;
        }

        public boolean hasNext() {
            return current.getNext() != null;
        }

        public boolean hasPrev() {
            return current.getPrev() != null;
        }

        public String currentValue() {
            return current.getValue();
        }

        public void moveToNext() {
            if (current.getNext() != null) {
                current = current.getNext();
            }
        }

        public void moveToPrev() {
            if (current.getPrev() != null) {
                current = current.getPrev();
            }
        }
    }

    private static class Node {
        private Node prev;
        private Node next;
        private String value;

        //public Node(){}
        public Node(String value) {
            this(null, value, null);
        }

        public Node(Node prev, String value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            String p = "null";
            String n = "null";
            if (prev != null) {
                p = prev.getValue();
            }
            if (next != null) {
                n = next.getValue();
            }
            return "Node{" +
                    "prev=" + p +
                    ", next=" + n +
                    ", value='" + value + '\'' +
                    '}';
        }
    }
}