package june;

import java.util.ArrayList;

public class EnchantedList extends Enchanted 
{
    //list of enchanted objects
    //add method
    private ArrayList<Enchanted> eList;
    private boolean setEmptyPos;
    
    public EnchantedList() {
        //creates empty gameobject, and names it.
        super("");
        super.setId(""+this.hashCode());
        super.commandGlobal("empty = new GameObject(); empty = util.instantiate (empty, Vector3(0,0,0), Quaternion.identity); empty.layer=2; objects.Add(\""+super.getId()+"\", empty); ");
        eList = new ArrayList<Enchanted>();
        setEmptyPos = false;
    }
    
    public void add(Enchanted ench) {
        //gets parent game object
        if (!setEmptyPos) {
            super.commandGlobal("objects[\""+super.getId()+"\"].transform.position = objects[\""+ench.getId()+"\"].transform.position");
            setEmptyPos = true;
        }
        super.commandGlobal("objects[\""+ench.getId()+"\"].transform.parent = objects[\""+super.getId()+"\"].transform");
        eList.add(ench);
    }
    
    public void buildBridge() {
        
        boolean isOdd = (eList.size()%2 == 1);
        int middleIndex = isOdd ? (eList.size()/2) : (eList.size()/2-1);
        double tempHeight = 0.0;
        double heightChange = 0.0;
        //determine the height change based on the length of the list (work on later)
        
        for(int i=0; i < eList.size(); i++) {
            
            if (i < middleIndex) {
                super.commandGlobal("objects[\""+eList.get(i).getId()+"\"].transform.position.y += "+tempHeight+"");
                heightChange = 2.0;
                tempHeight += heightChange;
            }
            else if (i == middleIndex) {
                if (!isOdd) middleIndex += 1;
                super.commandGlobal("objects[\""+eList.get(i).getId()+"\"].transform.position.y += "+tempHeight+"");
                //don't change tempheight
            }
            else {
                tempHeight -= heightChange;
                super.commandGlobal("objects[\""+eList.get(i).getId()+"\"].transform.position.y += "+tempHeight+"");
            }
            
        }
        
        
    }
    
    //public void remove(Enchanted ench) {
        
    //}
    
    public Enchanted get(int index)
    {
      return eList.get(index);
    }

    public int size()
    {
      return eList.size();
    }
}
