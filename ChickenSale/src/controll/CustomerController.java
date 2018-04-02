package controll;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.CustomerVO;

public class CustomerController implements Initializable {
	@FXML
	private TextField txtCNum;
	@FXML
	private TextField txtCName;
	@FXML
	private TextField txtCPhone;
	@FXML
	private TextField txtCAddress;
	@FXML
	private TextField txtCOther;
	@FXML
	private Button btnCSearch;
	@FXML
	private Button btnCustomerOk;
	@FXML
	private Button btnCustomerCancel;
	@FXML
	private Button btnCustomerDelete;

	@FXML
	private TableView<CustomerVO> tableViewCustomer;

	ObservableList<CustomerVO> data = FXCollections.observableArrayList();
	ObservableList<CustomerVO> selectCustomer = null; // ���̺��� ������ ���� ����
	int selectedIndex; // ���̺��� ������ ���� �ε��� ����

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		btnCustomerDelete.setText("������\n   ����");
		btnCustomerOk.setDisable(true);
		tableViewCustomer.setEditable(false);

		totalList();// ������Ʈ ���̺�� ��ü���
		// ���̺� �� �÷��̸� ����
		TableColumn colNum = new TableColumn("����ȣ");
		colNum.setMinWidth(60);
		colNum.setStyle("-fx-allignment: CENTER");
		colNum.setCellValueFactory(new PropertyValueFactory<>("num"));
		TableColumn colName = new TableColumn("����");
		colName.setMinWidth(80);
		colName.setStyle("-fx-allignment: CENTER");
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn colAddress = new TableColumn("�ּ�");
		colAddress.setMinWidth(230);
		colAddress.setStyle("-fx-allignment: CENTER");
		colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
		TableColumn colPhone = new TableColumn("��ȭ��ȣ");
		colPhone.setMinWidth(150);
		colPhone.setStyle("-fx-allignment: CENTER");
		colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		TableColumn colOther = new TableColumn("���");
		colOther.setMinWidth(120);
		colOther.setStyle("-fx-allignment: CENTER");
		colOther.setCellValueFactory(new PropertyValueFactory<>("other"));
		tableViewCustomer.getColumns().addAll(colNum, colName, colPhone, colAddress, colOther);

		tableViewCustomer.setItems(data);

		btnCSearch.setOnAction(event -> handlerBtnCSearchActoion(event));
		btnCustomerOk.setOnMouseClicked(event -> handlerBtnCustomerOkActoion(event));
		btnCustomerCancel.setOnAction(event -> {
			if (ConnectionData.mainOrOrder == 1) {
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
			} else {
				try {
					Parent order = FXMLLoader.load(getClass().getResource("/view/orderView.fxml"));
					Scene scene = new Scene(order);
					Stage orderStage = new Stage(StageStyle.DECORATED);
					orderStage.setTitle("�ֹ�");
					orderStage.setScene(scene);
					orderStage.setResizable(false);
					orderStage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Stage oldStage = (Stage) btnCustomerCancel.getScene().getWindow();
			oldStage.close();
		});

		btnCustomerDelete.setOnMouseClicked(event -> handlerBtnCustomerDeleteActoion(event));

		tableViewCustomer.setOnMouseClicked(event -> handlerTableViewCustomerActoion(event));

		// �ؽ�Ʈ �ʱ�ȭ �� ������ false�� ����
		ConnectionData.sceneChange = false;
	}

	// ���̺�� ������ ����
	public void handlerTableViewCustomerActoion(MouseEvent event) {
		try {
			if (event.getClickCount() != 2) {
				if (tableViewCustomer.getSelectionModel().getSelectedItems() != null) {
					btnCustomerOk.setDisable(false);
				}
			} else if (event.getClickCount() == 2) {
				CustomerDAO cDao = new CustomerDAO();
				selectCustomer = tableViewCustomer.getSelectionModel().getSelectedItems();
				selectedIndex = selectCustomer.get(0).getNum();
				ConnectionData.customerVO = cDao.getCustomerSearch(selectedIndex);
				ConnectionData.sceneChange = true;
				Parent order = FXMLLoader.load(getClass().getResource("/view/orderView.fxml"));
				Scene scene = new Scene(order);
				Stage orderStage = new Stage(StageStyle.DECORATED);
				orderStage.setTitle("�ֹ�");
				orderStage.setResizable(false);
				orderStage.setScene(scene);
				orderStage.show();

				Stage stage = (Stage) btnCustomerOk.getScene().getWindow();
				stage.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ������Ʈ �ֹ� ��ư
	public void handlerBtnCustomerOkActoion(MouseEvent event) {
		CustomerDAO cDao = new CustomerDAO();
		if (event.getClickCount() != 2) {
			try {
				selectCustomer = tableViewCustomer.getSelectionModel().getSelectedItems();
				selectedIndex = selectCustomer.get(0).getNum();
				ConnectionData.customerVO = cDao.getCustomerSearch(selectedIndex);
				ConnectionData.sceneChange = true;
				Parent order = FXMLLoader.load(getClass().getResource("/view/orderView.fxml"));
				Scene scene = new Scene(order);
				Stage orderStage = new Stage(StageStyle.DECORATED);
				orderStage.setTitle("�ֹ�");
				orderStage.setResizable(false);
				orderStage.setScene(scene);
				orderStage.show();

				Stage stage = (Stage) btnCustomerOk.getScene().getWindow();
				stage.close();
			} catch (Exception e) {

			}
			return;
		}

	}

	// ������Ʈ ���� ��ư
	public void handlerBtnCustomerDeleteActoion(MouseEvent event) {
		int deleteNum;
		CustomerDAO cDao = new CustomerDAO();
		if (event.getClickCount() != 2) {
			try {
				selectCustomer = tableViewCustomer.getSelectionModel().getSelectedItems();
				deleteNum = selectCustomer.get(0).getNum();
				cDao.getCustomerDelete(deleteNum);
				totalList();
			} catch (Exception e) {

			}
			return;
		}
	}

	// ������Ʈ �˻� ��ư
	public void handlerBtnCSearchActoion(ActionEvent event) {
		try {
			CustomerVO cVo = null;
			CustomerDAO cDao = new CustomerDAO();
			ArrayList<CustomerVO> list = null;

			if (!txtCNum.getText().equals("") && txtCName.getText().equals("") && txtCPhone.getText().equals("")
					&& txtCAddress.getText().equals("") && txtCOther.getText().equals("")) {
				list = cDao.getCustomerCheck("C_NUM", txtCNum.getText());
			} else if (txtCNum.getText().equals("") && !txtCName.getText().equals("") && txtCPhone.getText().equals("")
					&& txtCAddress.getText().equals("") && txtCOther.getText().equals("")) {
				list = cDao.getCustomerCheck("C_NAME", txtCName.getText());
			} else if (txtCNum.getText().equals("") && txtCName.getText().equals("") && txtCPhone.getText().equals("")
					&& !txtCAddress.getText().equals("") && txtCOther.getText().equals("")) {
				list = cDao.getCustomerCheck("C_ADDRESS", txtCAddress.getText());
			} else if (txtCNum.getText().equals("") && txtCName.getText().equals("") && !txtCPhone.getText().equals("")
					&& txtCAddress.getText().equals("") && txtCOther.getText().equals("")) {
				list = cDao.getCustomerCheck("C_PHONE", txtCPhone.getText());
			} else if (txtCNum.getText().equals("") && txtCName.getText().equals("") && txtCPhone.getText().equals("")
					&& txtCAddress.getText().equals("") && !txtCOther.getText().equals("")) {
				list = cDao.getCustomerCheck("C_OTHER", txtCOther.getText());
			} else if (txtCNum.getText().equals("") && txtCName.getText().equals("") && txtCPhone.getText().equals("")
					&& txtCAddress.getText().equals("") && txtCOther.getText().equals("")) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("�˻��� ���Է�");
				alert.setHeaderText("�˻�� �Է� ���� �ʾҽ��ϴ�.");
				alert.setContentText("�ϳ��� �˻��� ��� ������ ������ �˻����� ����ּ���.");
				alert.showAndWait();
				
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("�˻� ������");
				alert.setHeaderText("�ϳ��� �˻��� ���");
				alert.setContentText("�ϳ��� �˻��� ��� ������ ������ �˻����� ����ּ���.");
				alert.showAndWait();
			}
			if (list != null) {
				int rowCount = list.size();
				data.removeAll(data);
				for (int index = 0; index < rowCount; index++) {
					cVo = list.get(index);
					data.add(cVo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ������Ʈ ��ü ���
	public void totalList() {
		try {
			CustomerVO cVo = null;
			CustomerDAO cDao = new CustomerDAO();
			ArrayList<CustomerVO> list = null;
			list = cDao.getCustomerTotal();
			if (list != null) {
				int rowCount = list.size();
				data.removeAll(data);
				for (int index = 0; index < rowCount; index++) {
					cVo = list.get(index);
					data.add(cVo);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
