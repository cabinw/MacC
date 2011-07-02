/**
 * @author cabinw
 * @email cabinw@gmail.com
 * 
 * to filter the input,handle errors
 */

package com.macc.listener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class ComboFilter {
	private Shell shell;
	
	public ComboFilter(Shell shell){
		this.shell = shell;
	}
	
	/**
	 * filter logical statements
	 * @param s
	 */
	public boolean process(String s){
		
		//length error
		if(s.length() != 12){
			showErrorBox();
			return false;
		}
		
		//format error here
		for(int i=0; i<s.length(); ++i){
			if(!judge(s.charAt(i))){
				showErrorBox();
				return false;
			}
		}
		
		return true;
	}
	
	public void showErrorBox(){
		MessageBox messagebox = new MessageBox(shell,SWT.ICON_ERROR | SWT.YES );
		messagebox.setText("Error");
		messagebox.setMessage("Not a valid address");
		messagebox.open();
	}
	
	public boolean judge(char c){
		
		if( c == 'a' || c=='b' || c=='c' || c=='d' || c=='e' || c=='f' ||
				c == 'A' || c=='B' || c=='C' || c=='D' || c=='E' || c=='F' )
		{
			return true;
		}
		else if(c == '0' | c=='1' | c=='2' | c=='3' | c=='4' | c=='5' |
				c == '6' | c=='7' | c=='8' | c=='9' | c=='E')
		{
			return true;
		}
		return false;
		
	}
}
