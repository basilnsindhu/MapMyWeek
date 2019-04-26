//CS321 Section 6 
//Team 4
//Mike Faunda III

public class Building implements java.io.Serializable
{

   String name = "";
   double[] coord = new double[2];

   public Building()
   {
      name = "Default Buidling Name";
      coord = new double[2];
      coord[0] = 0.00;
      coord[1] = 0.00;
   }

   public Building(String n, double[] c)
   {
      name = n;
      coord[0] = c[0];
      coord[1] = c[1];
   }

   public String getName()
   {
      return name;
   }

   public double[] getCoord()
   {
      return coord;
   }

   public void setName(String n)
   {
      name = n;
   }

   public void setCoord(double[] c)
   {
      coord = c;
   }

}