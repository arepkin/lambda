package ru.liga.interning.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.liga.interning.tree.Tree;

/**
 * @author Repkin Andrey {@literal <arepkin@at-consulting.ru>}
 */
public class TreeTest {

    @Test
    public void classTest() {
        final Tree root = new Tree("Petya", 1, null, null);
        Tree.TreeProcessor.newItem("Vasya", 2, root);
        Tree.TreeProcessor.newItem("Sasha", 4, root);
        Tree.TreeProcessor.newItem("Masha", 6, root);

        final int sasha = Tree.TreeProcessor.lookup("Sasha", null, root);
        Assertions.assertEquals(sasha, 4);

        final Tree mashaBefore = root.getLeft();
        Assertions.assertEquals(Tree.TreeProcessor.lookup("Masha", null, root), 6);
        Tree.TreeProcessor.update("Masha", 7, root);
        Assertions.assertEquals(Tree.TreeProcessor.lookup("Masha", null, root), 7);
        Assertions.assertEquals(mashaBefore, root.getLeft());
    }

    @Test
    public void functionalTest() {
        final Tree root = new Tree("Petya", 1, null, null);
        Tree.FunctionalTreeProcessor.newItem("Vasya", 2, root);
        Tree.FunctionalTreeProcessor.newItem("Sasha", 4, root);
        Tree.FunctionalTreeProcessor.newItem("Masha", 6, root);

        final int sasha = Tree.FunctionalTreeProcessor.lookup("Sasha", null, root);
        Assertions.assertEquals(sasha, 4);

        final Tree mashaBefore = root.getLeft();
        Assertions.assertEquals(Tree.FunctionalTreeProcessor.lookup("Masha", null, root), 6);
        final Tree newUpdatedRoot = Tree.FunctionalTreeProcessor.fUpdate("Masha", 7, root);
        Assertions.assertEquals(Tree.FunctionalTreeProcessor.lookup("Masha", null, newUpdatedRoot), 7);
        Assertions.assertEquals(Tree.FunctionalTreeProcessor.lookup("Masha", null, root), 6);
        Assertions.assertNotEquals(mashaBefore, newUpdatedRoot.getLeft());
    }
}
