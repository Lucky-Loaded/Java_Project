import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Workers extends JFrame  {
	
	PreparedStatement state=null;
	Connection conn=null;
    JTabbedPane tab=new JTabbedPane();
    int id = -1;
    
    JTable tableW = new JTable();
    JScrollPane scrollerW = new JScrollPane(tableW);
    
    JTable tableC = new JTable();
    JScrollPane scrollerC = new JScrollPane(tableC);
    
    JTable tableP = new JTable();
    JScrollPane scrollerP = new JScrollPane(tableP);
    
	JPanel workerPanel=new JPanel();
	JPanel contractPanel=new JPanel();
	JPanel positionPanel=new JPanel();
	JPanel searchPanel = new JPanel();
	//1
    JPanel upPanelW = new JPanel();
    JPanel midPanelW  = new JPanel();
    JPanel downPanelW = new JPanel();
    //2
    JPanel upPanelC = new JPanel();
    JPanel midPanelC  = new JPanel();
    JPanel downPanelC = new JPanel();
    //3
    JPanel upPanelP = new JPanel();
    JPanel midPanelP  = new JPanel();
    JPanel downPanelP = new JPanel();
    
    JLabel name = new JLabel("Name: ");
    JLabel pos_name = new JLabel("Position name: ");
	JLabel date = new JLabel("Date: ");
	JLabel type = new JLabel("Type: ");
	JLabel date_time = new JLabel("Date: ");
	JLabel email = new JLabel("Email: ");
	JLabel city = new JLabel("City: ");
	
    JTextField nameTF = new JTextField();
    JTextField pos_nameTF = new JTextField();
    JTextField dateTF = new JTextField();
    JTextField typeTF = new JTextField();
    JTextField date_timeTF = new JTextField();
    JTextField emailTF = new JTextField();
    JTextField cityTF = new JTextField();
    //1
    JButton addBtnW = new JButton("Add");
	JButton deleteBtnW = new JButton("Delete");
	JButton editBtnW = new JButton("Edit");
	JButton searchBtnW = new JButton("Search");
	JComboBox<String> searchComboW = new JComboBox<String>();
	//2
	JButton addBtnC = new JButton("Add");
	JButton deleteBtnC = new JButton("Delete");
	JButton editBtnC = new JButton("Edit");
	JButton searchBtnC = new JButton("Search");
	//3
	JButton addBtnP = new JButton("Add");
	JButton deleteBtnP = new JButton("Delete");
	JButton editBtnP = new JButton("Edit");
	JButton searchBtnP = new JButton("Search");
	
    public Workers() {
        
        this.setSize(700,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //
        
        tab.add(workerPanel,"Workers");
    	tab.add(contractPanel,"Contracts");
    	tab.add(positionPanel,"Positions");
    	tab.add(searchPanel,"Info");
    workerPanel.add(upPanelW);
    	this.add(tab);
    	
    	//1
        upPanelW.setLayout(new GridLayout(4,2));
   	 upPanelW.add(name);
   	 upPanelW.add(nameTF);
   	 upPanelW.add(date);
   	 upPanelW.add(dateTF);
   	 upPanelW.add(email);
   	 upPanelW.add(emailTF);
   	 upPanelW.add(city);
   	 upPanelW.add(cityTF);
   	 ///
   	 //2
   	 ///
   	upPanelP.setLayout(new GridLayout(2,1));
  	 upPanelP.add(pos_name);
  	 upPanelP.add(pos_nameTF);
  	 
   //	 
//3
   	//
  	 upPanelC.setLayout(new GridLayout(4,2));
  	 upPanelC.add(type);
  	 upPanelC.add(typeTF);
  	 upPanelC.add(date_time);
  	 upPanelC.add(date_timeTF);

   	 //1
   	 midPanelW.add(addBtnW);
	 midPanelW.add(deleteBtnW);
	 midPanelW.add(editBtnW);
	// midPanelW.add(searchBtnW);
	 
   	 ///
	 //2
	 ///
	 midPanelP.add(addBtnP);
	 midPanelP.add(deleteBtnP);
	 midPanelP.add(editBtnP);
	// midPanelP.add(searchBtnP);
	 //
	 //3
	 //
	 midPanelC.add(addBtnC);
	 midPanelC.add(deleteBtnC);
	 midPanelC.add(editBtnC);
	 //midPanelC.add(searchBtnC);
	 //addBtnW.addActionListener(new AddWorker);
	 //1
	 addBtnP.addActionListener(new AddPosition());
	 deleteBtnP.addActionListener(new DeletePosition());
	 //2
	 
	 deleteBtnC.addActionListener(new DeleteContract());
	 //3
	
	 deleteBtnW.addActionListener(new DeleteWorker());
	 
	 
	 //1 DOWNPANEL
	 downPanelP.add(scrollerP);
	 scrollerP.setPreferredSize(new Dimension(400,200));
	 tableP.setModel(DBHelper.getAllData("position"));
	 tableP.addMouseListener(new TableListenerP());
	 //2
	 downPanelW.add(scrollerW);
	 scrollerW.setPreferredSize(new Dimension(600,200));
	 tableW.setModel(DBHelper.getAllData("worker"));
	 tableW.addMouseListener(new TableListenerW());
	 //3
	 downPanelC.add(scrollerC);
	 scrollerC.setPreferredSize(new Dimension(600,200));
	 tableC.setModel(DBHelper.getAllData("contract"));
	 tableC.addMouseListener(new TableListenerC());
	 
	 
	 //1
	workerPanel.setLayout(new GridLayout(3,1));
	workerPanel.setSize(700, 800);
	workerPanel.add(upPanelW);
	workerPanel.add(midPanelW);
	workerPanel.add(downPanelW);
	//2
	positionPanel.setLayout(new GridLayout(3,1));
	positionPanel.setSize(700,800);
	positionPanel.add(upPanelP);
	positionPanel.add(midPanelP);
	positionPanel.add(downPanelP);
		//3
	contractPanel.setLayout(new GridLayout(3,1));
	contractPanel.setSize(700,800);
	contractPanel.add(upPanelC);
	contractPanel.add(midPanelC);
	contractPanel.add(downPanelC);
	
	
	 this.setVisible(true);
	 /// END
    }
    public void clearFormW() {
		 nameTF.setText("");
		 dateTF.setText("");
		 emailTF.setText("");
		 cityTF.setText("");
	 }
    public void clearFormP() {
		 pos_nameTF.setText("");
		 
	 }
    public void clearFormC() {
		 typeTF.setText("");
		 date_timeTF.setText("");

	 }
    ///
    //1
    ///
    public class AddPosition implements ActionListener{
    	@Override
    	 public void actionPerformed(ActionEvent arg0) {
    		String pos_name = pos_nameTF.getText();
    		
    		conn = DBHelper.getConnection();
    		
    		String sql = "insert into Position values(null,?);";
    		try {
				state=conn.prepareStatement(sql);
				state.setString(1, pos_name);
				state.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		clearFormP();
    	}
    	
    }
    ///
    //2
    ///
    /* public class AddWorker implements ActionListener{
  	 @Override
  	 public void actionPerformed(ActionEvent arg0) {
  		 String name = nameTF.getText();
  		 String date = dateTF.getText();
  		 String email = emailTF.getText();
  		 String city = cityTF.getText();
  		 //
  		 
  		 conn=DBHelper.getConnection();
  		 String sql="insert into worker values(null,?,?,?,?);";
  		try {
  			state=conn.prepareStatement(sql);
  			state.setString(1, name);
  			state.setString(2, date);
  			state.setString(3, email);
  			state.setString(4, city);
  			
  			state.execute();
  			//table.setModel(DBHelper.getAllData("worker"));
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} 
  		
  		 */
  	 //}
    //
    //1
    //
    class DeletePosition implements ActionListener{
   	 @Override
   	 public void actionPerformed(ActionEvent e) {
   		 conn = DBHelper.getConnection();
   		 String sql = "delete from position where id=?";
   		 try {
   			state = conn.prepareStatement(sql);
   			state.setInt(1, id);
   			state.execute();
   			id = -1;
   			tableP.setModel(DBHelper.getAllData("position"));
   		} catch (SQLException e1) {
   			// TODO Auto-generated catch block
   			e1.printStackTrace();
   		}
   		 
   	 }
   	 
    }
    //
    //2
    //
    class DeleteWorker implements ActionListener{
   	 @Override
   	 public void actionPerformed(ActionEvent e) {
   		 conn = DBHelper.getConnection();
   		 String sql = "delete from worker where id=?";
   		 try {
   			state = conn.prepareStatement(sql);
   			state.setInt(1, id);
   			state.execute();
   			tableW.setModel(DBHelper.getAllData("worker"));
   			id = -1;
   		} catch (SQLException e1) {
   			// TODO Auto-generated catch block
   			e1.printStackTrace();
   		}
   		 
   	 }
   	 
    }
    //
    //3
    //
    class DeleteContract implements ActionListener{
   	 @Override
   	 public void actionPerformed(ActionEvent e) {
   		 conn = DBHelper.getConnection();
   		 String sql = "delete from contract where id=?";
   		 try {
   			state = conn.prepareStatement(sql);
   			state.setInt(1, id);
   			state.execute();
   			tableC.setModel(DBHelper.getAllData("contract"));
   			id = -1;
   		} catch (SQLException e1) {
   			// TODO Auto-generated catch block
   			e1.printStackTrace();
   		}
   		 
   	 }
   	 
    }

    class TableListenerW implements MouseListener{

    	@Override
    	public void mouseClicked(MouseEvent e) {
    		int row = tableW.getSelectedRow();
    		id = Integer.parseInt(tableW.getValueAt(row, 0).toString());
    		
    	}

    	@Override
    	public void mousePressed(MouseEvent e) {
    		// TODO Auto-generated method stub
    		
    	}

    	@Override
    	public void mouseReleased(MouseEvent e) {
    		// TODO Auto-generated method stub
    		
    	}

    	@Override
    	public void mouseEntered(MouseEvent e) {
    		// TODO Auto-generated method stub
    		
    	}

    	@Override
    	public void mouseExited(MouseEvent e) {
    		// TODO Auto-generated method stub
    		
    	}
    	 
     
     }
    class TableListenerP implements MouseListener{

    	@Override
    	public void mouseClicked(MouseEvent e) {
    		int row = tableP.getSelectedRow();
    		id = Integer.parseInt(tableP.getValueAt(row, 0).toString());
    		
    	}

    	@Override
    	public void mousePressed(MouseEvent e) {
    		// TODO Auto-generated method stub
    		
    	}

    	@Override
    	public void mouseReleased(MouseEvent e) {
    		// TODO Auto-generated method stub
    		
    	}

    	@Override
    	public void mouseEntered(MouseEvent e) {
    		// TODO Auto-generated method stub
    		
    	}

    	@Override
    	public void mouseExited(MouseEvent e) {
    		// TODO Auto-generated method stub
    		
    	}
    	 
     
     }
    class TableListenerC implements MouseListener{

    	@Override
    	public void mouseClicked(MouseEvent e) {
    		int row = tableC.getSelectedRow();
    		id = Integer.parseInt(tableC.getValueAt(row, 0).toString());
    		
    	}

    	@Override
    	public void mousePressed(MouseEvent e) {
    		// TODO Auto-generated method stub
    		
    	}

    	@Override
    	public void mouseReleased(MouseEvent e) {
    		// TODO Auto-generated method stub
    		
    	}

    	@Override
    	public void mouseEntered(MouseEvent e) {
    		// TODO Auto-generated method stub
    		
    	}

    	@Override
    	public void mouseExited(MouseEvent e) {
    		// TODO Auto-generated method stub
    		
    	}
    	 
     
     }
    } 

