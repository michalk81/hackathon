import scala.collection.mutable.PriorityQueue

trait Tree {

  def frequency: Int

}

case class Leaf(val frequency:Int, symbol:Char) extends Tree
case class Node(val frequency:Int, left: Tree, right: Tree) extends Tree



object EncodingResult {
  var huffman: Tree = _
  var encoded: java.util.BitSet = _
}



trait Encoder {
  
  implicit object Ord extends Ordering[Tree] {
    def compare(x: Tree, y :Tree) = y.frequency.compare(x.frequency)
  }
   
  
  def toBitSet(bits: List[Int]):java.util.BitSet = {
    new java.util.BitSet(bits.length) 
    
  }
  
  def buildEncodingTable(tree: Tree, code: List[Int], tableSoFar:Map[Char, List[Int]]):Map[Char, List[Int]] = {
    tree match {
      case Leaf(frequency:Int, ch: Char) => tableSoFar.updated(ch, code)
      case Node(_:Int, left:Tree, right:Tree) => buildEncodingTable(left, 0::code, tableSoFar) ++ buildEncodingTable(right, 1::code, tableSoFar) 
    }
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
  
  def buildTree(frequency:Map[Char, Int]):Tree = {

    val nodes = new PriorityQueue[Tree]()
    for {(ch, freq) <- frequency} yield nodes.enqueue(Leaf(freq, ch)) 
    
    merge(nodes)
  }
  
  def resolveFrequencies(chars: Array[Char]): Map[Char, Int] = {
    chars.groupBy(identity).mapValues(_.size)
  }
  
  def encode(chars:Array[Char]): List[Int] = {
    val encodingTable = buildEncodingTable(buildTree(resolveFrequencies(chars)), List(), Map())
    List() //Fold, etc
  }
}