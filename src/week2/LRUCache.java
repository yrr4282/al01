package week2;

import java.util.HashMap;

/**
 * 运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 * <p>
 * LRUCache(int capacity) 以正整数作为容量capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 * <p>
 * 进阶：你是否可以在O(1) 时间复杂度内完成这两种操作？
 * 双向链表 + HashMap
 */
public class LRUCache {
    class Node {
        Node pre;
        Node next;
        int key;
        int value;
    }

    private int capacity;
    private Node head = new Node();
    private Node tail = new Node();

    // 存储Node节点，按key值可以直接读取
    private HashMap<Integer, Node> mapForIndex = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.pre = head;
    }

    /**
     * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
     * @param key 关键字 key
     * @return 返回value
     */
    public int get(int key) {
        // 不存在，返回 - 1
        if (!mapForIndex.containsKey(key)) {
            return -1;
        }
        // 存在，返回value，将key插入链表表头
        Node curNode = mapForIndex.get(key);
        // 在链表中删除
        removeFromList(curNode);
        // 重新插入表头
        put(key, curNode.value);
        return curNode.value;
    }

    private void removeFromList(Node curNode) {
        Node preNode = curNode.pre;
        Node nextNode = curNode.next;
        preNode.next = nextNode;
        nextNode.pre = preNode;
        mapForIndex.remove(curNode.key);
    }

    /**
     *
     * @param key 关键字 key
     * @param value
     */
    public void put(int key, int value) {
        if (mapForIndex.containsKey(key)) {
            Node containNode = mapForIndex.get(key);
            removeFromList(containNode);
        }
        // 插入队头
        Node curNode = new Node();
        curNode.key = key;
        curNode.value = value;
        curNode.pre = head;
        curNode.next = head.next;
        head.next.pre = curNode;
        head.next = curNode;
        mapForIndex.put(key, curNode);
        if (mapForIndex.size()  > capacity) {
            removeFromList(tail.pre);
        }
    }
}
