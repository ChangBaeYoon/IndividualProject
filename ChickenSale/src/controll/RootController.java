package controll;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.CustomerVO;
import model.MenuVO;
import model.OrderVO;

public class RootController implements Initializable {
	@FXML
	private TextField txtSalePhoneSearch;
	@FXML
	private Button btnSalePhoneSearch;
	@FXML
	private Button btnSaleCustomer;

	@FXML
	private DatePicker dpDate1;
	@FXML
	private DatePicker dpDate2;
	@FXML
	private Button btnSaleListSearch;
	@FXML
	private Button btnSaleTodayList;
	@FXML
	private Button btnSaleTotalList;

	@FXML
	private Button btnSaleNewOrder;
	@FXML
	private Button btnSaleEditOrder;
	@FXML
	private Button btnSaleDeleteOrder;

	@FXML
	private Button btnBarchart;
	@FXML
	private Button btnPiechart;
	@FXML
	private TextField txtCallPhone;
	@FXML
	private Button btnCallPhone;

	@FXML
	private Button btnPath;
	@FXML
	private TextField txtPath;
	@FXML
	private Button btnExcelWrite;
	@FXML
	private Button btnPDFWrite;

	@FXML
	private Button btnMenu1;
	@FXML
	private Button btnMenu2;
	@FXML
	private Button btnMenu3;
	@FXML
	private Button btnMenu4;
	@FXML
	private Button btnMenu5;
	@FXML
	private Button btnMenu6;
	@FXML
	private Button btnMenu7;
	@FXML
	private Button btnMenu8;
	@FXML
	private Button btnMenu9;
	@FXML
	private Button btnMenu10;
	@FXML
	private Button btnMenu11;
	@FXML
	private Button btnMenu12;
	@FXML
	private Button btnMenu13;
	@FXML
	private Button btnMenu14;
	@FXML
	private Button btnMenu15;
	@FXML
	private Button btnMenu16;
	@FXML
	private Button btnMenu17;
	@FXML
	private Button btnMenu18;

	@FXML
	private TableView<OrderVO> tableViewSale;
	ObservableList<OrderVO> dataOrder = FXCollections.observableArrayList();
	ObservableList<OrderVO> selectOrder = null; // 테이블에서 선택한 정보 저장
	Stage stage;// 폴더 선택 스테이지

	boolean onlyImageCreate;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		TableColumn colDate = new TableColumn("주문일");
		colDate.setMinWidth(100);
		colDate.setStyle("-fx-allignment: CENTER");
		colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		TableColumn colPhone = new TableColumn("전화번호");
		colPhone.setMinWidth(120);
		colPhone.setStyle("-fx-allignment: CENTER");
		colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		TableColumn colAddress = new TableColumn("고객주소");
		colAddress.setMinWidth(180);
		colAddress.setStyle("-fx-allignment: CENTER");
		colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
		TableColumn colContents = new TableColumn("주문내역");
		colContents.setMinWidth(250);
		colContents.setStyle("-fx-allignment: CENTER");
		colContents.setCellValueFactory(new PropertyValueFactory<>("contents"));
		TableColumn colPrice = new TableColumn("판매금액");
		colPrice.setMinWidth(100);
		colPrice.setStyle("-fx-allignment: CENTER");
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		TableColumn colCash = new TableColumn("구분");
		colCash.setMinWidth(60);
		colCash.setStyle("-fx-allignment: CENTER");
		colCash.setCellValueFactory(new PropertyValueFactory<>("cash"));

		tableViewSale.getColumns().addAll(colDate, colPhone, colAddress, colContents, colPrice, colCash);
		tableViewSale.setItems(dataOrder);

		todayList();
		menuSearch();
		btnExcelWrite.setDisable(true);
		btnPDFWrite.setDisable(true);
		txtPath.setEditable(false);

		btnSalePhoneSearch.setOnAction(event -> handlerBtnSalePhoneSearchActoion(event));

		btnSaleListSearch.setOnAction(event -> handlerBtnSaleListSearchActoion(event));
		btnSaleTodayList.setOnAction(event -> handlerBtnSaleTodayListActoion(event));
		btnSaleTotalList.setOnAction(event -> handlerBtnSaleTotalListActoion(event));

		btnSaleNewOrder.setOnAction(event -> handlerBtnSaleNewOrderActoion(event));
		btnSaleEditOrder.setOnMouseClicked(event -> handlerBtnSaleEditOrderActoion(event));
		btnSaleDeleteOrder.setOnMouseClicked(event -> handlerBtnSaleDeleteOrderActoion(event));

		btnBarchart.setOnAction(event -> {
			onlyImageCreate = false;
			handlerBtnBarchartActoion(event);
		});
		btnPiechart.setOnAction(event ->{
			onlyImageCreate = false;
			handlerBtnPiechartActoion(event);
		});
		btnCallPhone.setOnAction(event -> handlerBtnCallPhoneActoion(event));

		btnPath.setOnAction(event -> {
			try {

				DirectoryChooser file = new DirectoryChooser();
				File selectedFile = file.showDialog(stage);
				txtPath.setText(selectedFile.getPath());
				ConnectionData.writerPath = txtPath.getText();
				btnExcelWrite.setDisable(false);
				btnPDFWrite.setDisable(false);
			} catch (Exception e) {

			}
		});
		btnExcelWrite.setOnAction(event -> {
			try {
				ObservableList<OrderVO> list;
				list = dataOrder;
				OrderExcelWriter excelWriter = new OrderExcelWriter();
				excelWriter.xlsWiter(list);
				excelWriter.xlsxWiter(list);

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Excel 출력 완료");
				alert.setHeaderText("Excel파일로 출력 되었습니다.");
				alert.setContentText(txtPath.getText() + " 폴더에 저장되었습니다.");
				alert.showAndWait();

				ConnectionData.writerPath = null;// 이미 사용한 경로 삭제
				btnExcelWrite.setDisable(true);
				btnPDFWrite.setDisable(true);
				txtPath.clear();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		btnPDFWrite.setOnAction(event -> {
			try {

				FXMLLoader loaderPdf = new FXMLLoader();
				loaderPdf.setLocation(getClass().getResource("/View/pdfImage.fxml"));

				Stage dialogPdf = new Stage(StageStyle.UTILITY);
				dialogPdf.initModality(Modality.WINDOW_MODAL);
				dialogPdf.initOwner(btnPDFWrite.getScene().getWindow());
				dialogPdf.setTitle("PDF 차트 이미지 선택");

				Parent parentPdf = (Parent) loaderPdf.load();

				Button btnPdfSave = (Button) parentPdf.lookup("#btnPdfSave");
				CheckBox cbBarImage = (CheckBox) parentPdf.lookup("#cbBarImage");
				CheckBox cbPieImage = (CheckBox) parentPdf.lookup("#cbPieImage");

				Scene scene = new Scene(parentPdf);
				dialogPdf.setScene(scene);
				dialogPdf.setResizable(false);
				dialogPdf.show();

				onlyImageCreate = true;
				handlerBtnBarchartActoion(event);
				handlerBtnPiechartActoion(event);
				
				ObservableList<OrderVO> list;
				list = dataOrder;
				OrderPDFWriter pDFWriter = new OrderPDFWriter();

				btnPdfSave.setOnAction(e -> {
					if (cbBarImage.isSelected() && cbPieImage.isSelected()) {
						pDFWriter.pDFWriter(list, 1);
					} else if (cbBarImage.isSelected() && !cbPieImage.isSelected()) {
						pDFWriter.pDFWriter(list, 2);
					} else if (!cbBarImage.isSelected() && cbPieImage.isSelected()) {
						pDFWriter.pDFWriter(list, 3);
					} else {
						pDFWriter.pDFWriter(list, 4);
					}
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("PDF 출력 완료");
					alert.setHeaderText("PDF 파일로 출력 되었습니다.");
					alert.setContentText(txtPath.getText() + " 폴더에 저장되었습니다.");
					alert.showAndWait();

					ConnectionData.writerPath = null;// 이미 사용한 경로 삭제
					btnExcelWrite.setDisable(true);
					btnPDFWrite.setDisable(true);
					txtPath.clear();

					dialogPdf.close();
				});

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		tableViewSale.setOnMouseClicked(event -> handlerTableViewSaleActoion(event));

		btnMenu1.setOnAction(event -> handlerBtnMenuActoion(event, 1));
		btnMenu2.setOnAction(event -> handlerBtnMenuActoion(event, 2));
		btnMenu3.setOnAction(event -> handlerBtnMenuActoion(event, 3));
		btnMenu4.setOnAction(event -> handlerBtnMenuActoion(event, 4));
		btnMenu5.setOnAction(event -> handlerBtnMenuActoion(event, 5));
		btnMenu6.setOnAction(event -> handlerBtnMenuActoion(event, 6));
		btnMenu7.setOnAction(event -> handlerBtnMenuActoion(event, 7));
		btnMenu8.setOnAction(event -> handlerBtnMenuActoion(event, 8));
		btnMenu9.setOnAction(event -> handlerBtnMenuActoion(event, 9));
		btnMenu10.setOnAction(event -> handlerBtnMenuActoion(event, 10));
		btnMenu11.setOnAction(event -> handlerBtnMenuActoion(event, 11));
		btnMenu12.setOnAction(event -> handlerBtnMenuActoion(event, 12));
		btnMenu13.setOnAction(event -> handlerBtnMenuActoion(event, 13));
		btnMenu14.setOnAction(event -> handlerBtnMenuActoion(event, 14));
		btnMenu15.setOnAction(event -> handlerBtnMenuActoion(event, 15));
		btnMenu16.setOnAction(event -> handlerBtnMenuActoion(event, 16));
		btnMenu17.setOnAction(event -> handlerBtnMenuActoion(event, 17));
		btnMenu18.setOnAction(event -> handlerBtnMenuActoion(event, 18));

		btnSaleCustomer.setOnAction(event -> handlerBtnSaleCustomerActoion(event));

		ConnectionData.sceneChange = false;// 텍스트 초기화 가 끝나고 false로 변경
		ConnectionData.mainOrOrder = 1; // 이전 스테이지 설정
		onlyImageCreate = false; // pdf 출력시 테이블뷰의 내용으로 이미지 생성 여부
	}

	// 파이 차트 버튼
	public void handlerBtnPiechartActoion(ActionEvent event) {

		try {
			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btnPiechart.getScene().getWindow());

			Parent parent = FXMLLoader.load(getClass().getResource("/view/piechart.fxml"));

			PieChart pieChart = (PieChart) parent.lookup("#pieChart");
			dialog.setTitle("날짜별 주문량 원 그래프");
			PieChart.Data slice = null;

			int count;
			for (int i = 0; i < dataOrder.size(); i++) {
				count = 0;
				for (int index = 0; index < dataOrder.size(); index++) {
					if (dataOrder.get(index).getDate().toString().equals(dataOrder.get(i).getDate().toString())) {
						count++;
					}
				}
				if (i == 0) {
					slice = new PieChart.Data(dataOrder.get(i).getDate().toString(), count);
					pieChart.getData().add(slice);
				}
				if (i != 0
						&& !dataOrder.get(i - 1).getDate().toString().equals(dataOrder.get(i).getDate().toString())) {
					slice = new PieChart.Data(dataOrder.get(i).getDate().toString(), count);
					pieChart.getData().add(slice);
				}
			}

			Button btnClose = (Button) parent.lookup("#btnClose");
			btnClose.setOnAction(e -> dialog.close());

			Scene scene = new Scene(parent);
			dialog.setScene(scene);
			if (!onlyImageCreate) {
				dialog.show();
			}

			// 원 그래프 이미지 저장
			WritableImage snapShot = scene.snapshot(null);
			ImageIO.write(SwingFXUtils.fromFXImage(snapShot, null), "png", new File("chartImage/orderPieChart.png"));
		} catch (IOException e) {
		}
	}

	// 바 차트 버튼
	public void handlerBtnBarchartActoion(ActionEvent event) {
		try {
			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btnBarchart.getScene().getWindow());
			dialog.setTitle("날짜별 매출액 막대 그래프");
			Parent parent = FXMLLoader.load(getClass().getResource("/view/barchart.fxml"));
			BarChart barChart = (BarChart) parent.lookup("#barChart");

			// 판매량
			XYChart.Series seriesOrder = new XYChart.Series();
			seriesOrder.setName("매출액");
			ObservableList orderList = FXCollections.observableArrayList();
			int take;
			for (int i = 0; i < dataOrder.size(); i++) {
				take = 0;
				for (int index = 0; index < dataOrder.size(); index++) {
					if (dataOrder.get(index).getDate().toString().equals(dataOrder.get(i).getDate().toString())) {
						take += Integer.parseInt(dataOrder.get(index).getPrice());
					}
				}
				orderList.add(new XYChart.Data(dataOrder.get(i).getDate().toString(), take));
			}
			seriesOrder.setData(orderList);
			barChart.getData().add(seriesOrder);

			// 바차트 종료
			Button btnClose = (Button) parent.lookup("#btnClose");
			btnClose.setOnAction(e -> dialog.close());
			Scene scene = new Scene(parent);
			dialog.setScene(scene);
			if (!onlyImageCreate) {
				dialog.show();
			}

			// 막대 그래프 이미지 저장
			WritableImage snapShot = scene.snapshot(null);
			ImageIO.write(SwingFXUtils.fromFXImage(snapShot, null), "png", new File("chartImage/orderBarChart.png"));
		} catch (IOException e) {
		}
	}

	// 날짜별 검색 버튼
	public void handlerBtnSaleListSearchActoion(ActionEvent event) {
		try {
			Date date1 = null;
			Date date2 = null;
			if (dpDate1.getValue() != null) {
				date1 = Date.valueOf(dpDate1.getValue());
			}
			if (dpDate2.getValue() != null) {
				date2 = Date.valueOf(dpDate2.getValue());
			}
			OrderDAO oDao = new OrderDAO();
			ArrayList<OrderVO> list = null;
			if (date1 != null && date2 != null) {
				list = oDao.getOrderDate(date1, date2, 1);
			} else if (date1 != null && date2 == null) {
				list = oDao.getOrderDate(date1, date2, 2);
			} else if (date1 == null && date2 != null) {
				list = oDao.getOrderDate(date1, date2, 3);
			} else {
				return;
			}
			tableViewSale.getItems().removeAll(dataOrder);
			try {
				OrderVO oVo = null;
				if (list != null) {
					int rowCount = list.size();
					dataOrder.removeAll(dataOrder);
					for (int index = 0; index < rowCount; index++) {
						oVo = list.get(index);
						dataOrder.add(oVo);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 금일 검색 버튼
	public void handlerBtnSaleTodayListActoion(ActionEvent event) {
		try {
			tableViewSale.getItems().removeAll(dataOrder);
			todayList();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 전체 검색 버튼
	public void handlerBtnSaleTotalListActoion(ActionEvent event) {
		try {
			tableViewSale.getItems().removeAll(dataOrder);
			totalList();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 전화 수신 버튼
	public void handlerBtnCallPhoneActoion(ActionEvent event) {
		if (!txtCallPhone.getText().equals("")) {
			txtSalePhoneSearch.setText(txtCallPhone.getText());

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("주문전화 수신");
			alert.setHeaderText("주문 전화가 수신되었습니다.");
			alert.setContentText(txtCallPhone.getText() + " 전화가 수신되었습니다.");
			alert.showAndWait();

			handlerBtnSalePhoneSearchActoion(event);
		}
	}

	// 주문 수정
	public void handlerBtnSaleEditOrderActoion(MouseEvent event) {
		try {
			OrderDAO oDao = new OrderDAO();
			ArrayList<OrderVO> list = null;
			selectOrder = tableViewSale.getSelectionModel().getSelectedItems();
			list = oDao.getOrderONum(selectOrder.get(0).getoNum());

			if (list != null) {
				CustomerDAO cDao = new CustomerDAO();

				ConnectionData.customerVO = cDao.getCustomerPhone(list.get(0).getPhone());
				ConnectionData.orderVOList = list;
				ConnectionData.editOrder = true;

				Parent order = FXMLLoader.load(getClass().getResource("/view/orderView.fxml"));
				Scene scene = new Scene(order);
				Stage orderStage = new Stage(StageStyle.DECORATED);
				orderStage.setTitle("주문수정");
				orderStage.setResizable(false);
				orderStage.setScene(scene);
				orderStage.show();

				Stage oldStage = (Stage) btnSaleEditOrder.getScene().getWindow();
				oldStage.close();
			}

		} catch (Exception e) {

		}
	}

	// 주문 삭제
	public void handlerBtnSaleDeleteOrderActoion(MouseEvent event) {
		try {
			OrderDAO oDao = new OrderDAO();
			selectOrder = tableViewSale.getSelectionModel().getSelectedItems();
			int orderONum = selectOrder.get(0).getoNum();
			oDao.getOrderDelete(orderONum);

		} catch (Exception e) {

		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("주문 삭제 완료");
		alert.setHeaderText("주문 삭제 성공");
		alert.setContentText("주문 삭제에 성공 하였습니다.");
		alert.showAndWait();

		tableViewSale.getItems().removeAll(dataOrder);
		todayList();

	}

	// 테이블 더블클릭 액션 주문 수정
	public void handlerTableViewSaleActoion(MouseEvent event) {
		try {
			if (event.getClickCount() == 2) {
				OrderDAO oDao = new OrderDAO();
				ArrayList<OrderVO> list = null;
				selectOrder = tableViewSale.getSelectionModel().getSelectedItems();
				list = oDao.getOrderONum(selectOrder.get(0).getoNum());

				if (list != null) {
					CustomerDAO cDao = new CustomerDAO();

					ConnectionData.customerVO = cDao.getCustomerPhone(list.get(0).getPhone());
					ConnectionData.orderVOList = list;
					ConnectionData.editOrder = true;

					Parent order = FXMLLoader.load(getClass().getResource("/view/orderView.fxml"));
					Scene scene = new Scene(order);
					Stage orderStage = new Stage(StageStyle.DECORATED);
					orderStage.setResizable(false);
					orderStage.setTitle("주문수정");
					orderStage.setScene(scene);
					orderStage.show();

					Stage oldStage = (Stage) btnSaleEditOrder.getScene().getWindow();
					oldStage.close();
				}
			}

		} catch (Exception e) {

		}
	}

	// 전화번호 검색
	public void handlerBtnSalePhoneSearchActoion(ActionEvent event) {
		try {
			if (!txtSalePhoneSearch.equals("")) {
				CustomerVO cVo = null;
				CustomerDAO cDao = new CustomerDAO();
				cVo = cDao.getCustomerPhone(txtSalePhoneSearch.getText());

				if (cVo != null) {
					ConnectionData.customerVO = cVo;
					ConnectionData.sceneChange = true;
					Parent order = FXMLLoader.load(getClass().getResource("/view/orderView.fxml"));
					Scene scene = new Scene(order);
					Stage orderStage = new Stage(StageStyle.DECORATED);
					orderStage.setTitle("주문");
					orderStage.setResizable(false);
					orderStage.setScene(scene);
					orderStage.show();

					Stage oldStage = (Stage) btnSalePhoneSearch.getScene().getWindow();
					oldStage.close();
				} else {
					ConnectionData.newPhone = txtSalePhoneSearch.getText();
					ConnectionData.newCustomer = true;
					handlerBtnSaleNewOrderActoion(event);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 새주문 버튼
	public void handlerBtnSaleNewOrderActoion(ActionEvent event) {
		try {
			Parent order = FXMLLoader.load(getClass().getResource("/view/orderView.fxml"));
			Scene scene = new Scene(order);
			Stage orderStage = new Stage(StageStyle.DECORATED);
			orderStage.setTitle("주문");
			orderStage.setResizable(false);
			orderStage.setScene(scene);
			orderStage.show();

			Stage oldStage = (Stage) btnSaleNewOrder.getScene().getWindow();
			oldStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 고객리스트 호출
	public void handlerBtnSaleCustomerActoion(ActionEvent event) {
		try {
			Parent customer = FXMLLoader.load(getClass().getResource("/view/customerListView.fxml"));
			Scene scene = new Scene(customer);
			Stage customerStage = new Stage(StageStyle.DECORATED);
			customerStage.setTitle("고객리스트");
			customerStage.setResizable(false);
			customerStage.setScene(scene);
			customerStage.show();

			Stage oldStage = (Stage) btnSaleCustomer.getScene().getWindow();
			oldStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 주문목록 전체 출력
	public void totalList() {
		try {
			OrderVO oVo = null;
			OrderDAO oDao = new OrderDAO();
			ArrayList<OrderVO> list = null;
			list = oDao.getOrderTotal();
			if (list != null) {
				int rowCount = list.size();
				dataOrder.removeAll(dataOrder);
				for (int index = 0; index < rowCount; index++) {
					oVo = list.get(index);
					dataOrder.add(oVo);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 주문목록 금일 출력
	public void todayList() {
		try {
			OrderVO oVo = null;
			OrderDAO oDao = new OrderDAO();
			ArrayList<OrderVO> list = null;
			list = oDao.getOrderToday();
			if (list != null) {
				int rowCount = list.size();
				dataOrder.removeAll(dataOrder);
				for (int index = 0; index < rowCount; index++) {
					oVo = list.get(index);
					dataOrder.add(oVo);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 메뉴 설정 창
	public void handlerBtnMenuActoion(ActionEvent event, int btnNum) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/menuOptionView.fxml"));
			Stage menuStage = new Stage(StageStyle.UTILITY);
			menuStage.initModality(Modality.WINDOW_MODAL);
			menuStage.initOwner(null);
			menuStage.setResizable(false);
			menuStage.setTitle("메뉴 설정");
			Parent parent = (Parent) loader.load();
			TextField menuName = (TextField) parent.lookup("#txtMOptionName");
			TextField menuPrice = (TextField) parent.lookup("#txtMOptionPrice");
			Button btnDel = (Button) parent.lookup("#btnMOptionDelete");
			Button btnOk = (Button) parent.lookup("#btnMOptionOk");
			Button btnCancel = (Button) parent.lookup("#btnMOptionCancel");

			boolean check; // true 메뉴 있음, false 메뉴 없음
			check = menuCheck(btnNum);// 메뉴에 정보가 있는지 확인하는 메소드 호출

			try {
				if (check) {
					MenuVO mVoText = null;
					MenuDAO mDaoText = new MenuDAO();

					mVoText = mDaoText.getMenuBtn(btnNum + "");
					menuName.setText(mVoText.getName());
					menuPrice.setText(mVoText.getPrice());
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

			btnDel.setOnAction(e -> {
				MenuVO mVo = null;
				MenuDAO mDao = new MenuDAO();
				mVo = new MenuVO(menuName.getText(), menuPrice.getText(), btnNum + "");

				if (check) {
					// 메뉴 있음
					try {
						mDao.getMenuDelete(mVo);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					// 메뉴 없음
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("메뉴 없음");
					alert.setHeaderText("메뉴 정보 없음.");
					alert.setContentText("저장된 메뉴가 없어 삭제 할 수 없습니다.");
					alert.showAndWait();
				}
				MenuClear();
				menuSearch();
				menuStage.close();
			});

			// 메뉴 설정 확인 버튼
			btnOk.setOnAction(e -> {
				if (menuName.getText().equals("") || menuPrice.getText().equals("")) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("메뉴 정보 부족");
					alert.setHeaderText("메뉴 이름 또는 메뉴 단가 없음");
					alert.setContentText("메뉴이름이나 메뉴단가를 입력해주세요.");
					alert.showAndWait();
					return;
				}

				MenuVO mVo = null;
				MenuDAO mDao = new MenuDAO();
				mVo = new MenuVO(menuName.getText(), menuPrice.getText(), btnNum + "");

				if (check) {
					// 수정
					try {
						mDao.getMenuEdit(mVo);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					// 새로 저장
					try {
						mDao.getMenuRegiste(mVo);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				menuSearch();
				menuStage.close();
			});

			// 메뉴설정 취소
			btnCancel.setOnAction(e -> {
				menuStage.close();
			});

			Scene scene = new Scene(parent);
			menuStage.setScene(scene);
			menuStage.setResizable(false);
			menuStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	// 메뉴설정 정보 확인 메소드
	public boolean menuCheck(int btnNum) {
		boolean editCheck = false;
		String btnText;
		switch (btnNum) {
		case 1:
			btnText = btnMenu1.getText();
			if (!btnText.equals(""))
				editCheck = true;
			break;
		case 2:
			btnText = btnMenu2.getText();
			if (!btnText.equals(""))
				editCheck = true;
			break;
		case 3:
			btnText = btnMenu3.getText();
			if (!btnText.equals(""))
				editCheck = true;
			break;
		case 4:
			btnText = btnMenu4.getText();
			if (!btnText.equals(""))
				editCheck = true;
			break;
		case 5:
			btnText = btnMenu5.getText();
			if (!btnText.equals(""))
				editCheck = true;
			break;
		case 6:
			btnText = btnMenu6.getText();
			if (!btnText.equals(""))
				editCheck = true;
			break;
		case 7:
			btnText = btnMenu7.getText();
			if (!btnText.equals(""))
				editCheck = true;
			break;
		case 8:
			btnText = btnMenu8.getText();
			if (!btnText.equals(""))
				editCheck = true;
			break;
		case 9:
			btnText = btnMenu9.getText();
			if (!btnText.equals(""))
				editCheck = true;
			break;
		case 10:
			btnText = btnMenu10.getText();
			if (!btnText.equals(""))
				editCheck = true;
			break;
		case 11:
			btnText = btnMenu11.getText();
			if (!btnText.equals(""))
				editCheck = true;
			break;
		case 12:
			btnText = btnMenu12.getText();
			if (!btnText.equals(""))
				editCheck = true;
			break;
		case 13:
			btnText = btnMenu13.getText();
			if (!btnText.equals(""))
				editCheck = true;
			break;
		case 14:
			btnText = btnMenu14.getText();
			if (!btnText.equals(""))
				editCheck = true;
			break;
		case 15:
			btnText = btnMenu15.getText();
			if (!btnText.equals(""))
				editCheck = true;
			break;
		case 16:
			btnText = btnMenu16.getText();
			if (!btnText.equals(""))
				editCheck = true;
			break;
		case 17:
			btnText = btnMenu17.getText();
			if (!btnText.equals(""))
				editCheck = true;
			break;
		case 18:
			btnText = btnMenu18.getText();
			if (!btnText.equals(""))
				editCheck = true;
			break;

		}
		return editCheck;
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
			btnMenu1.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 2:
			btnMenu2.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 3:
			btnMenu3.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 4:
			btnMenu4.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 5:
			btnMenu5.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 6:
			btnMenu6.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 7:
			btnMenu7.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 8:
			btnMenu8.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 9:
			btnMenu9.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 10:
			btnMenu10.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 11:
			btnMenu11.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 12:
			btnMenu12.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 13:
			btnMenu13.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 14:
			btnMenu14.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 15:
			btnMenu15.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 16:
			btnMenu16.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 17:
			btnMenu17.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;
		case 18:
			btnMenu18.setText(mVo.getName() + "\n" + mVo.getPrice() + "원");
			break;

		}
	}

	// 메뉴 텍스트 초기화
	public void MenuClear() {
		btnMenu1.setText("");
		btnMenu2.setText("");
		btnMenu3.setText("");
		btnMenu4.setText("");
		btnMenu5.setText("");
		btnMenu6.setText("");
		btnMenu7.setText("");
		btnMenu8.setText("");
		btnMenu9.setText("");
		btnMenu10.setText("");
		btnMenu11.setText("");
		btnMenu12.setText("");
		btnMenu13.setText("");
		btnMenu14.setText("");
		btnMenu15.setText("");
		btnMenu16.setText("");
		btnMenu17.setText("");
		btnMenu18.setText("");
	}

}
