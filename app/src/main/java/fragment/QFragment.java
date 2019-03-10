package fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

import adapter.FragmentPageAdapter;
import page.page.page101.R;

public class QFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename and change types and number of parameters
    public static QFragment newInstance() {
        QFragment fragment = new QFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }




    private View v;
    private Source source;
    TextView seat;
    EditText message,message_title;
    Button insert_btn;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_q, container, false);

        seat = (TextView) v.findViewById(R.id.message_tv);
        message = (EditText) v.findViewById(R.id.message_et);
        message_title = (EditText) v.findViewById(R.id.message_title);
        insert_btn = (Button) v.findViewById(R.id.message_btn);
        insert_btn.setOnClickListener(this);
        seat.setText("테이블 번호    :   "+1);

        return v;
    }

    public void insert_message(){

        String msg = message.getText().toString();
        String title = message_title.getText().toString();
        boolean join=false;

        if(msg.equals("")){
            Toast.makeText(getActivity(), "모든 정보를 입력해주세요",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        String response_ = "0";
        try{

            HttpPost httppost;
            HttpResponse response;
            HttpClient httpclient;
            List<NameValuePair> nameValuePairs;

            httpclient=new DefaultHttpClient();
            httppost= new HttpPost("http://cmcm1284.cafe24.com/skt_temp/join.php");
            nameValuePairs = new ArrayList<NameValuePair>(3);
            nameValuePairs.add(new BasicNameValuePair("seat_idx_fk",1+""));
            nameValuePairs.add(new BasicNameValuePair("proposal_title",title));
            nameValuePairs.add(new BasicNameValuePair("proposal_content",msg));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();

            response_ = httpclient.execute(httppost, responseHandler);

        }catch(Exception e){
            System.out.println("Exception : " + e.getMessage());
        }

        if(response_.equals("1")){
            Toast.makeText(getActivity(), "전달되었습니다 ^-^ 감사합니다!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getActivity(), "죄송합니다T_T 다시 한번 시도해주세요", Toast.LENGTH_SHORT).show();
        }
        return;

    }


    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {

            case R.id.message_btn:
                insert_message();
                break;

        }
    }





}
