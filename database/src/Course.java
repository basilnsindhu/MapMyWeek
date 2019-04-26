//CS321 Section 6 
//Team 4
//Mike Faunda III

public class Course implements java.io.Serializable
{

   String num;
   String name;
   Building house;
   String room;
   String time;
   String days;

   public Course()
   {
      num = "Default Num";
      name = "Default Name";
      house = new Building();
      room = "Default Room";
      time = "Default Time";
      days = "Default Days";
   }

   public Course(String nu, String na, Building b, String r, String t, String d)
   {
      num = nu;
      name = na;
      house = b;
      room = r;
      time = t;
      days = d;
   }
   
   public String getNum()
   {
      return num;
   }

   public String getName()
   {
      return name;
   }
   
   public Building getBuilding()
   {
      return house;
   }

   public String getRoom()
   {
      return room;
   }
   
   public String getTime()
   {
      return time;
   }
   
   public String getDays()
   {
      return days;
   }

   public void setNum(String n)
   {
      num = n;
   }

   public void setName(String n)
   {
      name = n;
   }
   
   public void setBuilding(Building b)
   {
      house = b;
   }

   public void setRoom(String r)
   {
      room = r;
   }
   
   public void setTime(String t)
   {
      time =  t;
   }
   
   public void setDays(String d)
   {
      days = d;
   }


}