/**
 * @author cabinw
 * @email cabinw@gmail.com
 * 
 * to run the shell script
 */

package com.macc.shell;

import java.io.*;

public class ShellRunner {
    public static void run(String shellCommand){
        
        Process child=null;    //child process
        try{
            child=Runtime.getRuntime().exec(shellCommand);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        if(child==null){
            return;
        }
    }
} 
