package controll;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.CustomerVO;
import model.MenuVO;
import model.OrderVO;

public class OrderController implements Initializable {
	@FXML
	private Label lblTitle;
	@FXML
	private TextField txtOrderPhoneSearch;
	@FXML
	private Button btnOrderPhoneSearch;
	@FXML
	private Button btnOrderCustomer;
	@FXML
	private TextField txtOrderCName;
	@FXML
	private TextField txtOrderCAddress;
	@FXML
	private TextField txtOrderCPhone;
	@FXML
	private TextField txtOrderCOther;

	@FXML
	private Button btnOrderCSave;
	@FXML
	private Button btnQuantityUp;
	@FXML
	private Button btnQuantityDown;
	@FXML
	private Button btnOrderDelete;
	@FXML
	private ComboBox<String> cbOrderCash;

	@FXML
	private Button btnOrderMenu1;
	@FXML
	private Button btnOrderMenu2;
	@FXML
	private Button btnOrderMenu3;
	@FXML
	private Button btnOrderMenu4;
	@FXML
	private Button btnOrderMenu5;
	@FXML
	private Button btnOrderMenu6;
	@FXML
	private Button btnOrderMenu7;
	@FXML
	private Button btnOrderMenu8;
	@FXML
	private Button btnOrderMenu9;
	@FXML
	private Button btnOrderMenu10;
	@FXML
	private Button btnOrderMenu11;
	@FXML
	private Button btnOrderMenu12;
	@FXML
	private Button btnOrderMenu13;
	@FXML
	private Button btnOrderMenu14;
	@FXML
	private Button btnOrderMenu15;
	@FXML
	private Button btnOrderMenu16;
	@FXML
	private Button btnOrderMenu17;
	@FXML
	private Button btnOrderMenu18;
	@FXML
	private Button btnOrderCancel;
	@FXML
	private Button btnOrderOk;
	@FXML
	private TableView<OrderVO> tableViewC;
	@FXML
	private TableView<OrderVO> tableViewOrder;

	ObservableList<OrderVO> dataC = FXCollections.observableArrayList();
	ObservableList<OrderVO> dataOrder = FXCollections.observableArrayList();

	ArrayList<OrderVO> editOrderList;
	
	boolean orderAndEditCustomer;//�ֹ��� ������ �����϶� true

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// �������� �޾� �ؽ�Ʈ �ʵ带 �ʱ�ȭ �� �ʿ䰡 �ִ°��
		if (ConnectionData.sceneChange) {
			txtOrderPhoneSearch.setText(ConnectionData.customerVO.getPhone());
			txtOrderCName.setText(ConnectionData.customerVO.getName());
			txtOrderCAddress.setText(ConnectionData.customerVO.getAddress());
			txtOrderCPhone.setText(ConnectionData.customerVO.getPhone());
			txtOrderCOther.setText(ConnectionData.customerVO.getOther());
		}

		if (ConnectionData.editOrder) {
			editOrderList = ConnectionData.orderVOList;

			txtOrderPhoneSearch.setText(ConnectionData.customerVO.getPhone());
			txtOrderCName.setText(ConnectionData.customerVO.getName());
			txtOrderCAddress.setText(ConnectionData.customerVO.getAddress());
			txtOrderCPhone.setText(ConnectionData.customerVO.getPhone());
			txtOrderCOther.setText(ConnectionData.customerVO.getOther());
			lblTitle.setText("�ֹ� ����");
			btnOrderOk.setText("   ��\n\n�� �� ");

			for (int index = 0; index < editOrderList.size(); index++) {
				dataOrder.add(editOrderList.get(index));
			}
		} else {
			btnOrderOk.setText("   ��\n\nȮ �� ");
		}

		if (ConnectionData.newCustomer) {
			txtOrderPhoneSearch.setText(ConnectionData.newPhone);
			txtOrderCPhone.setText(ConnectionData.newPhone);
		}

		tableViewC.setEditable(false);
		tableViewOrder.setEditable(false);
		btnOrderCSave.setText("������\n\n   ����");
		cbOrderCash.setItems(FXCollections.observableArrayList("����", "ī��"));
		cbOrderCash.setValue("����");
		btnOrderCancel.setText("   X\n\n�� �� ");

		previousOrderList();
		menuSearch();// �޴� �ؽ�Ʈ �ʱ�ȭ

		// �� �ֹ����� ���̺�� �÷��̸� ����
		TableColumn colDate = new TableColumn("�ֹ���");
		colDate.setMinWidth(60);
		colDate.setStyle("-fx-allignment: CENTER");
		colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		TableColumn colContents = new TableColumn("�ֹ�����");
		colContents.setMinWidth(150);
		colContents.setStyle("-fx-allignment: CENTER");
		colContents.setCellValueFactory(new PropertyValueFactory<>("contents"));
		TableColumn coltotalPrice = new TableColumn("�Ǹűݾ�");
		coltotalPrice.setMinWidth(60);
		coltotalPrice.setStyle("-fx-allignment: CENTER");
		coltotalPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		TableColumn colCash = new TableColumn("����");
		colCash.setMinWidth(30);
		colCash.setStyle("-fx-allignment: CENTER");
		colCash.setCellValueFactory(new PropertyValueFactory<>("cash"));
		tableViewC.getColumns().addAll(colDate, colContents, coltotalPrice, colCash);
		tableViewC.setItems(dataC);

		// �ֹ����� ���̺�� �÷��̸� ����
		TableColumn colMenuName = new TableColumn("�޴���");
		colMenuName.setMinWidth(120);
		colMenuName.setStyle("-fx-allignment: CENTER");
		colMenuName.setCellValueFactory(new PropertyValueFactory<>("contents"));
		TableColumn colQuantity = new TableColumn("����");
		colQuantity.setMinWidth(30);
		colQuantity.setStyle("-fx-allignment: CENTER");
		colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		TableColumn colPrice = new TableColumn("�Ǹűݾ�");
		colPrice.setMinWidth(120);
		colPrice.setStyle("-fx-allignment: CENTER");
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		tableViewOrder.getColumns().addAll(colMenuName, colQuantity, colPrice);
		tableViewOrder.setItems(dataOrder);

		btnOrderMenu1.setOnAction(event -> handlerBtnOrderMenuActoion(event, "1"));
		btnOrderMenu2.setOnAction(event -> handlerBtnOrderMenuActoion(event, "2"));
		btnOrderMenu3.setOnAction(event -> handlerBtnOrderMenuActoion(event, "3"));
		btnOrderMenu4.setOnAction(event -> handlerBtnOrderMenuActoion(event, "4"));
		btnOrderMenu5.setOnAction(event -> handlerBtnOrderMenuActoion(event, "5"));
		btnOrderMenu6.setOnAction(event -> handlerBtnOrderMenuActoion(event, "6"));
		btnOrderMenu7.setOnAction(event -> handlerBtnOrderMenuActoion(event, "7"));
		btnOrderMenu8.setOnAction(event -> handlerBtnOrderMenuActoion(event, "8"));
		btnOrderMenu9.setOnAction(event -> handlerBtnOrderMenuActoion(event, "9"));
		btnOrderMenu10.setOnAction(event -> handlerBtnOrderMenuActoion(event, "10"));
		btnOrderMenu11.setOnAction(event -> handlerBtnOrderMenuActoion(event, "11"));
		btnOrderMenu12.setOnAction(event -> handlerBtnOrderMenuActoion(event, "12"));
		btnOrderMenu13.setOnAction(event -> handlerBtnOrderMenuActoion(event, "13"));
		btnOrderMenu14.setOnAction(event -> handlerBtnOrderMenuActoion(event, "14"));
		btnOrderMenu15.setOnAction(event -> handlerBtnOrderMenuActoion(event, "15"));
		btnOrderMenu16.setOnAction(event -> handlerBtnOrderMenuActoion(event, "16"));
		btnOrderMenu17.setOnAction(event -> handlerBtnOrderMenuActoion(event, "17"));
		btnOrderMenu18.setOnAction(event -> handlerBtnOrderMenuActoion(event, "18"));

		btnOrderPhoneSearch.setOnAction(event -> handlerBtnOrderPhoneSearchActoion(event));
		btnOrderCustomer.setOnAction(event -> handlerBtnOrderCustomerActoion(event));
		btnOrderCSave.setOnAction(event -> handlerBtnOrderCSaveActoion(event));

		btnQuantityUp.setOnMouseClicked(event -> handlerBtnQuantityUpActoion(event));
		btnQuantityDown.setOnMouseClicked(event -> handlerBtnQuantityDownActoion(event));
		btnOrderDelete.setOnMouseClicked(event -> handlerBtnOrderDeleteActoion(event));

		btnOrderOk.setOnAction(event -> handlerBtnOrderOkActoion(event));
		btnOrderCancel.setOnAction(event -> {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/view/mainView.fxml"));
				Scene scene = new Scene(root);
				Stage mainStage = new Stage(StageStyle.DECORATED);
				mainStage.setTitle("�߰��� �Ǹ� ���� �ý���");
				mainStage.setResizable(false);
				mainStage.setScene(scene);
				mainStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Stage oldStage = (Stage) btnOrderCancel.getScene().getWindow();
			oldStage.close();
		});

		// �ؽ�Ʈ �ʱ�ȭ �� ������ false�� ����
		ConnectionData.sceneChange = false;
		ConnectionData.editOrder = false;
		ConnectionData.newCustomer = false;
		ConnectionData.mainOrOrder = 2; // ���� �������� ����
	}

	// ���� �ֹ����� ���
	public void previousOrderList() {
		if (!txtOrderPhoneSearch.getText().equals("")) {
			try {
				OrderVO oVo = null;
				OrderDAO oDao = new OrderDAO();
				ArrayList<OrderVO> list = null;
				list = oDao.getOrderPhoneSearch(txtOrderPhoneSearch.getText());
				if (list != null) {
					int rowCount = list.size();
					dataC.removeAll(dataC);
					for (int index = 0; index < rowCount; index++) {
						oVo = list.get(index);
						dataC.add(oVo);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// �ֹ� Ȯ�� ��ư
	public void handlerBtnOrderOkActoion(ActionEvent event) {
		try {
			if (!txtOrderCAddress.getText().equals("") && !txtOrderCPhone.getText().equals("")
					&& tableViewOrder.getItems().size() >= 1) {
				if (!lblTitle.getText().equals("�ֹ� ����")) {

					orderAndEditCustomer = true; //�ֹ��� ������ ����
					// �Ϲ� �ֹ�
					handlerBtnOrderCSaveActoion(event);
					orderAndEditCustomer = false;

					OrderVO oVo = new OrderVO();
					OrderDAO oDao = new OrderDAO();

					// �ֹ���ȣ ���� ����
					Date date = new Date();
					int random = (int) (Math.random() * 9 + 1);
					int random2 = (int) (Math.random() * 9 + 1);
					SimpleDateFormat sdfONum = new SimpleDateFormat("dms");
					String num = random + sdfONum.format(date) + random2;
					int oNum = Integer.parseInt(num);
					// �ֹ���ȣ ���� ��

					for (int index = 0; index < tableViewOrder.getItems().size(); index++) {
						// ����
						int quantity = tableViewOrder.getItems().get(index).getQuantity();
						// ����
						int totalPrice = Integer.parseInt(tableViewOrder.getItems().get(index).getPrice());
						if (quantity >= 2) {
							// �ΰ��̻��� ������ ��� ����
							totalPrice = quantity * Integer.parseInt(tableViewOrder.getItems().get(index).getPrice());
						}
						MenuVO mVo = new MenuVO();
						MenuDAO mDao = new MenuDAO();
						mVo = mDao.getMenuName(tableViewOrder.getItems().get(index).getContents());

						CustomerVO cVo = new CustomerVO();
						CustomerDAO cDao = new CustomerDAO();
						cVo = cDao.getCustomerPhone(txtOrderCPhone.getText());

						oVo.setcNum(cVo.getNum());
						oVo.setmNum(mVo.getNum());
						oVo.setoNum(oNum);
						oVo.setContents(tableViewOrder.getItems().get(index).getContents());
						oVo.setQuantity(tableViewOrder.getItems().get(index).getQuantity());
						oVo.setPrice(totalPrice + "");
						oVo.setCash(cbOrderCash.getValue().toString());
						oVo.setPhone(txtOrderCPhone.getText());
						oVo.setAddress(txtOrderCAddress.getText());

						oDao.getOrderRegiste(oVo);

					}
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("�ֹ� �Ϸ�");
					alert.setHeaderText("�ֹ� ����");
					alert.setContentText("�ֹ��� �����Ͽ����ϴ�.");
					alert.showAndWait();

					Parent root = FXMLLoader.load(getClass().getResource("/view/mainView.fxml"));
					Scene scene = new Scene(root);
					Stage mainStage = new Stage(StageStyle.DECORATED);
					mainStage.setTitle("�߰��� �Ǹ� ���� �ý���");
					mainStage.setResizable(false);
					mainStage.setScene(scene);
					mainStage.show();

					Stage oldStage = (Stage) btnOrderOk.getScene().getWindow();
					oldStage.close();

				} else {
					// �ֹ� ����
					OrderVO oVo = new OrderVO();
					OrderDAO oDao = new OrderDAO();
					int orderONum = editOrderList.get(0).getoNum();
					Date date = editOrderList.get(0).getDate();
					oDao.getOrderDelete(orderONum);

					orderAndEditCustomer = true;
					// �Ϲ� �ֹ�
					handlerBtnOrderCSaveActoion(event);
					orderAndEditCustomer = false;

					for (int index = 0; index < tableViewOrder.getItems().size(); index++) {
						// ����
						int quantity = tableViewOrder.getItems().get(index).getQuantity();
						// ����
						int totalPrice = Integer.parseInt(tableViewOrder.getItems().get(index).getPrice());
						if (quantity >= 2) {
							// �ΰ��̻��� ������ ��� ����
							totalPrice = quantity * Integer.parseInt(tableViewOrder.getItems().get(index).getPrice());
						}
						MenuVO mVo = new MenuVO();
						MenuDAO mDao = new MenuDAO();
						mVo = mDao.getMenuName(tableViewOrder.getItems().get(index).getContents());

						CustomerVO cVo = new CustomerVO();
						CustomerDAO cDao = new CustomerDAO();
						cVo = cDao.getCustomerPhone(txtOrderCPhone.getText());

						oVo.setcNum(cVo.getNum());
						oVo.setmNum(mVo.getNum());
						oVo.setoNum(orderONum);
						oVo.setDate(date);
						oVo.setContents(tableViewOrder.getItems().get(index).getContents());
						oVo.setQuantity(tableViewOrder.getItems().get(index).getQuantity());
						oVo.setPrice(totalPrice + "");
						oVo.setCash(cbOrderCash.getValue().toString());
						oVo.setPhone(txtOrderCPhone.getText());
						oVo.setAddress(txtOrderCAddress.getText());

						oDao.getOrderEditRegiste(oVo);

					}
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("�ֹ� ���� �Ϸ�");
					alert.setHeaderText("�ֹ� ���� ����");
					alert.setContentText("�ֹ� ������ �����Ͽ����ϴ�.");
					alert.showAndWait();

					Parent root = FXMLLoader.load(getClass().getResource("/view/mainView.fxml"));
					Scene scene = new Scene(root);
					Stage mainStage = new Stage(StageStyle.DECORATED);
					mainStage.setTitle("�߰��� �Ǹ� ���� �ý���");
					mainStage.setResizable(false);
					mainStage.setScene(scene);
					mainStage.show();

					Stage oldStage = (Stage) btnOrderOk.getScene().getWindow();
					oldStage.close();
				}
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("�ʼ� �׸� ���Է�");
				alert.setHeaderText("�ֹ��� �ʿ��� �ʼ� �׸��� �����մϴ�.");
				alert.setContentText("���ּ�, ��ȭ��ȣ, 1�� �̻��� ��ǰ�� ���� �ؾ� �ֹ��� �����մϴ�.");
				alert.showAndWait();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ���� Up
	public void handlerBtnQuantityUpActoion(MouseEvent event) {
		try {
			int quantity = tableViewOrder.getSelectionModel().getSelectedItems().get(0).getQuantity();
			tableViewOrder.getSelectionModel().getSelectedItems().get(0).setQuantity(quantity + 1);
			tableViewOrder.refresh();
		} catch (Exception e) {

		}
	}

	// ���� Down
	public void handlerBtnQuantityDownActoion(MouseEvent event) {
		try {
			int quantity = tableViewOrder.getSelectionModel().getSelectedItems().get(0).getQuantity();
			if (quantity > 1) {
				tableViewOrder.getSelectionModel().getSelectedItems().get(0).setQuantity(quantity - 1);
			} else {
				tableViewOrder.getItems().remove(tableViewOrder.getSelectionModel().getSelectedItems().get(0));
			}
			tableViewOrder.refresh();
		} catch (Exception e) {

		}
	}

	// ��ǰ�ֹ� �׸� ���� ��ư
	public void handlerBtnOrderDeleteActoion(MouseEvent event) {
		try {
			tableViewOrder.getItems().remove(tableViewOrder.getSelectionModel().getSelectedItems().get(0));
			tableViewOrder.refresh();
		} catch (Exception e) {

		}
	}

	// �޴� ��ư ���� �׼�
	public void handlerBtnOrderMenuActoion(ActionEvent event, String btnNum) {
		boolean check = false;
		int index;
		try {
			MenuDAO mDao = new MenuDAO();
			MenuVO mVo = null;
			mVo = mDao.getMenuBtn(btnNum);

			if (mVo != null) {
				for (index = 0; index < dataOrder.size(); index++) {
					if (mVo.getName().equals(dataOrder.get(index).getContents())) {
						check = true;
						int quantity = dataOrder.get(index).getQuantity();
						tableViewOrder.getItems().get(index).setQuantity(quantity + 1);
						tableViewOrder.refresh();
						break;
					}
				}
				if (!check) {
					OrderVO oVo = new OrderVO();
					oVo.setContents(mVo.getName());
					oVo.setQuantity(1);
					oVo.setPrice(mVo.getPrice());
					dataOrder.add(oVo);
				} else {

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ��ã�� ��ư
	public void handlerBtnOrderCustomerActoion(ActionEvent event) {
		try {
			Parent customer = FXMLLoader.load(getClass().getResource("/view/customerListView.fxml"));
			Scene scene = new Scene(customer);
			Stage customerStage = new Stage(StageStyle.DECORATED);
			customerStage.setTitle("������Ʈ");
			customerStage.setResizable(false);
			customerStage.setScene(scene);
			customerStage.show();

			Stage oldStage = (Stage) btnOrderCustomer.getScene().getWindow();
			oldStage.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ��ȭ��ȣ ã�� ��ư
	public void handlerBtnOrderPhoneSearchActoion(ActionEvent event) {
		try {
			String phone;
			phone = txtOrderPhoneSearch.getText();
			CustomerVO cVo = null;
			CustomerDAO cDao = new CustomerDAO();

			cVo = cDao.getCustomerPhone(phone);
			if (cVo != null) {
				txtOrderCName.setText(cVo.getName());
				txtOrderCAddress.setText(cVo.getAddress());
				txtOrderCPhone.setText(cVo.getPhone());
				txtOrderCOther.setText(cVo.getOther());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ������ ���� ��ư
	public void handlerBtnOrderCSaveActoion(ActionEvent event) {

		if (!txtOrderCAddress.getText().equals("") && !txtOrderCPhone.getText().equals("")) {
			try {
				CustomerVO cVo = null;
				CustomerDAO cDao = new CustomerDAO();
				cVo = cDao.getCustomerPhone(txtOrderCPhone.getText());
				if (cVo == null) {
					CustomerVO cvo = new CustomerVO();
					cvo.setName(txtOrderCName.getText());
					cvo.setAddress(txtOrderCAddress.getText());
					cvo.setPhone(txtOrderCPhone.getText());
					cvo.setOther(txtOrderCOther.getText());

					cDao.getCustomerRegiste(cvo);

				} else {
					OrderDAO oDao = new OrderDAO();
					OrderVO oVo = null;
					
					cVo.setName(txtOrderCName.getText());
					cVo.setAddress(txtOrderCAddress.getText());
					cVo.setPhone(txtOrderCPhone.getText());
					cVo.setOther(txtOrderCOther.getText());

					cDao.getCustomerEdit(cVo);
					
					//������ ���� �� �ش� �� �ֹ� ������ ��� �ּ� ���� ����
					oDao.getCustomerOrderEdit(cVo.getAddress(),cVo.getPhone());

				}
				if (!orderAndEditCustomer) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("�� ���� ���� �Ϸ�");
					alert.setHeaderText("�� ���� ���� ����!");
					alert.setContentText("�� ���� ���忡 �����Ͽ����ϴ�!!!");
					alert.showAndWait();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("�Է� ����");
			alert.setHeaderText("���ּ� �Ǵ� ��ȭ��ȣ ���Է�.");
			alert.setContentText("���ּҿ� ��ȭ��ȣ�� �ʼ��� �Է��ؾ� �մϴ�.");
			alert.showAndWait();
		}
	}

	// �޴� ���
	public void menuSearch() {
		try {
			MenuVO mVo = null;
			MenuDAO mDao = new MenuDAO();
			ArrayList<MenuVO> list;

			list = mDao.getMenuCheck();

			int btnSize = list.size();
			for (int index = 0; index < btnSize; index++) {
				mVo = list.get(index);
				menuPrint(mVo, Integer.parseInt(mVo.getBtn()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �޴� �ؽ�Ʈ ���
	public void menuPrint(MenuVO mVo, int btnNum) {
		switch (btnNum) {
		case 1:
			btnOrderMenu1.setText(mVo.getName() + "\n" + mVo.getPrice() + "��");
			break;
		case 2:
			btnOrderMenu2.setText(mVo.getName() + "\n" + mVo.getPrice() + "��");
			break;
		case 3:
			btnOrderMenu3.setText(mVo.getName() + "\n" + mVo.getPrice() + "��");
			break;
		case 4:
			btnOrderMenu4.setText(mVo.getName() + "\n" + mVo.getPrice() + "��");
			break;
		case 5:
			btnOrderMenu5.setText(mVo.getName() + "\n" + mVo.getPrice() + "��");
			break;
		case 6:
			btnOrderMenu6.setText(mVo.getName() + "\n" + mVo.getPrice() + "��");
			break;
		case 7:
			btnOrderMenu7.setText(mVo.getName() + "\n" + mVo.getPrice() + "��");
			break;
		case 8:
			btnOrderMenu8.setText(mVo.getName() + "\n" + mVo.getPrice() + "��");
			break;
		case 9:
			btnOrderMenu9.setText(mVo.getName() + "\n" + mVo.getPrice() + "��");
			break;
		case 10:
			btnOrderMenu10.setText(mVo.getName() + "\n" + mVo.getPrice() + "��");
			break;
		case 11:
			btnOrderMenu11.setText(mVo.getName() + "\n" + mVo.getPrice() + "��");
			break;
		case 12:
			btnOrderMenu12.setText(mVo.getName() + "\n" + mVo.getPrice() + "��");
			break;
		case 13:
			btnOrderMenu13.setText(mVo.getName() + "\n" + mVo.getPrice() + "��");
			break;
		case 14:
			btnOrderMenu14.setText(mVo.getName() + "\n" + mVo.getPrice() + "��");
			break;
		case 15:
			btnOrderMenu15.setText(mVo.getName() + "\n" + mVo.getPrice() + "��");
			break;
		case 16:
			btnOrderMenu16.setText(mVo.getName() + "\n" + mVo.getPrice() + "��");
			break;
		case 17:
			btnOrderMenu17.setText(mVo.getName() + "\n" + mVo.getPrice() + "��");
			break;
		case 18:
			btnOrderMenu18.setText(mVo.getName() + "\n" + mVo.getPrice() + "��");
			break;

		}
	}

}
