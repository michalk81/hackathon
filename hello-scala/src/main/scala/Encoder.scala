import scala.collection.mutable.BitSet
import scala.collection.mutable.PriorityQueue

abstract class Tree(val frequency:Int)
  
case class Leaf(frequency:Int, symbol:Char) extends Tree(frequency)
case class Node(frequency:Int, left: Tree, right: Tree) extends Tree(frequency)



object EncodingResult {
  var huffman: Tree = _
  var encoded: java.util.BitSet = _
}



trait Encoder {
  
  implicit object Ord extends Ordering[Tree] {
    def compare(x: Tree, y :Tree) = y.frequency.compare(x.frequency)
  }
   
  def encodeCharacter():java.util.BitSet = {
    new java.util.BitSet(2)
  }
  
  def encodeWithTree(input: Iterable[Char], bitset:java.util.BitSet, tree: Tree):java.util.BitSet = {
    new java.util.BitSet(2)
  }
  
  def merge(nodes: PriorityQueue[Tree]):Tree = {
    nodes.size match {
      case 0 => throw new IllegalArgumentException("Nothing to encode") 
      case 1 => nodes.dequeue()
      case _ => {
        // More than 1
        val n1:Tree = nodes.dequeue()
        val n2:Tree = nodes.dequeue()        
        nodes.enqueue(Node(n1.frequency + n2.frequency, n1, n2 ))
        merge(nodes)
      }
      
    }
  }
  
  def buildTree(frequency:List[(Char, Int)]):Tree = {

    val nodes = new PriorityQueue[Tree]()
    for {(ch, freq) <- frequency} yield nodes.enqueue(Leaf(freq, ch)) 
    
    merge(nodes)
  }
}