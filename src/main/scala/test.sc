val st =" ## intent:greeting"

st.matches("^ ## intent")

def explode[A,B](map:Map[A,Seq[B]]):Seq[(A,B)] ={
  map.toSeq.flatMap(pair =>{
    pair._2.map((pair._1,_))
  })
}


val vector = explode(Map(Label"l1" ->Seq("f1","f2","f3"),"l2" ->Seq("f1","f2","f3")))

import lasagna.{FileWriter, Label}
import lasagna.FileWriter._


def stringify(ele:(Label,Int)) = s"${ele._2},${ele._1.id}"

vector.foldLeft(FileWriter("temp"))((B, ele) =>{
  B.write(stringify(ele))
}).close()