/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labosfinal;


/**
 *
 * @author AS KHAN
 */
import java.net.*;
import java.io.*;

public class Server
{
	private Socket		 Serversocket = null;
	private ServerSocket server = null;
	private DataInputStream in	 = null;
OutputStream output =null;
	// constructor with port
	public Server(int port)
	{
		// starts server and waits for a connection
		try
		{
			server = new ServerSocket(port);
			System.out.println("Server started");

			System.out.println("Waiting for a client ...");

			Serversocket = server.accept();
			System.out.println("Client accepted");
                      output= Serversocket.getOutputStream();
			// takes input from the client socket
			in = new DataInputStream(
				new BufferedInputStream(Serversocket.getInputStream()));

			String line = "";

			// reads message from client until "Over" is sent
			while (!line.equals("Over"))
			{
				try
				{
					line = in.readUTF();
                                        double x = Double.valueOf(line);
                                        line = in.readUTF();
                                        double y = Double.valueOf(line);
                                      		String c =  in.readUTF();
                                          char z = c.charAt(0);
                                                     System.out.println(" recieved val 1 = "+x);             
                                        System.out.println(" recieved val 2 = "+y);                    
                                        System.out.println(" recieved operator  = "+z);
                                          PrintWriter writer = new PrintWriter(output, true);
                                          
                                          //write this bcz switch minus was not working
                                            if(z=='-')
                                                   writer.println("Subtraction = "+(x-y));
                                          switch(z){
                                              case '+':
                                                   writer.println("Sum = "+(x+y)); 
                                                  break;
                                                  case '-':
                                                         writer.println("Subtraction = "+(x-y));
                                                  break;
                                                  case '*':
                                                        writer.println("Multiplycation = "+(x*y));
                                                  break;
                                                  case '/':
                                                        writer.println("Divide = "+(x/y));
                                                  break;
                                                  default:
                                                        writer.println("Invalid operator ");
                                                      
                                                      break;
                                          }

				}
				catch(IOException i)
				{
					System.out.println(i);
				}
			}
			System.out.println("Closing connection");

			Serversocket.close();
			in.close();
		}
		catch(IOException i)
		{
			System.out.println(i);
		}
	}

	public static void main(String args[])
	{
		Server server = new Server(8080);
	}
}
