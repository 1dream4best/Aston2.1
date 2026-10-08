package com.aston.project.hashmap;

import java.util.Objects;

public class MyHashMap<KeyType, ValueType> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private Node<KeyType, ValueType>[] table;
    private int size;

    public MyHashMap() {
        table = createTable(DEFAULT_CAPACITY);
    }

    public ValueType put(KeyType key, ValueType value) {
        int index = getIndex(key);

        Node<KeyType, ValueType> current = table[index];

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                ValueType oldValue = current.value;
                current.value = value;
                return oldValue;
            }

            current = current.next;
        }

        Node<KeyType, ValueType> newNode =
                new Node<>(key, value, table[index]);

        table[index] = newNode;
        size++;

        if (size > table.length * LOAD_FACTOR) {
            resize();
        }

        return null;
    }

    public ValueType get(KeyType key) {
        int index = getIndex(key);

        Node<KeyType, ValueType> current = table[index];

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return current.value;
            }

            current = current.next;
        }

        return null;
    }

    public ValueType remove(KeyType key) {
        int index = getIndex(key);

        Node<KeyType, ValueType> current = table[index];
        Node<KeyType, ValueType> previous = null;

        while (current != null) {
            if (Objects.equals(current.key, key)) {

                if (previous == null) {
                    table[index] = current.next;
                } else {
                    previous.next = current.next;
                }

                size--;

                return current.value;
            }

            previous = current;
            current = current.next;
        }

        return null;
    }

    public int size() {
        return size;
    }

    private int getIndex(KeyType key) {
        int hash = key == null ? 0 : key.hashCode();

        hash ^= (hash >>> 16);

        return (hash & 0x7fffffff) % table.length;
    }

    private void resize() {
        Node<KeyType, ValueType>[] oldTable = table;

        table = createTable(oldTable.length * 2);

        for (Node<KeyType, ValueType> node : oldTable) {

            while (node != null) {
                Node<KeyType, ValueType> next = node.next;

                int index = getIndex(node.key);

                node.next = table[index];
                table[index] = node;

                node = next;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private Node<KeyType, ValueType>[] createTable(int capacity) {
        return (Node<KeyType, ValueType>[]) new Node[capacity];
    }

    private static class Node<KeyType, ValueType> {

        private final KeyType key;
        private ValueType value;
        private Node<KeyType, ValueType> next;

        private Node(
                KeyType key,
                ValueType value,
                Node<KeyType, ValueType> next
        ) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
