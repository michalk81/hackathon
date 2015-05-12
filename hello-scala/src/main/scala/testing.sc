object testing extends Encoder {
  import scala.collection.mutable.PriorityQueue
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def diff(t2: (Int,Char)) = t2._1 - t2._1        //> diff: (t2: (Int, Char))Int
  val x = new PriorityQueue[(Int, Char)]()(Ordering.by(diff))
                                                  //> x  : scala.collection.mutable.PriorityQueue[(Int, Char)] = PriorityQueue()
  x.enqueue((1,'a'))
  x.enqueue((2,'b'))
  x.dequeue()                                     //> res0: (Int, Char) = (1,a)
  x.dequeue()                                     //> res1: (Int, Char) = (2,b)
  
 

 
 
  val frequency = resolveFrequencies("veggie pizza".toCharArray())
                                                  //> frequency  : Map[Char,Int] = Map(e -> 2, a -> 1, i -> 2,   -> 1, v -> 1, g -
                                                  //| > 2, p -> 1, z -> 2)
 
  val myTree = buildTree(frequency)               //> myTree  : Tree = Node(12,Node(4,Leaf(2,g),Node(2,Leaf(1,v),Leaf(1,p))),Node(
                                                  //| 8,Node(4,Leaf(2,z),Leaf(2,i)),Node(4,Leaf(2,e),Node(2,Leaf(1,a),Leaf(1, ))))
                                                  //| )
  
    buildEncodingTable(myTree, List(), Map())     //> res2: Map[Char,List[Int]] = Map(e -> List(0, 1, 1), a -> List(0, 1, 1, 1), i
                                                  //|  -> List(1, 0, 1),   -> List(1, 1, 1, 1), v -> List(0, 1, 0), g -> List(0, 0
                                                  //| ), p -> List(1, 1, 0), z -> List(0, 0, 1))
 
 
 
 val encodingTable = buildEncodingTable(myTree, List(), Map())
                                                  //> encodingTable  : Map[Char,List[Int]] = Map(e -> List(0, 1, 1), a -> List(0, 
                                                  //| 1, 1, 1), i -> List(1, 0, 1),   -> List(1, 1, 1, 1), v -> List(0, 1, 0), g -
                                                  //| > List(0, 0), p -> List(1, 1, 0), z -> List(0, 0, 1))
 
 encodingTable.mapValues (x => x.reverse )        //> res3: scala.collection.immutable.Map[Char,List[Int]] = Map(e -> List(1, 1, 0
                                                  //| ), a -> List(1, 1, 1, 0), i -> List(1, 0, 1),   -> List(1, 1, 1, 1), v -> Li
                                                  //| st(0, 1, 0), g -> List(0, 0), p -> List(0, 1, 1), z -> List(1, 0, 0))
 
  def bitSet(bits: List[Int], bitset:java.util.BitSet, index:Int) {
   bits match {
     case Nil => ;
     case b => bitset.set(index, b==1)
     case b::bs => {
	     bitset.set(index, b == 1);
	     bitSet(bs, bitset, index +1)
     }
   }
  }                                               //> bitSet: (bits: List[Int], bitset: java.util.BitSet, index: Int)Unit
  
  
  
 
  val result = new java.util.BitSet(3)            //> result  : java.util.BitSet = {}
  bitSet(List(1), result , 1)
  
  result                                          //> res4: java.util.BitSet = {}
  result.toByteArray()                            //> res5: Array[Byte] = Array()
 
}