package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import simulator.control.Controller;

public class MainWindow extends JFrame{
	
	private Controller ctlr;
	public MainWindow(Controller c) 
	{
		super("Traffic simulator"); 
		ctlr= c;
		initGUI(); 
	} 
	 
	
	private void initGUI()
	{
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		mainPanel.add(new ControlPanel(ctlr), BorderLayout.PAGE_START);
		mainPanel.add(new StatusBar(ctlr),BorderLayout.PAGE_END);
		
		JPanel viewsPanel = new JPanel(new GridLayout(1,2));
		mainPanel.add(viewsPanel, BorderLayout.CENTER);
		
		JPanel tablesPanel = new JPanel();
		tablesPanel.setLayout(new BoxLayout(tablesPanel, BoxLayout.Y_AXIS));
		viewsPanel.add(tablesPanel); 
		
		JPanel mapsPanel = new JPanel();
		mapsPanel.setLayout(new BoxLayout(mapsPanel, BoxLayout.Y_AXIS));
		viewsPanel.add(mapsPanel);
		
		JPanel eventsView = createViewPanel(new JTable(new EventsTableModel(ctlr)),"Events");
		eventsView.setPreferredSize(new Dimension(500,200));
		tablesPanel.add(eventsView);
		
		JPanel mapView = createViewPanel(new MapComponent(ctlr),"Map");
		mapView.setPreferredSize(new Dimension(500,400));
		mapsPanel.add(mapView);
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.pack();
		this.setVisible(true); 
		
	}
	
	private JPanel createViewPanel(JComponent c, String title)
	{
		JPanel p = new JPanel(new BorderLayout());
		//TODO add a framed border to p with title
		p.add(new JScrollPane(c));
		return p; 
	}	
}
