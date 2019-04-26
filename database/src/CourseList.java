//CS321 Section 6 
//Team 4
//Mike Faunda III

import java.io.*;
import java.util.*;

public class CourseList implements java.io.Serializable
{
   Course[] storage;

   public CourseList()
   {
      storage = new Course[1];
      storage[0] = null;
   }

   public Course getCourse(int i)
   {
      return storage[i];
   }

   public Course getCourse(String num)
   {
      for(int x=0; x<storage.length; x++)
      {
         if(num.equals(storage[x].getNum()))
         {
            return storage[x];
         }
      }
      return new Course();
   }

   public void add(Course c)
   {
      if(storage[0] == null)
         storage[0] = c;
      else
      {
         Course[] temp = new Course[storage.length+1];
         for(int x = 0; x<storage.length; x++)
         {
            temp[x]=storage[x];
         }
         storage = temp;
         storage[storage.length-1]=c;
      }
   }
   
   public void remove(Course c)
   {
      boolean found = false;
      int i = 0;
      for(int x=0; x<storage.length; x++)
      {
         if(c.getNum().equals(storage[x].getNum()))
         {
            found = true;
            i = x;
            break;
         }
      }
   
      if(found)
      {
         Course[] temp = new Course[storage.length-1];
         found = false;
         for(int x=0; x<storage.length; x++)
         {
            if(c.getNum().equals(storage[x].getNum()))
            {
               found = true;
            }
         
            if(!found)
            {
               temp[x] = storage[x];
            }
            else
            {
               temp[x] = storage[x+1];
            }
         }
      }
   }

   public void writeList(String fName)
   {
      try
      {
         FileOutputStream fileOut = new FileOutputStream("../storage/"+fName+"C.ser");//creates a userArray serial file in output stream
         ObjectOutputStream out = new ObjectOutputStream(fileOut);//routs an object into the output stream.
         out.writeObject(this);// we designate our array of cards to be routed
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
         FileInputStream fileIn = new FileInputStream("../storage/"+fName+"C.ser");// Read serial file.
         ObjectInputStream in = new ObjectInputStream(fileIn);// input the read file.
         CourseList temp = (CourseList) in.readObject();// allocate it to the object file already instanciated.
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