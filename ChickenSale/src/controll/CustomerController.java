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
	ObservableList<CustomerVO> selectCustomer = null; // 테이블에서 선택한 정보 저장
	int selectedIndex; // 테이블에서 선택한 정보 인덱스 저장

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		btnCustomerDelete.setText("고객정보\n   삭제");
		btnCustomerOk.setDisable(true);
		tableViewCustomer.setEditable(false);

		totalList();// 고객리스트 테이블뷰 전체출력
		// 테이블 뷰 컬럼이름 설정
		TableColumn colNum = new TableColumn("고객번호");
		colNum.setMinWidth(60);
		colNum.setStyle("-fx-allignment: CENTER");
		colNum.setCellValueFactory(new PropertyValueFactory<>("num"));
		TableColumn colName = new TableColumn("고객명");
		colName.setMinWidth(80);
		colName.setStyle("-fx-allignment: CENTER");
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn colAddress = new TableColumn("주소");
		colAddress.setMinWidth(230);
		colAddress.setStyle("-fx-allignment: CENTER");
		colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
		TableColumn colPhone = new TableColumn("전화번호");
		colPhone.setMinWidth(150);
		colPhone.setStyle("-fx-allignment: CENTER");
		colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		TableColumn colOther = new TableColumn("비고");
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
					mainStage.setTitle("닭강정 판매 관리 시스템");
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
					orderStage.setTitle("주문");
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

		// 텍스트 초기화 가 끝나고 false로 변경
		ConnectionData.sceneChange = false;
	}

	// 테이블뷰 아이템 선택
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
				orderStage.setTitle("주문");
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

	// 고객리스트 주문 버튼
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
				orderStage.setTitle("주문");
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

	// 고객리스트 삭제 버튼
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

	// 고객리스트 검색 버튼
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
				alert.setTitle("검색어 미입력");
				alert.setHeaderText("검색어를 입력 하지 않았습니다.");
				alert.setContentText("하나의 검색만 허용 함으로 나머지 검색란은 비워주세요.");
				alert.showAndWait();
				
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("검색 부적합");
				alert.setHeaderText("하나의 검색만 허용");
				alert.setContentText("하나의 검색만 허용 함으로 나머지 검색란은 비워주세요.");
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

	// 고객리스트 전체 출력
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
