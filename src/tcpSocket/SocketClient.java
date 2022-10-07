package tcpSocket;

import java.io.*;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) throws IOException {

        try {
            while (true) {
                System.out.println("請輸入執行代碼:" + "\n"
                        +"0: Cashi查詢"+ "\n"
                        +"1: Mgni查詢"+ "\n"
                        +"2: Mgni動態查詢"+ "\n"
                        +"3: Mgni新增"+ "\n"
                        +"4: Mgni更新"+ "\n"
                        +"5: 資料刪除"+ "\n");
                Socket socket = new Socket("10.6.8.100", 1010);
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


                String requestStr = br.readLine();
                if ("end".equals(requestStr)) {
                    break;
                }
                OutputStream outputStream = socket.getOutputStream(); //發送
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
                bw.write(requestStr);
                bw.newLine();
                bw.flush();

                BufferedReader response = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String str = "";

                while ((str = response.readLine()) != null) {
                    System.out.println(str);
                }


                socket.close();
            }
            System.out.println("發送結束");
            //  {"requestType":"0","request":{"id":"MGI20221005161608500"}}
            //  {"requestType":"2","request":{"cmNo":"1","ccy":"TWD"}}

            //  {"requestType":"3","request":{"cmNo":"3","kacType":"1","bankNo":"003","ccy":"TWD","pvType":"1","bicaccNo":"0000000","iType":"","pReason":"","accAmt":[{"acc":"1","amt":1000},{"acc":"2","amt":700}],"ctName":"Leo","ctTel":"26262626"}}
            //  {"requestType":"4","request":{"id":"MGI20221005161608500","pvType":"2"}}
            //  {"requestType":"5","request":{"id":"MGI20221005161608500"}}

//            br.close();
//            bw.close();
//            outputStream.close();
//            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
