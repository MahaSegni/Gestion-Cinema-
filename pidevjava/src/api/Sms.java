package api;

import com.teknikindustries.bulksms.SMS;

public class Sms {
      
    public void send(){
        SMS sms1 = new SMS();
        sms1.SendSMS("faress258",
                "Faress258",
                "Happy Park, vous avez participer a notre evenement est ceci est un message de confirmation",
                "21652645760",
                "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0"
        );
    }
}