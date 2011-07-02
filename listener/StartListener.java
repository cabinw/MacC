/**
 * @author cabinw
 * @email cabinw@gmail.com
 * 
 * start button's listener
 */

package com.macc.listener;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import com.macc.gui.Data;
import com.macc.shell.MacGetter;
import com.macc.shell.ShellRunner;

public class StartListener extends SelectionAdapter{
	private Combo combo;
	private Button savebutton;
	private Button choicebutton2;
	private CLabel clabel;
	private Shell shell;
	private String usrMac;
	private String combotext;
	
	public StartListener(Combo combo, Button savebutton,Button choicebutton2,
			CLabel clabel, Shell shell, Display display) {
		super();
		this.combo = combo;
		this.savebutton = savebutton;
		this.clabel = clabel;
		this.shell = shell;
		this.choicebutton2 = choicebutton2;
	}


	public void widgetSelected(SelectionEvent e){
		combotext = combo.getText().trim();
		String[] split = combotext.split(":");
		usrMac = split[0];
		
		String mac=MacGetter.getMac();
		
		if(usrMac != null){
			
			ComboFilter filter = new ComboFilter(shell);
			if(!filter.process(usrMac))
				return;
			
			if(savebutton.getSelection()){
				try {
					Data.add(combotext);
					String[] s = Data.get();
					combo.setItems(s);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			if(mac == null){
				MessageBox messagebox = new MessageBox(shell,SWT.ICON_ERROR | SWT.YES );
				messagebox.setText("Error");
				messagebox.setMessage("Fail to change");
				messagebox.open();
				return;
			}
			
			if(choicebutton2.getSelection()){
				changeForever();
				System.out.println("Forever");
			}
			change();
			
		}
	}

	//edition 2 of kernel part statements
	private void change(){
		String mac;
		ShellRunner.run("gksudo ifconfig eth0 down");
		System.out.println("down");
		
		do{
			mac = MacGetter.getMac();
		}while(mac != null);
		ShellRunner.run("sudo ifconfig eth0 hw ether "+usrMac);
		ShellRunner.run("sudo ifconfig eth0 up");
		System.out.println("Done");
		
		do{
			mac = MacGetter.getMac();
		}while(mac == null);
		
		showMessagebox();
		
		mac=MacGetter.getMac();
    	clabel.setText("Your current MAC:\n "+mac);
	}
	
	/**
	 * change the mac forever
	 */
	public void changeForever(){
	/*
	 * TO-DO:
	 * 
	 */
	}

	/**
	 * show message box if changed successfully
	 */
	private void showMessagebox() {
		MessageBox messagebox = new MessageBox(shell,SWT.ICON_WARNING | SWT.YES );
		messagebox.setText("Done");
		messagebox.setMessage("MAC has changed successfully");
		messagebox.open();
	}
		
}
