package presenter.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import presenter.com.app.buffers.MessageBuffer;
import presenter.com.app.core.Connect;
import presenter.com.messages.Next;
import presenter.com.messages.Prev;

public class Home extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    }

    public void next(View v){
        MessageBuffer.getInstance().addMessage(new Next());
        Log.d("string","next");

    }

    public void prev(View v){
        MessageBuffer.getInstance().addMessage(new Prev());
        Log.d("string","prev");
    }

    public void conect(View v){
        Connect c = new Connect();
        c.execute();
    }
}
