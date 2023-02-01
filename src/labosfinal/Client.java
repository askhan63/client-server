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
import java.util.Scanner;

public class Client
{
    Scanner in = new Scanner(System.in);
	// initialize socket and input output streams
	private Socket clientSocket		 = null;
	private DataInputStream input = null;
	private DataOutputStream out	 = null;

	// constructor to put ip address and port
	public Client(String address, int port)
	{
		// establish a connection
		try
		{
			clientSocket = new Socket(address, port);
			System.out.println("Connected to server");

			input = new DataInputStream(System.in);
			out = new DataOutputStream(clientSocket.getOutputStream());
		}
		catch(UnknownHostException u)
		{
			System.out.println(u);
		}
		catch(IOException i)
		{
			System.out.println(i);
		}

		int x=0;
                
		// keep reading until "Over" is input
		while (x==0)
		{
			try
			{
                                
                        System.out.println("Enter num 1:");
                        double num1=in.nextDouble();
                         System.out.println("Enter num 2:");
                        double num2=in.nextDouble();
                           System.out.println("Enter operation : (+,-,*,/) :");
                        char c =in.next().charAt(0);

				out.writeUTF(num1+"");		
                                out.writeUTF(num2+"");               
                                out.writeUTF(c+"");

                                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                                 String fromServer = null;
                          clientSocket.setKeepAlive(true);
                          while ((in.readLine()) != null) {
                              fromServer = in.readLine();
                               System.out.println(fromServer);
                             
                                      }
                          

			}
			catch(IOException i)
			{
				System.out.println(i);
			}
                            x++;
                        System.out.println("Saying good bye");
                    
		}

		// close the connection
		try
		{
			input.close();
			out.close();
			clientSocket.close();
		}
		catch(IOException i)
		{
			System.out.println(i);
		}
	}

	public static void main(String args[])
	{
		Client client = new Client("127.0.0.1", 8080);
	}
}
