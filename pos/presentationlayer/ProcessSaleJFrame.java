package pos.presentationlayer;

import pos.domainlayer.ItemID;
import pos.domainlayer.Money;
import pos.domainlayer.Register;
import pos.domainlayer.Sale;
import pos.domainlayer.SalesLineItem;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Set;

import javax.swing.*;

public class ProcessSaleJFrame extends JFrame {
	
	Register register;
	Sale sale;
	ItemID id;
	Money money;
	
	
	JButton makeNewSalebtn;
	JButton enterItembtn;
	JButton endSalebtn;
	JButton makePaymentbtn;
	JButton calculater;
	JButton applyDiscount;
	
	JComboBox itemBox;
	
	JTextField itemIDField; //itemID출력
	JTextField quentityField;
	JTextField totalField; //주문한 총 금액
	JTextField amountField;//지불 금액
	JTextField balanceField;//지불 후 잔액
	JTextField descriptionField; //아이템 정보를 출력
	JTextField currentTotal;
	JTextField taxWithTotal;
	JTextField discountTotal;
	
	JTextArea stateArea = new JTextArea();
	JScrollPane scrollPane = new JScrollPane(stateArea);
	
	JLabel studentInfo = new JLabel("학번 : 20161042 ,이름 : 고유림");
	JLabel itemID;
	JLabel quentity;
	JLabel description;
	JLabel currentTotalLabel;
	JLabel totalLabel;
	JLabel amountLabel;
	JLabel balanceLabel;
	JLabel taxTotalLabel;
	JLabel totalAfterDiscount;
	
	JPanel infoPanel = new JPanel();
	JPanel button1 = new JPanel();
	JPanel itemInput = new JPanel();
	JPanel quentityInput = new JPanel();
	JPanel descriptionPane = new JPanel();
	JPanel currentTotalPane = new JPanel();
	JPanel button2 = new JPanel();
	JPanel button3 = new JPanel();
	JPanel totalInput = new JPanel();
	JPanel amountInput = new JPanel();
	JPanel button4 = new JPanel();
	JPanel balanceoutput = new JPanel();
	JPanel radioPane = new JPanel();
	JPanel disRadioPane = new JPanel();
	JPanel calcPane = new JPanel();
	JPanel totalPane = new JPanel();
	JPanel applyDiscountPane = new JPanel();
	JPanel totalAfterDisPane = new JPanel();
	
	ButtonGroup radiogroup = new ButtonGroup();
	JRadioButton tax1 ;
	JRadioButton tax2;
	
	ButtonGroup discountGroup = new ButtonGroup();
	JRadioButton discount1;
	JRadioButton discount2;
	
	public ProcessSaleJFrame(Register register) {
		super("POS System V0.2 학번:20161042, 이름:고유림");
		
		this.register = register;
		
		buildGUI();
		registerEventHanddler();
		
		
//		this.pack(); //창에 맞에 프레임 설정
		this.setSize(700, 600);
		this.setVisible(true); //pack이 보이도록 설정(가시화)
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫기 버튼 누르면 프레임에서 나가짐
	}
	private void setLayouts() {
		//구성
		Container contentPane = this.getContentPane();
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(16,1));
		contentPane.setLayout(new GridLayout(1,2));
		content.add(button1);
				
		content.add(itemInput);
		content.add(quentityInput);
		content.add(descriptionPane);
		
		content.add(button2);
		
		content.add(currentTotalPane);
				
		content.add(button3);
		
		content.add(radioPane);
		
		content.add(calcPane);
		
		content.add(totalPane);
		
		content.add(disRadioPane);
		
		content.add(applyDiscountPane);
		
		content.add(totalAfterDisPane);
		
		content.add(amountInput);
				
		content.add(button4);
		content.add(balanceoutput);
		
		contentPane.add(content); contentPane.add(scrollPane);
	}
	private void buildGUI() {
		// TODO Auto-generated method stub
		stateArea.setEnabled(false);
		//1.
		makeNewSalebtn = new JButton("1. Make New Sale()");
		button1.add(makeNewSalebtn);
		
		enterItembtn = new JButton("2. Enter Item()");
		button2.add(enterItembtn);
		
		endSalebtn = new JButton("3. End Sale()");
		button3.add(endSalebtn);
		
		calculater = new JButton("4. calculaterTax()");
		calcPane.add(calculater);
		
		makePaymentbtn = new JButton("6. Make Payment()");
		button4.add(makePaymentbtn);
		
		applyDiscount = new JButton("5. applyDiscount()");
		applyDiscountPane.add(applyDiscount);
		
		//2.
		
		itemID = new JLabel("ItemID: ");
		itemBox = new JComboBox();
		loadItemList();
		itemInput.add(itemID); itemInput.add(itemBox); 
		
		quentity = new JLabel("Quentity: ");
		quentityField = new JTextField(10);
		quentityInput.add(quentity); quentityInput.add(quentityField);
		
		description = new JLabel("Description: ");
		descriptionField = new JTextField(10);
		descriptionField.setEnabled(false);
		descriptionPane.add(description); descriptionPane.add(descriptionField);
		
		currentTotalLabel = new JLabel("current total : ");
		currentTotal = new JTextField(10);
		currentTotal.setEnabled(false);
		currentTotalPane.add(currentTotalLabel); currentTotalPane.add(currentTotal);
		
		tax1  = new JRadioButton("TaxMaster");
		tax2 = new JRadioButton("GoodAsTaxPro");
		radiogroup.add(tax1); radiogroup.add(tax2);
		radioPane.add(tax1); radioPane.add(tax2);
		
		taxTotalLabel = new JLabel("Tax With Total");
		taxWithTotal = new JTextField(10);
		taxWithTotal.setEnabled(false);
		totalPane.add(taxTotalLabel); totalPane.add(taxWithTotal);
		
		discount1 = new JRadioButton("BestForCustomer");
		discount2 = new JRadioButton("BestForStore");
		discountGroup.add(discount1);discountGroup.add(discount2);
		disRadioPane.add(discount1); disRadioPane.add(discount2);
		
		totalAfterDiscount = new JLabel("Total After Discount : ");
		discountTotal = new JTextField(10);
		discountTotal.setEnabled(false);
		totalAfterDisPane.add(totalAfterDiscount); totalAfterDisPane.add(discountTotal);
		
		amountLabel = new JLabel("Amount: ");
		amountField = new JTextField(10);
		amountInput.add(amountLabel); amountInput.add(amountField);
		
		balanceLabel = new JLabel("Balance: ");
		balanceField = new JTextField(10);
		balanceoutput.add(balanceLabel); balanceoutput.add(balanceField);
		
		setenableFalse();
		
		setLayouts();
		
	}
	public void loadItemList() {
		Set set = register.getProductHashMap().keySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()) {
			itemBox.addItem(iterator.next());
		}
	}
	
	private void setEnterItemTrue() {
		makeNewSalebtn.setEnabled(false);
		itemBox.setEnabled(true);
		quentityField.setEnabled(true);
		enterItembtn.setEnabled(true);
		endSalebtn.setEnabled(true);
	}
	
	private void setEnterItemFalse() {
		itemBox.setEnabled(false);
		quentityField.setEnabled(false);
		enterItembtn.setEnabled(false);
		endSalebtn.setEnabled(false);
	}
	
	private void setTaxSelectedTrue() {
		tax1.setEnabled(true);
		tax2.setEnabled(true);
		calculater.setEnabled(true);
	}
	
	private void setTaxSelectedFalse() {
		tax1.setEnabled(false);
		tax2.setEnabled(false);
		calculater.setEnabled(false);
	}
	
	private void setDiscountTrue() {
		discount1.setEnabled(true);
		discount2.setEnabled(true);
		applyDiscount.setEnabled(true);
	}
	
	private void setDiscountFalse() {
		discount1.setEnabled(false);
		discount2.setEnabled(false);
		applyDiscount.setEnabled(false);
	}
	
	private void setPayTrue() {
		amountField.setEnabled(true);
		makePaymentbtn.setEnabled(true);
	}
	private void setPayFalse() {
		makeNewSalebtn.setEnabled(true);
		amountField.setEnabled(false);
		makePaymentbtn.setEnabled(false);
	}
	private void setenableFalse() {
		// TODO Auto-generated method stub
		itemBox.setEnabled(false);
		quentityField.setEnabled(false);
		enterItembtn.setEnabled(false);
		endSalebtn.setEnabled(false);
		tax1.setEnabled(false);
		tax2.setEnabled(false);
		calculater.setEnabled(false);
		discount1.setEnabled(false);
		discount2.setEnabled(false);
		applyDiscount.setEnabled(false);
		amountField.setEnabled(false);
		balanceField.setEnabled(false);	
		makePaymentbtn.setEnabled(false);
	}
	private void registerEventHanddler() {
		// TODO Auto-generated method stub
		
		//활성화 버튼, sale 객체 만듬
		makeNewSalebtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sale = register.makeNewSale();
				
				setEnterItemTrue();
				amountField.setText("");
				balanceField.setText("");
				taxWithTotal.setText("");
				discountTotal.setText("");
				stateArea.append("Status: Make New Sale 버튼이 눌러졌습니다.\n");
			}
		});
		
		enterItembtn.addActionListener(enterItemHandler);
		endSalebtn.addActionListener(endSaleHandler);
		makePaymentbtn.addActionListener(makePaymentHandler);
		calculater.addActionListener(taxCalculatorHandler);
		tax1.addItemListener(new SelectedItemListener());
		tax2.addItemListener(new SelectedItemListener());
		discount1.addItemListener(new SelectedItemListener());
		discount2.addItemListener(new SelectedItemListener());
		applyDiscount.addActionListener(applyDiscountHandler);
	}
	ActionListener applyDiscountHandler = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String total = String.valueOf(sale.getTotal().getAmount2());
			discountTotal.setText(total);
			setDiscountFalse();
			setPayTrue();
			stateArea.append("Status : Total After Discount ("+String.valueOf(sale.getTotal().getAmount2())+")\n");
		}
	};
	
	ActionListener taxCalculatorHandler = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				sale.taxinitial();
				taxWithTotal.setText(String.valueOf(sale.getTaxTotal().getAmount2()));
				stateArea.append("Status : Tax With Total ("+String.valueOf(sale.getTaxTotal().getAmount2())+")\n");
				setTaxSelectedFalse();
				setDiscountTrue();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	};
	class SelectedItemListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			AbstractButton sel = (AbstractButton)e.getItemSelectable();
			if(e.getStateChange() == ItemEvent.SELECTED) {
				if(sel.getText().equals("TaxMaster")) {
					System.setProperty("taxcalculator.class.name", "pos.domainlayer.TaxMasterAdapter");
					stateArea.append("Status: TaxMaster 선택\n");
				}
				else if(sel.getText().equals("GoodAsTaxPro")) {
					System.setProperty("taxcalculator.class.name", "pos.domainlayer.GoodAsGoldTaxProAdapter");
					stateArea.append("Status: TaxMaster 선택\n");
				}else if(sel.getText().equals("BestForStore")) {
					System.setProperty("salepricingstrategy.class.name","pos.domainlayer.CompositeBestForStorePricingStrategy");
				}else if(sel.getText().equals("BestForCustomer")) {
					System.setProperty("salepricingstrategy.class.name","pos.domainlayer.CompositeBestForCustomerPricingStrategy"); 
				}
			}
			
		}
		
	}
	ActionListener enterItemHandler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int error_no = 0;
			try {
				String itemid = itemBox.getSelectedItem().toString();
				id = new ItemID(itemid);
				int q = Integer.parseInt(quentityField.getText());
				
				if(q<0) {
					error_no = 100;
					throw new Exception();
				}else {
					if(id.toString().equals("100") || id.toString().equals("200") || id.toString().equals("300") || id.toString().equals("400") || id.toString().equals("500")) {
						error_no = 0;
						register.enterItem(id,q);
						
						quentityField.setText("");
						descriptionField.setText(register.getCatalogProduct(id).getDescription());
						String currenttotal = String.valueOf(sale.getCurrentTotal());
						currentTotal.setText(currenttotal);
						
						stateArea.append("Status: 상품 정보: "+register.getCatalogProduct(id).getDescription()+", ");
						stateArea.append("상품 가격: "+register.getCatalogProduct(id).getPrice()+"\n");
					}
					else {
						error_no = 101;
						throw new Exception();
					}
				}
				error_no = 0;
			}catch(NumberFormatException exception) {
				JOptionPane.showMessageDialog(null, "숫자를 입력하시오.");
			}catch(Exception exception2) {
				if(error_no == 100){JOptionPane.showMessageDialog(null, "양수를 입력하시오.");}
				else if(error_no == 101) {JOptionPane.showMessageDialog(null, "없는 아이템 아이디입니다.");}
			}
		}
		
	};
	ActionListener endSaleHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			setEnterItemFalse();
			setTaxSelectedTrue();
			descriptionField.setText("");
			stateArea.append("Status: 상품 담기가 끝났습니다.\n");
		}
		
	};
	ActionListener makePaymentHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				int amount = Integer.parseInt(amountField.getText());
				double total = sale.getTotal().getAmount2();
				if(amount < total) {
					new Exception();
				}else {
					money = new Money(amount);
					register.makePayment(money);
					String balance = String.valueOf(register.getBalance().getAmount2());
					balanceField.setText(balance);
					itemBox.setSelectedIndex(0);
					currentTotal.setText("");
					setPayFalse();
					register.endSale();
					stateArea.append("Status : 판매 완료\n");
				}
			}catch(NumberFormatException exception) {
				JOptionPane.showMessageDialog(null, "양수를 입력하시오.");
			}catch(Exception excpetion) {
				JOptionPane.showMessageDialog(null, "금액이 적습니다.");
			}
		}
		
	};
}
