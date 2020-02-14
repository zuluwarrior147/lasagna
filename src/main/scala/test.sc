val st =" ## intent:greeting"

st.matches("^ ## intent")

def explode[A,B](map:Map[A,Seq[B]]):Seq[(A,B)] ={
  map.toSeq.flatMap(pair =>{
    pair._2.map((pair._1,_))
  })
}


explode(Map("l1" ->Seq("f1","f2","f3"),"l2" ->Seq("f1","f2","f3")))