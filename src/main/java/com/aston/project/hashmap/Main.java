package com.aston.project.hashmap;

public class Main {

    public static void main(String[] args) {

        MyHashMap<String, Integer> map = new MyHashMap<>();

        System.out.println("Добавляем элементы:");

        map.put("Java", 100);
        map.put("Python", 200);
        map.put("C++", 300);

        System.out.println("Java: " + map.get("Java"));
        System.out.println("Python: " + map.get("Python"));
        System.out.println("C++: " + map.get("C++"));

        System.out.println();

        System.out.println("Размер: " + map.size());

        System.out.println();

        System.out.println("Удаляем Java:");
        System.out.println("Удалённое значение: " + map.remove("Java"));

        System.out.println();

        System.out.println("Java после удаления: " + map.get("Java"));
        System.out.println("Размер после удаления: " + map.size());

        System.out.println();

        System.out.println("Проверяем замену значения:");
        map.put("Python", 500);

        System.out.println("Python: " + map.get("Python"));
    }
}