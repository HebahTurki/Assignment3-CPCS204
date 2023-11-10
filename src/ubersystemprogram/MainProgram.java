package ubersystemprogram;

import java.util.*;
import java.io.*;

/*
 Name : Hebah Turki Alahmari 
 IDs : 2105304
 Section : B9B
 Email: hmohammadalahmari@stu.kau.edu.sa
 Assignment#3
 */

public class MainProgram 
{
    public static UberTree uberTree = new UberTree(); //Declare & Create a BS tree
    
    public static void main(String[] args) throws FileNotFoundException 
    {
        //Open the input Files & reading the data.
        File command = new File("input.txt");
        
        //File pointer to the output file.
        File output = new File("Output.txt");
        
        //Check if the files exists.
        if(!command.exists())
        {
            System.out.println("The input files does not exists!!");
            System.exit(0);
        }
        
        //Make Scanners for input Files.
        Scanner inputFile = new Scanner(command);
        
        //Creating fileWriter to write in the File.
        PrintWriter pinFile = new PrintWriter(output);
        
        String commands;//reading the inputs
        int ID;
        
        pinFile.println("--------------- Welcome to Uber Booking System ---------------");
        do{
            commands = inputFile.next();
            
            if(commands.equals("QUIT"))
                break;
            
            switch(commands)
            {
                case "Add_Captain":
                {
                    pinFile.print("Command "+commands+": Add a new captain record in the System\n");
                    //Read Captain's information and add him to the uberTree
                    ID = inputFile.nextInt();
                    uberTree.add_Captain(ID, inputFile.next());
                    uberTree.display_all_Captain_info_by_ID(ID, pinFile);
                    break;
                }
                
                case "BOOK_RIDE":
                {
                    ID = inputFile.nextInt();//read the id of the wanted Captain
                    uberTree.BOOK_RIDE(ID, pinFile);//book him and printing
                    
                    pinFile.println("----------------------------------------------------------------");
                    break;
                }
                
                case "DISPLAY_CAPTAIN_INFO":
                {
                    pinFile.print("Command "+commands+":");
                    //Display a specific Captain's information
                    uberTree.display_all_Captain_info_by_ID(inputFile.nextInt(), pinFile);
                    break;
                }
                
                case "FINISH_RIDE":
                {
                    //end the ride and rate it 
                    uberTree.FINISH_RIDE(inputFile.nextInt(), inputFile.nextInt(), pinFile);
                    break;
                }
                
                case "DISPLAY_ALL_CAPTAINS":
                {
                    pinFile.print("Command "+commands+":\n\n");
                    uberTree.display_all_Captain_info(pinFile);
                    break;
                }
                
                case "DELETE_CAPTAIN":
                {
                    int id = inputFile.nextInt(); //Read the id to use it with searching & printing
                    CaptainNode delete = uberTree.findCaptain(id);
                    if(delete == null)
                    {
                        pinFile.println("Command DELETE_CAPTAIN: Couldn't find any captain with ID number "+id+"\n");
                    } 
                    else
                    {
                        uberTree.delete(id);
                        pinFile.println("Command  DELETE_CAPTAIN:The captain "+delete.getCaptainName()+" left Uber"); 
                    }
                    pinFile.println("----------------------------------------------------------------");
                    break;
                }
                
            }
            
        }while(inputFile.hasNext());
        
        pinFile.println("Thank you for using Uber System, Good Bye!");
        //close the file writer
        pinFile.close();
    }

}
