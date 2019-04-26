//CS321 Section 6 
//Team 4
//Mike Faunda III

import java.io.*;
import java.util.*;

public class BuildingList implements java.io.Serializable
{
   Building[] storage;

   public BuildingList()
   {
      storage = new Building[1];
      storage[0] = null;
   }

   public Building getBuilding(int i)
   {
      return storage[i];
   }
   
   public Building getBuilding(String name)
   {
      for(int x=0; x<storage.length; x++)
      {
         if(name.equals(storage[x].getName()))
         {
            return storage[x];
         }
      }
      return new Building();
   }

   public void add(Building b)
   {
      if(storage[0] == null)
         storage[0] = b;
      else
      {
         Building[] temp = new Building[storage.length+1];
         for(int x = 0; x<storage.length; x++)
         {
            temp[x]=storage[x];
         }
         storage = temp;
         storage[storage.length-1]=b;
      }
   }

   public void writeList(String fName)
   {
      try
      {
         FileOutputStream fileOut = new FileOutputStream("../storage/"+fName+"B.ser");//creates a serial file in output stream
         ObjectOutputStream out = new ObjectOutputStream(fileOut);//routs an object into the output stream.
         out.writeObject(this);
         out.close();// closes the data paths
         fileOut.close();// closes the data paths
      }
      catch(IOException i)//exception stuff
      {
         i.printStackTrace();
      }
   }

   public void readList(String fName)
   {
      try// If this doesnt work throw an exception
      {
         FileInputStream fileIn = new FileInputStream("../storage/"+fName+"B.ser");// Read serial file.
         ObjectInputStream in = new ObjectInputStream(fileIn);// input the read file.
         BuildingList temp = (BuildingList) in.readObject();// allocate it to the object file already instanciated.
         in.close();//closes the input stream.
         fileIn.close();//closes the file data stream.
         storage = temp.storage;
      }
      catch(IOException i)//exception stuff
      {
         System.out.println("File Not Found - Mike");
      }
      catch(ClassNotFoundException c)//more exception stuff
      {
         System.out.println("Error");
         c.printStackTrace();
         return;
      }
   }

}