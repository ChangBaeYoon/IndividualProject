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
	
	boolean orderAndEditCustomer;//주문시 고객정보 저장일때 true

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// 고객정보를 받아 텍스트 필드를 초기화 할 필요가 있는경우
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
			lblTitle.setText("주문 수정");
			btnOrderOk.setText("   √\n\n수 정 ");

			for (int index = 0; index < editOrderList.size(); index++) {
				dataOrder.add(editOrderList.get(index));
			}
		} else {
			btnOrderOk.setText("   √\n\n확 인 ");
		}

		if (ConnectionData.newCustomer) {
			txtOrderPhoneSearch.setText(ConnectionData.newPhone);
			txtOrderCPhone.setText(ConnectionData.newPhone);
		}

		tableViewC.setEditable(false);
		tableViewOrder.setEditable(false);
		btnOrderCSave.setText("고객정보\n\n   저장");
		cbOrderCash.setItems(FXCollections.observableArrayList("현금", "카드"));
		cbOrderCash.setValue("현금");
		btnOrderCancel.setText("   X\n\n닫 기 ");

		previousOrderList();
		menuSearch();// 메뉴 텍스트 초기화

		// 고객 주문내역 테이블뷰 컬럼이름 설정
		TableColumn colDate = new TableColumn("주문일");
		colDate.setMinWidth(60);
		colDate.setStyle("-fx-allignment: CENTER");
		colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		TableColumn colContents = new TableColumn("주문내역");
		colContents.setMinWidth(150);
		colContents.setStyle("-fx-allignment: CENTER");
		colContents.setCellValueFactory(new PropertyValueFactory<>("contents"));
		TableColumn coltotalPrice = new TableColumn("판매금액");
		coltotalPrice.setMinWidth(60);
		coltotalPrice.setStyle("-fx-allignment: CENTER");
		coltotalPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		TableColumn colCash = new TableColumn("구분");
		colCash.setMinWidth(30);
		colCash.setStyle("-fx-allignment: CENTER");
		colCash.setCellValueFactory(new PropertyValueFactory<>("cash"));
		tableViewC.getColumns().addAll(colDate, colContents, coltotalPrice, colCash);
		tableViewC.setItems(dataC);

		// 주문정보 테이블뷰 컬럼이름 설정
		TableColumn colMenuName = new TableColumn("메뉴명");
		colMenuName.setMinWidth(120);
		colMenuName.setStyle("-fx-allignment: CENTER");
		colMenuName.setCellValueFactory(new PropertyValueFactory<>("contents"));
		TableColumn colQuantity = new TableColumn("수량");
		colQuantity.setMinWidth(30);
		colQuantity.setStyle("-fx-allignment: CENTER");
		colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		TableColumn colPrice = new TableColumn("판매금액");
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
				mainStage.setTitle("닭강정 판매 관리 시스템");
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

		// 텍스트 초기화 가 끝나고 false로 변경
		ConnectionData.sceneChange = false;
		ConnectionData.editOrder = false;
		ConnectionData.newCustomer = false;
		ConnectionData.mainOrOrder = 2; // 이전 스테이지 설정
	}

	// 이전 주문내역 출력
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

	// 주문 확인 버튼
	public void handlerBtnOrderOkActoion(ActionEvent event) {
		try {
			if (!txtOrderCAddress.getText().equals("") && !txtOrderCPhone.getText().equals("")
					&& tableViewOrder.getItems().size() >= 1) {
				if (!lblTitle.getText().equals("주문 수정")) {

					orderAndEditCustomer = true; //주문시 고객정보 저장
					// 일반 주문
					handlerBtnOrderCSaveActoion(event);
					orderAndEditCustomer = false;

					OrderVO oVo = new OrderVO();
					OrderDAO oDao = new OrderDAO();

					// 주문번호 생성 시작
					Date date = new Date();
					int random = (int) (Math.random() * 9 + 1);
					int random2 = (int) (Math.random() * 9 + 1);
					SimpleDateFormat sdfONum = new SimpleDateFormat("dms");
					String num = random + sdfONum.format(date) + random2;
					int oNum = Integer.parseInt(num);
					// 주문번호 생성 끝

					for (int index = 0; index < tableViewOrder.getItems().size(); index++) {
						// 수량
						int quantity = tableViewOrder.getItems().get(index).getQuantity();
						// 가격
						int totalPrice = Integer.parseInt(tableViewOrder.getItems().get(index).getPrice());
						if (quantity >= 2) {
							// 두개이상의 수량일 경우 곱셈
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
					alert.setTitle("주문 완료");
					alert.setHeaderText("주문 성공");
					alert.setContentText("주문에 성공하였습니다.");
					alert.showAndWait();

					Parent root = FXMLLoader.load(getClass().getResource("/view/mainView.fxml"));
					Scene scene = new Scene(root);
					Stage mainStage = new Stage(StageStyle.DECORATED);
					mainStage.setTitle("닭강정 판매 관리 시스템");
					mainStage.setResizable(false);
					mainStage.setScene(scene);
					mainStage.show();

					Stage oldStage = (Stage) btnOrderOk.getScene().getWindow();
					oldStage.close();

				} else {
					// 주문 수정
					OrderVO oVo = new OrderVO();
					OrderDAO oDao = new OrderDAO();
					int orderONum = editOrderList.get(0).getoNum();
					Date date = editOrderList.get(0).getDate();
					oDao.getOrderDelete(orderONum);

					orderAndEditCustomer = true;
					// 일반 주문
					handlerBtnOrderCSaveActoion(event);
					orderAndEditCustomer = false;

					for (int index = 0; index < tableViewOrder.getItems().size(); index++) {
						// 수량
						int quantity = tableViewOrder.getItems().get(index).getQuantity();
						// 가격
						int totalPrice = Integer.parseInt(tableViewOrder.getItems().get(index).getPrice());
						if (quantity >= 2) {
							// 두개이상의 수량일 경우 곱셈
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
					alert.setTitle("주문 수정 완료");
					alert.setHeaderText("주문 수정 성공");
					alert.setContentText("주문 수정에 성공하였습니다.");
					alert.showAndWait();

					Parent root = FXMLLoader.load(getClass().getResource("/view/mainView.fxml"));
					Scene scene = new Scene(root);
					Stage mainStage = new Stage(StageStyle.DECORATED);
					mainStage.setTitle("닭강정 판매 관리 시스템");
					mainStage.setResizable(false);
					mainStage.setScene(scene);
					mainStage.show();

					Stage oldStage = (Stage) btnOrderOk.getScene().getWindow();
					oldStage.close();
				}
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("필수 항목 미입력");
				alert.setHeaderText("주문에 필요한 필수 항목이 부족합니다.");
				alert.setContentText("고객주소, 전화번호, 1개 이상의 상품을 선택 해야 주문이 가능합니다.");
				alert.showAndWait();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 수량 Up
	public void handlerBtnQuantityUpActoion(MouseEvent event) {
		try {
			int quantity = tableViewOrder.getSelectionModel().getSelectedItems().get(0).getQuantity();
			tableViewOrder.getSelectionModel().getSelectedItems().get(0).setQuantity(quantity + 1);
			tableViewOrder.refresh();
		} catch (Exception e) {

		}
	}

	// 수량 Down
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

	// 물품주문 항목 삭제 버튼
	public void handlerBtnOrderDeleteActoion(MouseEvent event) {
		try {
			tableViewOrder.getItems().remove(tableViewOrder.getSelectionModel().getSelectedItems().get(0));
			tableViewOrder.refresh();
		} catch (Exception e) {

		}
	}

	// 메뉴 버튼 선택 액션
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

	// 고객찾기 버튼
	public void handlerBtnOrderCustomerActoion(ActionEvent event) {
		try {
			Parent customer = FXMLLoader.load(getClass().getResource("/view/customerListView.fxml"));
			Scene scene = new Scene(customer);
			Stage customerStage = new Stage(StageStyle.DECORATED);
			customerStage.setTitle("고객리스트");
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

	// 전화번호 찾기 버튼
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

	// 고객정보 저장 버튼
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
					
					//고객정보 수정 후 해당 고객 주문 내역의 모든 주소 정보 수정
					oDao.getCustomerOrderEdit(cVo.getAddress(),cVo.getPhone());

				}
				if (!orderAndEditCustomer) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("고객 정보 저장 완료");
					alert.setHeaderText("고객 정보 저장 성공!");
					alert.setContentText("고객 정보 저장에 성공하였습니다!!!");
					alert.showAndWait();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("입력 부족");
			alert.setHeaderText("고객주소 또는 전화번호 미입력.");
			alert.setContentText("고객주소와 전화번호는 필수로 입력해야 합니다.");
			alert.showAndWait();
		}
	}

	// 메뉴 출력
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

	// 메뉴 텍스트 출력
	public void menuPrint(MenuVO mVo, int btnNum) {
		switch (btnNum) {
		case 1:
			btnOrderMenu1.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 2:
			btnOrderMenu2.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 3:
			btnOrderMenu3.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 4:
			btnOrderMenu4.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 5:
			btnOrderMenu5.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 6:
			btnOrderMenu6.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 7:
			btnOrderMenu7.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 8:
			btnOrderMenu8.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 9:
			btnOrderMenu9.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 10:
			btnOrderMenu10.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 11:
			btnOrderMenu11.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 12:
			btnOrderMenu12.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 13:
			btnOrderMenu13.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 14:
			btnOrderMenu14.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 15:
			btnOrderMenu15.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 16:
			btnOrderMenu16.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 17:
			btnOrderMenu17.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 18:
			btnOrderMenu18.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;

		}
	}

}
