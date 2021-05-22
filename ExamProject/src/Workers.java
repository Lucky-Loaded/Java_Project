import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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
	PreparedStatement timeCheck = null;
	Connection conn=null;
    JTabbedPane tab=new JTabbedPane();
    private ResultSet result = null;
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
	JLabel date = new JLabel("Date(yyyy-mm-dd) :  ");
	JLabel type = new JLabel("Type: ");
	//JLabel date_time = new JLabel("Date: ");
	JLabel email = new JLabel("Email: ");
	JLabel city = new JLabel("City: ");
	JLabel addContractComboLabel = new JLabel("Select Worker: ");
	JLabel addWorkerComboLabel = new JLabel("Select position: ");
	
    JTextField nameTF = new JTextField();
    JTextField pos_nameTF = new JTextField();
    JTextField dateTF = new JTextField();
    String[] item = {"Full-time", "Temporary"};
    JComboBox<String> typeCombo = new JComboBox<String>(item);
    JTextField emailTF = new JTextField();
    JTextField cityTF = new JTextField();
    //1
    JButton addBtnW = new JButton("Add");
	JButton deleteBtnW = new JButton("Delete");
	JButton editBtnW = new JButton("Edit");
	JButton searchBtnW = new JButton("Search");
	JButton allBtnW = new JButton("Reset");
	JComboBox<String> searchComboW = new JComboBox<String>();
	JComboBox<String> addWorkerCombo = new JComboBox<String>();
	
	//2
	JButton addBtnC = new JButton("Add");
	JButton deleteBtnC = new JButton("Delete");
	JButton editBtnC = new JButton("Edit");
	JButton searchBtnC = new JButton("Search");
	JComboBox<String> addContractCombo = new JComboBox<String>();
	JComboBox<String> searchComboC = new JComboBox<String>();
	//3
	JButton addBtnP = new JButton("Add");
	JButton deleteBtnP = new JButton("Delete");
	JButton editBtnP = new JButton("Edit");
	JButton searchBtnP = new JButton("Search");
	JComboBox<String> searchComboP = new JComboBox<String>();
    public Workers() {
        
        this.setSize(700,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //
        
        tab.add(workerPanel,"Workers");
    	tab.add(contractPanel,"Contracts");
    	tab.add(positionPanel,"Positions");
    	tab.add(searchPanel,"Info");
    	this.add(tab);
    	
    	//1
        upPanelW.setLayout(new GridLayout(5,2));
   	 upPanelW.add(name);
   	 upPanelW.add(nameTF);
   	 upPanelW.add(date);
   	 upPanelW.add(dateTF);
   	 upPanelW.add(email);
   	 upPanelW.add(emailTF);
   	 upPanelW.add(city);
   	 upPanelW.add(cityTF);
   	 upPanelW.add(addWorkerComboLabel);
   	 upPanelW.add(addWorkerCombo);
   	DBHelper.fillCombo(addWorkerCombo, "name", "position");
   	 //upPanelW.add(positionCombo);
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
  	 upPanelC.add(typeCombo);
  	 upPanelC.add(addContractComboLabel);
  	 upPanelC.add(addContractCombo);
  	 DBHelper.fillCombo(addContractCombo, "name", "worker");
  	 


   	 //1
   	 midPanelW.add(addBtnW);
	 midPanelW.add(deleteBtnW);
	 midPanelW.add(editBtnW);
	 midPanelW.add(searchBtnW);	
	 midPanelW.add(searchComboW);
	 midPanelW.add(allBtnW);
	 
   	 ///
	 //2
	 ///
	 midPanelP.add(addBtnP);
	 midPanelP.add(deleteBtnP);
	 midPanelP.add(editBtnP);
	 midPanelP.add(searchBtnP);
	 midPanelP.add(searchComboP);
	 //
	 //3
	 //
	 midPanelC.add(addBtnC);
	 midPanelC.add(deleteBtnC);
	 midPanelC.add(editBtnC);
	 midPanelC.add(searchBtnC);
	 midPanelC.add(searchComboC);
	 //1
	 addBtnP.addActionListener(new AddPosition());
	 searchBtnP.addActionListener(new SearchActionP());
	 deleteBtnP.addActionListener(new DeletePosition());
	 DBHelper.fillCombo(searchComboP, "name", "position");
	 editBtnP.addActionListener(new EditActionP());
	
	 //2
	 addBtnC.addActionListener(new AddContract());
	 deleteBtnC.addActionListener(new DeleteContract());
	 DBHelper.fillComboContract(searchComboC);
	 //3
	addBtnW.addActionListener(new AddWorker());
	 deleteBtnW.addActionListener(new DeleteWorker());
	 searchBtnW.addActionListener(new SearchActionW());
	 editBtnW.addActionListener(new EditActionW());
	 allBtnW.addActionListener(new SearchResetActionW());
	 DBHelper.fillCombo(searchComboW, "name" ,"worker");
	 
	 
	 //1 DOWNPANEL
	 downPanelP.add(scrollerP);
	 scrollerP.setPreferredSize(new Dimension(400,200));
	 tableP.setModel(DBHelper.getAllData("position"));
	 tableP.addMouseListener(new TableListenerP());
	 //2
	 downPanelW.add(scrollerW);
	 scrollerW.setPreferredSize(new Dimension(600,200));
	 tableW.setModel(DBHelper.getAllDataTable());
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
   // public void clearFormC() {
	//	 typeTF.setText("");
	//	 date_timeTF.setText("");

	// }
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
				tableP.setModel(DBHelper.getAllData("position"));
				DBHelper.fillCombo(searchComboP, "name" ,"position");
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
     public class AddWorker implements ActionListener{
  	 @Override
  	 public void actionPerformed(ActionEvent arg0) {
  		 String name = nameTF.getText();
  		 String date = dateTF.getText();
  		 String email = emailTF.getText();
  		 String city = cityTF.getText();
  		 //
  		String selectedItem = addWorkerCombo.getSelectedItem().toString();
		String[] items = selectedItem.split(" ");
		int itemID = Integer.parseInt(items[0]);
  		 conn=DBHelper.getConnection();
  		 String sql="insert into worker values(null,?,?,?,?,?);";
  		try {
  			state=conn.prepareStatement(sql);
  			state.setString(1, name);
  			state.setString(2, date);
  			state.setString(3, email);
  			state.setString(4, city);
  			state.setInt(5, itemID);
  			
  			state.execute();
  			tableW.setModel(DBHelper.getAllDataTable());
  			DBHelper.fillCombo(searchComboW, "name" ,"worker");

  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} 
  		clearFormW();
  		
  	 }
  	 }
     ///
     //3
     ///
     public class AddContract implements ActionListener{
      	 @Override
      	 public void actionPerformed(ActionEvent arg0) {
      		 String selectedType = addContractCombo.getSelectedItem().toString();
      		
      		 //
      		String selectedItem = addWorkerCombo.getSelectedItem().toString();
    		String[] items = selectedItem.split(" ");
    		int itemID = Integer.parseInt(items[0]);
      		 conn=DBHelper.getConnection();
      		 String sql="insert into contract values(null,?,?,?);";
      		Date date = Date.valueOf(LocalDate.now());
      		
      		try {
      			
      			state=conn.prepareStatement(sql);
      			state.setString(1, selectedType);
      			state.setString(2, date.toString());
      			state.setInt(3, itemID);
      			
      			
      			state.execute();
      			tableW.setModel(DBHelper.getAllDataTable());
      			DBHelper.fillCombo(searchComboC, "name" ,"worker");
      		} catch (SQLException e) {
      			// TODO Auto-generated catch block
      			e.printStackTrace();
      		} 
      		
      	 }
      	 }
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
     class DeleteAction implements ActionListener{
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
   			DBHelper.fillCombo(searchComboW, "name" ,"worker");
			
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
    class SearchActionW implements ActionListener{
		 @Override
		 public void actionPerformed(ActionEvent e) {
			String selectedItem = searchComboW.getSelectedItem().toString();
			String[] items = selectedItem.split(" ");
			int itemID = Integer.parseInt(items[0]);
			 
			 conn = DBHelper.getConnection();
			 
			 String sql = "select * from worker where id=? ";
			try { 
			
				state = conn.prepareStatement(sql);
				state.setInt(1, itemID);
				result = state.executeQuery();
				tableW.setModel(new MyModel(result));
				DBHelper.fillCombo(searchComboW, "name" ,"worker");
			} catch(SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			} catch(Exception e1) {
				//TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 
			
		 }
		 
	 }
    class SearchActionP implements ActionListener{
		 @Override
		 public void actionPerformed(ActionEvent e) {
			String selectedItem = searchComboP.getSelectedItem().toString();
			String[] items = selectedItem.split(" ");
			int itemID = Integer.parseInt(items[0]);
			 
			 conn = DBHelper.getConnection();
			 
			 String sql = "select * from position where id=? ";
			try { 
			
				state = conn.prepareStatement(sql);
				state.setInt(1, itemID);
				result = state.executeQuery();
				tableW.setModel(new MyModel(result));
				DBHelper.fillCombo(searchComboP, "name" ,"position");
			} catch(SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			} catch(Exception e1) {
				//TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 
			
		 }
		 
	 }
    class SearchActionC implements ActionListener{
		 @Override
		 public void actionPerformed(ActionEvent e) {
			String selectedItem = searchComboC.getSelectedItem().toString();
			String[] items = selectedItem.split(" ");
			int itemID = Integer.parseInt(items[0]);
			 
			 conn = DBHelper.getConnection();
			 
			 String sql = "select * from contract where id=? ";
			try { 
			
				state = conn.prepareStatement(sql);
				state.setInt(1, itemID);
				result = state.executeQuery();
				tableW.setModel(new MyModel(result));
				DBHelper.fillComboContract(searchComboC);
			} catch(SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			} catch(Exception e1) {
				//TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 
			
		 }
		 
	 }
    class SearchResetActionW implements ActionListener{
       

		public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
                conn = DBHelper.getConnection();

                String sql = "select * from worker";

                try {
                    state = conn.prepareStatement(sql);
                    state.execute();
                    tableW.setModel(DBHelper.getAllData("worker"));

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                }
    }
    class SearchResetActionP implements ActionListener{
        

		public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
                conn = DBHelper.getConnection();

                String sql = "select * from position";

                try {
                    state = conn.prepareStatement(sql);
                    state.execute();
                    tableW.setModel(DBHelper.getAllData("position"));

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                }
    }
    class SearchResetActionC implements ActionListener{
        
		public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
                conn = DBHelper.getConnection();

                String sql = "select * from contract";

                try {
                    state = conn.prepareStatement(sql);
                    state.execute();
                    tableW.setModel(DBHelper.getAllData("contract"));

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                }
    }
    ///
    //1
    ///
    class EditActionW implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
				// TODO Auto-generated method stub
				conn = DBHelper.getConnection();
				String sql = "UPDATE worker SET name = \'" + nameTF.getText() + "\', Birthday = \'"  + dateTF.getText() + "\', email = \'"+ emailTF.getText() + "\', City = \'"+ cityTF.getText() + "\'  WHERE ID=?;";
				try {
					state = conn.prepareStatement(sql);
					state.setInt(1, id);
					state.execute();
					id = -1;
					tableW.setModel(DBHelper.getAllDataTable());
					DBHelper.fillCombo(searchComboW, "name", "worker");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		}
    class EditActionC implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
				// TODO Auto-generated method stub
				conn = DBHelper.getConnection();
				String sql = "UPDATE worker SET name = \'" + nameTF.getText() + "\', Birthday = \'"  + dateTF.getText() + "\', email = \'"+ emailTF.getText() + "\', City = \'"+ cityTF.getText() + "\'  WHERE ID=?;";
				try {
					state = conn.prepareStatement(sql);
					state.setInt(1, id);
					state.execute();
					id = -1;
					tableW.setModel(DBHelper.getAllDataTable());
					DBHelper.fillCombo(searchComboW, "name", "worker");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		}
    class EditActionP implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
				// TODO Auto-generated method stub
				conn = DBHelper.getConnection();
				String sql = "UPDATE position SET name = \'" + pos_nameTF.getText() + "\'  WHERE ID=?;";
				try {
					state = conn.prepareStatement(sql);
					state.setInt(1, id);
					state.execute();
					id = -1;
					tableP.setModel(DBHelper.getAllData("position"));
					DBHelper.fillCombo(searchComboP, "name", "position");
					
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
    		if(e.getClickCount()== 2) {
    		
	    	nameTF.setText(tableW.getValueAt(row, 1).toString());
	    	dateTF.setText(tableW.getValueAt(row, 2).toString());
	    	emailTF.setText(tableW.getValueAt(row, 3).toString());
	    	cityTF.setText(tableW.getValueAt(row, 4).toString());
	    	addWorkerCombo.setSelectedItem(tableW.getComponentAt(row, 5));
    	}
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
    		if(e.getClickCount()== 2) {
        		
    	    	pos_nameTF.setText(tableP.getValueAt(row, 1).toString());
    	    	
        	}
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
    	 
     
    }}
