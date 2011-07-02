/**
 * @author cabinw
 * @email cabinw@gmail.com
 * 
 * The main class to launch UI
 */

package com.macc.gui;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import com.macc.listener.StartListener;
import com.macc.shell.MacGetter;

public  class MainWindow {
	private final static int WIDTH=210;
	private final static int HEIGHT=265;
	
	private Display display;
	private Shell shell;
	
	public void initWindow(){
		display = new Display();
		shell= new Shell(display,SWT.DIALOG_TRIM);
		
    	GridLayout gridlayout = new GridLayout();
    	gridlayout.numColumns = 2;
    	gridlayout.verticalSpacing = 8;
    	shell.setLayout(gridlayout);
    	
    	//Icon image
    	Image image = ImageFactory.loadImage(display, ImageFactory.ICON);
    	shell.setImage(image);
    	
    	/*
    	 * The label to show the current MAC address
    	 */
    	GridData clabelGriddata = new GridData();
    	clabelGriddata.horizontalSpan = 2;
    	clabelGriddata.horizontalAlignment=SWT.FILL;
    	CLabel clabel = new CLabel(shell,SWT.None);
    	clabel.setLayoutData(clabelGriddata);
    	String mac=MacGetter.getMac();
    	clabel.setText("Your current MAC:\n "+mac);
    	clabel.setImage(display.getSystemImage(SWT.ICON_INFORMATION));
    	
    	
    	/*
    	 * The combo text to input the address and save seleted address 
    	 */
    	GridData comboGriddata = new GridData();
    	comboGriddata.horizontalSpan = 2;
    	comboGriddata.horizontalAlignment=SWT.LEFT;
    	Combo combo=new Combo(shell,SWT.DROP_DOWN);
    	combo.setLayoutData(comboGriddata);
    	try {
			if(Data.get().length != 0){
				combo.setItems(Data.get());
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
    	
    	/*
    	 * Save button
    	 */
    	GridData savebuttonGriddata = new GridData();
    	savebuttonGriddata.horizontalSpan = 2;
    	savebuttonGriddata.horizontalAlignment=SWT.LEFT;
    	Button savebutton = new Button(shell,SWT.CHECK | SWT.LEFT);
    	savebutton.setText("Save this address");
    	savebutton.setLayoutData(savebuttonGriddata);
    	
    	/*
    	 * The choice button group
    	 */
    	Group choiceGroup = new Group(shell, SWT.SHADOW_ETCHED_IN);
    	choiceGroup.setLayout(new FillLayout(SWT.VERTICAL));
    	
    	Button choicebutton1 = new Button(choiceGroup,SWT.RADIO|SWT.LEFT);
    	choicebutton1.setText("Change for once");
    	choicebutton1.setSelection(true);
    	
    	Button choicebutton2 = new Button(choiceGroup,SWT.RADIO|SWT.LEFT);
    	choicebutton2.setText("Change forever");
    	choicebutton2.setEnabled(false);
    	choiceGroup.pack();
    	
    	
    	/*
    	 * Start button
    	 */
    	GridData startGriddata = new GridData();
    	startGriddata.verticalAlignment=3;
    	startGriddata.horizontalAlignment=SWT.TOP;
    	startGriddata.widthHint = 70;
    	startGriddata.heightHint = 50;
    	Button startButton = new Button(shell,SWT.PUSH);
    	startButton.setLayoutData(startGriddata);
    	startButton.setText("Change");    	
    	startButton.addSelectionListener(new StartListener(combo,savebutton,choicebutton2,clabel,shell,display));
    	
    	/*
    	 * About button
    	 */
    	GridData aboutGriddata = new GridData();
    	aboutGriddata.horizontalSpan = 2;
    	aboutGriddata.verticalSpan = 7;//warning why it is 7?
    	aboutGriddata.horizontalAlignment=SWT.LEFT;
    	aboutGriddata.heightHint = 10;
    	Button about = new Button(shell,SWT.PUSH);
    	about.setText("About");
    	about.setSize(40,10);
    	about.pack();
    	
    	about.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				MessageBox messagebox = new MessageBox(shell,SWT.ICON_WORKING| SWT.YES );
				messagebox.setText("About");
				messagebox.setMessage("MacC 0.3 \n"+"Written by Cabinw\n"+"浙江工业大学开源软件社区MOSN\n"
						+"Email/Gtalk:cabinw@gmail.com");
				messagebox.open();
			}
		});
    	
  
        shell.setText("MacC 0.4");
        shell.setLocation(250, 150);
        shell.setSize(WIDTH,HEIGHT);
	}
	
	
	public void showWindow(){
		shell.open();
        while(!shell.isDisposed()){
        	if(!display.readAndDispatch()){
        		display.sleep();
        	}
        }
        ImageFactory.dispose();
        display.dispose();
	}
}