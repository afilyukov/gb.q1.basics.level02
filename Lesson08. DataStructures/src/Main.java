public class Main {
    public static void main(String[] args) {
        TwoWayLinkedList names = new TwoWayLinkedList();
        names.add("Mike");
        names.delete(0);
        System.out.println(names);
        names.add("John");
        names.add("Jakub");
        names.add("Marry");
        names.add("Kaizerine");
        names.add(1, "Tomas");
        names.add(2, "James");
        names.replace(1, "Bob");
        System.out.println(names);

        names.delete(3);
        System.out.println(names);

        names.delete("John");
        System.out.println(names);

        //names.clearAll();
        System.out.println(names.getHead());
        System.out.println(names.getTail());
        System.out.println(names.getSize());

        TwoWayLinkedList.Iterator iterator = names.iteratorHead();
        while (iterator.hasNext()) {
            System.out.println(iterator.currentValue());
            iterator.moveToNext();
        }

        TwoWayLinkedList.Iterator revertIterator = names.iteratorTail();
        while (revertIterator.hasPrev()) {
            System.out.println(revertIterator.currentValue());
            revertIterator.moveToPrev();
        }
    }
}
