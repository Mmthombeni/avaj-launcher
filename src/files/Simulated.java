/*package files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Simulated
{
    static File file;
    static FileWriter fw;
    public void openfile()
    {
        try
        {

            file = new File("simulation.txt");
            if (!file.exists())
            {
                file.createNewFile();
            }
            fw = new FileWriter(file);
        }
        catch (IOException ieo)
        {
            System.out.println(ieo);
        }
    }
    public void closefile()
    {
        try
        {
            fw.close();
        }
        catch(IOException e)
        {
            System.out.println(" ");
        }
    }
     public void writetoFile(String ryd)
     {
         try
         {
             fw.write(ryd);
         }
         catch (IOException e)
         {

         }
     }
}
*/