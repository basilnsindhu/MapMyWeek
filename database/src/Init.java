//CS321 Section 6 
//Team 4
//Mike Faunda III


public class Init
{

   public static void main(String[]args)
   {
      Building b1 = new Building("Exploratory", new double[]{38.829653, -77.305504});
      Building b2 = new Building("Innovation", new double[]{38.828466, -77.307370});
      Building b3 = new Building("Engineering", new double[]{38.827555, -77.305094});
      Building b4 = new Building("David King", new double[]{38.830590, -77.306698});
      Building b5 = new Building("Lecture Hall", new double[]{38.833133, -77.307564});
      
      BuildingList mason = new BuildingList();
      mason.add(b1);
      mason.add(b2);
      mason.add(b3);
      mason.add(b4);
      mason.add(b5);
      mason.writeList("default");
   
      Course c1 = new Course("CS211", "Object-Oriented Programming", b1, "L004", "1:30pm - 2:45pm", "TR");
      Course c2 = new Course("CS262", "Intro to Low-level Programming", b2, "234", "3:00 - 4:15pm", "MW");
      Course c3 = new Course("CS310", "Data Structures", b2, "234", "1:30pm - 2:45pm", "MW");
      Course c4 = new Course("CS321", "Software Engineering", b3, "1007", "9:00am - 10:15pm", "TR");
      Course c5 = new Course("CS483", "Analysis of Algorithms", b5, "2", "3:00pm - 4:15pm", "MWF");
      Course c6 = new Course("CS484", "Data Mining", b4, "105", "10:30am - 12:45pm", "F");
      CourseList catalog = new CourseList();
      catalog.add(c1);
      catalog.add(c2);
      catalog.add(c3);
      catalog.add(c4);
      catalog.add(c5);
      catalog.add(c6);
      catalog.writeList("default");
      
      BuildingList newBList = new BuildingList();
      newBList.readList("default");
      System.out.println(newBList.storage[0].getName());
      
      CourseList newCList = new CourseList();
      newCList.readList("default");
      System.out.println(newCList.storage[0].getName());
   }

}