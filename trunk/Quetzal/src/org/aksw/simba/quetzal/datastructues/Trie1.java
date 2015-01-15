package org.aksw.simba.quetzal.datastructues;

import java.util.ArrayList;
import java.util.List;

public class Trie1
{
   private TrieNode1 root;
   
   /**
    * Constructor
    */
   public Trie1()
   {
      root = new TrieNode1();
   }

   /**
    * Adds a word to the Trie
    * @param word
    */
   public void addWord(String word)
   {
      root.addWord(word.toLowerCase());
   }
   
   /**
    * Get the words in the Trie with the given
    * prefix
    * @param prefix
    * @return a List containing String objects containing the words in
    *         the Trie with the given prefix.
    */
   public List getWords(String prefix)
   {
      //Find the node which represents the last letter of the prefix
      TrieNode1 lastNode = root;
      for (int i=0; i<prefix.length(); i++)
      {
      lastNode = lastNode.getNode(prefix.charAt(i));
      
      //If no node matches, then no words exist, return empty list
      if (lastNode == null) return new ArrayList();      
      }
      
      //Return the words which eminate from the last node
      return lastNode.getWords();
   }
   public static void main(String[] args) {
//		Trie1 trie = new Trie1();
//       trie.addWord("tea");
//       trie.addWord("tena");
//       trie.addWord("tenr");
//       trie.addWord("ab");
       TrieNode1 root = new TrieNode1();
       root.addWord("tea");
     // System.out.println( getNode1('t').getWords());
     //  System.out.println(trie.getWords("ten"));
	}
}