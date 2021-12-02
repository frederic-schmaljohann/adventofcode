val minP = 146810
val maxP = 612564

minP.to(maxP).count(it => {
  it.toString.sliding(2).forall(p => p(0) <= p(1)) && 
  it.toString.sliding(2).exists(p => p(0) == p(1))
})

def oneRepetitionIsTwo(n: String): Boolean = {
 var i = 0
 var found = false
 while (i < n.length - 1) {
   var cnt = 1
   if (n(i) == n(i + cnt)) {
     while (i + cnt < n.length && n(i) == n(i + cnt)) {
       cnt += 1
     }
     found = found || (cnt) == 2
   }
   else {
     cnt = 1
   }
   i = i + cnt
 }
 found
}

minP.to(maxP).count(it => {
 it.toString.sliding(2).forall(p => p(0) <= p(1)) &&
 it.toString.sliding(2).exists(p => p(0) == p(1)) && 
 oneRepetitionIsTwo(it.toString)
})