/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mt.manageapartment.View;

import com.mt.manageapartment.Dao.RoomDao;
import com.mt.manageapartment.Model.Room;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author ADMIN
 */
public class DetailView extends  JFrame{
    private JLabel totalRoom, revenue, revenue_word, paid_Room;
    private JPanel contentPane;
    JFreeChart chart;
    ChartPanel chartPanel;
    
    public DetailView(){
        super();
        init();
    }
    public void init(){
        setTitle("Thống kê");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1250, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        totalRoom = new JLabel();
        totalRoom.setBounds(20, 30, 200, 20);
        Font font = new Font("Times New Roman", Font.PLAIN, 14);
//        totalRoom.setFont(font);
        contentPane.add(totalRoom);
        
        paid_Room = new JLabel();
        paid_Room.setBounds(20, 60, 200, 20);
        paid_Room.setFont(font);
        contentPane.add(paid_Room);
        
        revenue = new JLabel();
        revenue.setBounds(20, 90, 200, 20);
        revenue.setFont(font);
        contentPane.add(revenue);
        
        revenue_word = new JLabel();
        revenue_word.setBounds(20, 110, 600, 20);
        revenue_word.setFont(font);
        contentPane.add(revenue_word);
        
        DefaultPieDataset dataset = new DefaultPieDataset();

        chart = ChartFactory.createPieChart("Biểu đồ thu phí theo từng phòng", dataset, true,true,false);
        chartPanel = new ChartPanel(chart);
        chartPanel.setFont(font);
        //chartPanel.setBackground(new Color(255,0,0));
        chartPanel.setBounds(600, 30, 600, 500);
        
        // Thiết lập font cho nhãn của biểu đồ
        chart.getLegend().setItemFont(font);
        contentPane.add(chartPanel);
    }
    public void loadDetail(){
        RoomDao roomDao = new RoomDao();
        ArrayList<Room> roomList = roomDao.getRoomList();
        int revenue_ = 0;
        int totalRoom_ = roomList.size();
        int total_paidRoom = 0;
        for(int i = 0; i < totalRoom_; i++){
            if(roomList.get(i).getTinhTrang().equals("Đã nộp")){
                total_paidRoom++;
            }
        }
        for(int i = 0; i < totalRoom_; i++){
            if(roomList.get(i).getTinhTrang().equals("Đã nộp")){
            revenue_ += roomList.get(i).getBaoDuong()+ roomList.get(i).getDien()
                    + roomList.get(i).getNuoc() + roomList.get(i).getQuanLy()
                    + roomList.get(i).getTrongXe() + roomList.get(i).getVeSinh();
            }
        }
        totalRoom.setText("Tổng số phòng đang có: " + formatNumberWithCommas(totalRoom_)+" phòng");
        paid_Room.setText("Số phòng đã nộp tiền: " + formatNumberWithCommas(total_paidRoom)+" phòng");
        revenue.setText("Doanh thu: "+formatNumberWithCommas(revenue_)+" VNĐ");
        ArrayList<String> kq = readNum(""+revenue_);
        String res="";
        for (int i = 0; i < kq.size(); i++) {
            res += kq.get(i)+" ";
        }
        revenue_word.setText("(" +res+ "đồng)");
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        for (int i = 0; i < roomList.size(); i++) {
            if(roomList.get(i).getTinhTrang().equals("Đã nộp")){
             int revene = roomList.get(i).getBaoDuong()+ roomList.get(i).getDien()
                    + roomList.get(i).getNuoc() + roomList.get(i).getQuanLy()
                    + roomList.get(i).getTrongXe() + roomList.get(i).getVeSinh();
            pieDataset.setValue(roomList.get(i).getHoTen(), revene);
            chart = ChartFactory.createPieChart("Biểu đồ doanh thu từ phí dịch vụ theo từng phòng", pieDataset, true,true,false);
            StandardPieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0} : {1} VNĐ ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
            //chart.getPlot().setLabelGenerator(labelGenerator);
            PiePlot plot = (PiePlot) chart.getPlot();
            plot.setLabelGenerator(labelGenerator);
            chartPanel.setChart(chart);
            chart.getTitle().setPaint(Color.RED);
            //chartPanel.setPreferredSize(new java.awt.Dimension(400, 400));
            chart.setAntiAlias(true);
            }

        }

    }
    public static String formatNumberWithCommas(int number) {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator('.');
        DecimalFormat formatter = new DecimalFormat("###,###,###,###", symbols);
        return formatter.format(number);
    }
    public static final String KHONG = "không";    
    public static final String MOT = "một";
    public static final String HAI = "hai";
    public static final String BA = "ba";
    public static final String BON = "bốn";
    public static final String NAM = "năm";
    public static final String SAU = "sáu";
    public static final String BAY = "bảy";
    public static final String TAM = "tám";
    public static final String CHIN = "chín";
    public static final String LAM = "lăm";
    public static final String LE = "lẻ";
    public static final String MUOI = "mươi";
    public static final String MUOIF = "mười";
    public static final String MOTS = "mốt";
    public static final String TRAM = "trăm";
    public static final String NGHIN = "nghìn";
    public static final String TRIEU = "triệu";
    public static final String TY = "tỷ";
 
 
    public static final String [] number = {KHONG, MOT, HAI, BA,
        BON, NAM, SAU, BAY, TAM, CHIN};
    
    public static ArrayList<String> readNum(String a)
    {
        ArrayList<String> kq = new ArrayList<String>();
 
 
        //Cắt chuổi string chử số ra thành các chuổi nhỏ 3 chử số
        ArrayList<String> List_Num = Split(a, 3);
 
 
        while (List_Num.size() != 0)
        {      
            //Xét 3 số đầu tiên của chuổi (số đầu tiên của List_Num)
            switch (List_Num.size() % 3)
            {
            //3 số đó thuộc hàng trăm
            case 1:
                kq.addAll(read_3num(List_Num.get(0)));
                break;
            // 3 số đó thuộc hàng nghìn
            case 2:
                ArrayList<String> nghin = read_3num(List_Num.get(0));
                if(!nghin.isEmpty()){
                    kq.addAll(nghin);
                    kq.add(NGHIN);
                }
                break;
            //3 số đó thuộc hàng triệu
            case 0:                    
                ArrayList<String> trieu = read_3num(List_Num.get(0));
                if(!trieu.isEmpty()) {
                    kq.addAll(trieu);
                    kq.add(TRIEU);
                }
                break;
            }
           
            //Xét nếu 3 số đó thuộc hàng tỷ
            if (List_Num.size() == (List_Num.size() / 3) * 3 + 1 && List_Num.size() != 1) kq.add(TY);
           
            //Xóa 3 số đầu tiên để tiếp tục 3 số kế
            List_Num.remove(0);
        }
 
 
        return kq;
    }
    public static ArrayList<String> read_3num(String a)
    {
        ArrayList<String> kq = new ArrayList<String>();
        int num = -1;
        try{ num = Integer.parseInt(a); } catch(Exception ex){}
        if (num == 0) return kq;
 
 
 
 
        int hang_tram = -1;
        try{ hang_tram = Integer.parseInt(a.substring(0, 1)); } catch(Exception ex){}
        int hang_chuc = -1;
        try{ hang_chuc = Integer.parseInt(a.substring(1, 2)); } catch(Exception ex){}
        int hang_dv = -1;
        try{ hang_dv = Integer.parseInt(a.substring(2, 3)); } catch(Exception ex){}
 
 
        //xét hàng trăm
        if (hang_tram != -1){
            kq.add(number[hang_tram]);
            kq.add(TRAM);
        }
 
 
        //xét hàng chục
        switch (hang_chuc)
        {
        case -1:
            break;
        case 1:
            kq.add(MUOIF);
            break;
        case 0:
            if (hang_dv != 0) kq.add(LE);
            break;
        default:
            kq.add(number[hang_chuc]);
            kq.add(MUOI);
            break;
        }
 
 
        //xét hàng đơn vị
        switch (hang_dv)
        {
        case -1:
            break;
        case 1:
            if ((hang_chuc != 0) && (hang_chuc != 1) && (hang_chuc != -1))
                kq.add(MOTS);
            else kq.add(number[hang_dv]);
            break;
        case 5:
            if ((hang_chuc != 0) && (hang_chuc != -1))
                kq.add(LAM);
            else kq.add(number[hang_dv]);
            break;
        case 0:
            if (kq.isEmpty()) kq.add(number[hang_dv]);
            break;
        default:
            kq.add(number[hang_dv]);
            break;
        }
        return kq;
    }
    public static ArrayList<String> Split(String str, int chunkSize)    {
        int du = str.length() % chunkSize;
        //Nếu độ dài chuổi không phải bội số của chunkSize thì thêm # vào trước cho đủ.
        if (du != 0)
            for (int i = 0; i < (chunkSize - du); i++) str = "#" + str;
        return splitStringEvery(str, chunkSize);
    }
 
 
    //Hàm cắt chuổi ra thành chuổi nhỏ
    public static ArrayList<String> splitStringEvery(String s, int interval) {
        ArrayList<String> arrList = new ArrayList<String>();
        int arrayLength = (int) Math.ceil(((s.length() / (double) interval)));
        String[] result = new String[arrayLength];
        int j = 0;
        int lastIndex = result.length - 1;
        for (int i = 0; i < lastIndex; i++) {
            result[i] = s.substring(j, j + interval);
            j += interval;
        }
        result[lastIndex] = s.substring(j);
        arrList.addAll(Arrays.asList(result));
        return arrList;
    }
    //Hàm Main
    public static void main (String[] args){
        DetailView detailView = new DetailView();
        detailView.setVisible(true);
        detailView.loadDetail();
    }
}
