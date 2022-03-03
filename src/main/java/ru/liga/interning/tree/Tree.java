package ru.liga.interning.tree;

/**
 * @author Repkin Andrey {@literal <arepkin@at-consulting.ru>}
 */
public class Tree {
    private String key;
    private Integer val;
    private Tree left;
    private Tree right;

    public Tree getLeft() {
        return left;
    }

    public Tree getRight() {
        return right;
    }

    public Tree(String key, Integer value, Tree left, Tree right) {
        this.key = key;
        this.val = value;
        this.left = left;
        this.right = right;
    }

    public class TreeProcessor {
        public static Integer lookup(String k, Integer defaultval, Tree t) {
            if (t == null) return defaultval;
            if (k.equals(t.key)) return t.val;
            return lookup(k, defaultval, k.compareTo(t.key) < 0 ? t.left : t.right);
        }

        public static void update(String k, Integer newval, Tree t) {
            if (t == null) { /* нужно обновить узел*/ } else if (k.equals(t.key)) t.val = newval;
            else update(k, newval, k.compareTo(t.key) < 0 ? t.left : t.right);
        }

        public static Tree newItem(String k, Integer newval, Tree t) {
            if (t == null)
                t = new Tree(k, newval, null, null);
            else if (k.equals(t.key))
                t.val = newval;
            else if (k.compareTo(t.key) < 0)
                t.left = newItem(k, newval, t.left);
            else
                t.right = newItem(k, newval, t.right);
            return t;
        }
    }

    public class FunctionalTreeProcessor {
        public static Integer lookup(String k, Integer defaultval, Tree t) {
            if (t == null) return defaultval;
            if (k.equals(t.key)) return t.val;
            return lookup(k, defaultval, k.compareTo(t.key) < 0 ? t.left : t.right);
        }

        public static Tree fUpdate(String k, Integer newval, Tree t) {
            if (t == null) return new Tree(k, newval, null, null);
            if (k.equals(t.key)) return new Tree(k, newval, t.left, t.right);
            if (k.compareTo(t.key) < 0)
                return new Tree(t.key, t.val, fUpdate(k, newval, t.left), t.right);
            else
                return new Tree(t.key, t.val, t.left, fUpdate(k, newval, t.right));
        }


        public static Tree newItem(String k, Integer newval, Tree t) {
            if (t == null)
                t = new Tree(k, newval, null, null);
            else if (k.equals(t.key))
                t.val = newval;
            else if (k.compareTo(t.key) < 0)
                t.left = newItem(k, newval, t.left);
            else
                t.right = newItem(k, newval, t.right);
            return t;
        }
    }
}
