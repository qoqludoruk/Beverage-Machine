import java.awt.Color;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.jar.JarOutputStream;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Test extends JFrame implements ActionListener{

	private JLabel lblSize, lblType,lblGlass, lblinfo;
	private JComboBox cbSize;
	private JTextField txtGlass;
	private JRadioButton rbJuice,rbWater,rbTea,rbCoffee;
	private ButtonGroup radioGroup;
	private JButton btnAdd,btnOrder;
	
	ArrayList<Order> orderList=new ArrayList<Order>();
	
	public Test() {
		setLayout(null); // I use null layout
		
		
		lblSize=new JLabel("Select size:");
		lblSize.setLocation(50,25);
		lblSize.setSize(100,20);
		add(lblSize);
		
		String sizes[]= {"Small","Medium","Large"};
		cbSize=new JComboBox(sizes);
		cbSize.setLocation(50, 50);
		cbSize.setSize(100, 20);
		add(cbSize);
		
		lblType=new JLabel("Select which type of beverage you want to order:");
		lblType.setLocation(50, 75);
		lblType.setSize(300, 20);
		add(lblType);
		
		rbJuice=new JRadioButton("Juice");
		rbWater=new JRadioButton("Water");
		rbTea=new JRadioButton("Tea");
		rbCoffee=new JRadioButton("Coffee");
		
		radioGroup=new ButtonGroup();
		radioGroup.add(rbJuice);
		radioGroup.add(rbWater);
		radioGroup.add(rbTea);
		radioGroup.add(rbCoffee);
		
		rbJuice.setLocation(50, 100);
		rbWater.setLocation(150, 100);
		rbTea.setLocation(250, 100);
		rbCoffee.setLocation(350, 100);
		rbJuice.setSize(100, 20);
		rbWater.setSize(100, 20);
		rbTea.setSize(100, 20);
		rbCoffee.setSize(100, 20);
		add(rbJuice);
		add(rbWater);
		add(rbTea);
		add(rbCoffee);
		
		lblGlass=new JLabel("Select how many glasses you want to order:");
		lblGlass.setLocation(50, 125);
		lblGlass.setSize(300, 20);
		add(lblGlass);
		
		txtGlass=new JTextField();
		txtGlass.setLocation(50, 150);
		txtGlass.setSize(300, 20);
		add(txtGlass);
		
		btnAdd=new JButton("Add");
		btnAdd.setLocation(50, 175);
		btnAdd.setSize(100, 50);
		add(btnAdd);
		
		btnOrder=new JButton("Order");
		btnOrder.setLocation(250, 175);
		btnOrder.setSize(100, 50);
		//btnOrder.setBackground(Color.gray); //this is for order button background color change
		add(btnOrder);
		
		lblinfo=new JLabel();
		lblinfo.setLocation(50, 225);
		lblinfo.setSize(350, 20);
		add(lblinfo);
		
		btnOrder.addActionListener(this);
		btnAdd.addActionListener(this);
		
		setSize(700, 500);
		setVisible(true);
		this.setTitle("Order beverage"); //this is title
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//getContentPane().setBackground(Color.white);
		//setResizable(false);
		
		
	}
	public static void main(String[] args) {
		
		new Test();	

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(btnAdd))
		{
			String warning="";
			boolean warn=false;
			int warnType=0;
			String orderType="";
			String size="";
			int noGlass=0;
			try
			{
				int sizeint=cbSize.getSelectedIndex();
				switch(sizeint) {  //use switch case to make size choice
					case 0: 
						size="small";
						break;
					case 1:
						size="medium";
						break;
					case 2:
						size="large";
						break;
						
				}
				String nGlass= txtGlass.getText().trim(); // to fix if any space is clicked
				
				if(nGlass.equals(""))
				{
					warn=true;
					warnType+=1;
					warning+=" enter an amount";
					
				}
				else {
					noGlass=Integer.parseInt(nGlass);
				}
				
				if(rbJuice.isSelected() && warn==false) {
					Object[] options = {"Apple", "Orange", "Pineapple"};
			      
			        Object selectionObject = JOptionPane.showInputDialog(null, "Select a fruit", "Select a fruit", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			        orderType = selectionObject.toString();
			        orderType+=" Juice";
			        			                
				}
				else if(rbWater.isSelected() && warn==false)  {
					
					orderType="Water";
					int opt=JOptionPane.showConfirmDialog(null, "Would you like ice?", "Ice", JOptionPane.YES_NO_OPTION);
					if(opt==0)
						orderType+=" with ice";
					
					
				}
				else if(rbTea.isSelected() && warn==false)
				{
					orderType="Tea";
					int opt=JOptionPane.showConfirmDialog(null, "Would you like sugar?", "Sugar", JOptionPane.YES_NO_OPTION);
					if(opt==0)
						orderType+=" with sugar";
				}
				else if(rbCoffee.isSelected() && warn==false)
				{
					orderType="Coffee";
					int opt=JOptionPane.showConfirmDialog(null, "Would you like milk?", "Milk", JOptionPane.YES_NO_OPTION);
					if(opt==0)
						orderType+=" with milk";
				}
				else {
					if(!rbJuice.isSelected() && !rbWater.isSelected() && !rbTea.isSelected() && !rbCoffee.isSelected() )
					{
						warn=true;
						warnType+=1;
						warning+=" a beverage type ";
					}
					
				}
				
				
				
				if(warn) { //missing information
					if(warnType==2)
						JOptionPane.showMessageDialog(null, "Choose a beverage type and enter an amount");
					else
						JOptionPane.showMessageDialog(null, "Choose "+warning);
				}	
				else {
					//order creation and adding 
					Order ord=new Order(size,orderType,noGlass);
					orderList.add(ord);
					lblinfo.setText(noGlass+" glass(es) of "+size+" "+orderType+" added.");
					
					
				}
					
				
			}
			catch (NumberFormatException e1) {
				System.out.println("Unvalid data...");
			}
		}
		else if(e.getSource().equals(btnOrder))
		{
			String orders="";
			double totalPrice=0;
			
			for(Order ord : orderList) { // for each to get total price
				orders+=ord.toSring()+"\n";
				totalPrice+=ord.getPrice();
			}
			JOptionPane.showMessageDialog(null, orders);
			JOptionPane.showMessageDialog(null, "You should pay "+totalPrice+" TL");
		}
	
		
	}

}
